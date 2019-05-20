package com.example.allpet_ver1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class mainpage_picture extends AppCompatActivity {

//    public String[] urls={
//            "https://ifh.cc/g/86mBl.jpg",
//    "https://ifh.cc/g/KHiC0.jpg",
//    "https://ifh.cc/g/6JVeb.jpg",
//   "https://ifh.cc/g/8Karu.jpg",
//    "https://ifh.cc/g/NaaSu.jpg",
//    "https://ifh.cc/g/Btxfh.jpg",
//    "https://ifh.cc/g/hdcuF.jpg",
//    "https://ifh.cc/g/o5dww.jpg",
//    "https://ifh.cc/g/dr7Yw.jpg",
//    "https://ifh.cc/g/9W80d.jpg"
//
//    };
    TextView selectdarea;
    Spinner spinner;
    String[] areas;
    BottomNavigationView bottomNavigationView;
    private  puppyAdapter adapter;
    private Context context = mainpage_picture.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_picture);
        //   GridView gridView= (GridView) findViewById(R.id.example_gridview);
       adapter = new puppyAdapter(this, new pup_data().getitems());
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        selectdarea=(TextView)findViewById(R.id.selected_area);
        spinner=(Spinner)findViewById(R.id.spinner);
        //spinner.setOnItemSelectedListener(this);
        areas =new String[]{"선택하세요","서울","부산","경기도","광주","인천"};

        adapter.setItems(new pup_data().getitems());
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setItems(new pup_data().getitems());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.enroll_dog:
                        break;
                    case R.id.search:
                        break;
                    case R.id.certificate:
                        break;
                    case R.id.profile:
                        break;

                }
                return true;
            }
        });
    }
}
