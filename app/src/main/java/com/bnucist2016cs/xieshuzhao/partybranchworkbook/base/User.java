package com.bnucist2016cs.xieshuzhao.partybranchworkbook.base;

import android.media.Image;

import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by User on 2018/5/5.
 */

public class User extends BmobUser {

    private static final long serialVersionID = 1L;

    BmobFile path;

    public void setPath(BmobFile path) {
        this.path = path;
    }

    public BmobFile getPath() {
        return path;
    }
}
