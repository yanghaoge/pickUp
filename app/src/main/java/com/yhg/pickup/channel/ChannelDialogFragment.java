package com.yhg.pickup.channel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yhg.pickup.R;
import com.yhg.pickup.channel.adapter.ChannelAdapter;
import com.yhg.pickup.channel.model.Channel;
import com.yhg.pickup.channel.nozzle.ItemDragHelperCallBack;
import com.yhg.pickup.channel.nozzle.OnChannelDragListener;
import com.yhg.pickup.channel.nozzle.OnChannelListener;

import java.util.ArrayList;
import java.util.List;

import static com.yhg.pickup.channel.model.Channel.TYPE_MY_CHANNEL;

/**
 * @author Administrator
 * @date 2017/4/18 0018
 */

public class ChannelDialogFragment extends DialogFragment implements OnChannelDragListener {
    private static String DATA_SELECTED = "1";
    private static String DATA_UNSELECTED = "0";
    private List<Channel> mDatas = new ArrayList<>();
    private ChannelAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ItemTouchHelper mHelper;
    private OnChannelListener mOnChannelListener;
    Context mContext;
    private List<Channel> mSelectedChannels = new ArrayList<>();

    public void setOnChannelListener(OnChannelListener onChannelListener) {
        mOnChannelListener = onChannelListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext =context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            //添加动画
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        return inflater.inflate(R.layout.fm_channel, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.icon_collapse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        processLogic();
    }

    public static ChannelDialogFragment newInstance() {
        ChannelDialogFragment dialogFragment = new ChannelDialogFragment();
        Bundle bundle = new Bundle();

        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    private void setDataType(List<Channel> datas, int type) {
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setItemType(type);
        }
    }

    private void processLogic() {
        mDatas.add(new Channel(Channel.TYPE_MY, "我的频道", ""));
        Bundle bundle = getArguments();
        //获取数据，待改
//        List<Channel> selectedDatas = (List<Channel>) bundle.getSerializable("");
//        List<Channel> unselectedDatas = (List<Channel>) bundle.getSerializable("");

        //本地没有title
        String[] channels = getResources().getStringArray(R.array.channel);
        String[] channelCodes = getResources().getStringArray(R.array.channel_code);
        //默认添加了全部频道
        for (int i = 0; i < channelCodes.length; i++) {
            String title = channels[i];
            String code = channelCodes[i];
            mSelectedChannels.add(new Channel(title, code));
        }

        setDataType(mSelectedChannels, TYPE_MY_CHANNEL);
//        setDataType(unselectedDatas, Channel.TYPE_OTHER_CHANNEL);
        mDatas.addAll(mSelectedChannels);
        mDatas.add(new Channel(Channel.TYPE_OTHER, "频道推荐", ""));
//        mDatas.addAll(unselectedDatas);

        mAdapter = new ChannelAdapter(mDatas,mContext);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                return itemViewType == TYPE_MY_CHANNEL || itemViewType == Channel.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });
        ItemDragHelperCallBack callBack = new ItemDragHelperCallBack(this);
        mHelper = new ItemTouchHelper(callBack);
        mAdapter.setOnChannelDragListener(this);
        //attachRecyclerView
        mHelper.attachToRecyclerView(mRecyclerView);
    }

    private DialogInterface.OnDismissListener mOnDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        if (starPos < 2 || endPos < 2) {
            return;
        }
        //我的频道之间移动
        if (mOnChannelListener != null) {
            //去除标题所占的一个index
            mOnChannelListener.onItemMove(starPos - 1, endPos - 1);
        }
        onMove(starPos, endPos);
    }

    private void onMove(int starPos, int endPos) {
        Channel startChannel = mDatas.get(starPos);
        //先删除之前的位置
        mDatas.remove(starPos);
        //添加到现在的位置
        mDatas.add(endPos, startChannel);
        mAdapter.notifyItemMoved(starPos, endPos);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        //移动到我的频道
        onMove(starPos, endPos);
        if (mOnChannelListener != null) {
            mOnChannelListener.onMoveToMyChannel(starPos - 1 - mAdapter.getMyChannelSize(), endPos - 1);
        }
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        //移动到推荐频道
        onMove(starPos, endPos);
        if (mOnChannelListener != null) {
            mOnChannelListener.onMoveToOtherChannel(starPos - 1, endPos - 2 - mAdapter.getMyChannelSize());
        }
    }

    @Override
    public void onStarDrag(RecyclerView.ViewHolder baseViewHolder) {
        //开始拖动
        mHelper.startDrag(baseViewHolder);
    }
}
