package com.frame.db.preference;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class BasePreference {

    protected SharedPreferences mPreferences;
    protected Context mContext;

    public static final String SERVICE = "service";

    public abstract SharedPreferences getPreferences(Context context);

    public BasePreference(Context context) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        mPreferences = getPreferences(context);
    }

    public void editString(String tag, String value) {
        // TODO Auto-generated method stub
        mPreferences.edit().putString(tag, value).commit();
    }

    public String getString(String tag, String value) {
        // TODO Auto-generated method stub
        return mPreferences.getString(tag, value);
    }

    public String getString(String tag) {
        // TODO Auto-generated method stub
        return mPreferences.getString(tag, "");
    }

    public void editBoolean(String tag, boolean value) {
        // TODO Auto-generated method stub
        mPreferences.edit().putBoolean(tag, value).commit();
    }

    public Boolean getBooleanTrue(String tag) {
        // TODO Auto-generated method stub
        return mPreferences.getBoolean(tag, true);
    }

    public Boolean getBooleanFalse(String tag) {
        // TODO Auto-generated method stub
        return mPreferences.getBoolean(tag, false);
    }

    public void editInt(String tag, int value) {
        // TODO Auto-generated method stub
        mPreferences.edit().putInt(tag, value).commit();
    }

    public int getInt(String tag, int value) {
        // TODO Auto-generated method stub
        return mPreferences.getInt(tag, value);
    }

    public int getInt(String tag) {
        // TODO Auto-generated method stub
        return mPreferences.getInt(tag, -1);
    }

    public void editLong(String tag, long value) {
        // TODO Auto-generated method stub
        mPreferences.edit().putLong(tag, value).commit();
    }

    public long getLong(String tag, long value) {
        // TODO Auto-generated method stub
        return mPreferences.getLong(tag, value);
    }

    public long getLong(String tag) {
        // TODO Auto-generated method stub
        return mPreferences.getLong(tag, -1);
    }

    public void cleanTag(String tag) {
        mPreferences.edit().remove(tag).commit();
    }
}
