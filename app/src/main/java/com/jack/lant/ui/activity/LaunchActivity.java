package com.jack.lant.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;

import com.jack.lant.R;
import com.jack.lant.base.ActivityManager;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置全屏//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 移除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luanch);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initView();

        ActivityManager.addActivity(this);
    }

    public void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jump();
            }
        }, 1000);
    }

    private void jump() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

}


