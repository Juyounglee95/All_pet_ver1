package com.example.allpet_ver1;

import android.os.Parcel;
import android.os.Parcelable;

public class people implements Parcelable {
    String id;
    String name;
    String job;
    String location1,location2;
    String phoneNum;
    String familyNum;
    String experience;
    String commentDesc;

    public people(String id, String name, String job, String location1, String location2, String phoneNum, String familyNum, String experience, String commentDesc){
        this.id=id;
        this.name=name;
        this.job=job;
        this.location1=location1;
        this.location2=location2;
        this.phoneNum=phoneNum;
        this.familyNum=familyNum;
        this.experience=experience;
        this.commentDesc=commentDesc;
    }

    protected people(Parcel in) {
        id = in.readString();
        name = in.readString();
        job = in.readString();
        location1 = in.readString();
        location2 = in.readString();
        phoneNum = in.readString();
        familyNum = in.readString();
        experience = in.readString();
        commentDesc = in.readString();
    }

    public static final Creator<people> CREATOR = new Creator<people>() {
        @Override
        public people createFromParcel(Parcel in) {
            return new people(in);
        }

        @Override
        public people[] newArray(int size) {
            return new people[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getFamilyNum() {
        return familyNum;
    }

    public void setFamilyNum(String familyNum) {
        this.familyNum = familyNum;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCommentDesc() {
        return commentDesc;
    }

    public void setCommentDesc(String commentDesc) {
        this.commentDesc = commentDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(job);
        dest.writeString(location1);
        dest.writeString(location2);
        dest.writeString(phoneNum);
        dest.writeString(familyNum);
        dest.writeString(experience);
        dest.writeString(commentDesc);
    }
}
