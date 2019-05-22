package com.example.allpet_ver1;

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

    }

    public void ApOnClick(View view){

    }

    public void MiOnClick(View view){

    }
}
