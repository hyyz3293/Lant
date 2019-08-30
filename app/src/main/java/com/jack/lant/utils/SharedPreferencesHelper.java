package com.jack.lant.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.jack.lant.base.AppManager;

import java.util.Map;

/**
jack 2018/5/11.
 */
public class SharedPreferencesHelper extends JKX_Util {

    /**
     * 保存在手机里面的名字
     */
    private static final String FILE_NAME = "JKXPrefsFile";

    @SuppressLint("StaticFieldLeak")
    private static SharedPreferencesHelper INSTANCE = new SharedPreferencesHelper();

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    /*单例模式*/
    public static synchronized SharedPreferencesHelper getINSTANCE(){
        return INSTANCE;
    }

    @SuppressLint("CommitPrefEdits")
    private SharedPreferencesHelper() {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     */
    public static void put(String key, Object object) {

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     */

    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }

    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有的数据
     */
    public static void clear() {
        boolean isTestingModel = (boolean) SharedPreferencesHelper.get("isTestingModel", false);
        editor.clear();
        editor.apply();
        AppManager.getInstance().setTestingModel(isTestingModel);
    }

    /**
     * 查询某个key是否存在
     */
    public static boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}
