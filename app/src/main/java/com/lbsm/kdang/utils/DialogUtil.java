package com.lbsm.kdang.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.webkit.WebView;

/**
 * Date on 2016/10/12.
 */

public class DialogUtil {

    public static void showDialog(Context context,String title,String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.show();

    }

    public static void showUrlDialog(Context context,String url) {

        final WebView webView=new WebView(context,null);
        webView.loadUrl(url);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(title);
        builder.setView(webView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                webView.destroy();
                dialog.dismiss();
            }
        });
        builder.show();

    }
}
