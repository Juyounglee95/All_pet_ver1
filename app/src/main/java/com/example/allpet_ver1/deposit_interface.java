package com.example.allpet_ver1;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface deposit_interface {
        String URL="http://18.191.37.77:5000/";
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @POST("{name}")
        Call<JsonObject> postTest(@Path("name") String name, @Body JsonObject id);
    }


