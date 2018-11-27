package com.yhg.pickup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.yhg.pickup.base.BaseActivity;
import com.yhg.pickup.home.HomeFm;
import com.yhg.pickup.manager.AppManager;

/**
 * 首页
 * @author yhg
 * @date 2018/10/12
 */

public class MainActivity  extends BaseActivity implements View.OnClickListener {

    private long exitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0,0);
        setContentView(R.layout.act_main);
        initView();
        initData();
    }

    private void initView() {
        View home = findViewById(R.id.home);
        View news = findViewById(R.id.news);
        View edit = findViewById(R.id.edit);
        View msg = findViewById(R.id.msg);
        View me = findViewById(R.id.me);
        home.setOnClickListener(this);
        news.setOnClickListener(this);
        edit.setOnClickListener(this);
        msg.setOnClickListener(this);
        me .setOnClickListener(this);
        HomeFm mMeFm = new HomeFm();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, mMeFm).commit();
    }

    private void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime <= 2000) {
                AppManager.getAppManager().finishAllActivity();
            } else {
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

    }
}
