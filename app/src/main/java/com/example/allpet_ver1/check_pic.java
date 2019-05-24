package com.example.allpet_ver1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class check_pic extends AppCompatActivity {
    String id;
    BottomNavigationView bottomNavigationView;
    private mission_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pic);
        Intent intent = getIntent();
        id = intent.getExtras().getString("Id");
    }
    protected void setView(ArrayList<mission_data> m){
       // Log.e("abcd", "Abcafda");
        adapter = new mission_adapter(this, m);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        adapter.setItems(m);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        //   adapter.setItems(m);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(check_pic.this, mainpage_picture.class);
                        intent.putExtra("Id", id);
                        //  intent.putExtra("Pw", pw);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.enroll_dog:
                        intent = new Intent(check_pic.this, dog_info_upload.class);
                        intent.putExtra("Id",id);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.certificate:
                        intent = new Intent(check_pic.this, certification.class);
                        intent.putExtra("Id", id);
                        //    intent.putExtra("Pw", pw);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profile:
                        break;

                }
                return true;
            }
        });
    }
    private class NetworkCall extends AsyncTask<Call,Void, ArrayList<mission_data> > {
        ArrayList<mission_data> items= new ArrayList<mission_data>();
        @Override
        protected ArrayList<mission_data> doInBackground(Call... calls) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(imgPath_interface.URL)
                    .build();
            imgPath_interface imgPath_interface= retrofit.create(imgPath_interface.class);
            JsonObject obj = new JsonObject();
            //   obj.addProperty("Type","2");
            obj.addProperty("Id",id);
          //  obj.addProperty("");
            Call<JsonArray> call = imgPath_interface.imgTest("selectAuth.sk",obj);
            try {
                JsonArray arr = call.execute().body();
                if (arr != null) {
                    String imgpath;
                    // = new ArrayList<puppy>();
                    // ArrayList<mission_data> m = new ArrayList<>();
                    Log.e("Size",String.valueOf(arr.size()));
                    for (int i = 0; i < arr.size(); i++) {
                        items.add(new mission_data(arr.get(i).getAsJsonObject().get("ImgPath").getAsString()));
                        Log.e("address", arr.get(i).getAsJsonObject().get("ImgPath").getAsString());
                        Log.e("Index", String.valueOf(i));

                        //Log.e("ITEM", items.get(i).getUrl());
                    }
                    return items;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        protected void onPostExecute(ArrayList<mission_data> p){
            super.onPostExecute(p);
            //  Log.e("TAG",p.get(1).getFile_name());
            setView(p);
        }



    }
}
