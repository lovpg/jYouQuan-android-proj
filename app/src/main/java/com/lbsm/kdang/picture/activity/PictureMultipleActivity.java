package com.lbsm.kdang.picture.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.frame.FrameApplication;
import com.frame.FrameConstant;
import com.frame.activity.BaseFragmentActivity;
import com.lbsm.kdang.KdangApplication;
import com.lbsm.kdang.picture.IntentUtil;
import com.lbsm.kdang.picture.activity.base.LargeActivity;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.util.PictureShow;
import com.lbsm.kdang.picture.view.MultipleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public class PictureMultipleActivity extends BaseFragmentActivity{

    private MultipleView multipleView;
    private int number = 9;
    public static void startPictureActivity(Context context,int number,int code){
        IntentUtil.startActivityBottom(new Intent(context, PictureMultipleActivity.class).putExtra("number", number), context, code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        multipleView = new MultipleView(this);
        setContentView(multipleView);
        number = getIntent().getIntExtra("number",9);
        multipleView.setNumber(number);

    }

    public void init(){

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        KdangApplication.getInstance().localImageHelper.isLocalFiles(KdangApplication.getInstance().getConfig().getAlumbFloder());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK)return;
        List<LocalFile> localFiles = null;
        switch (requestCode){
            case LargeActivity.LARGE:
                localFiles = (List<LocalFile>) data.getSerializableExtra("localFiles");
                if(localFiles != null && localFiles.size() > 0){
                    multipleView.addAll(localFiles);
                }
                break;
            case LargeActivity.LARGE_PREVIEW:
                localFiles = (List<LocalFile>) data.getSerializableExtra("localFiles");
                List<LocalFile> list = new ArrayList<LocalFile>();
                for (LocalFile localFile : localFiles){
                    if(localFile.isSelect())list.add(localFile);
                }
                multipleView.addPreview(list);
                break;
            case FrameConstant.IMAGE_CAPTURE:
                if(data != null){
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    String str = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(str)));
                    LocalFile localFile = PictureShow.saveCapture(PictureMultipleActivity.this, str);
                    multipleView.setPictureCamera(localFile);
                    KdangApplication.getInstance().localImageHelper.startLocalImage();
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
