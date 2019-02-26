package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.MainActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.ActivityCollector;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;

public class ChangeContentActivity extends BasicActivity {

    private EditText et_modify_content;
    private Button bt_confirm_content;
    private String lastContent;
    private String time;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_content);
        et_modify_content = (EditText) findViewById(R.id.modify_content);
        lastContent = getIntent().getStringExtra("CONTENT");
        if (lastContent != "There is no summary here~"){
            et_modify_content.setText(lastContent);
        }
        time = getIntent().getStringExtra("TIME");
        bt_confirm_content = (Button) findViewById(R.id.confirm_content);
        bt_confirm_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = et_modify_content.getText().toString();
                Book book = new Book();
                book.setContent(content);
                book.updateAll("start = ?",time);
                BookContentActivity.actionStart(ChangeContentActivity.this,time);
            }
        });
    }
}
