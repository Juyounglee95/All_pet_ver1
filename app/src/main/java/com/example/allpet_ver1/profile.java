package com.example.allpet_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profile extends AppCompatActivity {
    Button register,apply,missions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        register=(Button) findViewById(R.id.register);
        apply=(Button) findViewById(R.id.apply);
        missions=(Button) findViewById(R.id.missions);

    }

    public void ReOnClick(View view){
        Intent intent = new Intent(this,mypage.class);
        intent.putExtra("situation",1);
        startActivity(intent);
    }

    public void ApOnClick(View view){
        Intent intent = new Intent(this,mypage.class);
        intent.putExtra("situation",2);
        startActivity(intent);
    }

    public void MiOnClick(View view){
        Intent intent = new Intent(this,mypage.class);
        intent.putExtra("situation",3);
        startActivity(intent);
    }
}
