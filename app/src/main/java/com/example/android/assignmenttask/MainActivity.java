package com.example.android.assignmenttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.assignmenttask.Adapter.ItemAdapter;
import com.example.android.assignmenttask.Adapter.OnItemClickListener;
import com.example.android.assignmenttask.Model.University;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String COUNTRY = "India";
    private static final String PREF_NAME = "assignment_pref";

    private static final String CACHE_DATA = "offline_university_data";

    ArrayList<University> mUniversityArrayList;
    private ItemAdapter adapter;
    private RecyclerView rvUniversityList;

    private SharedPreferences mSharedPreferences;

    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = this.getSharedPreferences(PREF_NAME, 0);
        rvUniversityList = findViewById(R.id.rv_university_list);
        mUniversityArrayList = new ArrayList<>();

        mProgressBar = findViewById(R.id.loading_progress_bar);

        if(!checkInternetConnection()) {
            if(mSharedPreferences.getString(CACHE_DATA, "").isEmpty()) {
                showInternetConnectionErrorDialog();
            } else  {
                List<University> universities = new Gson().fromJson(mSharedPreferences.getString(CACHE_DATA, ""),
                        new TypeToken<List<University>>(){}.getType());
                generateDataList(universities);
            }
        }else {
            findUniversity();
        }

    }

    @Override
    public void onItemClick(String url) {
        if(!checkInternetConnection()) {
            showInternetConnectionErrorDialog();
            return;
        }
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    private Boolean checkInternetConnection() {
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null;
    }

    private void showInternetConnectionErrorDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.app_name))
                .setMessage("Please check internet connection")
                .setPositiveButton("OK", null).show();
    }

    private void findUniversity(){
        mProgressBar.setVisibility(View.VISIBLE);

        ApiUtilities.getRetrofit().getSearch(COUNTRY).enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {
                if(response.body().size() > 0){
                    List<University> postList = response.body();
                    doCaching(postList);
                    generateDataList(postList);
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }
    private void doCaching(List<University> list) {
        List<University> first20Universities = new ArrayList<>();
        int i = 0;
        while (i < 20) {
            first20Universities.add(list.get(i));
            i++;
        }
        String dataForCache = new Gson().toJson(first20Universities);
        mSharedPreferences.edit().putString(CACHE_DATA, dataForCache).apply();
    }

    private void generateDataList(List<University> postList) {
        adapter = new ItemAdapter((ArrayList<University>) postList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvUniversityList.setLayoutManager(linearLayoutManager);
        rvUniversityList.setAdapter(adapter);
    }
}