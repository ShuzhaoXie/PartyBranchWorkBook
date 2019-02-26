package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.util.List;

public class BookContentActivity extends AppCompatActivity {

    private TextView txt_label;
    private TextView txt_spot;
    private TextView txt_time;
    private TextView txt_content;
    private TextView txt_news;
    private String time;
    private List<String> paths;
    private ImageAdapter adapter;
    private List<Book> books;
    private Book book;
    private Boolean isPause = false;

    public static void actionStart(Context context,String time){
        Intent intent = new Intent(context,BookContentActivity.class);
        intent.putExtra("TIME",time);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.book_content_toolbar);
        setSupportActionBar(toolbar);

        init();
        setText();
        loadImages();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.book_content_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.change_content:
                changeContent();
                break;
            case R.id.change_news:
                changeNews();
                break;
            case R.id.change_photo:
                changePhoto();
                break;
            default:
        }
        return true;
    }

    public void init(){
        txt_label = (TextView) findViewById(R.id.content_label);
        txt_content = (TextView) findViewById(R.id.content_content);
        txt_news = (TextView) findViewById(R.id.content_news);
        txt_spot = (TextView) findViewById(R.id.content_spot);
        txt_time = (TextView) findViewById(R.id.content_time);
        time = getIntent().getStringExtra("TIME");
    }

    public void setText(){
        books = DataSupport.where("start = ?",time).find(Book.class);
        for (Book b:books){
            book = b;
        }
        txt_label.setText(book.getLabel());
        txt_spot.setText(book.getSpot());
        txt_time.setText(book.getStart());

        if (book.getContent()==null){
            txt_content.setText("There is no summary here~");
        }else{
            txt_content.setText(book.getContent());
        }
        if (book.getNews()==null){
            txt_news.setText("There is no news here~");
        }else{
            txt_news.setText(book.getNews());
        }

        paths = book.getPath();
        if (paths.size()==0){
            Log.e("mmp","0");
            Log.e("mmp",time);
        }
        for (String u:paths){
            Log.e("mmp",u);
        }
    }

    public void loadImages(){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImageAdapter(paths);
        recyclerView.setAdapter(adapter);
    }

    public void changeContent(){
        Intent intent = new Intent(BookContentActivity.this,ChangeContentActivity.class);
        intent.putExtra("CONTENT",book.getContent());
        intent.putExtra("TIME",book.getStart());
        startActivity(intent);
    }

    public void changeNews(){
        Intent intent = new Intent(BookContentActivity.this,ChangeNewsActivity.class);
        intent.putExtra("NEWS",book.getNews());
        intent.putExtra("TIME",book.getStart());
        startActivity(intent);
    }

    public void changePhoto(){
        Intent intent = new Intent(BookContentActivity.this,ChangeImageActivity.class);
        intent.putExtra("TIME",book.getStart());
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause =true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPause){
            isPause = false;
            books = DataSupport.where("start = ?",time).find(Book.class);
            for (Book b:books){
                book = b;
            }
            paths = book.getPath();
            adapter.setmPaths(paths);
            adapter.notifyDataSetChanged();
        }
    }
}
