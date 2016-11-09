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

import com.jaeger.library.StatusBarUtil;
import com.lbsm.kdang.R;
import com.lbsm.kdang.base.BaseActivity;
import com.lbsm.kdang.entity.vo.LoginAuthVo;
import com.lbsm.kdang.net.request.LoginReq;
import com.lbsm.kdang.utils.Constants;
import com.lbsm.kdang.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date: 2016/10/11.
 */

public class LoginActivity extends BaseActivity implements View.OnTouchListener {

    private static final String TAG = "LoginActivity";

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
    private String mPhoneNumber;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this,getColor(R.color.red_200),50);
        ButterKnife.bind(this);
        mLoginView.setOnTouchListener(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        hideKeyboard(view);
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, Constants.LOGIN_TO_REGISTER_FORRESULT_CODE);
                break;
        }
    }

    private void login() {
        mPhoneNumber = mAccountEdt.getText().toString();
        mPassword = mPasswordEdt.getText().toString();
        if (mPhoneNumber == null) {
            showShortToast(getString(R.string.error_phone_number_null));
            return;
        }
        if (!StringUtil.checkMobile(mPhoneNumber)) {
            showShortToast(getString(R.string.error_phone_number_false));
            return;
        }
        if (TextUtils.isEmpty(mPassword)) {
            showShortToast(getString(R.string.error_password_null));
            return;
        }

        mLoadingDialog.show();
        LoginReq loginReq = new LoginReq(LoginActivity.this, LoginActivity.this);
        loginReq.setApiParameters(mPhoneNumber, mPassword);

    }

    @Override
    public void onParseSuccess(Object t, int id, Object callBackData) {
        mLoadingDialog.dismiss();
        switch (id) {
            case LoginReq.HASH_CODE:
                LoginAuthVo loginAuthVo = (LoginAuthVo) t;
                if (loginAuthVo != null) {
                    getApp().getAccount().saveLoginAuth(loginAuthVo.getData());
                    getApp().getAccount().setPassword(mPassword);
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData) {
        mLoadingDialog.dismiss();
        showShortToast(errorInfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.LOGIN_TO_REGISTER_FORRESULT_CODE && data != null) {
            String phoneNumber = data.getStringExtra(Constants.PHONE_NUMBER);
            String password = data.getStringExtra(Constants.PASSWORD);
            if (phoneNumber != null && password != null) {
                mAccountEdt.setText(phoneNumber);
                mPasswordEdt.setText(password);
            }

        }
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
