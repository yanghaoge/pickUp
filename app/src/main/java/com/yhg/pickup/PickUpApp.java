package com.yhg.pickup;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yhg.pickup.utils.NetUtils;

/**
 *
 * @author Administrator
 * @date 2018/10/17
 */

public class PickUpApp extends Application {
    private static PickUpApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        NetUtils.initNet(this);
        Fresco.initialize(this);

    }

    public static PickUpApp getInstance() {
        return mInstance;
    }
}
