package com.jack.lant.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jack.lant.R;
import com.jack.lant.base.BaseActivity;
import com.jack.lant.base.BaseFragment;
import com.jack.lant.ui.fragment.HomeFragment;
import com.jack.lant.ui.fragment.MyFragment;
import com.jack.lant.ui.view.NoScrollViewPager;
import com.jack.lant.ui.view.TabIndicator;

public class MainActivity extends BaseActivity {

    private FrameLayout mFlMainContainer;
    /** 缓存容器*/
    private SparseArray<BaseFragment> mCacheContainer;
    private NoScrollViewPager viewPager;
    private TabIndicator mHomeTab, mMineTab;
    private HomeFragment mHomeFragment;
    private MyFragment mMyFragment;
    private long WAIT_TIME = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        mFlMainContainer = (FrameLayout) findViewById(R.id.mFlMainContainers);
        viewPager = LayoutInflater.from(this).inflate(R.layout.item_main_view_pager, mFlMainContainer, true).findViewById(R.id.vpMain);
        mHomeTab = (TabIndicator) findViewById(R.id.mHomeIndicator);
        mMineTab = (TabIndicator) findViewById(R.id.mUserIndicator);
        setStatusBar();

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
    public void initData() {
        super.initData();
        mCacheContainer = new SparseArray<>();
        mHomeFragment = new HomeFragment();
        mMyFragment = new MyFragment();

        mCacheContainer.put(0, mHomeFragment);
        mCacheContainer.put(1, mMyFragment);

        viewPager.setScroll(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mCacheContainer.get(position);
            }

            @Override
            public int getCount() {
                return mCacheContainer.size();
            }
        });

        initTab();
        setCurrentTab(0);
    }

    private void initTab() {
        mHomeTab.setTabIcon(R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        mHomeTab.setTabTitle("首页");
        mMineTab.setTabIcon(R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        mMineTab.setTabTitle("我的");

        mHomeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCenterAnimation(mHomeTab, 0);
            }
        });


        mMineTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCenterAnimation(mHomeTab, 1);
            }
        });
    }

    private void startCenterAnimation(View mView, final int i) {
        AnimationSet animation = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.8f, 1f, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);
        animation.addAnimation(scaleAnimation);
        mView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setCurrentTab(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setCurrentTab(int i) {
        switch (i) {
            case 0:
                viewPager.setCurrentItem(0, false);
                mHomeTab.setCurrentFocus(true);
                mHomeTab.setTextColor(true);
                mMineTab.setCurrentFocus(false);
                mMineTab.setTextColor(false);
                break;
            case 1:
                viewPager.setCurrentItem(1, false);
                mHomeTab.setCurrentFocus(false);
                mHomeTab.setTextColor(false);
                mMineTab.setCurrentFocus(true);
                mMineTab.setTextColor(true);
                break;
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
