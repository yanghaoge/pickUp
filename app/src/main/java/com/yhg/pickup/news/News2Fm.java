package com.yhg.pickup.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yhg.pickup.R;
import com.yhg.pickup.base.BaseFragment;
import com.yhg.pickup.utils.SystemUtils;
import com.yhg.pickup.widght.StickyItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */

public class News2Fm extends BaseFragment {

    private LayoutInflater mInflater;

    @Override
    public int getContentViewId() {
        return 0;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.fm_news, container, false);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new StickyItemDecoration());
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        recyclerView.setAdapter(new PerformerListAdapter(mContext, getData()));
    }

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width =SystemUtils.dip2px(70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;


            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(mContext).setBackground(R.color.red)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mContext, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mContext, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };
    private List<Performer> getData() {
        List<Performer> performers = new ArrayList<>();

//        Performer performer = new Performer("香港明星");
//        performers.add(performer);
//
//        Performer ldh = new Performer("刘德华", 10);
//        performers.add(ldh);
//        Performer zxy = new Performer("张学友", 10);
//        performers.add(zxy);
//        Performer zrf = new Performer("周润发", 10);
//        performers.add(zrf);
//        Performer lcw = new Performer("梁朝伟", 10);
//        performers.add(lcw);
//        Performer wyj = new Performer("吴毅将", 10);
//        performers.add(wyj);
//        Performer lm = new Performer("黎明", 10);
//        performers.add(lm);
//        Performer cgx = new Performer("陈冠希", 10);
//        performers.add(cgx);
//        Performer gfc = new Performer("郭富城", 10);
//        performers.add(gfc);
//        Performer xtf = new Performer("谢霆锋", 10);
//        performers.add(xtf);
//
//        Performer performerTw = new Performer("台湾明星：指的是中国台湾的一些有名气的电影，电视演员和歌手，他们具有很高的人气，成名时间早，成名时间久");
//        performers.add(performerTw);
//
//        Performer rxq = new Performer("任贤齐", 10);
//        performers.add(rxq);
//        Performer mtw = new Performer("孟庭苇", 10);
//        performers.add(mtw);
//        Performer ldy = new Performer("罗大佑", 10);
//        performers.add(ldy);
//        Performer lzs = new Performer("李宗盛", 10);
//        performers.add(lzs);
//        Performer xc = new Performer("小虫", 10);
//        performers.add(xc);
//        Performer zhj = new Performer("周华健", 10);
//        performers.add(zhj);
//        Performer zhl = new Performer("周杰伦", 10);
//        performers.add(zhl);
//
//        Performer performerNl = new Performer("内陆明星");
//        performers.add(performerNl);
//
//        Performer lh = new Performer("鹿晗", 10);
//        performers.add(lh);
//        Performer wzw = new Performer("王志文", 10);
//        performers.add(wzw);
//        Performer yq = new Performer("羽泉", 10);
//        performers.add(yq);
//        Performer lxl = new Performer("李小璐", 10);
//        performers.add(lxl);
//        Performer hh = new Performer("韩红", 10);
//        performers.add(hh);
//        Performer ny = new Performer("那英", 10);
//        performers.add(ny);
//        Performer lhh = new Performer("刘欢", 10);
//        performers.add(lhh);
//        Performer yk = new Performer("杨坤", 10);
//        performers.add(yk);
//        Performer zj = new Performer("周杰", 10);
//        performers.add(zj);
//
//        Performer performerOm = new Performer("美国明星");
//        performers.add(performerOm);
//        Performer mm = new Performer("梅梅", 10);
//        performers.add(mm);
//        Performer ade = new Performer("Gaga", 10);
//        performers.add(ade);
//        Performer hff = new Performer("黑寡妇", 10);
//        performers.add(hff);
//        Performer xlz = new Performer("小李子", 10);
//        performers.add(xlz);
//
//        Performer performerNba = new Performer("NBA明星");
//        performers.add(performerNba);
//        Performer xhd = new Performer("小皇帝", 10);
//        performers.add(xhd);
//        Performer kb = new Performer("科比", 10);
//        performers.add(kb);
//        Performer ym = new Performer("姚明", 10);
//        performers.add(ym);
//        Performer md = new Performer("麦迪", 10);
//        performers.add(md);
//        Performer dlt = new Performer("杜兰特", 10);
//        performers.add(dlt);
//        Performer kl = new Performer("库里", 10);
//        performers.add(kl);
//        Performer ouw = new Performer("欧文", 10);
//        performers.add(ouw);
//        Performer qd = new Performer("乔丹", 10);
//        performers.add(qd);
//        Performer alzw = new Performer("奥拉朱旺", 10);
//        performers.add(alzw);
//        Performer pp = new Performer("皮蓬", 10);
//        performers.add(pp);
//        Performer ldm = new Performer("罗德曼", 10);
//        performers.add(ldm);
//        Performer ke = new Performer("科尔", 10);
//        performers.add(ke);
//        Performer pesi = new Performer("皮尔斯", 10);
//        performers.add(pesi);
//        Performer jnt = new Performer("加内特", 10);
//        performers.add(jnt);
//        Performer lal = new Performer("雷阿伦", 10);
//        performers.add(lal);
//        Performer zmg = new Performer("字母哥", 10);
//        performers.add(zmg);
//        Performer adn = new Performer("安东尼", 10);
//        performers.add(adn);
//
//        Performer performerDy = new Performer("导演");
//        performers.add(performerDy);
//        Performer jzk = new Performer("贾樟柯", 10);
//        performers.add(jzk);
//        Performer ly = new Performer("李杨", 10);
//        performers.add(ly);
//        Performer fxg = new Performer("冯小刚", 10);
//        performers.add(fxg);
//        Performer lyy = new Performer("娄烨", 10);
//        performers.add(lyy);
//        Performer zym = new Performer("张艺谋", 10);
//        performers.add(zym);


        Performer performer = new Performer("香港明星");
        performers.add(performer);

        Performer ldh = new Performer("刘德华", 10);
        performers.add(ldh);
        Performer zxy = new Performer("张学友", 10);
        performers.add(zxy);
        Performer zrf = new Performer("周润发", 10);
        performers.add(zrf);
        Performer lcw = new Performer("梁朝伟", 10);
        performers.add(lcw);
        Performer wyj = new Performer("吴毅将", 10);
        performers.add(wyj);
        Performer lm = new Performer("黎明", 10);
        performers.add(lm);
        Performer cgx = new Performer("陈冠希", 10);
        performers.add(cgx);
        Performer gfc = new Performer("郭富城", 10);
        performers.add(gfc);
        Performer xtf = new Performer("谢霆锋", 10);
        performers.add(xtf);

        Performer performerTw = new Performer("台湾明星：指的是中国台湾的一些有名气的电影，电视演员和歌手，他们具有很高的人气，成名时间早，成名时间久");
        performers.add(performerTw);

        Performer rxq = new Performer("任贤齐", 10);
        performers.add(rxq);
        Performer mtw = new Performer("孟庭苇", 10);
        performers.add(mtw);

        Performer performerTw2 = new Performer("台湾明星：指的是中国台湾的一些有名气的电影，电视演员和歌手，他们具有很高的人气，成名时间早，成名时间久");
        performers.add(performerTw2);

        Performer rxq2 = new Performer("罗志祥", 10);
        performers.add(rxq2);

        Performer performerTw3 = new Performer("台湾明星：指的是中国台湾的一些有名气的电影，电视演员和歌手，他们具有很高的人气，成名时间早，成名时间久");
        performers.add(performerTw3);

        Performer rxq3 = new Performer("李宗盛", 10);
        performers.add(rxq3);

        Performer performerNl = new Performer("内陆明星");
        performers.add(performerNl);

        Performer lh = new Performer("鹿晗", 10);
        performers.add(lh);
        Performer wzw = new Performer("王志文", 10);
        performers.add(wzw);
        Performer yq = new Performer("羽泉", 10);
        performers.add(yq);
        Performer lxl = new Performer("李小璐", 10);
        performers.add(lxl);
        Performer hh = new Performer("韩红", 10);
        performers.add(hh);
        Performer ny = new Performer("那英", 10);
        performers.add(ny);
        Performer lhh = new Performer("刘欢", 10);
        performers.add(lhh);
        Performer yk = new Performer("杨坤", 10);
        performers.add(yk);
        Performer zj = new Performer("周杰", 10);
        performers.add(zj);

        Performer performerOm = new Performer("美国明星");
        performers.add(performerOm);
        Performer mm = new Performer("梅梅", 10);
        performers.add(mm);
        Performer ade = new Performer("Gaga", 10);
        performers.add(ade);
        Performer hff = new Performer("黑寡妇", 10);
        performers.add(hff);
        Performer xlz = new Performer("小李子", 10);
        performers.add(xlz);

        Performer performerNba = new Performer("NBA明星");
        performers.add(performerNba);
        Performer xhd = new Performer("小皇帝", 10);
        performers.add(xhd);
        Performer kb = new Performer("科比", 10);
        performers.add(kb);
        Performer ym = new Performer("姚明", 10);
        performers.add(ym);
        Performer md = new Performer("麦迪", 10);
        performers.add(md);
        Performer dlt = new Performer("杜兰特", 10);
        performers.add(dlt);
        Performer kl = new Performer("库里", 10);
        performers.add(kl);
        Performer ouw = new Performer("欧文", 10);
        performers.add(ouw);
        Performer qd = new Performer("乔丹", 10);
        performers.add(qd);
        Performer alzw = new Performer("奥拉朱旺", 10);
        performers.add(alzw);
        Performer pp = new Performer("皮蓬", 10);
        performers.add(pp);
        Performer ldm = new Performer("罗德曼", 10);
        performers.add(ldm);
        Performer ke = new Performer("科尔", 10);
        performers.add(ke);
        Performer pesi = new Performer("皮尔斯", 10);
        performers.add(pesi);
        Performer jnt = new Performer("加内特", 10);
        performers.add(jnt);
        Performer lal = new Performer("雷阿伦", 10);
        performers.add(lal);
        Performer zmg = new Performer("字母哥", 10);
        performers.add(zmg);
        Performer adn = new Performer("安东尼", 10);
        performers.add(adn);

        Performer performerDy = new Performer("导演");
        performers.add(performerDy);
        Performer jzk = new Performer("贾樟柯", 10);
        performers.add(jzk);
        Performer ly = new Performer("李杨", 10);
        performers.add(ly);
        Performer fxg = new Performer("冯小刚", 10);
        performers.add(fxg);
        Performer lyy = new Performer("娄烨", 10);
        performers.add(lyy);
        Performer zym = new Performer("张艺谋", 10);
        performers.add(zym);

        return performers;
    }

}
