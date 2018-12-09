package com.yhg.pickup.utils;

import android.app.Application;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yhg.pickup.common.Constans;
import com.yhg.pickup.params.Parameter;

import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 *
 * @author Administrator
 * @date 2018/11/26
 */

public class NetUtils {
    /**
     * 初始化网络配置
     * @param application
     */
    public static void initNet(Application application){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(10000L, TimeUnit.MILLISECONDS);
//全局的写入超时时间
        builder.writeTimeout(10000L, TimeUnit.MILLISECONDS);
//全局的连接超时时间
        builder.connectTimeout(10000L, TimeUnit.MILLISECONDS);
        //必须调用初始化
        OkGo.getInstance().init(application)
                //建议设置OkHttpClient，不设置将使用默认的
                .setOkHttpClient(builder.build())
                //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheMode(CacheMode.NO_CACHE)
                //全局统一缓存时间，默认永不过期，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .setRetryCount(0);
    }

    public static void post(Object object, String params, NetCallBack callback) {
        OkGo.<String>post(Constans.NET_URL).tag(object).params("param", params).execute(callback);
    }

    /**
     *  下载文件
     * @param object
     * @param url
     * @param callback
     */
    public static void downFile(Object object, String url, FileCallback callback) {
        OkGo.<File>get(url).tag(object).execute(callback);
    }

    public abstract static class NetCallBack extends StringCallback {

        @Override
        public void onSuccess(Response<String> response) {
            succuss(response.body());
        }

        @Override
        public void onError(Response<String> response) {
            super.onError(response);
        }

        @Override
        public void onFinish() {
            super.onFinish();
        }

      public abstract void succuss(String result);
      public abstract void error(String result);
    }

    private void initView(View view) {

        JSONObject jo = new JSONObject();
        try {
            jo.put("page", 1);
            jo.put("appid", 0);
            jo.put("siteID", 920);
            jo.put("userID", 0);
            jo.put("pageSize", 16);

            jo.put("operate", 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String params = Parameter.createnewsParam("PHSocket_GetHeadShareInfo", jo);

//        NetUtils.post(this, params, new NetUtils.NetCallBack() {
//            @Override
//            public void succuss(String result) {
//
//            }
//
//            @Override
//            public void error(String result) {
//
//            }
//        });
    }
}
