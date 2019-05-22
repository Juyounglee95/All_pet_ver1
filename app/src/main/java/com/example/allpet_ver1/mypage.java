package com.example.allpet_ver1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class mypage extends AppCompatActivity {
    ArrayAdapter<CharSequence> adspin1 = null;

    TextView selectedarea;
    Spinner spinner = null;
    String areas1;
    BottomNavigationView bottomNavigationView;
    private boardAdapter adapter;
    private Context context = mypage.this;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        //   GridView gridView= (GridView) findViewById(R.id.example_gridview);
        Intent intent = getIntent();
        num = intent.getExtras().getInt("situation");
        adapter = new boardAdapter(this, new board_data().getitems(num));
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        Intent i = getIntent();
        final String id = i.getExtras().getString("Id");
        final String pw = i.getExtras().getString("Pw");


        adapter.setItems(new board_data().getitems(num));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setItems(new board_data().getitems(num));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(mypage.this, mainpage_picture.class);
                        intent.putExtra("Id", id);
                        intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;
                    case R.id.enroll_dog:
//                        intent = new Intent(mypage.this, mainpage_picture.class);
//                        intent.putExtra("Id", id);
//                        intent.putExtra("Pw", pw);
//                        startActivity(intent);
                        break;
                    case R.id.search:
                        break;
                    case R.id.certificate:
                        intent = new Intent(mypage.this, certification.class);
                        intent.putExtra("Id", id);
                        intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        break;

                }
                return true;
            }
        });
    }
}
