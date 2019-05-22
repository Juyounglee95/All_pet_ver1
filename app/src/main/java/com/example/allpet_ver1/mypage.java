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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        //   GridView gridView= (GridView) findViewById(R.id.example_gridview);
        adapter = new boardAdapter(this, new board_data().getitems());
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        selectedarea = (TextView) findViewById(R.id.selected_area);
        spinner = (Spinner) findViewById(R.id.spinner);
        Intent i = getIntent();
        ArrayList<puppy> p = i.getExtras().getParcelableArrayList("puppy");
        final String id = i.getExtras().getString("Id");
        final String pw = i.getExtras().getString("Pw");
        //areas =new String[]{"선택하세요","서울","부산","경기도","광주","인천"};
        adspin1 = ArrayAdapter.createFromResource(this, R.array.board, android.R.layout.simple_spinner_item);

        //adapter에 값 input
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adspin1);

        //Adapter 값들을 spinner에 넣기
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adspin1.getItem(i).equals("모두")) {
                    areas1 = "모두";
                    selectedarea.setText(areas1);

                } else if (adspin1.getItem(i).equals("내가 올린 글")) {
                    areas1 = "내가 올린 글";
                    selectedarea.setText(areas1);

                } else if (adspin1.getItem(i).equals("내가 신청한 글")) {       //인천
                    areas1 = "내가 신청한 글";
                    selectedarea.setText(areas1);

                } else if (adspin1.getItem(i).equals("진행중인 글")) {       //광주
                    areas1 = "진행중인 글";
                    selectedarea.setText(areas1);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });





        adapter.setItems(new board_data().getitems());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setItems(new board_data().getitems());
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
