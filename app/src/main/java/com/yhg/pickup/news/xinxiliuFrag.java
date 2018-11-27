//package com.yhg.pickup.news;
//
///**
// * Created by Administrator on 2018/11/20.
// */
//
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//
//import java.util.Date;
//
//import static android.content.Context.MODE_PRIVATE;package com.www.ccoocity.ui.main.xinxiliu;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.text.TextUtils;
//import android.text.format.DateFormat;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.bayescom.sdk.BayesNativeListener;
//import com.bayescom.sdk.BayesSdkConfig;
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.google.gson.reflect.TypeToken;
//import com.www.ccoocity.callback.ILeftAndRightLoadDataListener;
//import com.www.ccoocity.db.HomeModel;
//import com.www.ccoocity.db.HomeReadModel;
//import com.www.ccoocity.manager.HttpParamsTool;
//import com.www.ccoocity.parser.Parameter;
//import com.www.ccoocity.service.DetailCacheService;
//import com.www.ccoocity.thirdpart.ads.AdvertiseView;
//import com.www.ccoocity.tools.Constants;
//import com.www.ccoocity.tools.ListviewTool;
//import com.www.ccoocity.tools.MoveTool;
//import com.www.ccoocity.ui.CcooApp;
//import com.www.ccoocity.ui.R;
//import com.www.ccoocity.ui.common.CommonBaseFragment;
//import com.www.ccoocity.ui.common.CommonResult;
//import com.www.ccoocity.ui.main.MainCcooActivity;
//import com.www.ccoocity.ui.main.MainWelcomeTool;
//import com.www.ccoocity.ui.main.mainTool.MainNavAddTool;
//import com.www.ccoocity.ui.main.mainTool.ViewPagerScrollTools;
//import com.www.ccoocity.ui.main.newhead.HomePressRefresh;
//import com.www.ccoocity.ui.main.xinxiliu.adapter.MyBottomAdapter;
//import com.www.ccoocity.ui.main.xinxiliu.model.IntelligcenGroup;
//import com.www.ccoocity.ui.main.xinxiliu.model.IntelligcenGroupData;
//import com.www.ccoocity.ui.main.xinxiliu.model.MyMainBean;
//import com.www.ccoocity.ui.main.xinxiliu.model.XinXiLiuBean;
//import com.www.ccoocity.ui.mall.bean.MallMainBean;
//import com.www.ccoocity.ui.my.JTools;
//import com.www.ccoocity.ui.task.Tool;
//import com.www.ccoocity.ui.topline.model.TopFabuBean;
//import com.www.ccoocity.util.CacheDataBean;
//import com.www.ccoocity.util.CustomToast;
//import com.www.ccoocity.util.DetailCacheTools;
//import com.www.ccoocity.util.LogUtils;
//import com.www.ccoocity.util.PageTurnUtils;
//import com.www.ccoocity.util.PublicUtils;
//import com.www.ccoocity.util.json.BaseJsonParser;
//import com.www.ccoocity.view.XListView;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import static android.content.Context.MODE_PRIVATE;
//
//
///**
// * @author Administrator
// *         首页头条-信息流
// */
//public class XinXiLiuFragment extends CommonBaseFragment implements
//        View.OnClickListener, HomePressRefresh, ILeftAndRightLoadDataListener {
//    /** 工具 */
//    private PublicUtils utils;
//    private LayoutInflater inflater;
//    /** 详细Listveiw的适配器 */
//    private MyBottomAdapter adapter1;
//    /** listview的上下部操控组件 */
//    private ListviewTool listTool1;
//    /** 下拉刷新 */
//    private MyRush1 myRush1;
//    /** 加载更多 */
//    private MyMore1 myMore1;
//    /** 轮播图下方菜单控制 */
//    private MainNavAddTool mainAddTool;
//    private XListView lv1;
//    private View layoutLoad1, layoutFail1;
//    /** 上方轮播图 */
//    private ViewPager topViewPager;
//    /** 上方轮播图的5个红点 */
//    private ImageView roundImage1, roundImage2, roundImage3, roundImage4, roundImage5;
//    private HttpParamsTool.PostHandler guanggaoHandler;
//    private List<View> viewpagerViewData = new ArrayList<View>();
//    /** 盛放上方轮播图的5个红点數組 */
//    private ImageView[] roundImage;
//    /** 轮播图信息缓存-键 */
//    private String param = "";
//    private ViewPagerScrollTools scroller;
//    /** 轮播图params */
//    private RelativeLayout.LayoutParams params;
//    /** 轮播图下方菜单view */
//    private LinearLayout addNavLayout;
//    /** 更新数目 */
//    private String mGnum;
//    /** 是否显示 更新数目（用于记录开启时候是否点击了跳过广告） */
//    private boolean isXianshi;
//    /** 适配器数据源 */
//    private List<Object> data1;
//    /** 新增数据源 */
//    private List<Object> newDataList = new ArrayList<>();
//    /** 页数  注：下拉刷新失败还原页数 */
//    private int page1 = 1;
//    /** 是否能加载更多 */
//    private boolean isAll1 = false;
//    /** 更新提示view */
//    private TextView topRefreshText;
//    /** 信息缓存-键 */
//    private String listParams = "";
//    private Activity mActivity;
//    /** 下拉0；上啦1 */
//    private int operate = 1;
//    private int minID = 0;
//    /** 当前智能组id */
//    private int intellGroupLast = 0;
//    /** 当前智能组循环次数 */
//    private String IntelligcenGroupRequireData = "";
//    /** 第一个没有置顶的角标（下一个需要置顶或者不置顶的都要从这个角标加起）*/
//    private int mNextTopIndex;
//    /** 是否是加载的缓存 */
//    private boolean isLoadCache;
//    /** 发布按钮 */
//    private SimpleDraweeView fabu_image;
//    /** 是否显示发布 */
//    private boolean isShowFabu;
//    private int minHisId = 0;
//    private Handler hadler;
//    private boolean isTouch;
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.mActivity = activity;
//    }
//
//    public static XinXiLiuFragment getInstance(int type) {
//        XinXiLiuFragment fm = new XinXiLiuFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("type", type);
//        fm.setArguments(bundle);
//        return fm;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = LayoutInflater.from(mActivity).inflate(R.layout.replytome_layout, null);
//        initTools();
//        initHandler();
//        initView(view);
////        isPrepared = true;
//        loadData();
//        return view;
//    }
//
//    public void requestMethod2() {
//        if (isLoading && isAll1) {
//            return;
//        }
//        isLoading = true;
//        HttpParamsTool.post(mFragment, listCreateParams(intellGroupLast), requestHandler);
//    }
//
//    private String listCreateParams(int intelnum) {
//        JSONObject jo = new JSONObject();
//        try {
//            jo.put("appid", CcooApp.PHONEID);
//            jo.put("siteID", PublicUtils.getCityId());
//            jo.put("userID", PublicUtils.getUserIDInt());
//            jo.put("page", page1);
//            jo.put("pageSize", 16);
//            if (page1 == 1) {
//                listParams = Constants.PHSocket_GetHeadShareInfo + jo.toString();
//            }
//            jo.put("minID", minID);
//            jo.put("operate", operate);
//            jo.put("intellGroup", intelnum);
//            jo.put("IntelligcenGroupRequireData", IntelligcenGroupRequireData);
//            jo.put("minHisId", minHisId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String params = Parameter.createnewsParam(Constants.PHSocket_GetHeadShareInfo, jo);
//        return params;
//    }
//
//    public void initTools() {
//        utils = new PublicUtils(mActivity);
//        inflater = LayoutInflater.from(mActivity);
//        data1 = new ArrayList<>();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            Constants.HOME_KEJIAN = 1;
//        }else {
//            Constants.HOME_KEJIAN = 0;
//        }
//    }
//
//    public void initView(View view) {
//        view.findViewById(R.id.mentcent_title).setVisibility(View.GONE);
//        lv1 = view.findViewById(R.id.lv);
//        // 总listview的适配器
//        adapter1 = new MyBottomAdapter(mActivity);
//        // 下拉刷新
//        myRush1 = new MyRush1();
//        // 加载更多
//        myMore1 = new MyMore1();
//        // listview的上下部操控组件
//        listTool1 = new ListviewTool(lv1, mActivity);
//        listTool1.MyChangeFootView2();
//        topRefreshText = view.findViewById(R.id.toprefresh_text);
//        lv1.addHeaderView(setHeader());
//        lv1.setAdapter(adapter1);
//        // 下拉
//        lv1.setXListViewListener(myRush1);
//        // 上拉隐藏调试
//        lv1.setOnScrollListener(myMore1);
//        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                //倍业广告暂时屏蔽
////                if (view instanceof AdvertiseView) {
////                    //倍业广告点击上报
////                    ((AdvertiseView) view).adDidClick(view);
////                    return;
////                }
//                int item = position - 2;
//                if (item >= 0 && item < data1.size()) {
//                    //根据判断是否跳转改变
//                    if (data1.get(item) instanceof XinXiLiuBean && ((XinXiLiuBean) data1.get(item)).getStyle() == 0) {
//                        MyMainBean myMainBean = (MyMainBean) data1.get(item);
//                        releaseSharePVSummary(myMainBean.getID() + "");
//                        PageTurnUtils.turnPage(myMainBean.getFirstType() + ","
//                                + myMainBean.getSecondType() + ","
//                                + myMainBean.getTheirID() + "," +
//                                myMainBean.getPartID(), "1", myMainBean.getUrl(), "", mActivity);
//                        if (!HomeReadModel.isHavaInfo(myMainBean.getID() + "", myMainBean.getFirstType(), myMainBean.getTheirID())) {
//                            HomeReadModel mHomeReadModel = new HomeReadModel(myMainBean.getID() + "", myMainBean.getFirstType(), myMainBean.getTheirID());
//                            mHomeReadModel.save();
//                            adapter1.notifyDataSetChanged();
//                        }
//                    }
//                }
//            }
//        });
//        layoutLoad1 = view.findViewById(R.id.layout_load);
//        layoutFail1 = view.findViewById(R.id.layout_fail);
//        layoutFail1.setOnClickListener(this);
//        //发布按钮
//        fabu_image= view.findViewById(R.id.fabu_image);
//        SharedPreferences  sp = getActivity().getSharedPreferences("cla", MODE_PRIVATE);
//        if (sp.getString("index4", "").equals("")) {
//            fabu_image.setVisibility(View.INVISIBLE);
//        } else {
//            isShowFabu=true;
//            fabu_image.setVisibility(View.VISIBLE);
//            fabu_image.setImageURI(Uri.parse(sp.getString("index4", "")));
//            fabu_image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    EventBus.getDefault().post(new TopFabuBean(getActivity().toString()));
//                }
//            });
//        }
//    }
//
//    /**
//     * 信息流点击统计
//     *
//     * @param releaseId
//     */
//    private void releaseSharePVSummary(String releaseId) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("siteID", PublicUtils.getCityId());
//            jsonObject.put("releaseId", Integer.parseInt(releaseId));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String params = Parameter.createnewsParam(Constants.PHSocket_ReleaseSharePVSummary, jsonObject);
//        HttpParamsTool.post(mFragment, params, new HttpParamsTool.PostHandler());
//    }
//
//    public View setHeader() {
//        View view = inflater.inflate(R.layout.newhead_headerview, null);
//        topViewPager = view.findViewById(R.id.top_viewpager);
//        params = (RelativeLayout.LayoutParams) topViewPager.getLayoutParams();
//        params.height = (int) (((float) CcooApp.getScreenWidth(mActivity)) * (250.0 / 720.0)) + 1;
//        topViewPager.setLayoutParams(params);
//        scroller = new ViewPagerScrollTools(mActivity);
//        scroller.initViewPagerScroll(topViewPager);
//        roundImage1 = view.findViewById(R.id.round_image1);
//        roundImage2 = view.findViewById(R.id.round_image2);
//        roundImage3 = view.findViewById(R.id.round_image3);
//        roundImage4 = view.findViewById(R.id.round_image4);
//        roundImage5 = view.findViewById(R.id.round_image5);
//        // 红圆圈数组，用来循环显示
//        roundImage = new ImageView[]{roundImage1, roundImage2, roundImage3, roundImage4, roundImage5};
//        HttpParamsTool.post(mFragment, guanggaoCreateParams(), guanggaoHandler);
//        addNavLayout = view.findViewById(R.id.add_nav_layout);
//        mainAddTool = new MainNavAddTool(addNavLayout, mActivity);
//        mainAddTool.addView();
//        return view;
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//
//    class MyPagerAdapter extends PagerAdapter {
//
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {// 必须重写的方法，样式固定
//            return arg0 == arg1;
//        }
//
//        @Override
//        public int getCount() {// 返回条目数
//            return viewpagerViewData.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {// 当到对应页面后，会调用此方法，返回字符串作为title，且实参是下标
//            return null;
//        }
//
//        @Override
//        public void destroyItem(View container, int position, Object object) {// 不重写可能会报错
//            ((ViewPager) container).removeView(viewpagerViewData.get(position));
//        }
//
//        @Override
//        public Object instantiateItem(View container, final int position) {// 当到对应页面后，会调用此方法，用LayoutInflater布局设置View对象，返回view对象，作为对应页面的view
//            View view = viewpagerViewData.get(position);
//            ViewGroup p = (ViewGroup) view.getParent();
//            if (p != null) {
//                p.removeAllViewsInLayout();
//            }
//            ((ViewPager) container).addView(view);
//            return view;
//        }
//
//    }
//
//    /**
//     * 信息流数据列表
//     */
//    HttpParamsTool.PostHandler requestHandler;
//
//    /**
//     * 获取数据失败或者数据请求失败
//     */
//    private void showErrorPage() {
//        layoutFail1.setVisibility(View.VISIBLE);
//        layoutLoad1.setVisibility(View.GONE);
//    }
//
//    public void initHandler() {
//        requestHandler = new HttpParamsTool.PostHandler() {
//            @Override
//            public void onSuccess(String arg0) {
//                super.onSuccess(arg0);
//                lv1.stopRefresh();
//                lv1.stopLoadMore();
//                if (operate != 0) {
//                    listTool1.removeFootView();
//                }
//                isLoading = false;
//                CommonResult commonResult = CommonResult.parserStatus(arg0);
//                if (commonResult.success()) {
//                    List<Object> list = parseResponseData(arg0);
//                    if (list != null && list.size() > 0) {
//                        if (operate == 0) {
//                            //添加缓存如果是下拉的就拼接
//                            if (HomeModel.getCacheData(HomeModel.class, listParams) != null) {
//                                cacheUpData(arg0, HomeModel.getCacheData(HomeModel.class, listParams));
//                            } else {
//                                HomeModel.saveAndUpdate(HomeModel.class, listParams, arg0);
//                            }
//                        } else if (operate != 0 && page1 == 1) {
//                            HomeModel.saveAndUpdate(HomeModel.class, listParams, arg0);
//                        }
//                    }
//                    //上拉
//                    if (operate != 0) {
//                        //判断数据是否已经加载完
//                        if (list.size() < 16) {
//                            isAll1 = true;
//                            listTool1.addAllFootView();
//                        }
//                    }
//                    if (data1 == null) {
//                        data1 = new ArrayList<>();
//                    }
//                    if (operate == 0) {
//                        //下拉数据置顶的放最前面，不置顶的放放到数据列表的置顶的后面
//                        mNextTopIndex = 0;
//                        for (int i = 0; i < data1.size(); i++) {
//                            if (data1.get(i) instanceof XinXiLiuBean && ((XinXiLiuBean) data1.get(i)).getStyle() == 0) {
//                                MyMainBean myMainBeany = (MyMainBean) data1.get(i);
//                                if (myMainBeany.getIsTop() == 1) {
//                                    mNextTopIndex = i + 1;
//                                } else {
//                                    break;
//                                }
//                            }
//                        }
//                        for (int i = 0; i < list.size(); i++) {
//                            if (list.get(i) instanceof XinXiLiuBean && ((XinXiLiuBean) list.get(i)).getStyle() == 0) {
//                                MyMainBean myMainBeany = (MyMainBean) list.get(i);
//                                if (myMainBeany.getIsTop() == 1) {
//                                    data1.add(0,list.get(i));
//                                    mNextTopIndex++;
//                                } else {
//                                    data1.add(mNextTopIndex, list.get(i));
//                                }
//                            }
//                        }
//                    } else {
//                        data1.addAll(list);
//                    }
//                    layoutFail1.setVisibility(View.GONE);
//                    layoutLoad1.setVisibility(View.GONE);
//                    lv1.setVisibility(View.VISIBLE);
//                    adapter1.setDataList(data1);
//                    //详情页面缓存
//                    cacheDetail();
//                } else {
//                    CustomToast.showToast(mActivity, commonResult.getMsg());
//                    if (operate != 0) {
//                        if (page1 == 1) {
//                            readChche();
//                        }
//                        page1 = page1 - 1 < 1 ? 1 : page1 - 1;
//                    }
//                    if (page1 == 1 && operate != 0 && (data1 == null || data1.size() <= 0)) {
//                        //第一页没有获取到数据或者获取数据失败,显示失败页面
//                        showErrorPage();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable arg0) {
//                utils.showConnectFail(arg0);
//                lv1.stopRefresh();
//                lv1.stopLoadMore();
//                if (operate != 0) {
//                    listTool1.removeFootView();
//                }
//                isLoading = false;
//                if (operate != 0) {
//                    /**
//                     * 如果第一页加载失败判断是否有缓存（不论是否过期）
//                     */
//                    if (page1 == 1) {
//                        readChche();
//                    }
//                    page1 = page1 - 1 < 1 ? 1 : page1 - 1;
//                }
//                if (page1 == 1 && (data1 == null || data1.size() <= 0)) {
//                    //第一页没有获取到数据或者获取数据失败,显示失败页面
//                    showErrorPage();
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                isLoading = false;
////                sendUpdateLeftAndRightNotice();
//            }
//        };
//        // 轮播图广告handler
//        guanggaoHandler = new HttpParamsTool.PostHandler() {
//
//            @Override
//            public void onSuccess(String arg0) {
//                super.onSuccess(arg0);
//                if (new JTools(mActivity, utils).success(arg0)) {
//                    setGuangGaoData(arg0);
//                    HomeModel.saveAndUpdate(HomeModel.class, param, arg0);
//                }else {
//                    HomeModel homeModel = HomeModel.getCacheData(HomeModel.class, param);
//                    if (homeModel != null) {
//                        if (new JTools(mActivity, utils).success(homeModel.getContent())) {
//                            setGuangGaoData(homeModel.getContent());
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable arg0) {
//                // TODO Auto-generated method stub
//                Log.i("失败", "失败" + arg0);
//                utils.showConnectFail(arg0);
//                HomeModel homeModel = HomeModel.getCacheData(HomeModel.class, param);
//                if (homeModel != null) {
//                    if (new JTools(mActivity, utils).success(homeModel.getContent())) {
//                        setGuangGaoData(homeModel.getContent());
//                        if (!PublicUtils.isNetworkAvailable(CcooApp.getInstance())) {
//                            onFinish();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                Log.i("完成", "請求完畢");
//                if (viewpagerViewData.size() == 0) {
//                    for (int i = 0; i < 4; i++) {
//                        View view = inflater.inflate(R.layout.mall_main_viewpager_item, null);
//                        ImageView image = view.findViewById(R.id.image);
//                        image.setOnTouchListener(new MyTouchListener());
//                        //嵌套布局无法锁定焦点触发ontouch后续事件 所以加入onclick强焦点
//                        image.setOnClickListener(v -> {
//                        });
//                        if (i == 0) {
//                            image.setImageResource(R.drawable.my_main_advice1);
//                        } else if (i == 1) {
//                            image.setImageResource(R.drawable.my_main_advice2);
//                        } else if (i == 2) {
//                            image.setImageResource(R.drawable.my_main_advice3);
//                        } else {
//                            image.setImageResource(R.drawable.my_main_advice4);
//                        }
//                        viewpagerViewData.add(view);
//                    }
//                    if (topViewPager != null && topViewPager.getAdapter() != null) {
//                        topViewPager.getAdapter().notifyDataSetChanged();
//                    }
//                }
//                setTopViewPager();
//            }
//        };
//    }
//
//    /**
//     * 读取缓存
//     */
//    private void readChche() {
//        try {
//            if (page1 == 1) {
//                HomeModel homeModel = HomeModel.getCacheData(HomeModel.class, listParams);
//                if (homeModel != null) {
//                    if (CommonResult.isSuccess(homeModel.getContent())) {
//                        isLoadCache = true;
//                        List<Object> list = parseResponseData(homeModel.getContent());
//                        if (data1 == null) {
//                            data1 = new ArrayList<>();
//                        }
//                        data1.addAll(list);
//                        //判断数据是否已经加载完
//                        if (list.size() < 16) {
//                            isAll1 = true;
//                            listTool1.addAllFootView();
//                        }
//                        layoutFail1.setVisibility(View.GONE);
//                        layoutLoad1.setVisibility(View.GONE);
//                        lv1.setVisibility(View.VISIBLE);
//                        adapter1.setDataList(data1);
//                        //详情页面缓存
//                        cacheDetail();
//                        return;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void cacheUpData(String arg0, HomeModel cacheData) {
//        try {
//            String content = arg0;
//            //下拉出来新的
//            JSONObject jsonMain = new JSONObject(content);
//            JSONObject serverObj = jsonMain.optJSONObject("ServerInfo");
//            JSONArray infoListArray = serverObj.optJSONArray("HeadTInfoList");
//            //缓存中的
//            JSONObject jsonMain1 = new JSONObject(cacheData.getContent());
//            JSONObject serverObj1 = jsonMain1.optJSONObject("ServerInfo");
//            JSONArray infoListArray2 = serverObj1.optJSONArray("HeadTInfoList");
//            //缓存中的数据添加到新的后面
//            for (int i = 0; i < infoListArray2.length(); i++) {
//                infoListArray.put(infoListArray2.get(i));
//            }
//            //缓存的数据更新一下重新放入缓存中
//            serverObj1.put("HeadTInfoList", infoListArray);
//            jsonMain1.put("ServerInfo", serverObj1);
//            content = jsonMain1.toString();
//            HomeModel.saveAndUpdate(HomeModel.class, listParams, content);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 轮播图广告请求参数拼接
//     * @return
//     */
//    private String guanggaoCreateParams() {
//        JSONObject jo = new JSONObject();
//        try {
//            jo.put("siteID", PublicUtils.getCityId());
//            jo.put("btype", 2561);
//            jo.put("rtype", 0);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        final String params = Parameter.createnewsParam(Constants.PHSocket_GetDivInfo, jo);
//        param = Constants.PHSocket_GetDivInfo + jo.toString();
//        return params;
//    }
//
//    /**
//     * 解析轮播图广告信息并配置viewpager
//     * @param jsonStr
//     */
//    public void setGuangGaoData(String jsonStr) {
//        if (jsonStr != null && !jsonStr.equals("")) {
//            try {
//                JSONObject jsonMain = new JSONObject(jsonStr);
//                if (jsonMain.get("ServerInfo") != null && !jsonMain.getString("ServerInfo").equals("null")) {
//                    JSONObject serverJson = new JSONObject(jsonMain.getString("ServerInfo"));
//                    // 下面是解析赋值viewpager
//                    JSONArray contentJsonArray = new JSONArray(serverJson.getString("DivBrandInfoList"));
//                    for (int i = 0; i < contentJsonArray.length(); i++) {
//                        JSONObject json = contentJsonArray.optJSONObject(i);
//                        final MallMainBean viewPagerBean = new MallMainBean();
//                        viewPagerBean.setTitle(json.get("Title").toString());
//                        viewPagerBean.setMemo(json.get("Memo").toString());
//                        viewPagerBean.setPic(json.get("Pic").toString());
//                        viewPagerBean.setUrlType(json.get("UrlType").toString());
//                        viewPagerBean.setWeburl(json.get("Weburl").toString());
//                        viewPagerBean.setNId(json.get("NId").toString());
//                        View view = inflater.inflate(R.layout.mall_main_viewpager_item, null);
//                        ImageView image = view.findViewById(R.id.image);
//                        new com.www.ccoocity.ui.main.mainTool.ImageTools(XinXiLiuFragment.this, image, json.get("Pic").toString(), CcooApp.getScreenWidth(mActivity), params.height, false, null);
//                        image.setOnClickListener(v -> PageTurnUtils.turnPage(viewPagerBean.getNId(),
//                                viewPagerBean.getUrlType(),
//                                viewPagerBean.getWeburl(),
//                                viewPagerBean.getTitle(), mActivity));
//                        image.setOnTouchListener(new MyTouchListener());
//                        viewpagerViewData.add(view);
//                    }
//                    if (topViewPager.getAdapter() != null) {
//                        topViewPager.getAdapter().notifyDataSetChanged();
//                    }
//                }
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    /**
//     * 设置广告轮播图
//     */
//    public void setTopViewPager() {
//        if (viewpagerViewData.size() != 0) {
//            // 设置圆点的显示的个数
//            for (int i = 1; i <= 5; i++) {
//                if (i > viewpagerViewData.size()) {
//                    roundImage[i - 1].setVisibility(View.GONE);
//                }
//            }
//            if (viewpagerViewData.size() == 1) {
//                roundImage[0].setVisibility(View.GONE);
//            }
//        } else {
//            // 设置圆点的显示的个数
//            for (int i = 1; i <= 5; i++) {
//                if (i > 4) {
//                    roundImage[i - 1].setVisibility(View.GONE);
//                }
//            }
//        }
//        topViewPager.setAdapter(new MyPagerAdapter());
//        topViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            // 当viewpager的item改变的时候
//            @Override
//            public void onPageSelected(int i) {
//                for (int j = 0; j <= 4; j++) {
//                    // 每循环一次作用的是一个圆点，整体的循环代表本次呈现的所有圆点状态
//                    if (j == i) {
//                        roundImage[j].setImageResource(R.drawable.main_tuan_dian1);
//                    } else {
//                        roundImage[j].setImageResource(R.drawable.main_tuan_dian2);
//                    }
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                mState = state;
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//                // TODO Auto-generated method stub
//            }
//        });
//        // 友盟 BUG IllegalStateException
//        try {
//            if ((getActivity() instanceof MainCcooActivity)) {
//                Intent intent = (getActivity()).getIntent();
//                if (intent.getBooleanExtra("welcomeFlag", true) && MainWelcomeTool.requestFlag) {
//                } else {
//                    timer.schedule(task, 5000, 4000);
//                }
//            }
//        } catch (Exception e) {
//        }
//
//    }
//
//    private int mState;
//    /**
//     * 定时器
//     */
//    TimerTask task = new TimerTask() {
//
//        @Override
//        public void run() {
//            if (handler != null && !isTouch && mState == 0) {
//                if (Constants.AD_DIALOG_IS_Show == 1 || Constants.UPDATE_DIALOG_IS_Show == 1) {
//                    try {
//                        if (topViewPager.getCurrentItem() != 0) {
//                            mActivity.runOnUiThread(() -> {
//                                try {
//                                    topViewPager.setCurrentItem(0, true);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    handler.sendEmptyMessage(0);
//                }
//            }
//        }
//    };
//    Timer timer = new Timer();
//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            // 友盟BUG java.lang.NullPointerException
//            try {
//                if (topViewPager.getCurrentItem() == viewpagerViewData.size() - 1) {
//                    scroller.setScrollDuration(200);
//                    topViewPager.setCurrentItem(0, true);
//                } else {
//                    scroller.setScrollDuration(1200);
//                    topViewPager.setCurrentItem(topViewPager.getCurrentItem() + 1, true);
//                }
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
//    };
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
//    }
//
//    private boolean isTran;
//    private Handler mHandler = new Handler();
//
//    public void startUpdate(final String num, int delay) {
//        mHandler.removeCallbacksAndMessages(null);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if ((operate != 0 && page1 == 1)) {
//                    if (isXianshi) {
//                        return;
//                    }
//                }
//                isXianshi = true;
//                update(num);
//            }
//        }, delay);
//    }
//
//    /**
//     * 更新提醒控制
//     * @param num
//     */
//    private void update(String num) {
//        hadler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                topRefreshText.setVisibility(View.GONE);
//                MoveTool.slideviewTop(topRefreshText, PublicUtils.dip2px(35));
//                isTran = false;
//            }
//        };
//        if (isTran) {
//            return;
//        }
//        if (!num.equals("0")) {
//            topRefreshText.setText("已更新" + num + "条信息");
//        } else {
//            topRefreshText.setText(Constants.NOTICE_NOLATEST);
//        }
//        topRefreshText.setVisibility(View.VISIBLE);
//        isTran = true;
//        // 动画添加
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    hadler.sendEmptyMessage(0);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return;
//            }
//        }.start();
//    }
//
//    /**
//     * 缓存详情数据
//     *
//     */
//    private void cacheDetail() {
//        try {
//            if (page1 <= Constants.CACHE_PAGE) {
//                // 开启缓存
//                CacheDataBean bean;
//                ArrayList<CacheDataBean> cd = new ArrayList<>();
//                if (data1 != null && data1.size() > 0) {
//                    //                for ((MyMainBean)MyMainBean b : data1) {
//                    for (int j = 0; j < data1.size(); j++) {
//                        if (data1.get(j) instanceof XinXiLiuBean && ((XinXiLiuBean) data1.get(j)).getStyle() == 0) {
//                            MyMainBean b = (MyMainBean) data1.get(j);
//                            if (b.getFirstType().equals("13")) {
//                                bean = new CacheDataBean(2, DetailCacheTools.getCacheNewsParams(b.getTheirID()));
//                                if (!DetailCacheTools.hasCacheNews(bean.getParams())) {
//                                    cd.add(bean);
//                                }
//                            } else {
//                                bean = new CacheDataBean(0, DetailCacheTools.getCacheBbsParams(Integer.parseInt(b.getTheirID())));
//                                if (!DetailCacheTools.hasCacheBbs(bean.getParams())) {
//                                    cd.add(bean);
//                                }
//                            }
//                        }
//                    }
//                }
//                DetailCacheService.startCacheService(mActivity, cd);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 数据解析
//     * @param jsonStr
//     * @return
//     */
//    private List<Object> parseResponseData(String jsonStr) {
//        List<Object> list = new ArrayList<>();
//        if (!TextUtils.isEmpty(jsonStr)) {
//            try {
//                JSONObject jsonMain = new JSONObject(jsonStr);
//                JSONObject serverObj = jsonMain.optJSONObject("ServerInfo");
//                minID = serverObj.optInt("minID", minID);
//                minHisId = serverObj.optInt("minHisId",minHisId);
//                JSONArray infoListArray = serverObj.optJSONArray("HeadTInfoList");
//                MyMainBean myMainBean;
//                if (infoListArray != null && infoListArray.length() > 0) {
//                    for (int i = 0; i < infoListArray.length(); i++) {
//                        JSONObject json = infoListArray.optJSONObject(i);
//                        myMainBean = new MyMainBean();
//                        myMainBean.setID(json.optInt("ID", 0));
//                        myMainBean.setFirstType(json.optString("FirstType", ""));
//                        myMainBean.setSecondType(json.optString("SecondType", ""));
//                        myMainBean.setTheirID(json.optString("TheirID", ""));
//                        myMainBean.setCssStyle(json.optString("CssStyle", ""));
//                        myMainBean.setTagStyle(json.optString("TagStyle", ""));
//                        myMainBean.setSourceType(json.optString("SourceType", ""));
//                        myMainBean.setIsVideo(json.optInt("IsVideo", 0));
//                        myMainBean.setUrl(json.optString("Url", ""));
//                        myMainBean.setIsTop(json.optInt("IsTop", 0));
//                        if (json.getString("Data") != null
//                                && !json.getString("Data").equals("")
//                                && !json.getString("Data").equals("null")) {
//                            JSONArray dataArray = json.optJSONArray("Data");
//                            if (dataArray != null && dataArray.length() > 0) {
//                                for (int k = 0; k < dataArray.length(); k++) {
//                                    JSONObject dataJson = dataArray.optJSONObject(k);
//                                    if (dataJson != null) {
//                                        myMainBean.setTitle(dataJson.optString("Title", ""));
//                                        myMainBean.setDescription(dataJson.optString("Description", ""));
//                                        myMainBean.setImage(dataJson.optString("Image"));
//                                        myMainBean.setImageCount(dataJson.optString("ImageCount", ""));
//                                        myMainBean.setImageCssStyle(dataJson.optString("ImageCssStyle", ""));
//                                        myMainBean.setTime(dataJson.optString("time", ""));
//                                        myMainBean.setVariable1(dataJson.optString("variable1", ""));
//                                        myMainBean.setVariable2(dataJson.optString("variable2", ""));
//                                        myMainBean.setVariable3(dataJson.optString("variable3", ""));
//                                        myMainBean.setVariable4(dataJson.optString("variable4", ""));
//                                        myMainBean.setVariable5(dataJson.optString("variable5", ""));
//                                        myMainBean.setVariable6(dataJson.optString("variable6", ""));
//                                        myMainBean.setVariable7(dataJson.optString("variable7", ""));
//                                        myMainBean.setVariable8(dataJson.optString("variable8", ""));
//                                        myMainBean.setVariable9(dataJson.optString("variable9", ""));
//                                        myMainBean.setVariable10(dataJson.optString("variable10", ""));
//                                        myMainBean.setVariable11(dataJson.optString("variable11", ""));
//                                        myMainBean.setVariable12(dataJson.optString("variable12", ""));
//                                        myMainBean.setVariable13(dataJson.optString("variable13", ""));
//                                        myMainBean.setVariable14(dataJson.optString("variable14", ""));
//                                    }
//                                }
//                            }
//                        }
//                        if (myMainBean.getTitle().equals("")
//                                && myMainBean.getVariable1().equals("")
//                                && myMainBean.getVariable8().equals("")) {
//                        } else {
//                            list.add(myMainBean);
//                        }
//                    }
//                    IntelligcenGroupData intelligcenGroups = BaseJsonParser.fromJson(serverObj.toString(), new TypeToken<IntelligcenGroupData>() {
//                    });
//                    if (intelligcenGroups != null && intelligcenGroups.getIntelligcenGroupData() != null && intelligcenGroups.getIntelligcenGroupData().size() > 0) {
//                        List<IntelligcenGroup> intelligcenGroups1 = intelligcenGroups.getIntelligcenGroupData().get(0);
//                        XinXiLiuBean mXinXiLiuBean = new XinXiLiuBean();
//                        if (intelligcenGroups1 != null && intelligcenGroups1.size() > 0) {
//                            int style = intelligcenGroups1.get(0).getStyle();
//                            //当 style 6 7 8的时候因为是竖直排列的智能组 个数不定
//                            // 所以整组添加到集合里面 在布局的时候算是多个item拼接到一块
//                            if (style == 6 || style == 7 || style == 8) {
//                                if (15 > list.size() && list.size() > 10) {
//                                    list.addAll(8, intelligcenGroups1);
//                                } else if (list.size() > 14) {
//                                    list.addAll(8, intelligcenGroups1);
//                                } else {
//                                    list.addAll(intelligcenGroups1);
//                                }
//
//                                //倍业广告测试
////                                int total = data1 == null ? 0 : data1.size();
////                                AdvertiseView advertiseView = getBayesView();
////                                advertiseView.setTag(total + list.size());
////                                putBayesAdView(advertiseView);
////                                list.add(advertiseView);
//                                //倍业广告测试
//                            } else {
//                                mXinXiLiuBean.setIntellGroup(intelligcenGroups1.get(0).getIntellGroup());
//                                mXinXiLiuBean.setStyle(intelligcenGroups1.get(0).getStyle());
//                                mXinXiLiuBean.setmGroup(intelligcenGroups1);
//                                if (list != null) {
//                                    if (list.size() < 15 && list.size() > 10) {
//                                        list.add(8, mXinXiLiuBean);
//                                    } else if (list.size() > 14) {
//                                        list.add(8, mXinXiLiuBean);
//                                    } else {
//                                        list.add(mXinXiLiuBean);
//                                    }
//
//                                    //倍业广告测试
////                                    int total = data1 == null ? 0 : data1.size();
////                                    AdvertiseView advertiseView = getBayesView();
////                                    advertiseView.setTag(total + list.size());
////                                    putBayesAdView(advertiseView);
////                                    list.add(advertiseView);
//                                    //倍业广告测试
//                                }
//                            }
//                        }
//                        //第二个智能组
//                        if (intelligcenGroups.getIntelligcenGroupData().size() > 1) {
//                            List<IntelligcenGroup> intelligcenGroups2 = intelligcenGroups.getIntelligcenGroupData().get(1);
//                            XinXiLiuBean mXinXiLiuBean2 = new XinXiLiuBean();
//                            if (intelligcenGroups2 != null && intelligcenGroups2.size() > 0) {
//                                int style = intelligcenGroups2.get(0).getStyle();
//                                if (style == 6 || style == 7 || style == 8) {
//                                    list.addAll(intelligcenGroups2);
//                                } else {
//                                    mXinXiLiuBean2.setIntellGroup(intelligcenGroups2.get(0).getIntellGroup());
//                                    mXinXiLiuBean2.setStyle(intelligcenGroups2.get(0).getStyle());
//                                    mXinXiLiuBean2.setmGroup(intelligcenGroups2);
//                                    list.add(mXinXiLiuBean2);
//                                }
//
//                                //倍业广告测试
////                                int total = data1 == null ? 0 : data1.size();
////                                AdvertiseView advertiseView = getBayesView();
////                                advertiseView.setTag(total + list.size());
////                                putBayesAdView(advertiseView);
////                                list.add(advertiseView);
//                                //倍业广告测试
//                            }
//                        }
//                    }
//                    intellGroupLast = serverObj.optInt("IntelligcenGroupItem");
//                    IntelligcenGroupRequireData = serverObj.optString("IntelligcenGroupRequireData");
//                }
//                if (!isLoadCache) {
//                    if (operate == 0) {
//                        startUpdate(jsonMain.optString("GxNum", "0"), 1200);
//                    } else if (operate != 0 && page1 == 1) {
//                        mGnum = jsonMain.optString("GxNum", "0");
//                        if (mGnum != null && !mGnum.equals("0")) {
//                            if ((getActivity() instanceof MainCcooActivity)) {
//                                Intent intent = (getActivity()).getIntent();
//                                if (intent.getBooleanExtra("welcomeFlag", true) && MainWelcomeTool.requestFlag) {
//                                    startUpdate(jsonMain.optString("GxNum", "0"), 5600);
//                                } else {
//                                    startUpdate(jsonMain.optString("GxNum", "0"), 1200);
//                                }
//                            }
//                        }
//                    }
//                }
//                //加载缓存标志初始化
//                isLoadCache=false;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    public static final String TAG = "--倍业广告--";
//
//    /**
//     * 存放倍业广告view
//     */
//    private List<AdvertiseView> bayesAdAllViews;
//
//    private void putBayesAdView(AdvertiseView advertiseView) {
//        if (bayesAdAllViews == null) {
//            bayesAdAllViews = new ArrayList<>();
//        }
//        bayesAdAllViews.add(advertiseView);
//    }
//    /**
//     * 生成倍业广告view
//     * @return
//     */
//    private AdvertiseView getBayesView() {
//        AdvertiseView advertiseView = new AdvertiseView(mActivity);
//        advertiseView.loadAd();
//        advertiseView.setBayesNativeListener(new BayesNativeListener() {
//            @Override
//            public void onAdReady() {
//                LogUtils.showLog(TAG, "onAdReady");
//            }
//
//            @Override
//            public void onAdShow() {
//                LogUtils.showLog(TAG, "onAdShow");
//            }
//
//            @Override
//            public void onAdClick() {
//                LogUtils.showLog(TAG, "onAdClick");
//            }
//
//            @Override
//            public void onAdFailed() {
//                LogUtils.showLog(TAG, "onAdFailed");
//            }
//
//            @Override
//            public void onAdReportOk(int i) {
//                String typeDes = BayesSdkConfig.getURLDesByType(i);
//                LogUtils.showLog(TAG, "onAdReportOk:" + i + "  typeDes=" + typeDes);
//            }
//
//            @Override
//            public void onAdReportFailed(int i) {
//                String typeDes = BayesSdkConfig.getURLDesByType(i);
//                LogUtils.showLog(TAG, "onAdReportFailed:" + i + "  typeDes=" + typeDes);
//            }
//
//            @Override
//            public void onAdClose() {
//                LogUtils.showLog(TAG, "onAdClose");
//            }
//        });
//
//        return advertiseView;
//    }
//
//    /**
//     * LISTVIEW下拉监听
//     */
//    private class MyRush1 implements XListView.IXListViewListener {
//
//        @Override
//        public void onRefresh() {
//            if (!isLoading) {
//                isLoading = true;
//                onLoad();// 刷新时间
//                operate = 0;
//                // 下面和刚进界面访问总列表的方式相同
//                HttpParamsTool.post(mFragment, listCreateParams(intellGroupLast), requestHandler);
//            }
//        }
//
//        @Override
//        public void onLoadMore() {
//        }
//
//        // 刷新时间
//        private void onLoad() {
//            Date time = new Date();
//            String strtime = DateFormat.format("yyyy'-'MM'-'dd'  'kk':'mm':'ss'", time).toString();
//            lv1.setRefreshTime(strtime);
//        }
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }
//
//    /**
//     * LISTVIEW上滑监听
//     */
//    private class MyMore1 implements AbsListView.OnScrollListener {
//        final SharedPreferences sp = mActivity.getSharedPreferences("cla", MODE_PRIVATE);
//
//        @Override
//        public void onScrollStateChanged(AbsListView view, int scrollState) {
//            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING || scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                if (isShowFabu) {
//                    fabu_image.setVisibility(View.GONE);
//                }
//
//            } else {
//                if (isShowFabu) {
//                    fabu_image.setVisibility(View.VISIBLE);
//                }
//            }
//            if (isAll1 || isLoading) {
//                return;
//            }
//            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                if (view.getLastVisiblePosition() >= data1.size() - 5) {
//                    isLoading = true;
//                    listTool1.addFootView();
//                    operate = 1;
//                    page1++;
//                    // 启动分页下载
//                    HttpParamsTool.post(mFragment, listCreateParams(intellGroupLast), requestHandler);
//                }
//            }
//        }
//
//        @Override
//        public void onScroll(AbsListView view, int firstVisibleItem,
//                             int visibleItemCount, int totalItemCount) {
//            //倍业广告测试  暂时屏蔽
////            if (bayesAdAllViews != null) {
////                for (AdvertiseView adView : bayesAdAllViews) {
////                    if (adView.getTag() == null) {
////                        break;
////                    }
////                    int pos = (int) adView.getTag();
////                    LogUtils.showLog(TAG, "first=" + firstVisibleItem + "  last=" + view.getLastVisiblePosition() + "  pos=" + pos);
////                    if (pos >= firstVisibleItem - 2 && pos <= view.getLastVisiblePosition() - 2) {
//                        adView.show();
////                    }
////                }
////            }
//        }
//
//    }
//
//    /**
//     * 点击事件
//     */
//    @Override
//    public void onClick(View v) {
//        // TODO Auto-generated method stub
//        switch (v.getId()) {
//            case R.id.layout_fail:
//                if (Tool.isNetworkConnected(mActivity)) {
//                    layoutFail1.setVisibility(View.GONE);
//                    layoutLoad1.setVisibility(View.VISIBLE);
//                    HttpParamsTool.post(mFragment, listCreateParams(intellGroupLast), requestHandler);
//                } else {
//                    CustomToast.showToastError1(mActivity);
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void autoRefresh() {
//        lv1.autoRefresh();
//    }
//
//    private boolean isLoading = false;
//
//    private boolean isVaild() {
//        return adapter1 != null
//                && !isAll1
//                && !isLoading
//                && layoutLoad1 != null
//                && layoutLoad1.getVisibility() == View.VISIBLE;
//    }
//
//    @Override
//    public void loadData() {
//        if (isVaild()) {
//            layoutFail1.setVisibility(View.GONE);
//            layoutLoad1.setVisibility(View.VISIBLE);
//            data1.clear();
//            requestMethod2();
//        }
//    }
//
//    @Subscribe
//    public void noticeStart(MyMainBean mMyMainBean) {
//        if (mMyMainBean != null && mGnum != null && !mGnum.equals("0")) {
//            startUpdate(mGnum, 1200);
//        }
//        try {
//            timer.schedule(task,5000,4000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        try {
//            EventBus.getDefault().unregister(this);
//            if (timer != null) {
//                timer.cancel();
//                timer = null;
//            }
//            if (task != null) {
//                task.cancel();
//                task = null;
//            }
//            if (handler != null) {
//                handler.removeCallbacksAndMessages(null);
//            }
//            if (mHandler != null) {
//                mHandler.removeCallbacksAndMessages(null);
//            }
//            if (hadler != null) {
//                hadler.removeCallbacksAndMessages(null);
//            }
//            if (mainAddTool != null) {
//                mainAddTool.removeAllCallBack();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private class MyTouchListener implements View.OnTouchListener {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                case MotionEvent.ACTION_MOVE:
//                    isTouch = true;
//                    break;
//                case MotionEvent.ACTION_UP:
//                    isTouch = false;
//                    break;
//                default:
//                    isTouch = false;
//                    break;
//            }
//            return false;
//        }
//    }
//}
