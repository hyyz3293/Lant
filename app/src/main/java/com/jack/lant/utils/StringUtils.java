package com.jack.lant.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.TimeUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 页面
 *
 * @author jack
 * @date 2018/9/8
 * @note -
 * ---------------------------------------------------------------------------------------------------------------------
 * @modified -
 * @date -
 * @note -
 */
public  class StringUtils {

    public static String getlocalip(Context mContext) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        //  Log.d(Tag, "int ip "+ipAddress);
        if (ipAddress == 0) return null;
        return ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
    }


    /**
     * 獲取當前時間
     * @return
     */
    public static String getNewTime() {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(System.currentTimeMillis());
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long s){
        if (s == 0)
            return "";
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*********************检测******************************/
    public static final int COLOR_ABNORMAL_RED = 0xFFFF4036;
    public static final int COLOR_ABNORMAL_GREEN = 0xFF3DCBB0;
    /*********************检测******************************/

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static float divto(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static float divto(int v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    public static String getTextCenter(String text, String begin, String end){
        try {
            int b=text.indexOf(begin)+begin.length();
            int e=text.indexOf(end,b);
            return text.substring(b,e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }


    public static String valueOf(Object string) {
        return String.valueOf(string);
    }


    public static String encodeName2(String name){
        if (StringUtils.isNotBlank(name)) {
            char[] m =  name.toCharArray();
            for(int i = 0; i < m.length; i++){
                if (m.length > 2) {
                    if(i > 0 && i< m.length - 1){
                        m[i] = '*';
                    }
                } else {
                    if(i > 0 && i < m.length){
                        m[i] = '*';
                    }
                }
            }
            return String.valueOf(m);
        }
        return "";
    }

    public static String encodeName(String name){
        char[] m =  name.toCharArray();
        for(int i=0; i<m.length;i++){
            if(i>0 && i<m.length){
                m[i] = '*';
            }
        }
        return String.valueOf(m);
    }

    public static String encodeMobile(String mobile){
        char[] m =  mobile.toCharArray();
        for(int i=0; i<m.length;i++){
            if(i>2 && i<7){
                m[i] = '*';
            }
        }
        String newMoblie =  String.valueOf(m);
        return newMoblie;
    }



    public static boolean isNotBlank(String str) {
        if (isBlank(str)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 获取文件名
     * @param wsa
     * @return
     */
    public static String getTimeStampFileName(int wsa){
        SimpleDateFormat timesdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = timesdf.format(new Date()).toString();
        String fileName = "";
        if (wsa == 0){
            fileName = timeStamp + ".png";
        }else if (wsa == 1){
            fileName = timeStamp + ".xls";
        } else if (wsa == 2) {
            fileName = timeStamp + ".jpg";
        } else if (wsa == 3) {
            fileName = timeStamp + ".txt";
        }
        return fileName;
    }

    /**
     * 获取文件名
     * @return
     */
    public static int getTimeStamp(){
        SimpleDateFormat timesdf = new SimpleDateFormat("HHmmss");
        String timeStamp = timesdf.format(new Date()).toString();
        int tims = 0;
        try {
            tims = Integer.parseInt(timeStamp);
        }catch (Exception e){
            e.printStackTrace();
            tims = 0;
        }
        return tims;
    }


    /**
     * 当前是横屏还是竖屏
     * @param mContext
     * @return
     */
    public static boolean getNewConfigH(Context mContext){
        if (mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 获取倒计时时间
     * @param num
     * @return
     */
    public static String getTimes(Long num) {
        String times = "";
        if (num > 0) {
            long hour = num / (60 * 60);
            long minute = (num/ (60)) - hour * 60;
            long sec =  num - hour * 60 * 60 - minute * 60;
            if (hour < 10)
                times = hour + "0";
            else
                times = hour + "";
            if (minute < 10)
                times = times + ":0" + minute;
            else
                times = times + ":" + minute;
            if (sec <10)
                times = times + ":0" + sec;
            else
                times = times + ":" + sec;
        }
        return times;
    }

    /**
     *
     *  字符
     * @return
     */
    public static String getOrderDt() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }


}
