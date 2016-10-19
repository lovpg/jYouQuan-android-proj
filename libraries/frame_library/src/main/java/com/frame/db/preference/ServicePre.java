package com.frame.db.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/9/18.
 */
public class ServicePre extends BasePreference{

    public static final String SERVICE = "service";
    public ServicePre(Context context) {
        super(context);
    }

    @Override
    public SharedPreferences getPreferences(Context context) {
       return context.getSharedPreferences("login_account", Context.MODE_PRIVATE);
    }


    public String getService(){
        return getString(SERVICE);
    }

    public void setService(String service){
        editString(SERVICE,service);
    }

}
