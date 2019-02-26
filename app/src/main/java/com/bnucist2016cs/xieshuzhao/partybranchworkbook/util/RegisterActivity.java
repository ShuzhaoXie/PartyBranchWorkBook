package com.bnucist2016cs.xieshuzhao.partybranchworkbook.util;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.User;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BasicActivity {

    EditText et_username,et_email,et_password,et_pwd;
    Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Bmob.initialize(this, "bc1a2bb52c3e63b49a91f6bbcaf12310");

        et_username = (EditText) findViewById(R.id.et_account);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_pwd = (EditText) findViewById(R.id.et_password_again);

        bt_register = (Button) findViewById(R.id.register_button);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){
        String name = et_username.getText().toString();
        String email = et_email.getText().toString();
        String pwd = et_password.getText().toString();
        String pwd2 = et_pwd.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ShowToast(R.string.toast_error_username_null);
            return;
        }
        if (TextUtils.isEmpty(email)){
            ShowToast(R.string.toast_error_email_null);
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            ShowToast(R.string.toast_error_password_null);
            return;
        }
        if (TextUtils.isEmpty(pwd2)){
            ShowToast(R.string.toast_error_password_again_null);
            return;
        }
        if (!pwd.equals(pwd2)){
            ShowToast(R.string.toast_error_comfirm_password);
            return;
        }


        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Registering...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        final User bu = new User();

        bu.setEmail(email);
        bu.setPassword(pwd);
        bu.setUsername(name);
        bu.signUp(new SaveListener<User>(){
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    Toast.makeText(RegisterActivity.this, "registration success", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }
                else{
                    progressDialog.dismiss();
                    ShowToast("Failed"+e.getMessage());
                }
            }
        });
    }
}
