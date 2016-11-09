package com.lbsm.kdang.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.lbsm.kdang.R;
import com.lbsm.kdang.base.BaseActivity;
import com.lbsm.kdang.entity.vo.LoginAuthVo;
import com.lbsm.kdang.net.request.RegisterReq;
import com.lbsm.kdang.net.request.SendVerifyCodeReq;
import com.lbsm.kdang.utils.Constants;
import com.lbsm.kdang.utils.DialogUtil;
import com.lbsm.kdang.utils.SdcardUtil;
import com.lbsm.kdang.utils.StringUtil;
import com.lbsm.kdang.widget.helper.CountDownButtonHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date: 2016/10/12.
 */

public class RegisterActivity extends BaseActivity
        implements View.OnTouchListener {

    private static final String TAG = "RegisterActivity";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.edt_register_account)
    EditText mRegisterAccountEdt;
    @Bind(R.id.edt_register_password)
    EditText mRegisterPasswordEdt;
    @Bind(R.id.edt_register_verify_code)
    EditText mRegisterVerifyCodeEdt;
    @Bind(R.id.btn_send_verify_code)
    Button mSendVerifyCodeBtn;
    @Bind(R.id.btn_register)
    Button mRegisterBtn;
    @Bind(R.id.tv_register_protocol)
    TextView mRegisterProtocolTv;
    @Bind(R.id.view_register)
    LinearLayout mRegisterView;

    private String mPhoneNumber;
    private String mPassword;
    private String mVerifyCode;
    private CountDownButtonHelper mCountDownButtonHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarUtil.setColor(this,getColor(R.color.red_200),50);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mRegisterView.setOnTouchListener(this);
        mRegisterProtocolTv.setMovementMethod(LinkMovementMethod.getInstance());
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }

    @OnClick({R.id.btn_send_verify_code, R.id.btn_register, R.id.tv_register_protocol})
    public void onClick(View view) {
        hideKeyboard(view);
        mPhoneNumber = mRegisterAccountEdt.getText().toString();
        mPassword = mRegisterPasswordEdt.getText().toString();
        mVerifyCode = mRegisterVerifyCodeEdt.getText().toString();
        switch (view.getId()) {

            case R.id.btn_send_verify_code:
                sendVerifyCodeReq(mPhoneNumber);
                break;

            case R.id.btn_register:
                mCountDownButtonHelper.cancle();
                saveAvatarFileToSdcard();
                File file = new File(SdcardUtil.getRootPath() + "/kdang/icon_avatar_default.pngt.png");
                register(mPhoneNumber, mPassword, mVerifyCode, file);
                break;

            case R.id.tv_register_protocol:
                DialogUtil.showUrlDialog(this, "http://app.lbslm.com/agreement.html");
                break;

            default:
                break;
        }
    }

    private void sendVerifyCodeReq(String mobileNumber) {
        if (mobileNumber == null) {
            showShortToast(getString(R.string.error_phone_number_null));
            return;
        }
        if (!StringUtil.checkMobile(mobileNumber)) {
            showShortToast(getString(R.string.error_phone_number_false));
            return;
        }
        mLoadingDialog.show();
        SendVerifyCodeReq sendVerifyCodeReq = new SendVerifyCodeReq(this, this);
        sendVerifyCodeReq.setApiParameters(mobileNumber);
    }


    private void saveAvatarFileToSdcard() {
        try {
            InputStream is = getAssets().open("avatar_default.png");

            File file = new File(SdcardUtil.getRootPath() + "/kdang");
            if (!file.exists()) {
                file.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(new File(file.getPath() + "/avatar_default.png"));
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void register(String mobileNumber, String password, String verifyCode, File file) {

        if (mobileNumber == null) {
            showShortToast(getString(R.string.error_phone_number_null));
            return;
        }
        if (!StringUtil.checkMobile(mobileNumber)) {
            showShortToast(getString(R.string.error_phone_number_false));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showShortToast(getString(R.string.error_password_null));
            return;
        }
        if (TextUtils.isEmpty(verifyCode)) {
            showShortToast(getString(R.string.error_verify_code_null));
            return;
        }

        mLoadingDialog.show();
        RegisterReq registerReq = new RegisterReq(this, this);
        registerReq.setApiParameters(mobileNumber, password, verifyCode, file.getPath());
    }

    @SuppressLint("NewApi")
    @Override
    public void onParseSuccess(Object t, int id, Object callBackData) {
        mLoadingDialog.dismiss();
        switch (id) {
            case RegisterReq.HASH_CODE:
                LoginAuthVo loginAuthVo = (LoginAuthVo) t;
                Log.d(TAG, loginAuthVo.toString());
                getApp().getAccount().saveLoginAuth(loginAuthVo.getData());
                showShortToast("register success");
                Intent intent = new Intent();
                intent.putExtra(Constants.PHONE_NUMBER, mPhoneNumber);
                intent.putExtra(Constants.PASSWORD, mPassword);
                setResult(Constants.LOGIN_TO_REGISTER_FORRESULT_CODE, intent);
                finish();
                break;

            case SendVerifyCodeReq.HASH_CODE:
                Log.d(TAG, "verify code success");
                mCountDownButtonHelper = new CountDownButtonHelper(mSendVerifyCodeBtn, this, 60, 1);
                mCountDownButtonHelper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
                    @Override
                    public void finish() {
                    }
                });
                mCountDownButtonHelper.start();
                break;

            default:
                break;
        }
    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData) {
        mLoadingDialog.dismiss();
        showLongToast(errorInfo);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        hideKeyboard(mRegisterAccountEdt);
        hideKeyboard(mRegisterPasswordEdt);
        hideKeyboard(mSendVerifyCodeBtn);
        return false;
    }
}
