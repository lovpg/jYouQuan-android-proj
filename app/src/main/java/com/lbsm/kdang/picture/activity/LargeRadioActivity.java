package com.lbsm.kdang.picture.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;

import com.frame.dialog.ErrorDialog;
import com.frame.utils.StringUtil;
import com.lbsm.kdang.R;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.picture.IntentUtil;
import com.lbsm.kdang.picture.activity.base.LargeActivity;
import com.lbsm.kdang.picture.adapter.LargeRadioAdapter;
import com.lbsm.kdang.picture.util.PictureShow;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class LargeRadioActivity extends LargeActivity implements View.OnClickListener{

    private List<String> uris;
    private boolean isNetwork;
    private int position;
    private LargeRadioAdapter largeRadioAdapter;
    private ErrorDialog errorDialog;

    public static void startLargeRadioActivity(Context context,Boolean isNet,List<String> list,int position,int code){
        Intent intent = new Intent(context,LargeRadioActivity.class);
        intent.putExtra("network",isNet);
        intent.putStringArrayListExtra("uris", (ArrayList<String>) list);
        intent.putExtra("position",position);
        IntentUtil.startActivityBottom(intent, context, code);
    }

    @Override
    protected void initView() {
        imageBack.setOnClickListener(this);

        uris = getIntent().getStringArrayListExtra("uris");
        uris.remove("add");
        isNetwork = getIntent().getBooleanExtra("network", false);
        position = getIntent().getIntExtra("position",0);
        imageOk.setVisibility(View.GONE);
        if(isNetwork){
            pictureDelete.setVisibility(View.VISIBLE);
            pictureDelete.setOnClickListener(this);
        }else{
            imageSave.setVisibility(View.VISIBLE);
            imageSave.setOnClickListener(this);
        }
        largeRadioAdapter = new LargeRadioAdapter(this);

        largeRadioAdapter.addAll(uris);
        pager.setAdapter(largeRadioAdapter);
        pager.setCurrentItem(position);
        deteltUri(position);
    }



    public void deteltUri(int position){
        if(uris != null && uris.size() > 0){
            this.position = position;
            pictureDelete.setTag(position);
            imgaeCount(position, uris.size());
        }

    }

    @Override
    protected void LargeCount(int position) {
        deteltUri(position);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.picture_delete:
                if(errorDialog == null){
                    errorDialog = new ErrorDialog(LargeRadioActivity.this, R.style.dialog_style);
                    errorDialog.setContent("Do you want to delete this Picture?");
                }
                errorDialog.show();
                errorDialog.mOK.setOnClickListener(this);
                errorDialog.mNo.setText("Cancel");
                errorDialog.mNo.setTextColor(getResources().getColor(android.R.color.black));
                errorDialog.mOK.setTextColor(getResources().getColor(android.R.color.black));

                break;
            case R.id.image_back:
                radioRutent();
                break;
            case R.id.image_save:
                Bitmap bitmap = null;
                String path = uris.get(position);
                if(!StringUtil.isHttp(path)){
                    bitmap = PictureShow.getSmallBitmap(ImageLoader.getInstance().getDiskCache().get(path).getAbsolutePath());
                }else{
                    bitmap = PictureShow.getSmallBitmap(path);
                }
                if(bitmap != null){
                    String str = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "description");
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(str)));
                    showShortToast("Saved OK");
                    KDangApplication.getInstance().localImageHelper.startLocalImage();
                }

                break;
            case R.id.ok:
                errorDialog.dismiss();
                uris.remove(position);
                largeRadioAdapter.addAll(uris);
                if(uris != null && uris.size() > 0){
                    if(uris.size() <= position){
                        position = uris.size()-1;

                    }
                    pager.setAdapter(largeRadioAdapter);
                    pager.setCurrentItem(position);
                    deteltUri(position);
                }else{
                    radioRutent();
                }
                break;
        }

    }

    public void radioRutent(){
        Intent intent = getIntent();
        intent.putStringArrayListExtra("uris", (ArrayList<String>) uris);
        this.setResult(RESULT_OK,intent);
        finishBottom();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            radioRutent();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
