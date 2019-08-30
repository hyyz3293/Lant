package com.jack.lant.utils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.jack.lant.base.AppManager;

import java.util.Hashtable;

/**
jack 2018/5/4.
 */
public class JKX_HeaderUtil {

    public static Hashtable<String, String> getDefaultHeaders() {
        Hashtable<String, String> headers = new Hashtable<String, String>();
        String skey = AppManager.getInstance().getSkey();
        String imei = AppManager.getInstance().getFakeIMEI();
        String vid = AppUtils.getAppVersionName();
        String model = DeviceUtils.getModel();
        String os = "Android";
        String osv = DeviceUtils.getSDKVersionName();
        if (skey != null){
            headers.put("skey", skey);
        }
        if (imei != null) {
            headers.put("imei", imei);
        }
        if (vid != null) {
            headers.put("vid", vid);
        }
        if (model != null) {
            headers.put("model", model);
        }
        if (osv != null) {
            headers.put("osv", osv);
        }
        headers.put("os", os);
        return headers;
    }

}
