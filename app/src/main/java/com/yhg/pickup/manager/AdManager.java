package com.yhg.pickup.manager;

import android.text.TextUtils;

/**
 * 广告管理者
 * @author yhg
 * @date 2018/11/27
 */

public class AdManager {

    /**
     * 是否有首页闪屏广告
     * @return
     */
    public static boolean isHaveSplashAd() {
        return !TextUtils.isEmpty(PreferencesManager.getString(PreferencesManager.SPLASH_AD, PreferencesManager.SPLASH_AD, ""));

    }

}
