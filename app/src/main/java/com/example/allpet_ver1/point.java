package com.example.allpet_ver1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class point extends AppCompatActivity {
    ArrayAdapter<CharSequence> adspin1 = null;

    TextView selectedarea;
    Spinner spinner = null;
    String areas1;
    BottomNavigationView bottomNavigationView;
    private pointAdapter adapter;
    private Context context = point.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        //   GridView gridView= (GridView) findViewById(R.id.example_gridview);
        Intent intent = getIntent();

        ArrayList<puppy> np = intent.getParcelableArrayListExtra("puppy");
        final String id = intent.getExtras().getString("Id");

        ArrayList<point_data> tmp = new ArrayList<>();
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1558498248334m0.jpg",300,"노즈워크","강아지 사료를 중간에 숨기시면 댕댕이가 찾으면서 놀 수 있게 만드는 도구입니다."));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1555577766543m0.jpg",550,"댕댕이 사료 5KG","고오급 사료 5KG입니다."));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1554706143973m0.jpg",145,"해충 방지제","해충이 많은 여름에 댕댕이에게 뿌려주면 해충이 주위에 가지 못합니다."));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1558514934671m0.jpg",700,"연어 사료","댕댕이가 아주 좋아하는 연어로 만든 사료입니다."));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1491902730713m0.jpg",180,"댕댕이 이발도구","이제 직접 이발해보세요. 동물병원에 맡기면 5만원입니다... ㅠ"));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1433498804923m0.jpg",82,"댕댕이 배변패드","뽀송뽀송한 댕댕이 배변패드입니다."));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1547618988879m0.jpg",231,"댕댕의 계단","댕댕이의 관절을 보호할 수 있는 계단입니다. 이제 침대에 자유롭게 올라가게 해주세요"));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1498807138116m0.jpg",83,"발 세정제","산책 후에 촥촥 뿌리면 오케이 에스케이!!"));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1476327559403m0.jpg",38,"슬라이스 햄","댕댕이가 좋아하는 햄을 슬라이스 친 것입니다."));
        tmp.add(new point_data("http://www.dogskingdom.co.kr/shop/data/goods/1551329552446m0.jpg",73,"간식 세트","댕댕이가 좋아하는 간식이 총 집합했다."));

        adapter = new pointAdapter(this, tmp);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);




//        final String pw = i.getExtras().getString("Pw");


        adapter.setItems(tmp);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

       
        adapter.setItems(tmp);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(point.this, mainpage_picture.class);
                        intent.putExtra("Id", id);
                      //  intent.putExtra("Pw", pw);
                        startActivity(intent);
                        break;
                    case R.id.enroll_dog:
//                        intent = new Intent(mypage.this, mainpage_picture.class);
//                        intent.putExtra("Id", id);
//                        intent.putExtra("Pw", pw);
//                        startActivity(intent);
                        break;
                    case R.id.certificate:
                        intent = new Intent(point.this, certification.class);
                        intent.putExtra("Id", id);
                    //    intent.putExtra("Pw", pw);
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
