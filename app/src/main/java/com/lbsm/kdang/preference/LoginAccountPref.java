package com.lbsm.kdang.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.frame.data.preference.BasePreference;
import com.lbsm.kdang.entity.LoginAuth;
import com.lbsm.kdang.entity.User;

public class LoginAccountPref extends BasePreference {

	public static final String UID="uid";
	public static final String USERNAME="username";
	public static final String SESSIONID="sessionId";
	public static final String TOKEN="token";
	public static final String NICKNAME="nickname";
	public static final String GENDER="gender";
	public static final String AVATAR="avatar";
	public static final String DISTANCETEXT = "distanceText" ;
	public static final String LOCATION = "location";
    public static final String CITY = "city";
    public static final String PASSWORD = "password";
	//服务器地址
	public static final String SERVICE = "service";
    /**环境切换**/
    public static final String ENVIRONMENT = "environment";
	public static final String IMSERVICE = "imserver";

	public LoginAccountPref(Context context) {
		super(context);
	}

	@Override
	public SharedPreferences getPreferences(Context context) {
		return context.getSharedPreferences("login_account", Context.MODE_PRIVATE);
	}
	
	public void setUid(long uid) {
		edtiLong(UID, uid);
	}
	
	public long getUid() {
		return getLong(UID);
	}
	
	public void setUserName(String userName) {
		editString(USERNAME, userName);
	}
	
	public String getUserName() {
		return getString(USERNAME);
	}

	public void setNickName(String nickName) {
		editString(NICKNAME, nickName);
	}
	
	public String getNickName() {
		return getString(NICKNAME);
	}
	
	public void setGender(String gender) {
		editString(GENDER, gender);
	}
	
	public String getGender() {
		return getString(GENDER);
	}
	
	public void setAvatar(String avatar) {
		editString(AVATAR, avatar);
	}
	
	public String getAvatar() {
		return getString(AVATAR);
	}
	
	public String getToken() {
		return getString(TOKEN);
	}
	
	public String getSessionid() {
		return getString(SESSIONID);
	}

	public void setPassword(String password){
		editString(PASSWORD,password);
	}

	public String getPassword(){
		return getString(PASSWORD);
	}

	public String getService(){
		return getString(SERVICE);
	}

	public void setService(String service){
		editString(SERVICE,service);
	}

	public void setImServer(String imserver){
		editString(IMSERVICE,imserver);
	}

	public String getImServer(){
		return getString(IMSERVICE);
	}


	public void savaThearAccount(final LoginAuth loginAuth) {
		new Thread(){
			public void run() {
				saveAccount(loginAuth);
			};
		}.start();
	}

	public void savaThearAccount(final User user) {
		new Thread(){
			public void run() {
				saveAccount(user);
			};
		}.start();
	}
	
	public void saveAccount(LoginAuth loginAuth) {
		mPreferences.edit().putLong(UID, loginAuth.getUid())
		.putString(USERNAME, loginAuth.getUsername())
		.putString(SESSIONID, loginAuth.getSessionId())
		.putString(TOKEN, loginAuth.getToken())
		.putString(NICKNAME, loginAuth.getNickname())
		.putString(GENDER, loginAuth.getGender())
		.putString(AVATAR, loginAuth.getAvatar())
		.putString(SERVICE,loginAuth.getServer())
		.putString(IMSERVICE, loginAuth.getImServer()).commit();
	}
	
	public void saveAccount(User user) {
		mPreferences.edit().putLong(UID, user.getUid())
		.putString(NICKNAME, user.getNickname())
		.putString(GENDER, user.getGender())
		.putString(AVATAR, user.getAvatar()).commit();		
	}
	
	public String getLocation() {
		return getString(LOCATION);
	}
	
	public void setLocation(String location) {
		editString(LOCATION, location);
	}


	
	public void clean() {
        String username = getUserName();
        String location = getLocation();
        String city = getCity();
		String password = getPassword();
		String service = getService();
		mPreferences.edit().clear().putString(USERNAME,username).putString(LOCATION, location)
				.putString(CITY, city).putString(PASSWORD,password)
				.commit();
	}

    public void setEnvironment(boolean isEnvironment){
        editBoolean(ENVIRONMENT,isEnvironment);
    }

    public boolean gettEnvironment(){
    return  getBooleanTrue(ENVIRONMENT);
    }


    public  void setCity(String city) {
        editString(CITY, city);
    }

    public String getCity() {
        return getString(CITY);
    }
}
