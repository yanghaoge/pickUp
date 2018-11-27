package com.yhg.pickup.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author Administrator
 * @date 2018/10/12
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected View mRootView;
    private Unbinder bind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public abstract int getContentViewId();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        //绑定framgent
        bind = ButterKnife.bind(this, mRootView);
        initAllMembersView(savedInstanceState);
        return mRootView;
    }

    /**
     * 数据初始化操作
     * @param savedInstanceState
     */
    protected abstract void initAllMembersView(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }


}
