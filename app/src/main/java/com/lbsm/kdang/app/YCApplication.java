package com.lbsm.kdang.app;


import android.app.Application;

import com.lbsm.kdang.preference.LoginAuthPref;

public class YCApplication extends Application {

    private LoginAuthPref account;
//    private UserPref user;
//
//
    public LoginAuthPref getAccount(){
        return account == null ? account = new LoginAuthPref(this) : account;
    }
//
//    public UserPref getUser(){
//        return user == null ? user = new UserPref(this) : user;
//    }




}
