package com.yhg.pickup.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/15.
 */

public class MeFm extends BaseFragment {

    @BindView(R.id.ry)
    RecyclerView ry;

    @Override
    public int getContentViewId() {
        return R.layout.fm_me;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        initView();
    }


    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ry.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter();
        ry.setAdapter(myAdapter);
    }


    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
