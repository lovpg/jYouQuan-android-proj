package com.lbsm.kdang.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbsm.kdang.R;
import com.lbsm.kdang.base.BaseFragment;

/**
 * date: 2016/10/20.
 */

public class MeFragm extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragm_me,container,false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
