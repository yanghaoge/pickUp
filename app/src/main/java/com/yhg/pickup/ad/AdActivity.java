package com.yhg.pickup.ad;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yhg.pickup.MainActivity;
import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseActivity;
import com.yhg.pickup.manager.AdManager;
import com.yhg.pickup.manager.AppManager;

/**
 * 闪屏页
 *
 * @author yhg
 */
public class AdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) {
            //判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
            //如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                AppManager.getAppManager().finishActivity();
                return;//finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
            }
        }
        setContentView(R.layout.act_splash);
        initView();
        if (AdManager.isHaveSplashAd()) {

        } else {
            startActivity(new Intent(this, MainActivity.class));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 150);
        }

    }

    private void initView() {

    }

}
