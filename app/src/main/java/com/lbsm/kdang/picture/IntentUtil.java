package com.lbsm.kdang.picture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.frame.R;

public class IntentUtil {

	public static void startActivityRight(Intent intent,Context context) {
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
	
	  public static void startActivityLeft(Intent intent,Context context) {
		  context.startActivity(intent);
		  ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	    }
	  
	  public static void startActivityBottom(Intent intent,Context context) {
		// TODO Auto-generated method stub
		  Activity activity = ((Activity)context);
		  activity.startActivity(intent);
		  activity.overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
	}
	  
	  public static void startActivityBottom(Intent intent,Context context,int code) {
			// TODO Auto-generated method stub
			  Activity activity = ((Activity)context);
			  activity.startActivityForResult(intent, code);
			  activity.overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
		}
	  public static void startActivityForResultLeft(Intent intent,Context context,int code) {
		// TODO Auto-generated method stub
		  Activity activity = ((Activity)context);
		  activity.startActivityForResult(intent, code);
		  activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	  public static void finishBottom(Context context) {
		  Activity activity = (Activity) context;
		  activity.finish();
		  activity.overridePendingTransition(R.anim.not_change, R.anim.push_bottom_out);
	    }
  
	  public static void finishRight(Context context) {
		  Activity activity = (Activity) context;
		  activity.finish();
		  activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	  }

}
