/*
 * Copyright (C) 14-6-6 下午3:42 Guangzhou Visions Tech Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frame.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.R;
import com.frame.dialog.LoadingDialog;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.frame.utils.ActivityContext;
import com.frame.utils.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;


/**
 * Use:
 * Created by yinglovezhuzhu@gmail.com on 2014-06-06.
 */
public class BaseFragmentActivity extends AppCompatActivity implements OnFailSessionObserver2, OnParseObserver2<Object> {

    protected String TAG = this.getClass().getSimpleName();
    protected LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContext.put(this);
        if (mLoadingDialog == null)
            mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityContext.remove(this);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        //    	LogUtil.d(TAG, this.getClass().getSimpleName() + " onCreateView() invoked!!");
        return super.onCreateView(name, context, attrs);
    }

    protected void showLongToast(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }


    protected CharSequence getText(TextView tv) {
        return tv.getText();
    }

    /**
     * 通过反射来设置对话框是否要关闭，在表单校验时很管用， 因为在用户填写出错时点确定时默认Dialog会消失， 所以达不到校验的效果
     * 而mShowing字段就是用来控制是否要消失的，而它在Dialog中是私有变量， 所有只有通过反射去解决此问题
     *
     * @param pDialog
     * @param pIsClose
     */
    public void setAlertDialogIsClose(DialogInterface pDialog, Boolean pIsClose) {
        try {
            Field field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(pDialog, pIsClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软件盘输入法
     *
     * @param view
     */
    protected void hideKeyboard(View view) {
        try {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 带动画的跳转
     *
     * @param intent
     */
    public void startActivityLeft(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    protected void startActivityRight(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    protected void startActivityForResultRight(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void startActivityForResultLeft(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    protected void startActivityForResultTop(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_top_in, R.anim.not_change);
    }

    protected void startActivityTop(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.push_top_in, R.anim.push_top_out);
    }

    protected void finishRight() {
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    protected void finishLeft() {
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    protected void finishBottom() {
        finish();
        overridePendingTransition(R.anim.not_change, R.anim.push_bottom_out);
    }

    protected void defaultFinish() {
        super.finish();
    }

    public boolean hasExtra(String key) {
        return getIntent().hasExtra(key);
    }

    /**
     * 判断list是否为空
     *
     * @param data
     * @return true为空，false不为空
     */
    protected boolean isListEmpty(List<?> data) {
        return (data == null) || (data.size() == 0);
    }

    /**
     * 添加点击事件
     *
     * @param view              需要添加点击事件的view
     * @param callbackFunctioin 点击后的回调函数，回调函数有且只有一个参数，参数类型为View
     */
    protected void addOnClickMethodToView(final View view, final String callbackFunctioin) {
        final Class<?> cls = getClass();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFinishing()) {
                    return;
                }
                try {
                    Method method = cls.getDeclaredMethod(callbackFunctioin, View.class);
                    method.setAccessible(true);
                    method.invoke(BaseFragmentActivity.this, v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id,
                              Object callBackData) {
        // TODO Auto-generated method stub
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
        if (StringUtil.isEmpty(errorInfo))
            showLongToast(errorInfo);
    }

    @Override
    public void onParseSuccess(Object t, int id, Object callBackData) {
    }

}
