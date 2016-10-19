package com.frame.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class DensityUtil {

	// Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics()));

	public static int dip2px(View view, String size) {
		return dip2px(view.getContext(), size);
	}

	public static int dip2px(Context context, String size) {
		if (size == null) {
			return 0;
		}
		float dpValue = Float.parseFloat(size.replace("dp", "").replace("dip", ""));
		return dip2px(context, dpValue);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	// //////////////////////////////////////////////////////

	public static int sp2px(Context context, String size) {
		if (size == null) {
			return 0;
		}
		float dpValue = Float.parseFloat(size.replace("sp", ""));
		return sp2px(context, dpValue);
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}


    /**
     * 获取屏幕的宽度
     * @param context
     * @return
     */
    public static int getWidth(Context context) {
        // TODO Auto-generated method stub
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getHeight(Context context) {
        // TODO Auto-generated method stub
        WindowManager  manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}