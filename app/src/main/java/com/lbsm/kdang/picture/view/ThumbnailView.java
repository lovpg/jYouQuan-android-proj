package com.lbsm.kdang.picture.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.lbsm.kdang.KdangApplication;
import com.lbsm.kdang.R;
import com.lbsm.kdang.picture.activity.LargeMultipleActivity;
import com.lbsm.kdang.picture.activity.base.LargeActivity;
import com.lbsm.kdang.picture.adapter.AlumbAdapter;
import com.lbsm.kdang.picture.adapter.PictureAdapter;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.picture.util.PictureShow;

/**
 * Created by Administrator on 2015/11/27.
 */
@SuppressLint("NewApi") 
public abstract class ThumbnailView extends LinearLayout implements View.OnClickListener{

    public Button pictureOk;
    protected Button selectAlumb;
    protected Button preview;
    protected GridView gridview;
    protected ImageView pictureBack;
    protected ImageView pictureCamera;

    protected View alumbView;
    protected ListView alumbList;

    protected List<LocalFile> localFiles;
    protected List<String> folders;

    protected PictureAdapter multipleAdapter;

    protected AlumbAdapter alumbAdapter;
    protected  TranslateAnimation showAction;
    protected  TranslateAnimation outAction;
    protected String folder;
    protected KdangApplication application;

    protected int number;


    public ThumbnailView(Context context) {
        super(context);

    }

    public ThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

   public ThumbnailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_picture,this);
        pictureOk = (Button) findViewById(R.id.piture_ok);
        selectAlumb = (Button) findViewById(R.id.select_alumb);
        preview = (Button) findViewById(R.id.preview);
        gridview = (GridView) findViewById(R.id.gridview);
        pictureBack = (ImageView) findViewById(R.id.piture_back);
        pictureCamera = (ImageView) findViewById(R.id.picture_camera);

        alumbView = findViewById(R.id.picture_alumb);
        alumbList = (ListView) findViewById(R.id.alumb_list);

        pictureBack.setOnClickListener(this);
        selectAlumb.setOnClickListener(this);
        alumbView.setOnClickListener(this);
        preview.setOnClickListener(this);
        pictureOk.setOnClickListener(this);

        alumbAdapter = new AlumbAdapter(getContext());
        gridview.setAdapter(multipleAdapter);
        alumbList.setAdapter(alumbAdapter);
        alumbView.setOnClickListener(this);
        application = KdangApplication.getInstance();


        alumbList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                folder = folders.get(position);
                application.getConfig().setAlumbFloder(folder);
                alumbSelect();
                alumbGone();
                setPictureList();
            }
        });

        localFiles = new ArrayList<LocalFile>();
        showAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        outAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        showAction.setDuration(500);
        outAction.setDuration(500);
        setPictureList();
        setAlumbList();
    }

    public void setPictureList(){
        folder = application.getConfig().getAlumbFloder();
        List<LocalFile> localFiles = application.localImageHelper.getLocalFiles(folder);
        if("All picturres".equals(folder) && !"camera".equals(localFiles.get(0).getThumbnailUri())){
            LocalFile localFile = new LocalFile();
            localFile.setThumbnailUri("camera");
            localFiles.add(0,localFile);
        }
        if(localFiles != null)multipleAdapter.addAll(localFiles);
    }

    public void setAlumbList(){
        folders = application.localImageHelper.folderNames;
        alumbAdapter.addAll(folders);
        alumbSelect();
    }

    public void alumbSelect(){
        alumbAdapter.selectAlumb(folder);
        selectAlumb.setText(folder);
    }

    public void addAll(List<LocalFile> localFiles){
        multipleAdapter.addAll(localFiles);
        selectPicture(null);
    }

    public void addPreview(List<LocalFile> localFiles){
        for (LocalFile localFile : multipleAdapter.getLocalFiles()){
            localFile.setIsSelect(false);
            localFile.setTime(0);
            for (LocalFile local : localFiles){
                if(localFile.getThumbnailUri().equals(local.getThumbnailUri())){
                    localFile.setIsSelect(true);
                    localFile.setTime(local.getTime());
                }
            }
        }
        multipleAdapter.notifyDataSetChanged();
        selectPicture(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.piture_back:
                pitureOnClickBack();
                break;
            case R.id.select_alumb:
                if(alumbView.getVisibility() == View.GONE){
                    alumbView.startAnimation(showAction);
                    alumbView.setVisibility(View.VISIBLE);
                }else{
                    alumbGone();
                }
                break;
            case R.id.picture_alumb:
                alumbGone();
                break;
            case R.id.preview:
                if(localFiles != null && localFiles.size() > 0)
                    LargeMultipleActivity.StartMultipActivity(getContext(), localFiles, 0, number, LargeActivity.LARGE_PREVIEW);
                break;
            case R.id.piture_ok:
                pitureOnClickOk();

                break;
        }
    }

    public abstract void pitureOnClickOk();
    public abstract void pitureOnClickBack();

    public void alumbGone(){
        alumbView.startAnimation(outAction);
        alumbView.setVisibility(View.GONE);
    }



    public void selectPicture(LocalFile local){
        int size = localFiles.size();
        localFiles.clear();
        for (LocalFile localFile : multipleAdapter.getLocalFiles()){
            if(localFile.isSelect()){
                localFiles.add(localFile);
                if(size >= number && local != null &&  local.getThumbnailUri().equals(localFile.getThumbnailUri())){
                    localFiles.remove(local);
                    localFile.setTime(0);
                    localFile.setIsSelect(false);
                }
            }
        }
        size = localFiles.size();
        pictureOk.setText(size > 0 ? "OK("+size+"/"+number+")" : "OK");
        preview.setText(size > 0 ? "Preview("+size+")" : "Preview");
    }

    protected LocalFile camereLocale;
    public void setPictureCamera(LocalFile localFile){
        this.camereLocale = localFile;
        pictureCamera.setVisibility(View.VISIBLE);
        PictureShow.displayImage(pictureCamera, camereLocale.getThumbnailUri());
    }
}
