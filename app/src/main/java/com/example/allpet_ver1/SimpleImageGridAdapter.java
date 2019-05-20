package com.example.allpet_ver1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SimpleImageGridAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] urls;
    public  SimpleImageGridAdapter(Context context, String[] urls){
        super(context, R.layout.gridview_item, urls);
        this.context = context;
        this.urls = urls;
        inflater = LayoutInflater.from(context);
    }
//    public View getView(int position, View convertView, ViewGroup parent){
//        if(null== convertView){
//            convertView = inflater.inflate(R.layout.gridview_item,parent, false);
//
//        }
//        Glide.with(context)
//                .load(urls[position])
//                .into((ImageView)convertView);
//
//
//        return convertView;
//    }
    @Override public View getView(int position, View recycled, ViewGroup container) {
        final ImageView myImageView;
        if (recycled == null) {
            myImageView = (ImageView) inflater.inflate(R.layout.gridview_item, container, false);
        } else {
            myImageView = (ImageView) recycled;
        }


        Glide.with(context)
                .load(urls[position])
                .centerCrop()
                .into(myImageView);

        return myImageView;
    }
}
