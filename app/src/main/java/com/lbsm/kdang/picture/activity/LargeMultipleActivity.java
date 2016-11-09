package com.lbsm.kdang.picture.activity;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lbsm.kdang.R;
import com.lbsm.kdang.picture.IntentUtil;
import com.lbsm.kdang.picture.activity.base.LargeActivity;
import com.lbsm.kdang.picture.adapter.LargeMultipleAdapter;
import com.lbsm.kdang.picture.entity.LocalFile;

/**
 * Created by Administrator on 2015/11/30.
 */
public class LargeMultipleActivity extends LargeActivity implements View.OnClickListener{

    private List<LocalFile> localFiles = null;
    private int position;
    private LargeMultipleAdapter largeMultipleAdapter;
    private int number;

    private List<LocalFile> selcecLocalFiles;

    public static void StartMultipActivity(Context context,List<LocalFile> localFiles,int posttion,int number,int code){
        Bundle bundle = new Bundle();
        bundle.putSerializable("localFiles", (Serializable) localFiles);
        Intent intent = new Intent(context,LargeMultipleActivity.class);
        intent.putExtra("posttion", posttion);
        intent.putExtra("number", number);
        intent.putExtras(bundle);
        IntentUtil.startActivityBottom(intent, context, code);
    }

    @Override
    protected void initView() {
        checkBox.setVisibility(View.VISIBLE);
        checkBox.setOnClickListener(this);
        imageOk.setOnClickListener(this);
        imageBack.setOnClickListener(this);
        localFiles = (List<LocalFile>) getIntent().getSerializableExtra("localFiles");
        position = getIntent().getIntExtra("posttion", 0);
        number = getIntent().getIntExtra("number",0);

        largeMultipleAdapter = new LargeMultipleAdapter(this);
        pager.setAdapter(largeMultipleAdapter);

        largeMultipleAdapter.addAll(localFiles);
        pager.setCurrentItem(position);
        checkSelect();
        LargeCount(position);

    }

    @Override
    protected void LargeCount(int position) {
        LocalFile localFile = localFiles.get(position);
        checkBox.setChecked(localFile.isSelect());
        checkBox.setTag(localFile);
        imgaeCount(position,localFiles.size());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_check:
                LocalFile localFile = (LocalFile) v.getTag();
                if (localFile.isSelect()) {
                    localFile.setTime(0);
                    localFile.setIsSelect(false);
                } else {
                    localFile.setTime(System.currentTimeMillis());
                    if (count < number){
                        localFile.setIsSelect(true);
                        checkBox.setChecked(true);
                    }else checkBox.setChecked(false);

                }
                checkSelect();
                break;
            case R.id.image_ok:
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("localFiles", (Serializable) largeMultipleAdapter.getLocalFiles());
                intent.putExtras(bundle);
                this.setResult(RESULT_OK, intent);
                finishBottom();
                break;
            case R.id.image_back:
                finishBottom();
                break;
        }
    }

    public int count;
    public void checkSelect(){
        count = 0;
        for (LocalFile localFile : largeMultipleAdapter.getLocalFiles()){
            if(localFile.isSelect())count++;
        }
        imageOk.setText(count > 0 ? "OK(" + count + "/"+number+")" : "OK");
        if(largeMultipleAdapter != null)largeMultipleAdapter.notifyDataSetChanged();
    }


}
