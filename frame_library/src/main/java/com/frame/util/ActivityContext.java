package com.frame.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.frame.network.utils.LogUtil;

public class ActivityContext {
	private static Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	private static Context mContext;
	
	public static void put(Object obj) {
		LogUtil.e("ActivityContext", "ActivityContext put:" + obj);
		String key = obj.getClass().getSimpleName();
		data.put(key, obj);
	}

	public static void remove(Object obj) {
		LogUtil.e("ActivityContext", "ActivityContext remove:" + obj);
		String key = obj.getClass().getSimpleName();
		data.remove(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> clazz) {
		String key = clazz.getSimpleName();
		T bean = (T) data.get(key);

		LogUtil.e("ActivityContext", "ActivityContext get:" + clazz.getSimpleName() + " " + bean);
		return bean;
	}

	public static <T extends Activity> T runOnUiThread(Class<T> clazz, Runnable action) {
		T activity = get(clazz);
		if (activity != null) {
			activity.runOnUiThread(action);
		}
		return activity;
	}

	public static <T extends Activity> View findViewById(Class<T> clazz, int id) {
		T activity = get(clazz);
		if (activity == null) {
			return null;
		}
		return activity.findViewById(id);
	}

	public static void printKeys() {
		LogUtil.e("ActivityContext", "ActivityContext keys:" + data.keySet());
	}

	public static Context getContext() {
		return mContext;
	}

	public static void setContext(Context mContext) {
		ActivityContext.mContext = mContext;
	}
	
}