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


import com.frame.R;
import com.frame.dialog.LoadingDialog;
import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.frame.util.ActivityContext;
import com.frame.util.StringUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Use:
 * Created by yinglovezhuzhu@gmail.com on 2014-06-06.
 */
@SuppressLint("NewApi")
public class BaseFragment extends Fragment implements OnFailSessionObserver2,OnParseObserver2<Object> {

    protected final String TAG = this.getClass().getSimpleName();
    /**异步加载图片**/
	protected LoadingDialog mLoadingDialog;
	public View contextView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
		 super.onActivityCreated(savedInstanceState);
        ActivityContext.put(this);
  		if(mLoadingDialog == null)mLoadingDialog = new LoadingDialog(getActivity());
    }


    @Override
    public void onDestroy() {
    	ActivityContext.remove(this);
        super.onDestroy();
//        LogUtil.e(TAG, TAG + "==>>onDestroy()");
    }


//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        LogUtil.e(TAG, TAG + "==>>onViewCreated()");
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        LogUtil.e(TAG, TAG + "==>>onDestroyView()");
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        LogUtil.e(TAG, TAG + "==>>onAttach()=>>>" + activity.getClass().getSimpleName());
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        LogUtil.e(TAG, TAG + "==>>onDetach()");
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        LogUtil.e(TAG, TAG + "==>>onSaveInstanceState()");
//    }

    public void startActivityLeft(Intent intent) {
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void startActivityRight(Intent intent) {
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    protected void startActivityTop(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_top_in, R.anim.push_top_out);
    }

    public void showShortTost(String msg) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void showShortTost(int resId) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
        }
    }

    public void showLongToast(String msg) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public void showLongToast(int resId) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), resId, Toast.LENGTH_LONG).show();
        }
    }

	@Override
	public void onParseSuccess(Object t, int id, Object callBackData) {
	}

	@Override
	public void onFailSession(String errorInfo, int failCode, int id,
			Object callBackData) {
		if(mLoadingDialog != null)mLoadingDialog.dismiss();
        if (StringUtils.isEmpty(errorInfo))showLongToast(errorInfo);
	}
	

    /**
     * 隐藏软件盘输入法
     * @param view
     */
    protected void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
