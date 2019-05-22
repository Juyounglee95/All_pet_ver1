package com.example.allpet_ver1;

import android.os.Parcel;
import android.os.Parcelable;

public class puppy implements Parcelable {

    private String url1, url2, url3, neutral, description, add1, add2, breeds, p_id, startDate, endDate, gender;
    private String name;
    private int status;
    private int age;
    private int money, count;

//p_id: 주인 id
    public puppy(String url1,String url2, String url3, String name,
                 int money,String neutral, String description, String add1,
                 int age,
                 String gender,
                 String add2, String breeds, String p_id, String startDate, String endDate, int status, int count ) {
        this.gender = gender;
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
        this. neutral= neutral;
        this.description = description;
        this.add1= add1;
        this.add2 =add2;
        this.breeds = breeds;
        this.p_id = p_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
       this.name = name;
       this.money= money;
        this.age = age;
        this.count = count;


    }

    protected puppy(Parcel in) {
        url1 = in.readString();
        url2 = in.readString();
        url3 = in.readString();
        neutral = in.readString();
        description = in.readString();
        add1 = in.readString();
        add2 = in.readString();
        breeds = in.readString();
        p_id = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        name = in.readString();
        status = in.readInt();
        age = in.readInt();
        money = in.readInt();
        gender = in.readString();
        count =in.readInt();
    }

    public static final Creator<puppy> CREATOR = new Creator<puppy>() {
        @Override
        public puppy createFromParcel(Parcel in) {
            return new puppy(in);
        }

        @Override
        public puppy[] newArray(int size) {
            return new puppy[size];
        }
    };
    public void setCount(int count){
        this.count = count;
    }
    public String getUrl1() {
        return url1;
    }

    public String getUrl2() {
        return url2;
    }
    public String getUrl3() {
        return url3;
    }
    public String getNeutral() {
        return neutral;
    }
    public String getDescription() {
        return description;
    }
    public String getAdd1() {
        return add1;
    }
    public String getAdd2() {
        return add2;
    }
    public String getBreeds() {
        return breeds;
    }
    public String getP_id() {
        return p_id;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public int getCount(){
        return count;
    }
    public int getStatus() {
        return status;
    }
    public int getAge(){
        return age;
    }
    public String getname() {
        return name;
    }

    public int getmoney() {
        return money;
    }
    public String getGender (){
        return gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url1);
        dest.writeString(url2);
        dest.writeString(url3);
        dest.writeString(neutral);
        dest.writeString(description);
        dest.writeString(add1);
        dest.writeString(add2);
        dest.writeString(breeds);
        dest.writeString(p_id);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(name);
        dest.writeInt(status);
        dest.writeInt(age);
        dest.writeInt(money);
        dest.writeString(gender);
        dest.writeInt(count);
    }
}