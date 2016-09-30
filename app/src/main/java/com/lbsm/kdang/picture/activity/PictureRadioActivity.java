package com.lbsm.kdang.picture.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import com.frame.FrameApplication;
import com.frame.FrameConstant;
import com.frame.activity.BaseFragmentActivity;
import com.frame.util.FileUtil;
import com.lbsm.kdang.KdangApplication;
import com.lbsm.kdang.picture.IntentUtil;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.util.PictureShow;
import com.lbsm.kdang.picture.view.RadioView;

/**
 * Created by Administrator on 2015/12/2.
 */
public class PictureRadioActivity extends BaseFragmentActivity{

    public static void startPictureRadioActivity(Context context){
        IntentUtil.startActivityBottom(new Intent(context, PictureRadioActivity.class), context, FrameConstant.IMAGE_RADIO);
    }
    RadioView radioView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        radioView = new RadioView(this);
        setContentView(radioView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FrameConstant.IMAGE_CUT:
                if (resultCode == RESULT_OK) {
                    Bitmap bm = data.getParcelableExtra("data");
                    if (bm != null && !bm.isRecycled()) {
                        if (radioView.mCutFile == null || TextUtils.isEmpty(radioView.mCutFile.getPath()) || !radioView.mCutFile.exists() ) {
                            radioView.mCutFile = radioView.getCutPath();
                            FileUtil.saveBitmap( radioView.mCutFile, bm);
                        }
                    }
                    bm = null;
                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();
                    bundle.putString("cutPath",radioView.mCutFile.getAbsolutePath());
                    intent.putExtras(bundle);
                    this.setResult(RESULT_OK,intent);
                }
                finishBottom();
                break;
            case FrameConstant.IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    if(data != null){
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        String str = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(str)));
                        LocalFile localFile = PictureShow.saveCapture(PictureRadioActivity.this, str);
                        radioView.setPictureCamera(localFile);
                        radioView.pictureOk.setVisibility(View.VISIBLE);
                        KdangApplication.getInstance().localImageHelper.startLocalImage();
                    }
                }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
