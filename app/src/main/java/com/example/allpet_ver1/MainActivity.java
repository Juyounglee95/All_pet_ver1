package com.example.allpet_ver1;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

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
String id, pw;

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



        //hihihhihi
    }
    public void login(View view) throws  JSONException{
        id = edit_id.getText().toString();
        pw = edit_pw.getText().toString();

        getID(id, pw);
        //startActivity(intent);

    }
    private void getID(final String id, final String pw) throws JSONException {
      //  Toast.makeText(MainActivity.this, "FIRST",Toast.LENGTH_SHORT).show();
        intent = new Intent(this, mainpage_picture.class);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RetrofitExService.URL)
                .build();
      //  Toast.makeText(MainActivity.this, "BUILD",Toast.LENGTH_SHORT).show();

        RetrofitExService retrofitExService= retrofit.create(RetrofitExService.class);
        JsonObject obj = new JsonObject();
        obj.addProperty("Id",id);
        obj.addProperty("Pw", pw);

        //Toast.makeText(MainActivity.this, "CALL",Toast.LENGTH_SHORT).show();

        Call<JsonObject> call = retrofitExService.postTest("Login.sk",obj);
        call.enqueue(new Callback<JsonObject>() {


            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if(response.isSuccessful()){

                    JsonObject obj = response.body();
                    //Toast.makeText(MainActivity.this, "SUCCESS",Toast.LENGTH_SHORT).show();
                    if(obj!=null){

                        String result = obj.get("isLogin").getAsString();
                       if(result.equals("true")){
                           intent.putExtra("Id", id);
                           intent.putExtra("Pw", pw);
                            startActivity(intent);
                        }else{

                            Toast.makeText(MainActivity.this,"아이디와 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Log.e("FAIL",call.toString());
                Toast.makeText(MainActivity.this, "로그인 실패",Toast.LENGTH_SHORT);
            }
        });


    }


}
