package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.ActivityCollector;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;

public class ChangeNewsActivity extends BasicActivity {

    private EditText et_modify_news;
    private Button bt_confirm_news;
    private String time;
    private String news;
    private String last_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_news);
        et_modify_news = (EditText) findViewById(R.id.modify_news);
        last_news = getIntent().getStringExtra("NEWS");
        if (last_news!="There is no news here~"){
            et_modify_news.setText(last_news);
        }
        time = getIntent().getStringExtra("TIME");
        bt_confirm_news = (Button) findViewById(R.id.confirm_news);
        bt_confirm_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                news = et_modify_news.getText().toString();
                Book book = new Book();
                book.setNews(news);
                book.updateAll("start = ?",time);
                BookContentActivity.actionStart(ChangeNewsActivity.this,time);
            }
        });
    }
}
