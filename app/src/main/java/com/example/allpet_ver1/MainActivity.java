package com.example.allpet_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String id, pw;
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
        intent = new Intent(this, mainpage_picture.class);
        //hihihhihi
    }
    public void login(View view){
        id = edit_id.getText().toString();
        pw = edit_pw.getText().toString();

        startActivity(intent);
    }



}
