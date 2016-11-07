package com.lbsm.kdang.picture;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.frame.FrameConstant;
import com.frame.utils.StringUtil;
import com.lbsm.kdang.picture.entity.LocalFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/4.
 */
public class LocalImageHelper {

    public static LocalImageHelper instance;
    private Context mContent;
    public List<LocalFile> paths = null;
    public Map<String,List<LocalFile>> folders = new HashMap<String, List<LocalFile>>();
    public List<String> folderNames = new ArrayList<String>();




    public LocalImageHelper(Context context){
        this.mContent = context;
        if(paths == null) paths = new ArrayList<LocalFile>();
    }

    public void startLocalImage(){
        new Thread(){
            @Override
            public void run() {
                initImage();

            }
        }.start();
    }

    public static void init(Context context) {
        instance = new LocalImageHelper(context);
    }


    public synchronized void initImage() {
        paths.clear();
        Cursor cursor = mContent.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,  // 大图URI
                FrameConstant.STORE_IMAGES,   // 字段
                null,         // No where clause
                null,         // No where clause
                MediaStore.Images.Media.DATE_TAKEN + " DESC"); //根据时间升序

        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String path = cursor.getString(cursor.getColumnIndexOrThrow(FrameConstant.STORE_IMAGES[1]));
                File file = new File(path);
                if (file.exists()){
                    //小图URI
                    String thumbUri = getThumbnail(id, path);
                    //获取大图URI
                    String uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().appendPath(Integer.toString(id)).build().toString();
                    //获取目录名
                    String folder = file.getParentFile().getName();
                    if(!StringUtil.isEmpty(uri)) continue;
                    if(!StringUtil.isEmpty(thumbUri)) thumbUri = uri;

                    LocalFile localFile = new LocalFile();
                    localFile.setOriginalUri(uri);
                    localFile.setThumbnailUri(thumbUri);
                    int degree = cursor.getInt(2);
                    if (degree != 0) degree = degree + 180;
                    localFile.setOrientation(360 - degree);
                    localFile.setThumbnaiPath(path);
                    paths.add(localFile);
                    if (folders.containsKey(folder)){
                        folders.get(folder).add(localFile);
                    }else{
                        List<LocalFile> files = new ArrayList<LocalFile>();
                        files.add(localFile);
                        folders.put(folder,files);
                    }
                }
            }
        }

        folders.put("All picturres",paths);
        cursor.close();

        initFlodes();
    }


    private String getThumbnail(int id, String path) {
        //获取大图的缩略图
        String uri = null;
        Cursor cursor = mContent.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, FrameConstant.THUMBNAIL_STORE_IMAGE,
                MediaStore.Images.Thumbnails.IMAGE_ID + " = ?", new String[]{id + ""}, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int thumId = cursor.getInt(0);
            uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI.buildUpon().appendPath(Integer.toString(thumId)).build().toString();
            cursor.close();
            return uri;
        }
        cursor.close();
        return uri;
    }

    public List<LocalFile> getLocalFiles(String folder){
        return folders.get(folder);
    }

    public void initFlodes(){
        Iterator iter = folders.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            folderNames.add(key);
        }
    }

    public void isLocalFiles(final String folder){
        new Thread(){
            @Override
            public void run() {

                for (LocalFile localFile : folders.get(folder)){
                    localFile.setIsSelect(false);
                    localFile.setTime(-1);
                }
            }
        }.start();

    }

    public String sendPicByUri(Uri selectedImage, Context context) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        String picturePath = null;
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;
        } else {
            picturePath = selectedImage.getPath();
        }
        return picturePath;
    }
}
