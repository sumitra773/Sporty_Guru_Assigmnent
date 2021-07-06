package com.example.android.assignmenttask;

import com.example.android.assignmenttask.Model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search")
    Call<List<University>> getSearch(@Query("country") String country);
}
