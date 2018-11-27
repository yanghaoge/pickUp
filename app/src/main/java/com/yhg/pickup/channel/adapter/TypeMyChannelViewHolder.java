package com.yhg.pickup.channel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yhg.pickup.R;

/**
 * Created by Administrator on 2018/10/19.
 */

public class TypeMyChannelViewHolder extends RecyclerView.ViewHolder {
  TextView tvChannel;
   View ivDelete;

    public TypeMyChannelViewHolder(View itemView) {
        super(itemView);
        tvChannel = itemView.findViewById(R.id.tvChannel);
        ivDelete = itemView.findViewById(R.id.ivDelete);

    }
}
