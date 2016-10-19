package com.lbsm.kdang.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.frame.activity.BaseFragmentActivity;
import com.lbsm.kdang.R;
import com.lbsm.kdang.net.request.LoginReq;
import com.lbsm.kdang.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zrf on 2016/10/11.
 */

public class LoginActivity extends BaseFragmentActivity implements View.OnTouchListener {

    @Bind(R.id.edt_login_account)
    EditText mAccountEdt;
    @Bind(R.id.edt_login_password)
    EditText mPasswordEdt;
    @Bind(R.id.view_login)
    LinearLayout mLoginView;
    @Bind(R.id.btn_login)
    Button mLoginBtn;
    @Bind(R.id.btn_register)
    Button mRegisterBtn;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private boolean mIsLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginView.setOnTouchListener(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        String accountNumber=mAccountEdt.getText().toString();
        String password=mPasswordEdt.getText().toString();
        if(!StringUtil.checkMobile(accountNumber)){
            showShortToast("phone nummber can not be null or false");
            return;
        }
        if(TextUtils.isEmpty(password)){
            showShortToast("password can not be null");
            return;
        }

        LoginReq loginReq = new LoginReq(LoginActivity.this, LoginActivity.this, LoginActivity.this);
        loginReq.setApiParameters(accountNumber, password);

    }

    @Override
    public void onParseSuccess(Object t, int id, Object callBackData) {
    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        hideKeyboard(mAccountEdt);
        hideKeyboard(mPasswordEdt);
        return false;
    }

}
