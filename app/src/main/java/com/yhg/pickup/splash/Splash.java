package com.yhg.pickup.splash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yhg.pickup.MainActivity;
import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseActivity;

/**
 * @author Administrator
 */
public class Splash extends BaseActivity {

    private int mScreenHeight;
    private int mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        ImageView logoImg = findViewById(R.id.biz_ad_img_slogan);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_splash_bottom);
        //显示底部图片
        logoImg.setImageBitmap(bitmap);
        startActivity(new Intent(this,MainActivity.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },150);
    }

}
