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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class pointAdapter extends RecyclerView.Adapter<pointAdapter.ViewHolder> {
    private ArrayList<point_data> items = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    public View view;
    public pointAdapter(Context context, ArrayList<point_data> items ){

        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }
    public pointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardpoint_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull pointAdapter.ViewHolder viewHolder, int position) {
        final point_data item = items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getUrl())
                .into(viewHolder.pup_image);

        viewHolder.pup_name.setText(item.getDetail());
        viewHolder.pup_point.setText(String.valueOf(item.getPoint()));

        viewHolder.pup_name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, point_info.class);
                intent.putExtra("sksk",item);
//                intent.putExtra("url", item.getUrl1());
//                intent.putExtra("name", item.getname());
//                intent.putExtra("money", item.getmoney());
                context.startActivity(intent);
            }
        });

        viewHolder.pup_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, point_info.class);
                intent.putExtra("sksk",item);
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

    public void setItems(ArrayList<point_data> items) {
        this.items = items;
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pup_image;
        TextView pup_name;
        TextView pup_point;

        ViewHolder(View itemView) {
            super(itemView);

            pup_image = itemView.findViewById(R.id.image);
            pup_point = itemView.findViewById(R.id.point);
            pup_name = itemView.findViewById(R.id.title);

        }
    }
}
