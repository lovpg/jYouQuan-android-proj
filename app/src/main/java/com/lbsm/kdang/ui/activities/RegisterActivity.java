package com.lbsm.kdang.ui.activities;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.lbsm.kdang.R;
import com.lbsm.kdang.base.BaseActivity;
import com.lbsm.kdang.entity.vo.LoginAuthVo;
import com.lbsm.kdang.net.HttpUrl;
import com.lbsm.kdang.net.request.RegisterReq;
import com.lbsm.kdang.utils.DialogUtil;
import com.lbsm.kdang.utils.StringUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zrf on 2016/10/12.
 */

public class RegisterActivity extends BaseActivity
        implements View.OnTouchListener {

    private static final String TAG="RegisterActivity";

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

    private final int SEND_VERIFY_CODE_SUCCESS =1;
    private final int SEND_VERIFY_CODE_FAIL =2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
            }
        });

    }

    @OnClick({R.id.btn_send_verify_code, R.id.btn_register, R.id.tv_register_protocol})
    public void onClick(View view) {
        String mobileNumber=mRegisterAccountEdt.getText().toString();
        String password=mRegisterPasswordEdt.getText().toString();
        String verifyCode=mRegisterVerifyCodeEdt.getText().toString();
        switch (view.getId()) {
            case R.id.btn_send_verify_code:
                sendVerifyCodeReq(mobileNumber);

            case R.id.btn_register:

                try {
                    InputStream is=getAssets().open("avatar_default.png");
                    Log.d(TAG,"assets is: "+is.toString());
                    File file=new File("/sdcard/kdang");
                    if(!file.exists()){
                        file.mkdir();
                    }
                    FileOutputStream fos=new FileOutputStream(new File("/sdcard/kdang/avatar_default.png"));
                    byte[] buffer=new byte[1024];
                    int byteCount=0;
                    while ((byteCount=is.read(buffer))!=-1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                File file=new File("/sdcard/kdang/avatar_default.png");
                checkRegisterParamsAndDoRegisterReq(mobileNumber,password,verifyCode,file);


                break;
            case R.id.tv_register_protocol:
                DialogUtil.showUrlDialog(this, "http://app.lbslm.com/agreement.html");
                break;
        }
    }

    private void checkRegisterParamsAndDoRegisterReq(String mobileNumber, String password, String verifyCode, File file) {

        if(!StringUtil.checkMobile(mobileNumber)){
            Toast.makeText(this,"mobile number can not be null or false",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"password can not be null or false",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(verifyCode)){
            Toast.makeText(this,"verify code can not be null or false",Toast.LENGTH_SHORT).show();
            return;
        }

        mLoadingDialog.show();
        RegisterReq registerReq=new RegisterReq(this,this);
        registerReq.setApiParameters(mobileNumber,password,file.getPath(),verifyCode);
    }


    private void sendVerifyCodeReq(String mobileNumber) {
        if(!StringUtil.checkMobile(mobileNumber)){
            Toast.makeText(this,"mobile number can not be null or false",Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post().url(HttpUrl.SEND_VERIFY_CODE).addParams("mobile",mobileNumber).build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(RegisterActivity.this,"发送失败，请重新点击发送",Toast.LENGTH_SHORT).show();
            }

            @SuppressLint("NewApi")
            @Override
            public void onResponse(Object response, int id) {
                mSendVerifyCodeBtn.setText("已发送");
                mSendVerifyCodeBtn.setTextAppearance(R.style.TextSmallBlackStyle);
                mSendVerifyCodeBtn.setEnabled(false);
                mSendVerifyCodeBtn.setBackground(getResources().getDrawable(R.drawable.btn_gray_selector));

            }
        });
    }

    @Override
    public void onParseSuccess(Object t, int id, Object callBackData) {
        switch (id){
            case RegisterReq.HASH_CODE:
                mLoadingDialog.dismiss();
                LoginAuthVo loginAuth = (LoginAuthVo) t;
                Log.d(TAG,loginAuth.toString());
                getApp().getAccount().saveAccount(loginAuth.getData());
                showShortToast("register success");
                finish();
        }
    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData) {
        mLoadingDialog.dismiss();
        showLongToast(errorInfo);
    }

    @Override
    protected void onDestroy() {
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
