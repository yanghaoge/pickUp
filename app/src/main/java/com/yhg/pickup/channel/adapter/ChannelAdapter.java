package com.yhg.pickup.channel.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yhg.pickup.R;
import com.yhg.pickup.channel.model.Channel;
import com.yhg.pickup.channel.nozzle.OnChannelDragListener;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/10/5 0005
 */

public class ChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Channel> mData;
    private boolean mIsEdit;
    private long startTime;
    // touch 间隔时间  用于分辨是否是 "点击"
    private static final long SPACE_TIME = 120;
    private RecyclerView mRecyclerView;
    private float startX;
    private float startY;
    Context mContext;

    public ChannelAdapter(List<Channel> data, Context context) {
        this.mData = data;
        this.mContext = context;
        mIsEdit = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mRecyclerView = (RecyclerView) parent;

        switch (viewType) {
            case Channel.TYPE_MY:
                return new TypeMyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_channel_title, parent, false));
            case Channel.TYPE_OTHER:
                return new TypeOtherViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_channel_title, parent, false));
            case Channel.TYPE_MY_CHANNEL:
                return new TypeMyChannelViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_channel, parent, false));
            case Channel.TYPE_OTHER_CHANNEL:
                return new TypeOtherChannelViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_channel, parent, false));
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case Channel.TYPE_MY:
                initData(holder, mData.get(position));
                break;
            case Channel.TYPE_OTHER:
                initData2(holder, mData.get(position));
                break;
            case Channel.TYPE_MY_CHANNEL:
                initData3(holder, position, mData.get(position));
                break;
            case Channel.TYPE_OTHER_CHANNEL:
                initData4(holder, position, mData.get(position));
                break;
            default:
                break;
        }
    }

    private void initData2(RecyclerView.ViewHolder holder, Channel channel) {
        final TypeOtherViewHolder holder2 = (TypeOtherViewHolder) holder;
        holder2.tvTitle.setText(channel.title);
        holder2.tvEdit.setVisibility(View.GONE);
    }

    private void initData(RecyclerView.ViewHolder holder, Channel channel) {
        final TypeMyViewHolder holder1 = (TypeMyViewHolder) holder;
        holder1.tvTitle.setText(channel.title);
        holder1.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsEdit) {
                    startEditMode(true);
                    holder1.tvEdit.setText("完成");
                } else {
                    startEditMode(false);
                    holder1.tvEdit.setText("编辑");
                }
            }
        });

    }

    private void initData3(final RecyclerView.ViewHolder holder, int position, final Channel channel) {
        final TypeMyChannelViewHolder holder3 = (TypeMyChannelViewHolder) holder;
        if (mIsEdit && !(channel.title.equals("推荐"))) {
            holder3.ivDelete.setVisibility(View.VISIBLE);
        } else {
            holder3.ivDelete.setVisibility(View.GONE);
        }
        holder3.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!mIsEdit) {
                    //开启编辑模式
                    startEditMode(true);
                    mIsEdit = true;
                }
                if (onChannelDragListener != null) {
                    if (channel.title.equals("推荐")) {
                        return false;
                    }
                    onChannelDragListener.onStarDrag(holder);
                }

                return true;
            }
        });

        holder3.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!mIsEdit) {
                    return false;//正常模式无需监听触摸
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getRawX();
                        startY = event.getRawY();
                        startTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (System.currentTimeMillis() - startTime > SPACE_TIME) {
                            //当MOVE事件与DOWN事件的触发的间隔时间大于100ms时，则认为是拖拽starDrag
                            if (onChannelDragListener != null) {
                                Log.d("ceshi", "111" + (System.currentTimeMillis() - startTime));
                                if (channel.title.equals("推荐")) {
                                    return false;
                                }
                                onChannelDragListener.onStarDrag(holder3);
                            }
                        }
                        float LastX1 = event.getRawX();
                        float LastY1 = event.getRawY();
                        if (Math.abs(startX - LastX1) < 6 && Math.abs(startY - LastY1) < 6) {
                        } else {
                            if (mIsEdit) {
                                if (onChannelDragListener != null) {
                                    if (channel.title.equals("推荐")) {
                                        return false;
                                    }
                                    Log.d("ceshi", "222");
                                    onChannelDragListener.onStarDrag(holder3);
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_UP:
                        float LastX = event.getRawX();
                        float LastY = event.getRawY();
                        if (Math.abs(startX - LastX) < 10 && Math.abs(startY - LastY) < 10) {
                            if (mIsEdit) {
                                if (channel.title.equals("推荐")) {
                                    return false;
                                }
                                Log.d("ceshi", "333");
                                int otherFirstPosition = getOtherFirstPosition();
                                int currentPosition = holder3.getAdapterPosition() - 0;
                                //获取到目标View
                                View targetView = mRecyclerView.getLayoutManager().findViewByPosition(otherFirstPosition);
                                //获取当前需要移动的View
                                View currentView = mRecyclerView.getLayoutManager().findViewByPosition(currentPosition);
                                // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                                // 如果在屏幕内,则添加一个位移动画
                                if (mRecyclerView.indexOfChild(targetView) >= 0 && otherFirstPosition != -1) {
                                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                                    int spanCount = ((GridLayoutManager) manager).getSpanCount();
                                    int targetX = targetView.getLeft();
                                    int targetY = targetView.getTop();
                                    int myChannelSize = getMyChannelSize();//这里我是为了偷懒 ，算出来我的频道的大小
                                    if (myChannelSize % spanCount == 1) {
                                        //我的频道最后一行 之后一个，移动后
                                        targetY -= targetView.getHeight();
                                    }

                                    //我的频道 移动到 推荐频道的第一个
                                    channel.setItemType(Channel.TYPE_OTHER_CHANNEL);//改为推荐频道类型

                                    if (onChannelDragListener != null) {
                                        onChannelDragListener.onMoveToOtherChannel(currentPosition, otherFirstPosition - 1);
                                    }
                                    startAnimation(currentView, targetX, targetY);
                                } else {
                                    channel.setItemType(Channel.TYPE_OTHER_CHANNEL);//改为推荐频道类型
                                    if (otherFirstPosition == -1) {
                                        otherFirstPosition = mData.size();
                                    }
                                    if (onChannelDragListener != null) {
                                        onChannelDragListener.onMoveToOtherChannel(currentPosition, otherFirstPosition - 1);
                                    }
                                }
//                            GlobalParams.mRemovedChannels.add(channel);
                            }
                        }
                        startTime = 0;
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        holder3.ivDelete.setTag(true);//在我的频道里面设置true标示，之后会根据这个标示来判断编辑模式是否显示
        holder3.tvChannel.setText(channel.title);
        holder3.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行删除，移动到推荐频道列表
                if (mIsEdit) {
                    if (channel.title.equals("推荐")) {
                        return;
                    }
                    int otherFirstPosition = getOtherFirstPosition();
                    int currentPosition = holder3.getAdapterPosition() - 0;
                    //获取到目标View
                    View targetView = mRecyclerView.getLayoutManager().findViewByPosition(otherFirstPosition);
                    //获取当前需要移动的View
                    View currentView = mRecyclerView.getLayoutManager().findViewByPosition(currentPosition);
                    // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                    // 如果在屏幕内,则添加一个位移动画
                    if (mRecyclerView.indexOfChild(targetView) >= 0 && otherFirstPosition != -1) {
                        RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                        int spanCount = ((GridLayoutManager) manager).getSpanCount();
                        int targetX = targetView.getLeft();
                        int targetY = targetView.getTop();
                        int myChannelSize = getMyChannelSize();//这里我是为了偷懒 ，算出来我的频道的大小
                        if (myChannelSize % spanCount == 1) {
                            //我的频道最后一行 之后一个，移动后
                            targetY -= targetView.getHeight();
                        }

                        //我的频道 移动到 推荐频道的第一个
                        channel.setItemType(Channel.TYPE_OTHER_CHANNEL);//改为推荐频道类型

                        if (onChannelDragListener != null) {
                            onChannelDragListener.onMoveToOtherChannel(currentPosition, otherFirstPosition - 1);
                        }
                        startAnimation(currentView, targetX, targetY);
                    } else {
                        channel.setItemType(Channel.TYPE_OTHER_CHANNEL);//改为推荐频道类型
                        if (otherFirstPosition == -1) {
                            otherFirstPosition = mData.size();
                        }
                        if (onChannelDragListener != null) {
                            onChannelDragListener.onMoveToOtherChannel(currentPosition, otherFirstPosition - 1);
                        }
                    }
//                            GlobalParams.mRemovedChannels.add(channel);
                }
            }
        });
    }

    private void initData4(RecyclerView.ViewHolder holder, int position, final Channel channel) {
        final TypeOtherChannelViewHolder holder4 = (TypeOtherChannelViewHolder) holder;
        holder4.tvChannel.setText(channel.title);
        holder4.ivDelete.setVisibility(View.GONE);
//频道推荐列表

        holder4.tvChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int myLastPosition = getMyLastPosition();
                int currentPosition = holder4.getAdapterPosition() - 0;
                //获取到目标View
                View targetView = mRecyclerView.getLayoutManager().findViewByPosition(myLastPosition);
                //获取当前需要移动的View
                View currentView = mRecyclerView.getLayoutManager().findViewByPosition(currentPosition);
                // 如果targetView不在屏幕内,则indexOfChild为-1  此时不需要添加动画,因为此时notifyItemMoved自带一个向目标移动的动画
                // 如果在屏幕内,则添加一个位移动画
                if (mRecyclerView.indexOfChild(targetView) >= 0 && myLastPosition != -1) {
                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                    int spanCount = ((GridLayoutManager) manager).getSpanCount();
                    int targetX = targetView.getLeft() + targetView.getWidth();
                    int targetY = targetView.getTop();

                    int myChannelSize = getMyChannelSize();//这里我是为了偷懒 ，算出来我的频道的大小
                    if (myChannelSize % spanCount == 0) {
                        //添加到我的频道后会换行，所以找到倒数第4个的位置

                        View lastFourthView = mRecyclerView.getLayoutManager().findViewByPosition(getMyLastPosition() - 3);
//                                        View lastFourthView = mRecyclerView.getChildAt(getMyLastPosition() - 3);
                        targetX = lastFourthView.getLeft();
                        targetY = lastFourthView.getTop() + lastFourthView.getHeight();
                    }


                    // 推荐频道 移动到 我的频道的最后一个
                    channel.setItemType(Channel.TYPE_MY_CHANNEL);//改为推荐频道类型
                    if (onChannelDragListener != null) {
                        onChannelDragListener.onMoveToMyChannel(currentPosition, myLastPosition + 1);
                    }
                    startAnimation(currentView, targetX, targetY);
                } else {
                    channel.setItemType(Channel.TYPE_MY_CHANNEL);//改为推荐频道类型
                    if (myLastPosition == -1) {
                        myLastPosition = 0;//我的频道没有了，改成0
                    }
                    if (onChannelDragListener != null) {
                        onChannelDragListener.onMoveToMyChannel(currentPosition, myLastPosition + 1);
                    }
                }
//                                GlobalParams.mRemovedChannels.remove(channel);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }


    private OnChannelDragListener onChannelDragListener;

    public void setOnChannelDragListener(OnChannelDragListener onChannelDragListener) {
        this.onChannelDragListener = onChannelDragListener;
    }

    public int getMyChannelSize() {
        int size = 0;
        for (int i = 0; i < mData.size(); i++) {
            Channel channel = (Channel) mData.get(i);
            if (channel.getItemType() == Channel.TYPE_MY_CHANNEL) {
                size++;
            }
        }
        return size;

    }

    private void startAnimation(final View currentView, int targetX, int targetY) {
        final ViewGroup parent = (ViewGroup) mRecyclerView.getParent();
        final ImageView mirrorView = addMirrorView(parent, currentView);
        TranslateAnimation animator = getTranslateAnimator(targetX - currentView.getLeft(), targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);//暂时隐藏
        mirrorView.startAnimation(animator);
        animator.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parent.removeView(mirrorView);//删除添加的镜像View
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);//显示隐藏的View
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 添加需要移动的 镜像View
     */
    private ImageView addMirrorView(ViewGroup parent, View view) {
        view.destroyDrawingCache();
        //首先开启Cache图片 ，然后调用view.getDrawingCache()就可以获取Cache图片
        view.setDrawingCacheEnabled(true);
        ImageView mirrorView = new ImageView(view.getContext());
        //获取该view的Cache图片
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        //销毁掉cache图片
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);//获取当前View的坐标
        int[] parenLocations = new int[2];
        mRecyclerView.getLocationOnScreen(parenLocations);//获取RecyclerView所在坐标
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);//在RecyclerView的Parent添加我们的镜像View，parent要是FrameLayout这样才可以放到那个坐标点
        return mirrorView;
    }

    private int ANIM_TIME = 360;

    /**
     * 获取位移动画
     */
    private TranslateAnimation getTranslateAnimator(float targetX, float targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetX,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.ABSOLUTE, targetY);
        // RecyclerView默认移动动画250ms 这里设置360ms 是为了防止在位移动画结束后 remove(view)过早 导致闪烁
        translateAnimation.setDuration(ANIM_TIME);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    /**
     * 获取推荐频道列表的第一个position
     *
     * @return
     */
    private int getOtherFirstPosition() {
        //之前找到了第一个pos直接返回
//        if (mOtherFirstPosition != 0) return mOtherFirstPosition;
        for (int i = 0; i < mData.size(); i++) {
            Channel channel = (Channel) mData.get(i);
            if (Channel.TYPE_OTHER_CHANNEL == channel.getItemType()) {
                //找到第一个直接返回
                return i;
            }
        }
        return -1;
    }

    /**
     * 我的频道最后一个的position
     *
     * @return
     */
    private int getMyLastPosition() {
        for (int i = mData.size() - 1; i > -1; i--) {
            Channel channel = (Channel) mData.get(i);
            if (Channel.TYPE_MY_CHANNEL == channel.getItemType()) {
                //找到第一个直接返回
                return i;
            }
        }
        return -1;
    }

    /**
     * 开启编辑模式
     */
    private void startEditMode(boolean isEdit) {
        mIsEdit = isEdit;
        int visibleChildCount = mRecyclerView.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = mRecyclerView.getChildAt(i);
            ImageView imgEdit = (ImageView) view.findViewById(R.id.ivDelete);
            if (imgEdit != null) {
                boolean isVis = imgEdit.getTag() == null ? false : (boolean) imgEdit.getTag();
                imgEdit.setVisibility(isVis && isEdit && !mData.get(i).title.equals("推荐") ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }
}
