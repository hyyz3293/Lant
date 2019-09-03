package com.jack.lant.ui.activity.simple;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jack.lant.R;
import com.jack.lant.base.BaseActivity;
import com.jack.lant.ui.adapter.LanServiceAdapter;
import com.jack.lant.ui.model.LanEntity;
import com.jack.lant.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/** 局域网测试 */
public class LanServiceActivity extends BaseActivity implements View.OnClickListener {

    private long WAIT_TIME = 0;
    private String serverIp = "";


    private TextView mTvStatus;
    private TextView mTvIpAddress, mTvSendMsg;
    private EditText mEtSend;

    private RecyclerView mSRecycle;
    private static LanServiceAdapter mAdapter;
    private static List<LanEntity> mList;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_lan_service;
    }

    @Override
    public void initView() {
        super.initView();
        setStatusBar();
        mTvStatus = (TextView) findViewById(R.id.tvStatusBar);
        mTvIpAddress = findViewById(R.id.service_address);
        mTvSendMsg = findViewById(R.id.send_msg);
        mEtSend = findViewById(R.id.et_message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTvStatus.setVisibility(View.VISIBLE);
            initStatusHeight(mTvStatus);
        }
        mSRecycle = findViewById(R.id.service_recycle);
    }

    @Override
    public void initData() {
        super.initData();
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LanEntity lanEntity = new LanEntity();
            if (i / 2 == 0)
                lanEntity.receiveType = 1;
            else
                lanEntity.receiveType = 0;
            lanEntity.viewType = 1;

            lanEntity.content = i + "··········";
            lanEntity.time = TimeUtils.getNowString();
            mList.add(lanEntity);
        }
        mAdapter = new LanServiceAdapter(mList);
        mSRecycle.setLayoutManager(new LinearLayoutManager(mContext));
        mSRecycle.setAdapter(mAdapter);
        mAdapter.setNewData(mList);

        initServices();
    }


    /** 状态栏*/
    private void setStatusBar() {
        //6.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        ///getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void initListener() {
        super.initListener();
        mTvSendMsg.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_msg:

                break;

        }
    }



    /** 服务器端 */
    @SuppressLint("CheckResult")
    private void initServices() {
        serverIp = StringUtils.getlocalip(mContext);
        //ip.setText("IP addresss:" + serverIp);
        LogUtils.e("IP addresss", serverIp);
        mTvIpAddress.setText("服务器IP: " + serverIp + ":30000");


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {

                        OutputStream output;
                        String serverContent = "hello hehe";
                        try {
                            ServerSocket serverSocket = new ServerSocket(30000);
                            while (true) {
                                LogUtils.e("aaaaaaa");
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

                                    bff.close();
                                    output.close();
                                    socket.close();

                                    return result;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        return "";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String msg) throws Exception {
                        LogUtils.e(msg + "11aaaaaaaaaaaaaaaaaaaaaaaa");
                        LanEntity lanEntity = new LanEntity();
                        lanEntity.viewType = 1;
                        lanEntity.receiveType = 1;
                        lanEntity.content = msg + "";
                        lanEntity.time = TimeUtils.getNowString();
                        mList.add(lanEntity);
                        mAdapter.notifyDataSetChanged();
                    }
                });
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
