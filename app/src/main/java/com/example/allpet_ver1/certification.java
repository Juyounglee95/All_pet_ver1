package com.example.allpet_ver1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class certification extends AppCompatActivity {
    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
         PieChartView pieChartView = findViewById(R.id.chart);
        List pieData = new ArrayList<>();
        success = 80;
        pieData.add(new SliceValue(success, Color.parseColor("#FFFACD")).setLabel("SUCCESS"));
        pieData.add(new SliceValue((100-success), Color.GRAY));


        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("MY GOAL").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);
    }
}
