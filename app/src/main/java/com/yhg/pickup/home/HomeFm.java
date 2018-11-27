package com.yhg.pickup.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;
import com.yhg.pickup.channel.ChannelDialogFragment;
import com.yhg.pickup.params.Parameter;
import com.yhg.pickup.utils.NetUtils;

import org.json.JSONObject;


/**
 * Created by Administrator on 2018/10/15.
 */

public class HomeFm extends BaseFragment implements View.OnClickListener {

    private LayoutInflater mInflater;
    private TextView mChannelMannager;
    private int receiveType;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.fm_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {


    }

    private void initView(View view) {
        mChannelMannager= view.findViewById(R.id.channel_mannager);
        mChannelMannager.setOnClickListener(this);
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

        NetUtils.post(this,params,new NetUtils.NetCallBack(){
            @Override
            public void succuss(String result) {

            }

            @Override
            public void error(String result) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.channel_mannager:
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance();
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
                break;
                default:
                    break;
        }
    }

}
