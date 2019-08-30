package com.jack.lant;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.jack.lant.utils.JKX_Util;

public class JkxApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        JKX_Util.init(this);
        mContext = this;
    }

}
