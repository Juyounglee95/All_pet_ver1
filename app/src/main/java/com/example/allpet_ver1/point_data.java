package com.example.allpet_ver1;

import java.io.Serializable;

public class point_data implements Serializable {
    String url;
    int point;
    String detail;
    String description;
    public point_data(String url, int point, String detail,String description){
        this.url = url;
        this.point = point;
        this.detail = detail;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public int getPoint() {
        return point;
    }

    public String getDetail() {
        return detail;
    }
    public String getDescription() {
        return description;
    }
}
