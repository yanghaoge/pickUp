package com.yhg.pickup.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;

/**
 * Created by Administrator on 2018/10/15.
 */

public class MeFm extends BaseFragment {

    private LayoutInflater mInflater;
    RecyclerView ry ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.fm_me, container, false);
        ry= view.findViewById(R.id.ry);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ry.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter();
        ry.setAdapter(myAdapter);
    }

    private void initData() {


    }


    private class MyAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_xq, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
