package com.jack.lant.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jack.lant.R;
import com.jack.lant.base.AppManager;
import com.jack.lant.base.BaseActivity;
import com.jack.lant.ui.view.PhoneExtendEditText;
import com.jack.lant.ui.view.dialog.IosPopupPhoneDialog;
import com.jack.lant.utils.SoftKeyBoardListener;
import com.jack.lant.utils.StringUtils;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvStatus, mTvSave, mTvLoginOut;
    private PhoneExtendEditText mEtCode, mEtName, mEtPhone;

    private long WAIT_TIME = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        super.initView();
        setStatusBar();
        mTvStatus = (TextView) findViewById(R.id.tvStatusBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTvStatus.setVisibility(View.VISIBLE);
            initStatusHeight(mTvStatus);
        }

    }

    /** 状态栏*/
    private void setStatusBar() {
        //6.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void initListener() {
        super.initListener();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long time = System.currentTimeMillis();
        if (time - WAIT_TIME > 2000) {
            ToastUtils.showShort("再按一次退出");
            WAIT_TIME = time;
            return true;
        } else {
            finish();
            try {
                ActivityUtils.finishAllActivities();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
