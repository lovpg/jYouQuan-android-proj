package com.lbsm.kdang.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import com.frame.utils.DateUtil;
import com.lbsm.kdang.app.KDangApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Utils {

	public static String getUuid() {
		// TODO Auto-generated method stub
		 String uuid = UUID.randomUUID().toString().replace("-", "");
		 return uuid;
	}
	
	public static List<Size> getResolutionList(Camera camera)
	{ 
		Parameters parameters = camera.getParameters();
		List<Size> previewSizes = parameters.getSupportedPreviewSizes();
		return previewSizes;
	}
	
	public static class ResolutionComparator implements Comparator<Size> {

		@Override
		public int compare(Size lhs, Size rhs) {
			if(lhs.height!=rhs.height)
			return lhs.height-rhs.height;
			else
			return lhs.width-rhs.width;
		}
		 
	}
	/**
	 * 检测Sdcard是否存在
	 * 
	 * @return
	 */
	public static boolean isExitsSdcard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}
	
	public static String[] getVideoData(Intent intent, Context context) {
		// TODO Auto-generated method stub	
		String[] str = null;
		Uri uri=intent.getParcelableExtra("uri");
		String[] projects = new String[] { MediaStore.Video.Media.DATA, MediaStore.Video.Media.DURATION };
		Cursor cursor = context.getContentResolver().query(uri, projects, null,null, null);
		int duration=0;
		String filePath=null;
		if (cursor.moveToFirst()) {
			// 路径：MediaStore.Audio.Media.DATA
			str = new String[2];
			filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
			str[0] = filePath;
			// 总播放时长：MediaStore.Audio.Media.DURATION
			duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
			str[1] = String.valueOf(duration);

		}
		if(cursor!=null){
	    	cursor.close();
	    	cursor=null;
	    }
		return str;
	}
	
	public static String dateFilter(long postTime) {
		// TODO Auto-generated method stub
		String day = DateUtil.formatDate("yyyy-MM-dd", postTime);
		String today = DateUtil.formatDate("yyyy-MM-dd", System.currentTimeMillis());
		if (day.equals(today)) {
			return DateUtil.formatDate("HH:mm", postTime);
		}else{
			return DateUtil.formatDate("MM-dd HH:mm", postTime);
		}
	}
//
//	public static Boolean isDatum(){
//        boolean datum = true;
//        UserFerence user = KDangApplication.getInstance().getUser();
//        if(!StringUtil.isEmpty(user.getSpeaking()))datum = false;
//        else if(!StringUtil.isEmpty(user.getLearning()))datum = false;
//        else if(!StringUtil.isEmpty(user.getRegion())) datum = false;
//        else if(!StringUtil.isEmpty(user.getGender()))datum = false;
//		else if(!StringUtil.isEmpty(user.getNickname()))datum = false;
//        return  datum;
//    }

    public static String getFilePath(String path){
        return "file://"+path;
    }


	/**
	 * 程序是否在前台运行
	 *
	 * @return
	 */
	public static boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		// device

		ActivityManager activityManager = (ActivityManager) KDangApplication.getInstance().getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = KDangApplication.getInstance().getPackageName();

		List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}

		return false;
	}

	public static String getListString(List<Object> list){
		String str = "";
		for (int i=0;i<list.size();i++){
			str = str + String.valueOf(list.get(i))+",";
		}
		return str;
	}


	public static String getListString(long[] objects){
		String str = "";
		for (int i=0;i<objects.length;i++){
			str = str + String.valueOf(objects[i])+",";
		}
		return str;
	}

	public static int getRandomHeight(){
		Random random=new Random();
		int ran = (random.nextInt(10) + 1) * 20;
		return ran;
	}

	public static List<String> getRoomTag(String tag){
		List<String> list = new ArrayList<>();
		String[] tags = tag.split(":");
		for (int i=0;i < tags.length;i++){
			list.add(tags[i]);
		}
		return list;
	}

	public static int getVoiceLength(Context context, String path){
		MediaPlayer player= MediaPlayer.create(context, Uri.fromFile(new File(path)));
		int time=player.getDuration();
		return time;
	}

	public static String getLiveLike(int like){
		 return "tlk_like_"+(like+1)+"@2x.png";
	}

	public static int getImageHeight(float height){
		int imgeHeght = 0;
		if(height < 430)imgeHeght = (int) height;
		if(height < 600)imgeHeght = (int) (height / 1.3);
		if(height < 700)imgeHeght = (int) (height / 1.5);
		else imgeHeght = (int) (height / 2);
		return imgeHeght;
	}
}
