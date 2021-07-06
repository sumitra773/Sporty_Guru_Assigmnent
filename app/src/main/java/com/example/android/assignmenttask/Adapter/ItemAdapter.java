package com.example.android.assignmenttask.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.assignmenttask.Model.University;
import com.example.android.assignmenttask.R;
import com.example.android.assignmenttask.WebViewActivity;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final ArrayList<University> mUniversitiesArrayList;

    private final OnItemClickListener mListener;

    public ItemAdapter(ArrayList<University> articlesArrayList, OnItemClickListener listener) {
        mListener = listener;
        mUniversitiesArrayList = articlesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(mUniversitiesArrayList.get(position).getWebPages().get(0));
            }
        });
        String stateAndProvince;
        if (mUniversitiesArrayList.get(position).getStateProvince() == null)
            stateAndProvince = "";
        else
            stateAndProvince = mUniversitiesArrayList.get(position).getStateProvince() + ", ";
        holder.tvUniversityName.setText(mUniversitiesArrayList.get(position).getName());
        holder.tvAddress.setText(
                stateAndProvince
                        + mUniversitiesArrayList.get(position).getCountry() + "("
                        + mUniversitiesArrayList.get(position).getAlphaTwoCode() + ")"
        );


    }

    @Override
    public int getItemCount() {
        return mUniversitiesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUniversityName, tvAddress;
        RelativeLayout mRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUniversityName = itemView.findViewById(R.id.tv_university_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            mRelativeLayout = itemView.findViewById(R.id.rt_layout);

        }
    }
}
