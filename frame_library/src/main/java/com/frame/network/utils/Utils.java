package com.frame.network.utils;

import android.content.Context;
import android.widget.Toast;

import com.frame.network.NetEventCode;

public class Utils {

	public static void ToastUtil(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
	public static int getErrorCode(String code) {
		// TODO Auto-generated method stub
		if (NetEventCode.CODE_STRING_SUCCESS.equals(code)) {
			return NetEventCode.CODE_SUCCESS;
		}else if(NetEventCode.CODE_STRING_ERROR_PASSWORD.equals(code)){
			return NetEventCode.CODE_ERROR_PASSWORD;
		}
		return NetEventCode.CODE_NOT_EXIST_FIELD;
	}
	
	public static String getCookieAssembly(String uid,String token,String sessionId,String username) {		
		// TODO Auto-generated method stub
		String cookie = "";
		if (isEmple(uid))cookie = cookie + "uid="+uid;else cookie = "";
		if (isEmple(token)) cookie = cookie + ",token="+token;else cookie = "";
		if (isEmple(sessionId)) cookie = cookie + ",SESSIONID="+sessionId;else cookie = ""; 
		if (isEmple(username)) cookie = cookie + ",username="+username;else cookie = "";
		return cookie;
	}
	
	public static boolean isEmple(String str) {
		// TODO Auto-generated method stub
		if (str == null || "".equals(str) || "null".equals(str)) {
			return false;
		}else{
			return true;
		}
	}
}
