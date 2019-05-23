package com.example.allpet_ver1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class reqPage extends AppCompatActivity {
  //  ArrayAdapter<CharSequence> adspin1 = null;

    //TextView selectedarea;
   // Spinner spinner = null;
   // String areas1;
    String id;
    BottomNavigationView bottomNavigationView;
    private boardAdapter adapter;
    private Context context = reqPage.this;
    ArrayList<puppy> p = new ArrayList<>();
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_page);
        //   GridView gridView= (GridView) findViewById(R.id.example_gridview);
        Intent intent = getIntent();
        p = intent.getParcelableArrayListExtra("puppy");
        id = intent.getExtras().getString("Id");
        NetworkCall networkCall= new NetworkCall();
        networkCall.execute();

    }
    protected void setView(ArrayList<puppy> p){
        adapter = new boardAdapter(this, p);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        adapter.setItems(p);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setItems(p);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(reqPage.this, mainpage_picture.class);
                        intent.putExtra("Id", id);
                        //  intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;
                    case R.id.enroll_dog:
                        intent = new Intent(reqPage.this, dog_info_upload.class);
                        intent.putExtra("Id",id);
                        startActivity(intent);
                        break;
                    case R.id.certificate:
                        intent = new Intent(reqPage.this, certification.class);
                        intent.putExtra("Id", id);
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
    private class NetworkCall extends AsyncTask<Call,Void, ArrayList<puppy> > {
        ArrayList<puppy> items= new ArrayList<puppy>();
        @Override
        protected ArrayList<puppy> doInBackground(Call... calls) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(imgPath_interface.URL)
                    .build();
            imgPath_interface imgPath_interface= retrofit.create(imgPath_interface.class);
            JsonObject obj = new JsonObject();
            obj.addProperty("Type","2");
            obj.addProperty("Id",id);
            Call<JsonArray> call = imgPath_interface.imgTest("selectReqPet.sk",obj);
            try {
                JsonArray arr = call.execute().body();
                if (arr != null) {
                    String imgpath;
                    // = new ArrayList<puppy>();
                    Log.e("Size",String.valueOf(arr.size()));
                    for (int i = 0; i < arr.size(); i++) {

                        Log.e("Index", String.valueOf(i));
                        items.add(new puppy(arr.get(i).getAsJsonObject().get("ImgPath1").getAsString(),
                                arr.get(i).getAsJsonObject().get("ImgPath2").getAsString(),
                                arr.get(i).getAsJsonObject().get("ImgPath3").getAsString(),
                                arr.get(i).getAsJsonObject().get("PetName").getAsString(),
                                arr.get(i).getAsJsonObject().get("Deposit").getAsInt(),
                                arr.get(i).getAsJsonObject().get("Neutral").getAsString(),
                                arr.get(i).getAsJsonObject().get("Description").getAsString(),
                                arr.get(i).getAsJsonObject().get("Address1").getAsString(),
                                arr.get(i).getAsJsonObject().get("Age").getAsInt(),
                                arr.get(i).getAsJsonObject().get("Gender").getAsString(),
                                arr.get(i).getAsJsonObject().get("Address2").getAsString(),
                                arr.get(i).getAsJsonObject().get("Breeds").getAsString(),
                                arr.get(i).getAsJsonObject().get("Id").getAsString(),
                                arr.get(i).getAsJsonObject().get("StartDate").getAsString(),
                                arr.get(i).getAsJsonObject().get("EndDate").getAsString(),
                                arr.get(i).getAsJsonObject().get("StatusValue").getAsInt(),
                                arr.get(i).getAsJsonObject().get("RequestCnt").getAsInt(),
                                arr.get(i).getAsJsonObject().get("Seq").getAsInt()
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
        protected void onPostExecute(ArrayList<puppy> p){
            super.onPostExecute(p);
            Log.e("TAG",p.get(1).getname());
            setView(p);
        }



    }
}
