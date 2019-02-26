package com.bnucist2016cs.xieshuzhao.partybranchworkbook.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.BasicActivity;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class LosePasswordActivity extends BasicActivity {

    EditText et_email;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_password);
        //Bmob.initialize(this, "bc1a2bb52c3e63b49a91f6bbcaf12310");

        et_email = (EditText) findViewById(R.id.find_password_email);

        btn = (Button) findViewById(R.id.lose_password_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                losePassword();
            }
        });
    }

    public void losePassword(){
        final String email = et_email.getText().toString();

        if (TextUtils.isEmpty(email)){
            ShowToast(R.string.toast_error_email_null);
            return;
        }

        User.requestEmailVerify(email, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    ShowToast("Successfully requested a verification email. Please go to the "+email+" to activate the account.");
                }else {
                    ShowToast("Fail："+e.getMessage());
                }
            }
        });
    }
}
