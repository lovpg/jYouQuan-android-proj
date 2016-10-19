package com.lbsm.kdang.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lbsm.kdang.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/11.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.img_welcome)
    ImageView mWelcomeImg;

    private boolean mIsLogin = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!mIsLogin) {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();

            }
        }, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
