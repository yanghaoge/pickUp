package com.yhg.pickup.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;
import com.yhg.pickup.channel.ChannelDialogFragment;
import com.yhg.pickup.me.MeFm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 * @author Administrator
 * @date 2018/10/15
 */

public class HomeFm extends BaseFragment {

    @BindView(R.id.article_reward_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.channel_mannager)
    TextView channelMannager;
    @BindView(R.id.content)
    ViewPager mViewPager;
    private MyAdapter adapter;
    public List<TextView> mTextViewList = new ArrayList<>();
    @Override
    public int getContentViewId() {
        return R.layout.fm_home;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        initData();
    }

    private void initData() {
        adapter = new MyAdapter(getChildFragmentManager(), getResources().getStringArray(R.array.channel));
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                if (mTextViewList == null || mTextViewList.size() < 2) {
                    return;
                }
                for (int j = 0; j < mTextViewList.size(); j++) {
                    mTextViewList.get(j).setTextColor(Color.parseColor("#333333"));
                }
                mTextViewList.get(i).setTextColor(Color.parseColor("#f03732"));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //为TabLayout设置ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        //使用ViewPager的适配器
        mTabLayout.setTabsFromPagerAdapter(adapter);
        //3.重点来了：必须重新调用setCustomView方法来设置自定义的View
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(adapter.getTabView(i));
            if (i == 0) {
                TextView tabViewDefault = (TextView) mTabLayout.findViewById(R.id.tab_text);
                tabViewDefault.setTextColor(Color.parseColor("#f03732"));
                //初始化默认tab 使他颜色设置成选中状态
            }
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {

        private List<String> title;

        public MyAdapter(FragmentManager fm, String[] stringArray) {
            super(fm);
            this.title = Arrays.asList(stringArray);
        }

        /**
         * 这个是你想要的tabview
         */
        public View getTabView(int position) {
            //获得view
            View v = LayoutInflater.from(mContext).inflate(R.layout.tab_item, null);
            TextView tv = (TextView) v.findViewById(R.id.tab_text);
            tv.setText(title.get(position));
            mTextViewList.add(tv);
            return v;
        }

        @Override
        public Fragment getItem(int position) {
            return new MeFm();
        }

        @Override
        public int getCount() {
            return title.size();
        }

        /**
         * 配置标题的方法
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

    }

    @OnClick(R.id.channel_mannager)
    public void onViewClicked() {
        ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance();
        dialogFragment.show(getChildFragmentManager(), "CHANNEL");
    }
}
