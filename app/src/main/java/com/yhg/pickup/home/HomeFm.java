package com.yhg.pickup.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;
import com.yhg.pickup.channel.ChannelDialogFragment;
import com.yhg.pickup.params.Parameter;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 * @author Administrator
 * @date 2018/10/15
 */

public class HomeFm extends BaseFragment {

    @BindView(R.id.article_reward_tablayout)
    TabLayout articleRewardTablayout;
    @BindView(R.id.channel_mannager)
    TextView channelMannager;
    @BindView(R.id.content)
    ViewPager content;
    @BindView(R.id.simpleDraweeView)
    SimpleDraweeView simpleDraweeView;

    @Override
    public int getContentViewId() {
        return R.layout.fm_home;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        initView(mRootView);
    }

    private void initView(View view) {

        JSONObject jo = new JSONObject();
        try {
            jo.put("page", 1);
            jo.put("appid", 0);
            jo.put("siteID", 920);
            jo.put("userID", 0);
            jo.put("pageSize", 16);

            jo.put("operate", 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String params = Parameter.createnewsParam("PHSocket_GetHeadShareInfo", jo);

//        NetUtils.post(this, params, new NetUtils.NetCallBack() {
//            @Override
//            public void succuss(String result) {
//
//            }
//
//            @Override
//            public void error(String result) {
//
//            }
//        });
    }


    @OnClick(R.id.channel_mannager)
    public void onViewClicked() {
        ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance();
        dialogFragment.show(getChildFragmentManager(), "CHANNEL");
    }
}
