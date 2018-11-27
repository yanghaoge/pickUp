package com.yhg.pickup.channel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yhg.pickup.R;

/**
 * Created by Administrator on 2018/10/19.
 */

public class TypeOtherViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvEdit;

    public TypeOtherViewHolder(View itemView) {
        super(itemView);
        tvTitle =itemView.findViewById(R.id.tvTitle);
        tvEdit =itemView.findViewById(R.id.tvEdit);
    }
}
