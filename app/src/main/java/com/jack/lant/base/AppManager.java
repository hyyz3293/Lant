package com.jack.lant.base;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.jack.lant.JkxApplication;
import com.jack.lant.ui.activity.LoginActivity;
import com.jack.lant.ui.activity.MainActivity;
import com.jack.lant.ui.model.UserInfoEntity;
import com.jack.lant.utils.SharedPreferencesHelper;
import com.jack.lant.utils.StringUtils;

import java.util.UUID;

/**
jack 2018/5/8.
 */
public class AppManager {
    private static volatile AppManager instance;
    private String skey;
    private String fakeIMEI;
    private String channelId;

    public static synchronized AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    private AppManager() {
        //loadData();
    }

    /* 加载缓存数据到单例 */
    private void loadData() {
        skey = (String) SharedPreferencesHelper.get("skey", "");
        fakeIMEI = (String) SharedPreferencesHelper.get("fakeIMEI", "");
    }

    /* 判断登录状态 */
    public boolean isLogin() {
        return !TextUtils.isEmpty(skey);
    }

    /* 获取skey */
    public String getSkey() {
        return skey;
    }

    /* 设置skey */
    public void login(String skey) {
        this.skey = skey;
        SharedPreferencesHelper.put("skey", skey);
    }

    /**
     * 保存数据
     * @param user
     * @param pwd
     */
    public void setUserInfoEntity(String user, String pwd) {
        loginStatu(true);
        SharedPreferencesHelper.put("app_user", user);
        SharedPreferencesHelper.put("app_pwd", pwd);
    }

    /**
     * 獲取數據
     * @return
     */
    public UserInfoEntity getUserInfoEntity() {
        UserInfoEntity entity = new UserInfoEntity();
        Object a = SharedPreferencesHelper.get("app_user", "");
        Log.e("", a + "");
        String user = (String) SharedPreferencesHelper.get("app_user", "");
        String pwd = (String) SharedPreferencesHelper.get("app_pwd", "");
        if (StringUtils.isNotBlank(user)) {
            entity.user = user;
            entity.pwd = pwd;
            return entity;
        }
        return null;
    }



    /** 是否登录 */
    public void loginStatu(boolean islogin) {
        SharedPreferencesHelper.put("login_stat", islogin);
    }


    /** 是否登录 */
    public boolean loginStatu() {
        boolean islogin = (boolean) SharedPreferencesHelper.get("login_stat", false);
        return islogin;
    }


    /* 获取channelId */
    public String getChannelId() {
        channelId =  SharedPreferencesHelper.get("ChannelId","0").toString();
        return channelId;
    }

    /* 设置channelId */
    public void ChannelId(String channelId) {
        this.channelId = channelId;
        SharedPreferencesHelper.put("ChannelId", channelId);
    }

    /* 获取UUID唯一识别码 */
    public String getFakeIMEI() {
        if (TextUtils.isEmpty(fakeIMEI)) {
            fakeIMEI = UUID.randomUUID().toString().replace("-", "");
            SharedPreferencesHelper.put("fakeIMEI", fakeIMEI);
        }
        return fakeIMEI;
    }

    /* 设置测试环境还是正式环境 */
    public void setTestingModel(Boolean isTest) {
        SharedPreferencesHelper.put("isTestingModel", isTest);
        if (isTest) {//测试环境
            SharedPreferencesHelper.put("host", "http://gcs.t.jikexiu.com/si/app/");
            SharedPreferencesHelper.put("host_two", "http://api-t.jikexiu.com/");
            SharedPreferencesHelper.put("host_there", "http://t.jikexiu.com/");

        } else {//正式环境
            SharedPreferencesHelper.put("host", "https://gcs.jikexiu.com/si/app/");
            SharedPreferencesHelper.put("host_two", "http://api.jikexiu.com/");
            SharedPreferencesHelper.put("host_there", "https://m.jikexiu.com/");
        }
    }


    /*获取请求地址*/
    public String getHost() {
        return (String) SharedPreferencesHelper.get("host", "https://gcs.jikexiu.com/si/app/");
    }

    /*获取请求地址*/
    public String getHostTwo() {
        return (String) SharedPreferencesHelper.get("host_two", "http://api.jikexiu.com/");
    }

    /*获取请求地址*/
    public String getHostThere() {// TODO: 2019-08-22 21.49 jack 要求改的
        return (String) SharedPreferencesHelper.get("host_there", "https://m.jikexiu.com/");
    }

    //推广写死的请求地址
    public String getTuiGuangHost() {//"http://gcs.jikexiu.com/app"
        return (String) SharedPreferencesHelper.get("host_there", "https://gcs.jikexiu.com/app");
    }

    /*退出登录，清除数据*/
    public void mainLoginOut() {
        try {
            skey = "";
            fakeIMEI = "";
//            Intent homeIntent = new Intent(JkxApplication.mContext, LoginActivity.class);
//            ActivityUtils.getTopActivity().startActivity(homeIntent);
            //SpUtils.putChannleidEnable(APP.getAppContext(), false);
            //ActivityUtils.finishActivity(MainActivity.class);
            SharedPreferencesHelper.clear();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
