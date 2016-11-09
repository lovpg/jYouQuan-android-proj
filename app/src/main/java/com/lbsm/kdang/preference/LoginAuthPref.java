package com.lbsm.kdang.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.frame.db.preference.BasePreference;
import com.lbsm.kdang.entity.LoginAuth;

/**
 * date: 2016/10/26.
 */

public class LoginAuthPref extends BasePreference{

    private static final String LOGIN_AUTH="loginAuth";

    public static final String UID="uid";
    public static final String USERNAME="username";
    public static final String SESSIONID="sessionId";
    public static final String TOKEN="token";
    public static final String NICKNAME="nickname";
    public static final String GENDER="gender";
    public static final String AVATAR="avatar";
    public static final String SERVER="server";
    public static final String IMSERVER="imserver";

    public static final String PASSWORD="password";

    public LoginAuthPref(Context context) {
        super(context);
    }

    @Override
    public SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(LOGIN_AUTH,Context.MODE_PRIVATE);
    }

    public long getUid() {
        return getLong(UID);
    }

    public void setUid(long uid) {
        editLong(UID,uid);
    }

    public String getUsername() {
        return getString(USERNAME);
    }

    public void setUsername(String username) {
        editString(USERNAME,username);
    }

    public String getSessionId() {
        return getString(SESSIONID);
    }

    public void setSessionId(String sessionId) {
        editString(SESSIONID,sessionId);
    }

    public String getToken() {
        return getString(TOKEN);
    }

    public void setToken(String token) {
        editString(TOKEN,token);
    }

    public String getNickname() {
        return getString(NICKNAME);
    }

    public void setNickname(String nickname) {
        editString(NICKNAME,nickname);
    }

    public String getGender() {
        return getString(GENDER);
    }

    public void setGender(String gender) {
        editString(GENDER,gender);
    }

    public String getAvatar() {
        return getString(AVATAR);
    }

    public void setAvatar(String avatar) {
        editString(AVATAR,avatar);
    }

    public String getServer() {
        return getString(SERVER);
    }

    public void setServer(String server) {
        editString(SERVER,server);
    }

    public String getImserver() {
        return getString(IMSERVER);
    }

    public void setImserver(String imserver) {
        editString(IMSERVER,imserver);
    }

    public void saveLoginAuth(LoginAuth loginAuth){
        mPreferences.edit().putLong(UID,loginAuth.getUid())
                .putString(USERNAME,loginAuth.getUsername())
                .putString(SESSIONID,loginAuth.getSessionId())
                .putString(TOKEN,loginAuth.getToken())
                .putString(NICKNAME,loginAuth.getNickname())
                .putString(GENDER,loginAuth.getGender())
                .putString(AVATAR,loginAuth.getAvatar())
                .putString(SERVER,loginAuth.getServer())
                .putString(IMSERVER,loginAuth.getImserver()).commit();

    }

    public void setPassword(String password){
        editString(PASSWORD,password);
    }

    public String getPassword(){
        return getString(PASSWORD);
    }
}
