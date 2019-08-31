package com.jack.lant.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/** 局域网测试 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvStatus;
    private long WAIT_TIME = 0;

    private int type = 0; // 0==服务器  1==客户端

    private String serverIp = "";

    @SuppressLint("HandlerLeak")
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                //client_content.append("client" + msg.obj + "\n");
                LogUtils.e("client" + msg.obj + "\n");
            }
        }
    };


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

    @Override
    public void initData() {
        super.initData();
        if (getIntent() != null)
            type = getIntent().getIntExtra("type", 0);

    }

    /** 服务器端 */
    private void initServices() {
        serverIp = StringUtils.getlocalip(mContext);
        //ip.setText("IP addresss:" + serverIp);
        LogUtils.d("dd","ddddddddddd");
        new Thread() {
            public void run() {
                OutputStream output;
                String serverContent = "hello hehe";
                try {
                    ServerSocket serverSocket = new ServerSocket(30000);
                    while (true) {
                        Message msg = new Message();
                        msg.what = 1;
                        try {
                            Socket socket = serverSocket.accept();
                            //向client发送消息
                            output = socket.getOutputStream();
                            output.write(serverContent.getBytes("utf-8"));
                            output.flush();
                            socket.shutdownOutput();
                            //获取输入信息
                            BufferedReader bff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            //读取信息
                            String result = "";
                            String buffer = "";
                            while ((buffer = bff.readLine()) != null) {
                                result = result + buffer;
                            }
                            msg.obj = result.toString();
                            mHandler.sendMessage(msg);
                            bff.close();
                            output.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }.start();
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
