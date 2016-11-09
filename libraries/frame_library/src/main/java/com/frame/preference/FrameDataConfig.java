package com.frame.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class FrameDataConfig extends BasePreference{

	public FrameDataConfig(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SharedPreferences getPreferences(Context context) {
		// TODO Auto-generated method stub
		return context.getSharedPreferences("olla_config", Context.MODE_PRIVATE);
	}

	
	public static String ALUMB_FLODER = "alumbFloder";
	
	public  void setAlumbFloder(String alumbFloder) {
		editString(ALUMB_FLODER,alumbFloder);
	}

	public String getAlumbFloder() {
		return getString(ALUMB_FLODER,"All picturres");
	}

}
