package com.example.allpet_ver1;

import java.util.ArrayList;

public class board_data {
    ArrayList<board> items = new ArrayList<>();
    public ArrayList<board> getitems(int num) {
        board b1 = new board("https://ifh.cc/g/86mBl.jpg", "무료분양합니다.", 1);

        board b2 = new board("https://ifh.cc/g/KHiC0.jpg", "유기견을 도와주세요", 2);
        board b3 = new board("https://ifh.cc/g/6JVeb.jpg", "댕댕이 보증금이 타이어보다 싸다", 3);
        board b4 = new board("https://ifh.cc/g/8Karu.jpg", "상현이 형이 피는 담배는 릴 하이브리드", 2);
        board b5 = new board("https://ifh.cc/g/NaaSu.jpg", "상현이 형이 차는 시계는 ALBA", 3);
        board b6 = new board("https://ifh.cc/g/86mBl.jpg", "상현이 형 안경테는 금테", 1);


        items.add(b1);
        items.add(b2);
        items.add(b3);
        items.add(b4);
        items.add(b5);
        items.add(b6);
        ArrayList<board> tmp = new ArrayList<>();
        for(int i=0;i<items.size();i++) {
            if(items.get(i).getSituation() == num)
                tmp.add(new board(items.get(i).getUrl(),items.get(i).getTitle(),items.get(i).getSituation()));
        }

        return tmp;
    }
}
