package com.example.allpet_ver1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class peopleAdapter extends RecyclerView.Adapter<peopleAdapter.ViewHolder> {
    private ArrayList<people> items = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    public View view;
    int p=0;
    public peopleAdapter(Context context, ArrayList<people> items) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public peopleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_people, parent, false);
        peopleAdapter.ViewHolder viewHolder = new peopleAdapter.ViewHolder(itemview);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull peopleAdapter.ViewHolder viewHolder, int position) {

        final people item = items.get(position);

//        Glide.with(viewHolder.itemView.getContext())
//                .load(item.getUrl1())
//                .into(viewHolder.pup_image);

        viewHolder.commentDesc.setText(item.getCommentDesc());
        viewHolder.id.setText(item.getId());
        viewHolder.name.setText(item.getName());
        viewHolder.job.setText(item.getJob());
        viewHolder.location1.setText(item.getLocation1());
        viewHolder.location2.setText(item.getLocation2());
        viewHolder.phoneNum.setText(item.getPhoneNum());
        viewHolder.familyNum.setText(item.getFamilyNum());
        viewHolder.experience.setText(item.getExperience());
        p = position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<people> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        TextView job;
        TextView location1;
        TextView location2;
        TextView phoneNum;
        TextView familyNum;
        TextView experience;
        TextView commentDesc;


        ViewHolder(View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            job=itemView.findViewById(R.id.job);
            location1=itemView.findViewById(R.id.location1);
            location2=itemView.findViewById(R.id.location2);
            phoneNum=itemView.findViewById(R.id.phoneNum);
            familyNum=itemView.findViewById(R.id.familyNum);
            experience=itemView.findViewById(R.id.experience);
            commentDesc=itemView.findViewById(R.id.title);
        }
    }
}