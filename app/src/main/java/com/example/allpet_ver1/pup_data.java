package com.example.allpet_ver1;

import java.util.ArrayList;

public class pup_data {
    ArrayList<puppy> items = new ArrayList<>();
    public ArrayList<puppy> getitems() {
        puppy p1 = new puppy("https://ifh.cc/g/86mBl.jpg", "개1", 1);

        puppy p2 = new puppy("https://ifh.cc/g/KHiC0.jpg", "개2", 100);
        puppy p3 = new puppy("https://ifh.cc/g/6JVeb.jpg", "개3", 12);
        puppy p4 = new puppy("https://ifh.cc/g/8Karu.jpg", "개4", 11);
        puppy p5 = new puppy("https://ifh.cc/g/NaaSu.jpg", "개5", 17);
        puppy p6 = new puppy("https://ifh.cc/g/Btxfh.jpg", "개6", 18);
        puppy p7 = new puppy("https://ifh.cc/g/hdcuF.jpg", "개7", 143);
        puppy p8 = new puppy("https://ifh.cc/g/o5dww.jpg", "개8", 13);
        puppy p9 = new puppy("https://ifh.cc/g/dr7Yw.jpg", "개9", 11);
        puppy p10 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
       puppy p11 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
       puppy p12 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
       puppy p13 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);
       puppy p14 = new puppy("https://ifh.cc/g/9W80d.jpg", "개10", 10);

        items.add(p1);
       items.add(p2);
       items.add(p3);
       items.add(p4);
       items.add(p5);
       items.add(p6);
       items.add(p7);
       items.add(p8);
       items.add(p9);
       items.add(p10);

       items.add(p11);
       items.add(p12);
       items.add(p13);
       items.add(p14);


       return items;
    }

}
