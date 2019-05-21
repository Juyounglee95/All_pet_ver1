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

public class mainpage_picture extends AppCompatActivity  {
    ArrayAdapter<CharSequence> adspin1, adspin2 = null;

    TextView selectedarea, selectedarea2;
    Spinner spinner= null;
    Spinner spinner2= null;
    String areas1;
    String areas2;
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
        selectedarea=(TextView)findViewById(R.id.selected_area);
        selectedarea2=(TextView)findViewById(R.id.selected_area2);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        //areas =new String[]{"선택하세요","서울","부산","경기도","광주","인천"};
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do,android.R.layout.simple_spinner_item);

        //adapter에 값 input
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adspin1);

        //Adapter 값들을 spinner에 넣기
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //서울시
                if (adspin1.getItem(i).equals("서울")) {
                   areas1 = "서울";
                   selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_seoul, android.R.layout.simple_spinner_dropdown_item);

                } else if (adspin1.getItem(i).equals("인천")) {       //인천
                    areas1 = "인천";

                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_incheon, android.R.layout.simple_spinner_dropdown_item);

                } else if(adspin1.getItem(i).equals("광주")) {       //광주
                    areas1 = "광주";

                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_gwangju, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("대구")) {       //대구
                    areas1 = "대구";

                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_daegu, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("울산")) {       //울산
                    areas1 = "울산";
                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_ulsan, android.R.layout.simple_spinner_dropdown_item);

                }else if(adspin1.getItem(i).equals("대전")) {       //대전
                    areas1 = "대전";
                    selectedarea.setText(areas1);
                    adspin2 = ArrayAdapter.createFromResource(mainpage_picture.this, R.array.spinner_do_daejeon, android.R.layout.simple_spinner_dropdown_item);
                }

                adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adspin2);

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        areas2 = adspin2.getItem(i)+"";

                        selectedarea2.setText(areas2);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });





        adapter.setItems(new pup_data().getitems());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setItems(new pup_data().getitems());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.enroll_dog:
                        break;
                    case R.id.search:
                        break;
                    case R.id.certificate:
                        intent = new Intent(mainpage_picture.this, certification.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        intent = new Intent(mainpage_picture.this, mypage.class);
                        startActivity(intent);
                        break;

                }
                return true;
            }
        });
    }
}
