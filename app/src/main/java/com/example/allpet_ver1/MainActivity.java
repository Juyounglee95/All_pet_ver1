package com.example.allpet_ver1;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String id="", pw="";
    EditText edit_id, edit_pw;
    Button bt_login;
    Intent  intent;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_id = (EditText)findViewById(R.id.input_email);
        edit_pw = (EditText)findViewById(R.id.input_pw);
        bt_login = (Button)findViewById(R.id.loginButton);

        intent = new Intent(this, mainpage_picture.class);

        //hihihhihi
    }
    public void login(View view) throws  JSONException{
        id = edit_id.getText().toString();
        pw = edit_pw.getText().toString();
        getID();

    }
    private void getID() throws JSONException {
      //  Toast.makeText(MainActivity.this, "FIRST",Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RetrofitExService.URL)
                .build();
      //  Toast.makeText(MainActivity.this, "BUILD",Toast.LENGTH_SHORT).show();

        RetrofitExService retrofitExService= retrofit.create(RetrofitExService.class);
        JsonObject obj = new JsonObject();
        obj.addProperty("Id","admin");
        obj.addProperty("Pw", "admin");

        //Toast.makeText(MainActivity.this, "CALL",Toast.LENGTH_SHORT).show();

        Call<JsonObject> call = retrofitExService.postTest(obj);
        call.enqueue(new Callback<JsonObject>() {


            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.isSuccessful()){

                    JsonObject obj = response.body();
                    //Toast.makeText(MainActivity.this, "SUCCESS",Toast.LENGTH_SHORT).show();
                    if(obj!=null){
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "SUCCESS",Toast.LENGTH_SHORT).show();
                        Log.e("TAG", obj.toString()+"%%%%%%%%%%%%%%%%%%%@!#!*$&#$!*732716344784782");
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Log.e("FAIL",call.toString());
                Toast.makeText(MainActivity.this, "FAIL",Toast.LENGTH_SHORT);
            }
        });


    }


}
