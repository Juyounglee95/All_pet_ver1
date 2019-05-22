package com.example.allpet_ver1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    TextView user_id;
    TextView end_date, mission_cnt, price;
    Button register,apply,missions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        register=(Button) findViewById(R.id.register);
        apply=(Button) findViewById(R.id.apply);
        missions=(Button) findViewById(R.id.missions);

        user_id=(TextView) findViewById(R.id.user_id);
        end_date=(TextView) findViewById(R.id.end_date);
        mission_cnt=(TextView) findViewById(R.id.mission_cnt);
        price=(TextView) findViewById(R.id.price);
    }

    public void ReOnClick(View view){

    }

    public void ApOnClick(View view){

    }

    public void MiOnClick(View view){

    }
}
