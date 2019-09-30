package com.jack.lant.ui.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
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

//        sms主要结构：
//        　　_id：          短信序号，如100
//        　　thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
//        　　address：  发件人地址，即手机号，如+86138138000
//        　　person：   发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
//        　　date：       日期，long型，如1346988516，可以对日期显示格式进行设置
//        　　protocol： 协议0SMS_RPOTO短信，1MMS_PROTO彩信
//        　　read：      是否阅读0未读，1已读
//        　　status：    短信状态-1接收，0complete,64pending,128failed
//        　　type：       短信类型1是接收到的，2是已发出
//        　　body：      短信具体内容
//        　　service_center：短信服务中心号码编号，如+8613800755500
//
//        既然需要操作数据库，便少不了使用ContentResolver，所以我们应该还需要了解，短信的content uri :
//        全部短信：content://sms/
//        收件箱：content://sms/inbox
//        发件箱：content://sms/sent
//        草稿箱：content://sms/draft


public class Message2Activity extends BaseActivity {

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


//        //LogUtils.e(event.address);
//        //LogUtils.e(event.msg);
//       // mTvPhone.setText("来源：" + event.address);
//        //mTvContent.setText(event.msg);
//        BankEntity bankEntity = BankUtils.messageBank("106980096319", "您尾数为2916的账号于9月4日转入0903顺昌县迅达手机店0.01元,余额78.69元【厦门银行】");
//        if (bankEntity != null) {
//            mTvBankName.setText("银行名字：" + StringUtils.valueOf(bankEntity.bankName));
//            mTvBankMoney.setText("金额：" + StringUtils.valueOf(bankEntity.money));
//            mTvBankCode.setText("尾号：" + StringUtils.valueOf(bankEntity.code));
//        }
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage event) {
        LogUtils.e(event.address);
        LogUtils.e(event.msg);

        getSmsFromPhone();
    }

    private Uri SMS_INBOX = Uri.parse("content://sms/inbox");
    public void getSmsFromPhone() {
        ContentResolver cr = getContentResolver();
        String[] projection = new String[] { "body","address" };//"_id", "address", "person",, "date", "type
        String where = " date >  "
                + (System.currentTimeMillis() - 10 * 60 * 1000);
        Cursor cur = cr.query(SMS_INBOX, projection, where, null, "date desc");
        if (null == cur)
            return;
        if (cur.moveToFirst()) {
            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String msg = cur.getString(cur.getColumnIndex("body"));

            mTvPhone.setText("来源：" + number);
            mTvContent.setText(msg);
            //TODO 这里是具体处理逻辑
            BankEntity bankEntity = BankUtils.messageBank(number, msg);
            if (bankEntity != null) {
                mTvBankName.setText("银行名字：" + StringUtils.valueOf(bankEntity.bankName));
                mTvBankMoney.setText("金额：" + StringUtils.valueOf(bankEntity.money));
                mTvBankCode.setText("尾号：" + StringUtils.valueOf(bankEntity.code));
            }
        }
    }

}
