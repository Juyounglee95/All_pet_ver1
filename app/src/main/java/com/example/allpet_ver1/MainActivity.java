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


public class MainActivity extends AppCompatActivity {
    String id="", pw="";
    EditText edit_id, edit_pw;
    Button bt_login;
    Intent  intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_id = (EditText)findViewById(R.id.input_email);
        edit_pw = (EditText)findViewById(R.id.input_pw);
        bt_login = (Button)findViewById(R.id.loginButton);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), mainpage_picture.class);
                id = edit_id.getText().toString().trim();
                pw = edit_pw.getText().toString().trim();

                // URL 설정.
                String url = "http://18.191.37.77:8080/PET/Login.sk";

                // AsyncTask를 통해 HttpURLConnection 수행.
                ContentValues content=new ContentValues();
                content.put("Id",id);
                content.put("Pw", pw);

                NetworkTask networkTask = new NetworkTask(url, content);
                networkTask.execute();

                startActivity(intent);
            }
        });


    }
//    public void login(View view){
//        id = edit_id.getText().toString();
//        pw = edit_pw.getText().toString();
//
//        startActivity(intent);
//    }

    //Void
    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            Log.e(null,result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            Log.e(null,"===============성공======================");
            Log.e(null,s);
            Log.e(null,"===============성공======================");
        }
    }


}
