package com.bnucist2016cs.xieshuzhao.partybranchworkbook.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by User on 2018/5/5.
 */

public class BasicActivity extends AppCompatActivity{

    private String objectId;
    Toast mToast;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void ShowToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text,
                                Toast.LENGTH_LONG);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });
        }
    }

    public void ShowToast(final int resId) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (mToast == null) {
                    mToast = Toast.makeText(BasicActivity.this.getApplicationContext(), resId,
                            Toast.LENGTH_LONG);
                } else {
                    mToast.setText(resId);
                }
                mToast.show();
            }
        });
    }

    public String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd, yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String res = simpleDateFormat.format(date);
        return res;
    }

    public void updateUserInfos(){
        User u = BmobUser.getCurrentUser(User.class);

    }

    public String getDirName(){
        User u = BmobUser.getCurrentUser(User.class);
        return u.getObjectId();
    }

    public String GetUsername(){
        User u = BmobUser.getCurrentUser(User.class);
        return u.getUsername();
    }

    public String GetEmail(){
        User u = BmobUser.getCurrentUser(User.class);
        return u.getEmail();
    }

    public BmobFile GetAvatar(){
        User u = BmobUser.getCurrentUser(User.class);
        return u.getPath();
    }
}
