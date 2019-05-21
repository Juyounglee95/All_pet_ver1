package com.example.allpet_ver1;



import android.util.Log;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitExService {
    String URL="http://18.191.37.77:8080/";
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("PET/Login.sk")
    Call<JsonObject> postTest(@Body JsonObject id);
}
