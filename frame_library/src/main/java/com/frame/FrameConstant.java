package com.frame;

import android.provider.MediaStore;

public interface FrameConstant {
	 //大图遍历字段
    String[] STORE_IMAGES = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.ORIENTATION
    };
    //小图遍历字段
     String[] THUMBNAIL_STORE_IMAGE = {
            MediaStore.Images.Thumbnails._ID,
            MediaStore.Images.Thumbnails.DATA
    };
     int IMAGE_CAPTURE = 0x1000;
     int IMAGE_DELETE = 0x1001;
     int IMAGE_RADIO = 0x1002;
     int IMAGE_CUT = 0x1003;



     int MAKE_CODE = 0x101;
     int IMAGE_SELECT = 0x102;

     
}
