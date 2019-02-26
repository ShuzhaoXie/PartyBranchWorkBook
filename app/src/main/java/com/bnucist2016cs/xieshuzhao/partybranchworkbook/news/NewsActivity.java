package com.bnucist2016cs.xieshuzhao.partybranchworkbook.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.MainActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;

public class NewsActivity extends AppCompatActivity {


    private TextView newsTitleText;
    private TextView newsContentText;

    public static void actionStart(Context context,String title,String content){
        Intent intent = new Intent(context,NewsActivity.class);
        intent.putExtra("TITLE",title);
        intent.putExtra("CONTENT",content);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }

        newsTitleText = (TextView) findViewById(R.id.title_text);
        newsContentText = (TextView) findViewById(R.id.news_content);

        newsTitleText.setText(getIntent().getStringExtra("TITLE"));
        newsContentText.setText(getIntent().getStringExtra("CONTENT"));
    }
}
