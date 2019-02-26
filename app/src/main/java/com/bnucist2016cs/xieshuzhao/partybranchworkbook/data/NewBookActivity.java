package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.MainActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;

import java.util.Calendar;

public class NewBookActivity extends BasicActivity {

    private EditText et_new_label;
    private EditText et_new_spot;
    private Button btn_new_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        init();
    }

    public void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.new_book_toolbar);
        setSupportActionBar(toolbar);

        et_new_label = (EditText) findViewById(R.id.new_label);
        et_new_spot = (EditText) findViewById(R.id.new_spot);
        btn_new_confirm = (Button) findViewById(R.id.new_book_confirm);
        btn_new_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBook();
            }
        });
    }

    private void newBook(){
        String label = et_new_label.getText().toString();
        String spot = et_new_spot.getText().toString();

        Book book = new Book();
        book.setLabel(label);
        book.setSpot(spot);
        book.setStart(getTime());
        book.save();
        Intent intent = new Intent(NewBookActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
