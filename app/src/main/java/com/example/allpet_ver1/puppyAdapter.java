package com.example.allpet_ver1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class puppyAdapter extends RecyclerView.Adapter<puppyAdapter.ViewHolder> {
    private ArrayList<puppy> items = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    public View view;
    public puppyAdapter(Context context, ArrayList<puppy> items ){
       // Log.e("Adapter","^^^^^");
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }
    public puppyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull puppyAdapter.ViewHolder viewHolder, final int position) {

        final puppy item = items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getUrl1())
                .into(viewHolder.pup_image);
        String s = item.getname()+" / "+item.getmoney();
        viewHolder.pup_name.setText(s);
        viewHolder.pup_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, dog_info.class);
                intent.putExtra("puppy", item);
                intent.putExtra("Id", item.getP_id());
                intent.putExtra("num", item.getSeq());
//                intent.putExtra("url", item.getUrl1());
//                intent.putExtra("name", item.getname());
//                intent.putExtra("money", item.getmoney());
                context.startActivity(intent);
            }
        });
        //  viewHolder.tvContent.setText(item.getContent());
        // viewHolder.tvGenre.setText(item.getGenre());

    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<puppy> items) {
        this.items = items;
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pup_image;
        TextView pup_name;

        ViewHolder(View itemView) {
            super(itemView);

            pup_image = itemView.findViewById(R.id.image);

            pup_name = itemView.findViewById(R.id.title);

        }
    }
}
