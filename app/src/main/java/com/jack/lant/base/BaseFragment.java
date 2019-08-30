package com.jack.lant.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.BarUtils;
import com.jack.lant.ui.view.AbstractStatus;
import com.jack.lant.ui.view.StatusLayout;

import java.util.HashMap;

/**
 * Copyright (c) 2016,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * <p/>
 * 文件名称：BaseFragment<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：jack<br>
 * 完成日期：2016/11/11<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：jack<br>
 * 完成日期：2016/11/11<br>
 */

public abstract class BaseFragment extends Fragment {
    protected View mView;
//    protected Context mContext;
    protected BaseActivity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mContext = context;
        mContext = (BaseActivity) context;
    }

    /** 状态 */
    private HashMap<View, StatusLayout> mStatusMap = new HashMap<>();

    public void showStatus(View originView, AbstractStatus status) {
        StatusLayout statusLayout = mStatusMap.get(originView);
        if (statusLayout == null) {
            statusLayout = new StatusLayout(getActivity(), originView);
            mStatusMap.put(originView, statusLayout);
        }
        statusLayout.showStatus(status);
    }

    public void hideStatus(View originView) {
        StatusLayout statusLayout = mStatusMap.get(originView);
        if (statusLayout != null) {
            statusLayout.hideStatus();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        }
        if (mView == null) {
            mView = inflater.inflate(getLayoutResource(), container, false);
        }

//        hud = KProgressHUD.create(mContext)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("数据加载中...");

        initView();
        initData();
        initListener();

        return mView;
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    /** 统一设置状态栏 高度 */
    public void initStatusHeight(TextView view) {
        try {
            ViewGroup.LayoutParams linearParams = view.getLayoutParams();
            linearParams.height = BarUtils.getStatusBarHeight();
            linearParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            view.setLayoutParams(linearParams);
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }

    protected abstract int getLayoutResource();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
