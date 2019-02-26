package com.bnucist2016cs.xieshuzhao.partybranchworkbook.util;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.User;
import com.bumptech.glide.Glide;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class SetMyInfoActivity extends BasicActivity {

    private EditText username;
    private EditText email;
    private EditText old_pass;
    private EditText new_pass;
    private Button btn;
    private ImageButton ibt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_my_info);
        username = findViewById(R.id.set_info_username);
        email = findViewById(R.id.set_info_email);
        old_pass = findViewById(R.id.set_info_old_password);
        new_pass = findViewById(R.id.set_info_news_password);
        btn = findViewById(R.id.set_info_button);
        ibt = findViewById(R.id.set_info_avatar);

        username.setText(GetUsername());
        email.setText(GetEmail());
        if (GetAvatar()==null){
            Glide.with(SetMyInfoActivity.this).load(R.drawable.apple_pic).into(ibt);
        }else{
            Glide.with(SetMyInfoActivity.this).load(GetAvatar()).into(ibt);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String op = old_pass.getText().toString();
                String np = old_pass.getText().toString();

                if (!TextUtils.isEmpty(op)){
                    ShowToast("Old password cannot be empty");
                    return;
                }
                if(!TextUtils.isEmpty(np)){
                    ShowToast("New password cannot be empty");
                    return;
                }
                BmobUser.updateCurrentUserPassword(op, np, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            ShowToast("The password has been modified successfully. You can log in with your new password.");
                        }else{
                            ShowToast("Failed:" + e.getMessage());
                        }
                    }
                });

                User newUser=new User();
                newUser.setUsername(username.getText().toString());
                newUser.setEmail(email.getText().toString());
                newUser.update(getDirName(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            ShowToast("Update user's information successfully");
                        }else{
                            ShowToast("Failed to update user's information:" + e.getMessage());
                        }
                    }
                });
            }
        });

    }
}
