package com.lbsm.kdang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lbsm.kdang.R;
import com.lbsm.kdang.ui.activities.MainActivity;

public class IntentUtils {

//    public static void startActivityRight(Intent intent, Context context) {
//        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//    }
//
//    public static void startActivityLeft(Intent intent, Context context) {
//        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//    }
//
//    public static void startActivityBottom(Intent intent, Context context) {
//        // TODO Auto-generated method stub
//        Activity activity = ((Activity) context);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
//    }
//
//    public static void startActivityLiveBottom(Intent intent, Context context) {
//        // TODO Auto-generated method stub
//        Activity activity = ((Activity) context);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.bottom_live_in, R.anim.bottom_live_out);
//    }
//
//
//    public static void startActivityTop(Intent intent, Context context) {
//        Activity activity = ((Activity) context);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(olla.yuyong.underlying.R.anim.push_top_in, olla.yuyong.underlying.R.anim.push_top_out);
//    }
//
//    public static void startActivityBottom(Intent intent, Context context, int code) {
//        // TODO Auto-generated method stub
//        Activity activity = ((Activity) context);
//        activity.startActivityForResult(intent, code);
//        activity.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
//    }

    public static void startActivityForResultLeft(Intent intent, Context context, int code) {
        // TODO Auto-generated method stub
        Activity activity = ((Activity) context);
        activity.startActivityForResult(intent, code);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    public static void startIntentMain(Context context, Intent intent) {
        // TODO Auto-generated method stub
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        finishRight(context);
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

//    public static void startIntentWidth(Context context, SimpleUser simpleUser) {
//        // TODO Auto-generated method stub
//        Activity activity = (Activity) context;
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("simpleUser", simpleUser);
//        Intent intent = new Intent(context, WithActivity.class);
//        intent.putExtras(bundle);
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//    }
//
//    public static void startIntentMessage(Context context, SimpleUser simpleUser) {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("simpleUser", simpleUser);
//        startActivityLeft(new Intent(context, MessageActivity.class).putExtras(bundle), context);
//    }
//
//    public static void startIntentDetaile(Context context, Share share, String fous) {
//        Intent intent = new Intent(context, DetailActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("share", share);
//        intent.putExtras(bundle);
//        if (StringUtil.isEmpty(fous)) intent.putExtra("focus", "comment");
//        startActivityLeft(intent, context);
//    }

    public static void finishBottom(Context context, Intent intent) {
        Activity activity = (Activity) context;
        activity.setResult(activity.RESULT_OK, intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.not_change, R.anim.push_bottom_out);
    }

//    public static void startBarActivity(Context context, Bar bar){
//        context.startActivity(new Intent(context, bar.getCategory().equals(CreateBarActivity.Category.PLACES)
//        ? BarPlaceActivity.class : BarActivity.class).putExtra(BarActivity.KEY_BAR, bar));
//    }
//
//    public static void startBarActivity(Context context, Bar bar) {
//        if (CreateBarActivity.Category.CHATS.equalsIgnoreCase(bar.getCategory()))
//            QNewCharActivity._startActivity(context, bar);
//        else
//            BarActivity.startBar(context, bar);
//    }
//
//    public static void startPostActivity(Context context, Post post, boolean foucs) {
//        if (CreateBarActivity.Category.PLACES.equalsIgnoreCase(post.getBar().getCategory()))
//            PostPlaceActivity.startPostActivity(context, post, foucs);
//        else if (CreateBarActivity.Category.CHATS.equalsIgnoreCase(post.getBar().getCategory()))
//            ToturDetailActivity._startActivityForResult(context, post, foucs);
//        else
//            PostActivity.startPostActivity(context, post, foucs);
//
//    }
//
//    public static void startLiveActivity(Context context, LiveRoomVO liveRoomVO, ErrorDialog errorDialog, View.OnClickListener onClickListener) {
//        Intent intent = new Intent();
//        intent.putExtra("liveRoomVO", liveRoomVO);
//        if (liveRoomVO.getUser().getUid() == OllaApplication.getInstance().getAccount().getUid() ||
//                liveRoomVO.getStatus() == 2 || liveRoomVO.getStatus() == 1) {
//            LiveRoomVO roomVO = OllaApplication.getInstance().getLiveRoomVO();
//            if (roomVO != null && !roomVO.getRoomId().equals(liveRoomVO.getRoomId())) {
//                errorDialog = new ErrorDialog(context);
//                errorDialog.setContent("Change room？");
//                errorDialog.show();
//                errorDialog.mNo.setText("Cancel");
//                errorDialog.mOK.setText("Sure");
//                errorDialog.mOK.setOnClickListener(onClickListener);
//            } else {
//                intent.setClass(context, LiveActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                IntentUtils.startActivityBottom(intent, context);
//            }
//
//        } else {
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setClass(context, LiveDetilsActivity.class);
//            IntentUtils.startActivityLeft(intent, context);
//        }
//    }


}
