package com.yhg.pickup.ad;

import android.content.Intent;
import android.os.Bundle;

import com.yhg.pickup.MainActivity;
import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseActivity;
import com.yhg.pickup.manager.AdManager;

/**
 * 闪屏页
 * @author yhg
 */
public class AdActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        initView();
        if (AdManager.isHaveSplashAd()) {

        }else {
            startActivity(new Intent(this,MainActivity.class));
        }

    }

    private void initView() {

    }

}
