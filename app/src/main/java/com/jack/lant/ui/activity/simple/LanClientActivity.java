package com.jack.lant.ui.activity.simple;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jack.lant.R;
import com.jack.lant.base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/** 局域网测试--客户端 */
public class LanClientActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvStatus, mTvSendMsg, mTviPt;
    private long WAIT_TIME = 0;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_lan_client;
    }

    @Override
    public void initView() {
        super.initView();
        setStatusBar();
        mTvStatus = (TextView) findViewById(R.id.tvStatusBar);
        mTvSendMsg = findViewById(R.id.client_send_msg);
        mTviPt = findViewById(R.id.service_address);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTvStatus.setVisibility(View.VISIBLE);
            initStatusHeight(mTvStatus);
        }
    }

    @Override
    public void initData() {
        super.initData();
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
        mTvSendMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.client_send_msg:
                new MyThread("aaaaaaa").start();
                break;
        }
    }

    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //result.append("server:" + msg.obj + "\n");
                mTviPt.setText(msg.obj + "\n");
            }
        }

    };



    class MyThread extends Thread {

        public String content;

        public MyThread(String str) {
            content = str;
        }

        @Override
        public void run() {
            //定义消息
            Message msg = new Message();
            msg.what = 1;
            try {
                //连接服务器 并设置连接超时为5秒
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.31.171", 30000), 1000);

                //获取输入输出流
                OutputStream ou = socket.getOutputStream();
                //获取输出输出流
                BufferedReader bff = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                //向服务器发送信息
                ou.write(content.getBytes("utf-8"));
                ou.flush();

                //读取发来服务器信息
                String result = "";
                String buffer = "";
                while ((buffer = bff.readLine()) != null) {
                    result = result + buffer;
                }
                msg.obj = result.toString();
                //发送消息 修改UI线程中的组件
                myHandler.sendMessage(msg);
                //关闭各种输入输出流
                bff.close();
                ou.close();
                socket.close();
            } catch (SocketTimeoutException aa) {
                //连接超时 在UI界面显示消息
                msg.obj =  "服务器连接失败！请检查网络是否打开";
                //发送消息 修改UI线程中的组件
                myHandler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        long time = System.currentTimeMillis();
//        if (time - WAIT_TIME > 2000) {
//            ToastUtils.showShort("再按一次退出");
//            WAIT_TIME = time;
//            return true;
//        } else {
//            finish();
//            try {
//                ActivityUtils.finishAllActivities();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
