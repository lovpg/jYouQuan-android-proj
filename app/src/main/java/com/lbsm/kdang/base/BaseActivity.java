package com.lbsm.kdang.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.frame.activity.BaseFragmentActivity;
import com.lbsm.kdang.app.KDangApplication;

/**
 * Created by zrf on 2016/10/11.
 */

public class BaseActivity extends BaseFragmentActivity {

    protected KDangApplication getApp() {
        return KDangApplication.getInstance();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 隐藏软件盘输入法
     * @param view
     */
    protected void hideKeyboard(View view) {
        try{
            if(view != null){
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
