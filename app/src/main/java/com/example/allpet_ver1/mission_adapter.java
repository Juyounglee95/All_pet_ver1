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

public class mission_adapter extends RecyclerView.Adapter<mission_adapter.ViewHolder> {
    private ArrayList<mission_data> items = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    public View view;
    public mission_adapter(Context context, ArrayList<mission_data> items ){

        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }
    public mission_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardboard_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);

        return viewHolder;
    }

    public void onBindViewHolder(@NonNull mission_adapter.ViewHolder viewHolder, int position) {
        final mission_data item = items.get(position);
        Log.e("VIEW", item.getFile_name());

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getFile_name())
                .into(viewHolder.pup_image);

        viewHolder.pup_name.setText(item.getFile_name());
//        viewHolder.pup_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, dog_board.class);
//                intent.putExtra("puppy",item);
////                intent.putExtra("url", item.getUrl1());
////                intent.putExtra("name", item.getname());
////                intent.putExtra("money", item.getmoney());
//                context.startActivity(intent);
//            }
//        });
//        viewHolder.pup_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, dog_board.class);
//                intent.putExtra("puppy",item);
////                intent.putExtra("url", item.getUrl1());
////                intent.putExtra("name", item.getname());
////                intent.putExtra("money", item.getmoney());
//                context.startActivity(intent);
//            }
//        });
        //  viewHolder.tvContent.setText(item.getContent());
        // viewHolder.tvGenre.setText(item.getGenre());

    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<mission_data> items) {
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
