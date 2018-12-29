package com.yhg.pickup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.yhg.pickup.base.BaseActivity;
import com.yhg.pickup.news.News2Fm;
import com.yhg.pickup.news.NewsFm;

/**
 * 首页
 * @author yhg
 * @date 2018/10/12
 */

public class LiziActivity extends BaseActivity implements View.OnClickListener {


    private boolean flag;
    private AppBarLayout mAppBarLayout;
    private EditText mEditText;
    private TextView mTextView;
    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0,0);
        setContentView(R.layout.fm_zong);
        initView();
        initData();
    }

    private void initView() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View viewById = findViewById(R.id.top_body_layout);
        View byId = findViewById(R.id.button);
         mEditText = findViewById(R.id.edit_text);
         mTextView = findViewById(R.id.text_view);
        viewById.setOnClickListener(this);
        byId.setOnClickListener(this);
        mAppBarLayout=findViewById(R.id.appbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new NewsFm()).commit();
    }

    private void initData() {

    }


    /**
     * EditorText获取焦点和输入法
     *
     * @param e
     */
    protected void getFocus(EditText e) {

        e.requestFocus();
        e.setFocusable(true);
        e.setFocusableInTouchMode(true);
        imm.showSoftInput(e, 0);
    }
    protected void close() {
        try {
            if (imm != null) {
                //得到InputMethodManager的实例
                if (imm.isActive()) {
                    //如果开启
                    imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
                    //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.top_body_layout||v.getId()==R.id.button) {
            if (flag){
                onPageSelected();
                mTextView.setVisibility(View.VISIBLE);
                mEditText.setVisibility(View.GONE);
                close();
                flag=!flag;
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new NewsFm()).commit();
            }else {
                onPageSelected();
                flag=!flag;
                mTextView.setVisibility(View.GONE);
                mEditText.setVisibility(View.VISIBLE);
                getFocus(mEditText);
                getSupportFragmentManager().beginTransaction().replace(R.id.framlayout, new News2Fm()).commit();
            }
        }

    }

    public void onPageSelected() {
        int i0 = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL;
        int i1 = AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED;
        View appBarChildAt = mAppBarLayout.getChildAt(0);
        AppBarLayout.LayoutParams appBarParams = (AppBarLayout.LayoutParams) appBarChildAt.getLayoutParams();
        if (flag) {
            appBarParams.setScrollFlags(i0 | i1);// 重置折叠效果
        } else {
            appBarParams.setScrollFlags(0);//这个加了之后不可滑动
        }
        appBarChildAt.setLayoutParams(appBarParams);

    }
}
