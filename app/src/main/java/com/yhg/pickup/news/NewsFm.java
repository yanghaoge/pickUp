package com.yhg.pickup.news;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;
import com.yhg.pickup.widght.MyGridView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/11/20.
 */

public class NewsFm extends BaseFragment implements View.OnClickListener {

    private LayoutInflater mInflater;
    RecyclerView ry ;
    private MyGridView mLettersLayout;
    private ArrayList<SpringAnimation> mLetterAnims;
    private ArrayList<SpringAnimation> mLetterAnims2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.fm_me, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {


    }

    private void initView(View view) {
        ry = view.findViewById(R.id.ry);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ry.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter();
        ry.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    private class MyAdapter extends  RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.koo, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            SimpleDraweeView simpleDraweeView  = new SimpleDraweeView(mContext);
            simpleDraweeView.setImageResource(R.drawable.zz);
            holder.mKoo.removeAllViews();
            holder.mKoo.addView(simpleDraweeView);
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        FrameLayout mKoo;
        public ViewHolder(View itemView) {
            super(itemView);
            mKoo =  itemView.findViewById(R.id.koo);
        }
    }
}
