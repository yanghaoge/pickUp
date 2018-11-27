package com.yhg.pickup.utils;

import android.app.Activity;
import android.content.Context;

import com.jaeger.library.StatusBarUtil;
import com.yhg.pickup.PickUpApp;
import com.yhg.pickup.R;

/**
 * 系统工具类
 * @author Administrator
 * @date 2018/10/12
 */

public class SystemUtils {


    private static float mScreenWidth;
    private static float mScreenHeight;

    public static float getScreenWidth(Context context) {
        if (context == null || mScreenWidth != 0) {
            return mScreenWidth;
        }
        mScreenWidth =context.getResources().getDisplayMetrics().widthPixels;
        return mScreenWidth;
    }

    public static float getScreenHeight(Context context) {
        if (context == null || mScreenHeight != 0) {
            return mScreenHeight;
        }
        mScreenHeight= context.getResources().getDisplayMetrics().heightPixels;
        return mScreenHeight;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = PickUpApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void changeStatueBarStyle(Activity activity) {
        int apha = 0;
        int rColor;
        if (false) {
            rColor = activity.getResources().getColor(R.color.red);
        } else if (false) {
            rColor = activity.getResources().getColor(R.color.black);
        } else if (false) {
            rColor = activity.getResources().getColor(R.color.transparent);
            apha = 255;
        } else {
            rColor = activity.getResources().getColor(R.color.color_fa);
        }
        StatusBarUtil.setColorForSwipeBack(activity, rColor, apha);
        if (rColor == activity.getResources().getColor(R.color.color_fa)) {
            StatusBarUtil.setLightMode(activity);
        } else {
            StatusBarUtil.setDarkMode(activity);
        }
    }
}
