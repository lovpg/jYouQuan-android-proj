package com.lbsm.kdang.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;

import com.frame.FrameConstant;
import com.frame.activity.BaseFragmentActivity;
import com.lbsm.kdang.R;
import com.lbsm.kdang.picture.entity.LocalFile;
import com.lbsm.kdang.view.SharePhotoInput;

public class PostActivity extends BaseFragmentActivity{

	private SharePhotoInput photoInput;
	private List<LocalFile> localFiles;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_share);
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		photoInput = (SharePhotoInput) findViewById(R.id.phont_input);
	}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK) return;
        switch (requestCode) {
            case FrameConstant.IMAGE_SELECT:
                    if(data != null){
                        boolean isCamera = data.getBooleanExtra("isCamera",false);
                        if(isCamera){
                            LocalFile localFile = (LocalFile) data.getSerializableExtra("localFile");
                            photoInput.addLocalFile(localFile);
                        }else{
                            localFiles = (List<LocalFile>) data.getSerializableExtra("localFiles");
                            if(localFiles != null && localFiles.size() > 0)
                            photoInput.addAllLocalFile(localFiles);
                        }

                        localFiles = null;
                    }
                break;
            case FrameConstant.IMAGE_DELETE:
                if(data != null){
                    localFiles = new ArrayList<LocalFile>();
                    List<String> list = data.getStringArrayListExtra("uris");
                    for (String str : list){
                        for (LocalFile localFile : photoInput.getLocalFiles()){
                            if(str.equals(localFile.getThumbnailUri()) || str.equals(localFile.getCompressUri())){
                               localFiles.add(localFile);
                                break;
                            }
                        }
                    }
                    photoInput.cleanFiles();
                    photoInput.addAllLocalFile(localFiles);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

	
}
