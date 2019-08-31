package com.jack.lant.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.jack.lant.R;
import com.jack.lant.base.BaseActivity;
import com.jack.lant.ui.Data.BankEntity;
import com.jack.lant.ui.Data.BankUtils;
import com.jack.lant.ui.model.Event.EventMessage;
import com.jack.lant.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MessageActivity extends BaseActivity {

    private TextView mTvStatus;
    private TextView mTvPhone, mTvContent, mTvBankName, mTvBankMoney, mTvBankCode;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_meeage;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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
    public void initView() {
        super.initView();
        setStatusBar();
        mTvStatus = (TextView) findViewById(R.id.tvStatusBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTvStatus.setVisibility(View.VISIBLE);
            initStatusHeight(mTvStatus);
        }
        mTvPhone = findViewById(R.id.msg_phone);
        mTvContent = findViewById(R.id.content);
        mTvBankName = findViewById(R.id.bank_name);
        mTvBankMoney = findViewById(R.id.bank_money);
        mTvBankCode = findViewById(R.id.bank_code);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage event) {
        LogUtils.e(event.address);
        LogUtils.e(event.msg);
        mTvPhone.setText("来源：" + event.address);
        mTvContent.setText(event.msg);
        BankEntity bankEntity = BankUtils.messageBank(event.address, event.msg);
        if (bankEntity != null) {
            mTvBankName.setText("银行名字：" + StringUtils.valueOf(bankEntity.bankName));
            mTvBankMoney.setText("金额：" + StringUtils.valueOf(bankEntity.money));
            mTvBankCode.setText("尾号：" + StringUtils.valueOf(bankEntity.code));
        }
    }

}
