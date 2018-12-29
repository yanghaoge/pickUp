package com.yhg.pickup.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */

public class NewsFm extends BaseFragment {

    private LayoutInflater mInflater;

    @Override
    public int getContentViewId() {
        return 0;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.fm_nes, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
    }

    private void initData() {

    }

    private void initView(View view) {
        SwipeMenuRecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        GroupAdapter adapter = new GroupAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setmListItems(createDataList());
    }


    private static class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

        static final int VIEW_TYPE_NON_STICKY = R.layout.item_menu_main;
        static final int VIEW_TYPE_STICKY = R.layout.item_menu_sticky;

        private List<String> mListItems = new ArrayList<>();

        public void setmListItems(List<String> mListItems) {
            this.mListItems = mListItems;
            notifyDataSetChanged();
        }

        @Override
        public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(viewType, parent, false);
            return new GroupViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GroupViewHolder holder, int position) {
            holder.bind(mListItems.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            if (mListItems.get(position).length()<2) {
                return VIEW_TYPE_STICKY;
            }
            return VIEW_TYPE_NON_STICKY;
        }

        @Override
        public int getItemCount() {
            return mListItems.size();
        }

    }

    private static class GroupViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        GroupViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_title);
        }
        void bind(String item) {
            text.setText(item);
        }
    }

    protected List<String> createDataList() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                strings.add("" + i / 10);
            }
            strings.add("第" + i + "个Item");
        }
        return strings;
    }

}
