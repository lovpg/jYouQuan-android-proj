package com.frame.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.frame.R;
import com.frame.utils.DensityUtil;


/**
 * Created by LDM on 2014/8/11. Email : nightkid-s@163.com
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        this(context, R.style.dialog_style);
    }

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtil.dip2px(context, "60dp"), DensityUtil.dip2px(context, "60dp"));
        ProgressBar bar = new ProgressBar(context);
        Drawable d = context.getResources().getDrawable(+R.anim.anim_loading);
        if (d != null) d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        bar.setIndeterminateDrawable(d);
        getWindow().setContentView(bar, params);

        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        try {
            super.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
