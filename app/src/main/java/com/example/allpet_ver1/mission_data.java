package com.example.allpet_ver1;

import android.os.Parcel;
import android.os.Parcelable;

public class mission_data implements Parcelable {

    String file_name;
    public mission_data( String file_name){

        this.file_name = file_name;
    }

    protected mission_data(Parcel in) {

        file_name = in.readString();
    }

    public static final Creator<mission_data> CREATOR = new Creator<mission_data>() {
        @Override
        public mission_data createFromParcel(Parcel in) {
            return new mission_data(in);
        }

        @Override
        public mission_data[] newArray(int size) {
            return new mission_data[size];
        }
    };


    public String getFile_name(){
        return  file_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(file_name);
    }
}
