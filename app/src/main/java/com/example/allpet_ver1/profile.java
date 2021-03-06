package com.example.allpet_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    TextView user_id;
    TextView end_date, mission_cnt, price;
    Button register,apply,missions;
    String id;
    ArrayList<puppy> p = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        p = intent.getParcelableArrayListExtra("puppy");
        id =intent.getExtras().getString("Id");
        setContentView(R.layout.activity_profile);

        //p = intent.getParcelableArrayListExtra("puppy");
        //id = intent.getExtras().getString("Id");
        register=(Button) findViewById(R.id.register);
        apply=(Button) findViewById(R.id.apply);
        missions=(Button) findViewById(R.id.missions);

        user_id=(TextView) findViewById(R.id.user_id);
        end_date=(TextView) findViewById(R.id.end_date);
        mission_cnt=(TextView) findViewById(R.id.mission_cnt);
        price=(TextView) findViewById(R.id.price);
        user_id.setText(id);
    }

    public void ReOnClick(View view){
        Intent intent = new Intent(this,mypage.class);

        intent.putParcelableArrayListExtra("puppy",p);
        intent.putExtra("Id", id);
        startActivity(intent);
    }

    public void ApOnClick(View view){

        Intent intent = new Intent(this,reqPage.class);
        intent.putParcelableArrayListExtra("puppy",p);
        intent.putExtra("Id", id);
        //intent.putExtra("situation",2);
        startActivity(intent);

    }

    public void MiOnClick(View view){
        Intent intent = new Intent(this, mission.class);
        intent.putExtra("Id", id);
        startActivity(intent);
        /*Intent intent = new Intent(this,mission.class);
        intent.putExtra("situation",3);
        startActivity(intent);*/
    }
    public  void checkOnClick(View  view){
        Intent intent = new Intent(this, check_pic.class);
        intent.putExtra("Id", id);
        startActivity(intent);
    }
    public void PointClick(View view){
        Intent intent = new Intent(this, point.class);
        intent.putExtra("Id", id);
        startActivity(intent);
        /*Intent intent = new Intent(this,mission.class);
        intent.putExtra("situation",3);
        startActivity(intent);*/
    }
}
