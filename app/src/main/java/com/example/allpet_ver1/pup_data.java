package com.example.allpet_ver1;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pup_data {
    ArrayList<puppy> items = new ArrayList<>();

    private void getID() throws JSONException {

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(imgPath_interface.URL)
                .build();
        //  Toast.makeText(MainActivity.this, "BUILD",Toast.LENGTH_SHORT).show();

       imgPath_interface imgPath_interface= retrofit.create(imgPath_interface.class);
        JsonObject obj = new JsonObject();
        obj.addProperty("Id","");
       // obj.addProperty("Pw", pw);

        //Toast.makeText(MainActivity.this, "CALL",Toast.LENGTH_SHORT).show();

        Call<JsonArray> call = imgPath_interface.imgTest("selectPetList.sk",obj);
        call.enqueue(new Callback<JsonArray>() {


            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                if (response.isSuccessful()) {

                    JsonArray obj = response.body();
                  //  Log.e("IMG", obj.toString());
                    //Toast.makeText(MainActivity.this, "SUCCESS",Toast.LENGTH_SHORT).show();
                    if (obj != null) {
                        String imgpath;
                        // = new ArrayList<puppy>();
                        for (int i = 0; i < obj.size(); i++) {


                            items.add(new puppy(obj.get(i).getAsJsonObject().get("ImgPath").getAsString(), "개", 1));
                            Log.e("ITEM", items.get(i).getUrl());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                t.printStackTrace();
                Log.e("FAIL",call.toString());

            }
        });


    }



   public ArrayList<puppy> getitems()throws JSONException{
        getID();
        Log.e("WAIT","&&&&");

//
//        puppy p1 = new puppy("https://ifh.cc/g/86mBl.jpg", "개1", 1);
//
//        puppy p2 = new puppy("https://ifh.cc/g/KHiC0.jpg", "개2", 100);
//        puppy p3 = new puppy("https://ifh.cc/g/6JVeb.jpg", "개3", 12);
//        puppy p4 = new puppy("https://ifh.cc/g/8Karu.jpg", "개4", 11);
//        puppy p5 = new puppy("https://ifh.cc/g/NaaSu.jpg", "개5", 17);
//        puppy p6 = new puppy("https://ifh.cc/g/Btxfh.jpg", "개6", 18);
//        puppy p7 = new puppy("https://ifh.cc/g/hdcuF.jpg", "개7", 143);
//        puppy p8 = new puppy("https://ifh.cc/g/o5dww.jpg", "개8", 13);
//        puppy p9 = new puppy("https://ifh.cc/g/dr7Yw.jpg", "개9", 11);
//        puppy p10 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
//       puppy p11 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
//       puppy p12 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
//       puppy p13 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
//       puppy p14 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
//
//        items.add(p1);
//       items.add(p2);
//       items.add(p3);
//       items.add(p4);
//       items.add(p5);
//       items.add(p6);
//       items.add(p7);
//       items.add(p8);
//       items.add(p9);
//       items.add(p10);
//
//       items.add(p11);
//       items.add(p12);
//       items.add(p13);
//       items.add(p14);
//

       return items;
    }

}
