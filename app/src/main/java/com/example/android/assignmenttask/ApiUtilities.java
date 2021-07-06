package com.example.android.assignmenttask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static final String BASE_URL = "http://universities.hipolabs.com/";

    private static Retrofit retrofit = null;

    public static ApiInterface getRetrofit()
    {
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
