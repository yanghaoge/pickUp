package com.yhg.pickup.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yhg.pickup.R;
import com.yhg.pickup.manager.AppManager;
import com.yhg.pickup.utils.SystemUtils;

/**
 *
 * @author yhg
 * @date 2018/10/12
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.translate_to_left,
                R.anim.translate_to_left_hide);
        AppManager.getAppManager().addActivity(this);
        SystemUtils.changeStatueBarStyle(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_to_right,
                R.anim.translate_to_right_hide);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
