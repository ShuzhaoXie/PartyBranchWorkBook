package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by User on 2018/6/12.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    private List<String> mPaths;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView bookImage;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            bookImage = (ImageView) view.findViewById(R.id.book_image);
        }
    }

    public ImageAdapter(List<String> paths){
        mPaths = paths;
    }
    public void setmPaths(List<String> paths) {
        mPaths = paths;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String path = mPaths.get(position);
        Glide.with(context).load(path).into(holder.bookImage);
    }

    @Override
    public int getItemCount() {
        return mPaths.size();
    }
}
