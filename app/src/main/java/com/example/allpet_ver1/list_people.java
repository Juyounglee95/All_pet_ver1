package com.example.allpet_ver1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class list_people extends AppCompatActivity {
    TextView selectedarea;
    String areas1;
    BottomNavigationView bottomNavigationView;
    private peopleAdapter adapter;
    SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_people);
        //   GridView gridView= (GridView) findViewById(R.id.example_gridview);
        Intent intent = getIntent();
        ArrayList<people> np = intent.getParcelableArrayListExtra("people");
        final String id = intent.getExtras().getString("Id");
        final String name = intent.getExtras().getString("name");
        final String job = intent.getExtras().getString("job");
        final String location1 = intent.getExtras().getString("location1");
        final String location2 = intent.getExtras().getString("location2");
        final String phoneNum = intent.getExtras().getString("phoneNum");
        final String familyNum = intent.getExtras().getString("familyNum");
        final String experience = intent.getExtras().getString("experience");
        final String commentDesc = intent.getExtras().getString("commentDesc");

        setupRecyclerView();


        ArrayList<people> tmp = new ArrayList<>();
        //  if(np!=null){
        for(int i=0;i<np.size();i++) {
            tmp.add(np.get(i));

//            if(np.get(i).getP_id().equals(id)) {
//                tmp.add(np.get(i));
//            }
        }
        //  }
        adapter = new peopleAdapter(this, tmp);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


//        final String pw = i.getExtras().getString("Pw");


        adapter.setItems(tmp);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setItems(tmp);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.home:
                        intent = new Intent(list_people.this, mainpage_picture.class);
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
                    case R.id.search:
                        break;
                    case R.id.certificate:
                        intent = new Intent(list_people.this, certification.class);
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

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
//                adapter.players.remove(position);
//                adapter.notifyItemRemoved(position);
//                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }
}
