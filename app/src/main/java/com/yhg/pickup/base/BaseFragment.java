package com.yhg.pickup.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2018/10/12.
 */

public class BaseFragment extends Fragment {
   protected  Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
