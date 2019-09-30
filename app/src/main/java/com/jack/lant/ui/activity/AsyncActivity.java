package com.jack.lant.ui.activity;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jack.lant.R;
import com.jack.lant.base.BaseActivity;
import com.jack.lant.utils.WifiUtils;

public class AsyncActivity extends BaseActivity {

    private TextView mTvAddress;
    RecyclerView mAppList;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_async;
    }

    @Override
    public void initView() {
        super.initView();
        mTvAddress = findViewById(R.id.async_txt);
        mAppList = findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = findViewById(R.id.content_main);
    }

    @Override
    public void initData() {
        super.initData();
        ///if (WifiUtils.getWifiState(mContext))
        mTvAddress.setText("服务器地址：" + WifiUtils.getWifiConnectIp(mContext));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


 }
