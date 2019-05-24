package com.example.allpet_ver1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class list_people extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private peopleAdapter adapter;
    SwipeController swipeController = null;
    ArrayList<people> np = new ArrayList<>();
    String p_id="";

    int position=0;

    String Id="";
    String ReqName="";
    String Job="";
    String FamilyNum="";
    String PhoneNum="";
    String Location1="";
    String Location2="";
    String title="";
    String experience="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);

        Intent intent = getIntent();
        p_id=intent.getExtras().getString("Id");

        list_people.NetworkCall networkCall = new list_people.NetworkCall();
        networkCall.execute();

//        ArrayList<people> tmp = new ArrayList<>();
//        tmp.add(new people("admin","홍길동","student","서울","강남구","11111","1","1","33434"));
//
//        adapter = new peopleAdapter(this, tmp);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);



       //  if(np!=null){
//        for(int i=0;i<np.size();i++) {
//            tmp.add(np.get(i));
//
////            if(np.get(i).getP_id().equals(id)) {
////                tmp.add(np.get(i));
////            }
//        }
        //  }


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(list_people.this, mainpage_picture.class);
                        intent.putExtra("Id", "");
                        //  intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;
                    case R.id.enroll_dog:
//                        intent = new Intent(mypage.this, mainpage_picture.class);
//                        intent.putExtra("Id", id);
//                        intent.putExtra("Pw", pw);
//                        startActivity(intent);
                        break;
                    case R.id.certificate:
                        intent = new Intent(list_people.this, certification.class);
                        intent.putExtra("Id", "");
                        //    intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        break;

                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(list_people.this);

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage("승인 하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(list_people.this);

                                // AlertDialog 셋팅
                                alertDialogBuilder1
                                        .setMessage("승인되었습니다.")
                                        .setCancelable(false)
                                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                setvalue();
                                                list_people.NetworkCall2 networkCall = new list_people.NetworkCall2();
                                                networkCall.execute();
                                                dialog.cancel();
                                            }
                                        });

                                // 다이얼로그 생성
                                AlertDialog alertDialog1 = alertDialogBuilder1.create();

                                alertDialog1.setTitle("승인");
                                alertDialog1.setIcon(R.drawable.dog_foot);

                                // 다이얼로그 보여주기
                                alertDialog1.show();
                                dialog.cancel();

                            }
                        })
                        .setNegativeButton("아뇨", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 취소한다
                                dialog.cancel();
                            }
                        });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.setTitle("확인");
                alertDialog.setIcon(R.drawable.dog_foot);

                // 다이얼로그 보여주기
                alertDialog.show();
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    public void setview(ArrayList<people> p){
        adapter = new peopleAdapter(this, p);
        np = p;
        adapter.setItems(p);
        position = adapter.p;

        setupRecyclerView();
    }

    public void setvalue(){

        Id=np.get(position).getId();
        ReqName=np.get(position).getName();
        Job=np.get(position).getJob();
        FamilyNum=np.get(position).getFamilyNum();
        PhoneNum=np.get(position).getPhoneNum();
        Location1=np.get(position).getLocation1();
        Location2=np.get(position).getLocation2();
        title=np.get(position).getCommentDesc();
        experience=np.get(position).getExperience();
    }

    private class NetworkCall extends AsyncTask<Call,Void, ArrayList<people> > {
        ArrayList<people> items = new ArrayList<people>();

        @Override
        protected ArrayList<people> doInBackground(Call... calls) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(imgPath_interface.URL)
                    .build();
            imgPath_interface imgPath_interface = retrofit.create(imgPath_interface.class);
            JsonObject obj = new JsonObject();
            obj.addProperty("Type", "1");
            obj.addProperty("PetSeq", 657);
            Call<JsonArray> call = imgPath_interface.imgTest("selectReqPet.sk", obj);
            try {
                JsonArray arr = call.execute().body();
                if (arr != null) {
                    String imgpath;
                    // = new ArrayList<puppy>();
                    Log.e("Size", String.valueOf(arr.size()));
                    for (int i = 0; i < arr.size(); i++) {

                        Log.e("Index", String.valueOf(i));
                        Log.e(null,arr.get(i).getAsJsonObject().toString());
                        items.add(new people(arr.get(i).getAsJsonObject().get("Id").getAsString(),
                                arr.get(i).getAsJsonObject().get("ReqName").getAsString(),
                                arr.get(i).getAsJsonObject().get("Job").getAsString(),
                                arr.get(i).getAsJsonObject().get("Location1").getAsString(),
                                arr.get(i).getAsJsonObject().get("Location2").getAsString(),
                                arr.get(i).getAsJsonObject().get("PhoneNum").getAsString(),
                                arr.get(i).getAsJsonObject().get("FamilyNum").getAsString(),
                                arr.get(i).getAsJsonObject().get("Experience").getAsString(),
                                arr.get(i).getAsJsonObject().get("CommentDesc").getAsString()
                        ));
                        //Log.e("ITEM", items.get(i).getUrl());
                    }
                    return items;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(ArrayList<people> p) {
            super.onPostExecute(p);
            np =p;
          //  Log.e("TAG", p.get().getName());
            setview(p);
        }
    }

    private class NetworkCall2 extends AsyncTask<Call,Void, String > {

        @Override
        protected String doInBackground(Call... calls) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(imgPath_interface.URL)
                    .build();
            list_people_interface imgPath_interface = retrofit.create(list_people_interface.class);
            JsonObject obj = new JsonObject();
//            obj.addProperty("Experience", experience);
//            obj.addProperty("CommentDesc", title);
//            obj.addProperty("FamilyNum", FamilyNum);
//            obj.addProperty("PhoneNum",PhoneNum);
//            obj.addProperty("RegDate", "2019-05-20 10:20:14.0");
//            obj.addProperty("PetSeq", "null");
//            obj.addProperty("Location1", Location1);
//            obj.addProperty("Id", Id);
//            obj.addProperty("Location2", Location2);
//            obj.addProperty("Job", Job);
//            obj.addProperty("ReqName", ReqName);
            obj.addProperty("statusvalue" ,2);
            obj.addProperty("seq",657 );
            Call<JsonObject> call = imgPath_interface.postTest("updatePetDetail.sk", obj);
            Log.e("obj", obj.toString());
            try {
                String d ="";
                JsonObject object = call.execute().body();
                //  Log.e("AAAAa", object.getAsString());
                if (object != null) {
                    d = object.get("Result").getAsString();
                    Log.e("TAG",d+"======================================");
                }
                return d;
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);
            Log.e("TAG",s+"======================================");
        }

    }
}
