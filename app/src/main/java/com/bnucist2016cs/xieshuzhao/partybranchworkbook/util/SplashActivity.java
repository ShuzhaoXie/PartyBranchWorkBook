package com.bnucist2016cs.xieshuzhao.partybranchworkbook.util;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.MainActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;

import cn.bmob.v3.Bmob;

public class SplashActivity extends BasicActivity {

    private final int SPLASH_DISPLAY_LENGHT = 2000;//启屏显示时间ms单位
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_splash);

        Bmob.initialize(this, "bc1a2bb52c3e63b49a91f6bbcaf12310");

        startActivity(new Intent(this, LoginActivity.class));

        finish();
        /*handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGHT);*/
    }
}
