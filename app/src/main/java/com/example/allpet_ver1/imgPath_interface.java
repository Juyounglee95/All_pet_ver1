package com.example.allpet_ver1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface imgPath_interface {
    String URL="http://18.191.37.77:8080/";
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("PET/{name}")
    Call<JsonArray> imgTest(@Path("name") String name, @Body JsonObject id);
}
