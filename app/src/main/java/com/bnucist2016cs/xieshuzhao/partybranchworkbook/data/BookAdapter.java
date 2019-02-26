package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by User on 2018/6/12.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    
    private List<Book> mBookList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View bookView;
        TextView bookSpot;
        TextView bookLabel;
        TextView bookTime;

        public ViewHolder(View view){
            super(view);
            bookView = view;
            bookSpot = (TextView) view.findViewById(R.id.spot);
            bookLabel = (TextView) view.findViewById(R.id.label);
            bookTime = (TextView) view.findViewById(R.id.book_item_time);
        }
    }
    public BookAdapter(List<Book> bookList){
        mBookList = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = mBookList.get(viewHolder.getAdapterPosition());
                BookContentActivity.actionStart(view.getContext(),book.getStart());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Book book = mBookList.get(position);
        holder.bookLabel.setText(book.getLabel());
        holder.bookSpot.setText(book.getSpot());
        holder.bookTime.setText(book.getStart());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }
}
