package com.yhg.pickup.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.yhg.pickup.PickUpApp;

/**
 * 文件统一管理类
 * @author yhg
 * @date 2018/11/27
 */

public class PreferencesManager {
    public static final String SPLASH_AD = "SPLASH_AD";

    public static void clearSp(String type) {
        PickUpApp.getInstance().getSharedPreferences(type, Context.MODE_PRIVATE).edit().clear().apply();
    }

    public static SharedPreferences getSp(String type) {
        return PickUpApp.getInstance().getSharedPreferences(type, Context.MODE_PRIVATE);
    }

    public static boolean putInt(String type, String key, int value) {
        return getSp(type).edit().putInt(key, value).commit();
    }

    public static int getInt(String type, String key, int defValue){
        return getSp(type).getInt(key, defValue);
    }

    public static boolean putLong(String type, String key, long value){
        return getSp(type).edit().putLong(key, value).commit();
    }

    public static long getLong(String type, String key, long defValue){
        return getSp(type).getLong(key, defValue);
    }

    public static boolean putString(String type, String key, String value){
        return getSp(type).edit().putString(key, value).commit();
    }

    public static String getString(String type, String key, String defValue){
        return getSp(type).getString(key, defValue);
    }

    public static boolean putBoolean(String type, String key, boolean value){
        return getSp(type).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String type, String key, boolean defValue){
        return getSp(type).getBoolean(key, defValue);
    }

    public static boolean clearAllValue(String type){
        return getSp(type).edit().clear().commit();
    }
}
