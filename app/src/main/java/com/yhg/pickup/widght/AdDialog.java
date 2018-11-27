package com.yhg.pickup.widght;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yhg.pickup.R;
import com.yhg.pickup.utils.SystemUtils;
import com.yhg.pickup.utils.ViewPagerScrollTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/16.
 */

public class AdDialog extends Dialog {
    private  Context mContext;
    ViewPager pager;
    List<View> views = new ArrayList<>();
    private LinearLayout rounds;
    private Handler handlerTouTiao;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handlerTouTiao.sendEmptyMessage(0);
        }
    };
    private int flag;

    public AdDialog(@NonNull Context context) {
        super(context, R.style.BaseDialog);
        mContext =context;
        setCanceledOnTouchOutside(false);
        init();
    }

    private ImageView getImg(boolean end){
        ImageView imageView = new ImageView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SystemUtils.dip2px(8),SystemUtils.dip2px(8));
        layoutParams.weight =1;
        if (end) {
            imageView.setBackgroundResource(R.drawable.main_tuan_dian2);
        }else {
            imageView.setBackgroundResource(R.drawable.main_tuan_dian1);
            layoutParams.leftMargin=SystemUtils.dip2px(10);
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private void init() {
        setContentView(R.layout.dialog_ad);
        views.add(LayoutInflater.from(mContext).inflate(R.layout.ad_item,null));
        views.add(LayoutInflater.from(mContext).inflate(R.layout.ad_item,null));
//        views.add(LayoutInflater.from(mContext).inflate(R.layout.ad_item,null));
//        views.add(LayoutInflater.from(mContext).inflate(R.layout.ad_item,null));
//        views.add(LayoutInflater.from(mContext).inflate(R.layout.ad_item,null));
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rounds = findViewById(R.id.rounds);
        if (views.size()>1) {
            for (int i = 0; i < views.size(); i++) {
                rounds.addView(getImg(i == 0));
            }
        }else {
            rounds.setVisibility(View.GONE);
        }
        if (views.size() ==2) {
            views.add(views.get(0)); views.add(views.get(1));
        }
        pager= findViewById(R.id.pager);
        pager.setOffscreenPageLimit(1);
        final ViewPagerScrollTools scroller = new ViewPagerScrollTools(mContext);
        scroller.setScrollDuration(2000);
        scroller.initViewPagerScroll(pager);
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size() < 2 ? views.size() : Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = views.get(position % views.size());
                ViewGroup p = (ViewGroup) view.getParent();
                if (p != null) {
                    p.removeView(view);
                }
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView(views.get(position % views.size()));
//                container.removeView((View)object);
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // 当viewpager的item改变的时候
            @Override
            public void onPageSelected(int i) {
                i = i%rounds.getChildCount();
                for (int j = 0; j < rounds.getChildCount(); j++) {
                    // 每循环一次作用的是一个圆点，整体的循环代表本次呈现的所有圆点状态
                    if (j == i) {
                        rounds.getChildAt(j).setBackgroundResource(R.drawable.main_tuan_dian2);
                    } else {
                        rounds.getChildAt(j).setBackgroundResource(R.drawable.main_tuan_dian1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                flag = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
        });

        handlerTouTiao = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (pager != null && views != null ) {
                    if ( views.size() >1) {
                        if (pager.getCurrentItem() + 1 < Integer.MAX_VALUE) {
                            if (flag == 0) {
                                pager.setCurrentItem(pager.getCurrentItem() + 1, true);
                            }
                            postDelayed(runnable, 3000);
                        }
                    }
                }
            }
        };
        pager.postDelayed(runnable,3000);
    }

}
