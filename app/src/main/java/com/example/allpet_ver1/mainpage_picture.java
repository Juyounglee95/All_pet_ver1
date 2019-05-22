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
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mainpage_picture extends AppCompatActivity  {
    ArrayAdapter<CharSequence> adspin1, adspin2 = null;
    ArrayList<puppy> np = new ArrayList<>();
    TextView selectedarea, selectedarea2;
    Spinner spinner= null;
    Spinner spinner2= null;
    String areas1;
    String areas2;
    BottomNavigationView bottomNavigationView;
    private  puppyAdapter adapter;
    private Context context = mainpage_picture.this;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_picture);
        //   GridView gridView= (GridView) findViewById(R.id.example_gridview);
        Intent intent = getIntent();
        final String id = intent.getExtras().getString("Id");
        final String pw = intent.getExtras().getString("Pw");
        NetworkCall networkCall = new NetworkCall();
        networkCall.execute();


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        selectedarea=(TextView)findViewById(R.id.selected_area);
        selectedarea2=(TextView)findViewById(R.id.selected_area2);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        //areas =new String[]{"선택하세요","서울","부산","경기도","광주","인천"};
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do,android.R.layout.simple_spinner_item);

        //adapter에 값 input
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adspin1);

        //Adapter 값들을 spinner에 넣기
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //서울시
                if (adspin1.getItem(i).equals("서울")) {
                   areas1 = "서울";
                   selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);

                } else if (adspin1.getItem(i).equals("인천")) {       //인천
                    areas1 = "인천";

                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_incheon, android.R.layout.simple_spinner_dropdown_item);

                } else if(adspin1.getItem(i).equals("광주")) {       //광주
                    areas1 = "광주";

                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_gwangju, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("대구")) {       //대구
                    areas1 = "대구";

                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_daegu, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("울산")) {       //울산
                    areas1 = "울산";
                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_ulsan, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("대전")) {       //대전
                    areas1 = "대전";
                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_daejeon, android.R.layout.simple_spinner_dropdown_item);
                }

                adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adspin2);

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        areas2 = adspin2.getItem(i)+"";

                        selectedarea2.setText(areas2);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.enroll_dog:
                        intent = new Intent(mainpage_picture.this, dog_info_upload.class);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        break;
                    case R.id.certificate:
                        intent = new Intent(mainpage_picture.this, certification.class);
                        intent.putExtra("Id", id);
                        intent.putExtra("Pw", pw);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profile:
                        intent = new Intent(mainpage_picture.this, profile.class);
                        intent.putExtra("Id", id);
                        intent.putExtra("Pw", pw);
                        startActivity(intent);
                        finish();
                        break;

                }
                return true;
            }
        });
    }
    public void setview(ArrayList<puppy> puppy){
        adapter = new puppyAdapter(this, puppy);
        adapter.setItems(puppy);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);


    }

    private class NetworkCall extends AsyncTask<Call,Void, ArrayList<puppy> >{
        ArrayList<puppy> items= new ArrayList<puppy>();
        @Override
        protected ArrayList<puppy> doInBackground(Call... calls) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(imgPath_interface.URL)
                    .build();
            imgPath_interface imgPath_interface= retrofit.create(imgPath_interface.class);
            JsonObject obj = new JsonObject();
            obj.addProperty("Id","");
            Call<JsonArray> call = imgPath_interface.imgTest("selectPetList.sk",obj);
            try {
                JsonArray arr = call.execute().body();
                if (arr != null) {
                    String imgpath;
                    // = new ArrayList<puppy>();
                    for (int i = 0; i < arr.size(); i++) {


                        items.add(new puppy(arr.get(i).getAsJsonObject().get("ImgPath1").getAsString(),arr.get(i).getAsJsonObject().get("ImgPath2").getAsString(),arr.get(i).getAsJsonObject().get("ImgPath3").getAsString(),
                                arr.get(i).getAsJsonObject().get("PetName").getAsString(),arr.get(i).getAsJsonObject().get("Deposit").getAsInt(),arr.get(i).getAsJsonObject().get("Neutral").getAsString(),
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
                                arr.get(i).getAsJsonObject().get("RequestCnt").getAsInt()));
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
            setview(p);
        }



    }
}

