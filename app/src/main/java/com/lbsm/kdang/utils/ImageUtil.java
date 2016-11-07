package com.lbsm.kdang.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.frame.utils.FileNameUtil;
import com.lbsm.kdang.R;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.entity.SimpleUser;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

public class ImageUtil {

    public static void loadIsCover(ImageView imageView,String url){
        if(!StringUtil.isEmpty(url))return;
        if (!StringUtil.isHttp(url)) {
//            loadCover(imageView, url);
        } else {
            displayImage(imageView, Utils.getFilePath(url));
        }
    }

    public static void loadAvatar(String url, ImageView imageView) {
        // TODO Auto-generated method stub
        if(!StringUtil.isEmpty(url))return;
        if (!StringUtil.isHttp(url)) {
            loadImage(imageView, url);
        } else {
            displayImage(imageView, Utils.getFilePath(url));
        }
    }


    public static void loadStringAvatar(String url, ImageView imageView) {
        // TODO Auto-generated method stub
        if(!StringUtil.isEmpty(url))return;
        if (!StringUtil.isHttp(url)) {
            loadStringImage(imageView, url);
        } else {
            displayImage(imageView, Utils.getFilePath(url));
        }
    }


    public void displayImage(final String uri, final ImageView imageView){
        ImageLoader.getInstance().displayImage(uri, imageView, KDangApplication.getInstance().options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                if(s.equals(uri)){
                    imageView.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }

    public static void loadAcatar(String url,ImageView imageView,int flag){
        switch (flag){
            case 120:
                break;
            case 160:
                break;
            case 200:
                break;
        }
    }

    public static void loadAvatar120(String url, ImageView imageView) {
        // TODO Auto-generated method stub
        if(!StringUtil.isEmpty(url))return;
        if (!StringUtil.isHttp(url)) {
            loadImage_120(imageView, url, KDangApplication.getInstance().options);
        } else {
            displayImage(imageView, Utils.getFilePath(url));
        }
    }

    public static void loadAvatar160(String url, ImageView imageView) {
        // TODO Auto-generated method stub
        if(!StringUtil.isEmpty(url))return;
        if (!StringUtil.isHttp(url)) {
            loadImage_120(imageView, url, KDangApplication.getInstance().options);
        } else {
            displayImage(imageView, Utils.getFilePath(url));
        }
    }

    public static void loadAvatar_200(String url, ImageView imageView) {
        // TODO Auto-generated method stub
        if(!StringUtil.isEmpty(url))return;
        if (!StringUtil.isHttp(url)) {
            loadImage_200(imageView, url);
        } else {
            displayImage(imageView, Utils.getFilePath(url));
        }
    }

    public static void loadStringAvatar_200(String url, ImageView imageView) {
        // TODO Auto-generated method stub
        if(!StringUtil.isEmpty(url))return;
        if (!StringUtil.isHttp(url)) {
            loadStringImage_200(imageView, url);
        } else {
            displayImage(imageView, Utils.getFilePath(url));
        }
    }


    public static void loadImage_160(final ImageView imageView, String path) {
        // TODO Auto-generated method stub

        try {
            path = transImageUrl_160(path);
            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        imageView.setImageResource(R.drawable.icon_avatar_default);
                    else
                        imageView.setImageBitmap(arg2);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }



    public static void loadImage_200(final ImageView imageView, String path) {
        // TODO Auto-generated method stub

        try {
            path = transImageUrl_200(path);
            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        imageView.setImageResource(R.drawable.icon_avatar_default);
                    else
                        imageView.setImageBitmap(arg2);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void loadStringImage_200(final ImageView imageView, String path) {
        // TODO Auto-generated method stub

        try {
            path = transImageUrl_200(path);
            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    String str = (String) imageView.getTag();
                    if (transImageUrl_200(str).equals(url))imageView.setImageBitmap(arg2);

                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void loadImage(final ImageView imageView, String path, DisplayImageOptions circularOptions) {
        // TODO Auto-generated method stub

        try {
            ImageLoader.getInstance().loadImage(path, null, circularOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        imageView.setImageResource(R.drawable.icon_avatar_default);
                    else
                        imageView.setImageBitmap(arg2);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void loadImage_120(final ImageView imageView, String path, DisplayImageOptions circularOptions) {
        // TODO Auto-generated method stub

        try {
            path = transImageUrl_120(path);
            ImageLoader.getInstance().loadImage(path, null, circularOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    imageView.setImageResource(R.drawable.icon_avatar_default);
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        imageView.setImageResource(R.drawable.icon_avatar_default);
                    else
                        imageView.setImageBitmap(arg2);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
    public static Bitmap blurImageAmeliorate(Bitmap bmp)
    {
        long start = System.currentTimeMillis();
        // 高斯矩阵
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 16; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++)
        {
            for (int k = 1, len = width - 1; k < len; k++)
            {
                idx = 0;
                for (int m = -1; m <= 1; m++)
                {
                    for (int n = -1; n <= 1; n++)
                    {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
        Log.d("may", "used time=" + (end - start));
        return bitmap;
    }

    public static void loadImageUser_120(final ImageView imageView, String path, DisplayImageOptions circularOptions) {
        // TODO Auto-generated method stub


        try {
            path = transImageUrl_120(path);
            ImageLoader.getInstance().loadImage(path, null, circularOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    SimpleUser simpleUser = (SimpleUser)imageView.getTag();
                    if(simpleUser != null){
                        if (url.equals(transImageUrl_120(simpleUser.getAvatar())))
                            imageView.setImageBitmap(arg2);
                    }

                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void loadImageString_120(final ImageView imageView, String path, DisplayImageOptions circularOptions) {
        // TODO Auto-generated method stub


        try {
            path = transImageUrl_120(path);
            ImageLoader.getInstance().loadImage(path, null, circularOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    String str = (String) imageView.getTag();
                    if (url.equals(transImageUrl_120(str)))
                        imageView.setImageBitmap(arg2);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    public static void loadImage(final ImageView imageView, String path) {
        // TODO Auto-generated method stub

        try {
            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    imageView.setImageResource(R.drawable.icon_avatar_default);
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        imageView.setImageResource(R.drawable.icon_avatar_default);
                    else
                        imageView.setImageBitmap(arg2);
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void loadBlurImage(final ImageView imageView, String path, final Context context) {
        // TODO Auto-generated method stub

        try {
            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    imageView.setImageResource(R.drawable.icon_avatar_default);
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        imageView.setImageResource(R.drawable.icon_avatar_default);
                    else
                        FastBlur.blur(arg2, imageView, KDangApplication.getInstance());
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void loadViewBlurImage(final View view, String path) {
        // TODO Auto-generated method stub

        try {
            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    view.setBackgroundResource(R.drawable.icon_avatar_default);
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    if (TextUtils.isEmpty(url))
                        view.setBackgroundResource(R.drawable.icon_avatar_default);
                    else
                        FastBlur.blur(arg2,view,KDangApplication.getInstance());
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }


    public static void loadStringImage(final ImageView imageView, String path) {
        // TODO Auto-generated method stub

        try {
            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    imageView.setImageResource(R.drawable.icon_avatar_default);
                }

                @Override
                public void onLoadingComplete(String url, View arg1, Bitmap arg2) {
                    String str = (String) imageView.getTag();
                    if (str.equals(url)) imageView.setImageBitmap(arg2);

                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    /**
     * 圆角图片
     */
    public static void loadImageRound(final ImageView imageView, String path, DisplayImageOptions circularOptions, final int round) {
        // TODO Auto-generated method stub

        try {
            ImageLoader.getInstance().loadImage(path, circularOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String arg0, View arg1) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                    // TODO Auto-generated method stub
                    imageView.setImageResource(R.drawable.icon_avatar_default);
                }

                @Override
                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                    // TODO Auto-generated method stub
                    //					imageView.setImageBitmap(PhotoUtils.getRoundedCornerBitmap(arg2, round));
                    arg2.recycle();
                }

                @Override
                public void onLoadingCancelled(String arg0, View arg1) {
                    // TODO Auto-generated method stub

                }
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void displayImage(final ImageView imageView, String url) {
        // TODO Auto-generated method stub
        ImageLoader.getInstance().displayImage(url, imageView, KDangApplication.getInstance().options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }

    public static File avatarFile(Context context) {
        // TODO Auto-generated method stub
        File mPicFolder = null;
        try {
            mPicFolder = new File(FileNameUtil.getExternalFilesDir(context), Constants.CACHE_AVATAR_DIR);
            if (!mPicFolder.exists()) {
                mPicFolder.mkdirs();
            }
            return mPicFolder;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return mPicFolder;
    }

//    public static void loadCover120(final ImageView imageView, String path) {
//        try {
//            if (!StringUtil.isEmpty(path)) {
//                imageView.setImageResource(R.drawable.olla_group_post);
//                return;
//            }
//            path = transImageUrl_120(path);
//            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
//                @Override
//                public void onLoadingStarted(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                }
//
//                @Override
//                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//                    // TODO Auto-generated method stub
//                    imageView.setImageResource(R.drawable.olla_groub_bar);
//                }
//
//                @Override
//                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//                    // TODO Auto-generated method stub
//                    imageView.setImageBitmap(arg2);
//                }
//
//                @Override
//                public void onLoadingCancelled(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                }
//            });
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//    }


//    public static void loadBarCover120(final ImageView imageView, String path) {
//        try {
//            if (!StringUtil.isEmpty(path)) {
//                imageView.setImageResource(R.drawable.olla_group_post);
//                return;
//            }
//            path = transImageUrl_120(path);
//            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
//                @Override
//                public void onLoadingStarted(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                }
//
//                @Override
//                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//                    // TODO Auto-generated method stub
//                    imageView.setImageResource(R.drawable.olla_groub_bar);
//                }
//
//                @Override
//                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//                    // TODO Auto-generated method stub
//                    try {
//                        Bar bar = (Bar) imageView.getTag();
//                        if(bar != null)if(transImageUrl_120(bar.getBarAvator()).equals(arg0))imageView.setImageBitmap(arg2);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//
//                @Override
//                public void onLoadingCancelled(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                }
//            });
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//    }


//    public static void loadCover(final ImageView imageView, String path) {
//        try {
//            if (!StringUtil.isEmpty(path)) {
//                imageView.setImageResource(R.drawable.olla_group_post);
//                return;
//            }
//            ImageLoader.getInstance().loadImage(path, null, KDangApplication.getInstance().options, new ImageLoadingListener() {
//                @Override
//                public void onLoadingStarted(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                }
//
//                @Override
//                public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
//                    // TODO Auto-generated method stub
//                    imageView.setImageResource(R.drawable.olla_groub_bar);
//                }
//
//                @Override
//                public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//                    // TODO Auto-generated method stub
//                    imageView.setImageBitmap(arg2);
//                }
//
//                @Override
//                public void onLoadingCancelled(String arg0, View arg1) {
//                    // TODO Auto-generated method stub
//                }
//            });
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//    }


    public static void loadAvatar(ImageView imageView,String url,int size){
        switch (size){
            case 120:
                url = transImageUrl_120(url);
                break;
            case 160:
                url = transImageUrl_160(url);
                break;
            case 200:
                url = transImageUrl_200(url);
                break;
        }
    }


    public static String transImageUrl_120(String url) {
        return transImageUrl(url, "_120x120_crop.jpg");
    }

    public static String transImageUrl_160(String url) {
        return transImageUrl(url, "_160x160_crop.jpg");
    }

    public static String transImageUrl_200(String url) {
        return transImageUrl(url, "_200x200.jpg");
    }

    private static String transImageUrl(String url, String size) {
        if(url == null) return url;
        return url.replace(".jpg",size);
    }

//    public static String getThumbnailImagePath(String thumbRemoteUrl) {
//        String thumbImageName= thumbRemoteUrl.substring(thumbRemoteUrl.lastIndexOf("/") + 1, thumbRemoteUrl.length());
//        String path = PathUtil.getInstance().getImagePath()+"/"+ "th"+thumbImageName;
//        Log.d("msg", "thum image path:" + path);
//        return path;
//    }



}
