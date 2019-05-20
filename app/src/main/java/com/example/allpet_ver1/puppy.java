package com.example.allpet_ver1;
public class puppy {

    private String url;
    private String name;
    private int money;

    public puppy(String url, String name, int money) {

        this.url = url;
       this.name = name;
       this.money= money;


    }

    public String getUrl() {
        return url;
    }

    public String getname() {
        return name;
    }

    public int getmoney() {
        return money;
    }


}