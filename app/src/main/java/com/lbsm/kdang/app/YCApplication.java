package com.lbsm.kdang.app;


import android.app.Application;

import com.frame.db.DBHelper;
import com.lbsm.kdang.preference.LoginAccountPref;
import com.lbsm.kdang.preference.UserPref;

public class YCApplication extends Application {

    private LoginAccountPref account;
    public DBHelper dbHelper;
    private UserPref user;


    public LoginAccountPref getAccount(){
        return account == null ? account = new LoginAccountPref(this) : account;
    }

    public UserPref getUser(){
        return user == null ? user = new UserPref(this) : user;
    }

}
