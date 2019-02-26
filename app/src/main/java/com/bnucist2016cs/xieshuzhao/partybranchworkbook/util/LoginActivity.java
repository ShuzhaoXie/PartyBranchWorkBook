package com.bnucist2016cs.xieshuzhao.partybranchworkbook.util;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.MainActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.User;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.config.BmobConstants;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BasicActivity implements OnClickListener {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;
    EditText et_username, et_password;
    Button btn_login, btn_register, btn_losePassword;

    //private MyBroadcastReceiver receiver = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Bmob.initialize(this, "bc1a2bb52c3e63b49a91f6bbcaf12310");

        init();

        boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            et_username.setText(account);
            et_password.setText(password);
            checkBox.setChecked(true);
        }
        //IntentFilter filter = new IntentFilter();
        //filter.addAction(BmobConstants.ACTION_REGISTER_SUCCESS_FINISH);
        //registerReceiver(receiver, filter);
    }

    private void init() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        et_username = (EditText) findViewById(R.id.lg_email);
        et_password = (EditText) findViewById(R.id.lg_password);
        btn_login = (Button) findViewById(R.id.email_sign_in_button);
        btn_register = (Button) findViewById(R.id.register_button);
        btn_losePassword = (Button) findViewById(R.id.lose_password);
        checkBox = (CheckBox) findViewById(R.id.remember_pass);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_losePassword.setOnClickListener(this);
    }

    /*public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && BmobConstants.ACTION_REGISTER_SUCCESS_FINISH.equals(intent.getAction())) {
                finish();
            }
        }
    }*/

    @Override
    public void onClick(View view) {
        if (view==btn_register){
            Intent intent = new Intent(LoginActivity.this,
                    RegisterActivity.class);
            startActivity(intent);
        }else if (view == btn_login){

            login();
        }else {
            losePassword();
        }
    }

    public void login(){
        final String name = et_username.getText().toString();
        final String password = et_password.getText().toString();

        if (TextUtils.isEmpty(name)) {
            ShowToast(R.string.toast_error_username_null);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ShowToast(R.string.toast_error_password_null);
            return;
        }



        final ProgressDialog progress = new ProgressDialog(
                LoginActivity.this);
        progress.setMessage("Logging in...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    editor = pref.edit();
                    if (checkBox.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",name);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    ShowToast("login successful!");
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                    progress.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    ShowToast("Login failed:"+e.getMessage());
                    progress.dismiss();
                }
            }
        });
    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //unregisterReceiver(receiver);
    }

    public void losePassword(){
        Intent intent = new Intent(LoginActivity.this, LosePasswordActivity.class);
        startActivity(intent);
    }
}

