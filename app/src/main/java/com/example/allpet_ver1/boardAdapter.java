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

public class boardAdapter extends RecyclerView.Adapter<boardAdapter.ViewHolder> {
    private ArrayList<puppy> items = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    public View view;
    public boardAdapter(Context context, ArrayList<puppy> items ){

        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }
    public boardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardboard_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull boardAdapter.ViewHolder viewHolder, int position) {
        final puppy item = items.get(position);


        Glide.with(viewHolder.itemView.getContext())
                .load(item.getUrl1())
                .into(viewHolder.pup_image);

        viewHolder.pup_name.setText(item.getDescription());

        if(item.getStatus() == 1) {
            viewHolder.pup_status.setText("분양 진행");
        } else if(item.getStatus() == 2) {
            viewHolder.pup_status.setText("거래 완료");
        } else {
            viewHolder.pup_status.setText("보증 완료");
        }
        viewHolder.pup_status.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, dog_board.class);
                intent.putExtra("puppy",item);
//                intent.putExtra("url", item.getUrl1());
//                intent.putExtra("name", item.getname());
//                intent.putExtra("money", item.getmoney());
                context.startActivity(intent);
            }
        });
        viewHolder.pup_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, list_apply_people.class);
                intent.putExtra("puppy",item);
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
        TextView pup_status;
        ViewHolder(View itemView) {
            super(itemView);

            pup_image = itemView.findViewById(R.id.image);
            pup_status = itemView.findViewById(R.id.status);
            pup_name = itemView.findViewById(R.id.title);

        }
    }
}
