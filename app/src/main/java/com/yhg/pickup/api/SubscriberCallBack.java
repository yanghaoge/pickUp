package com.yhg.pickup.api;

import android.text.TextUtils;

import com.yhg.pickup.model.response.ResultResponse;

import org.reactivestreams.Subscriber;

/**
 * @author yhg
 * @description: 抽取CallBack
 * @date 2017/6/18  21:37
 */
public abstract class SubscriberCallBack<T> implements Subscriber<ResultResponse<T>> {

    @Override
    public void onNext(ResultResponse response) {
        boolean isSuccess = (!TextUtils.isEmpty(response.message) && response.message.equals("success"))
                || !TextUtils.isEmpty(response.success) && response.success.equals("true");
        if (isSuccess) {
            onSuccess((T) response.data);
        } else {
            onFailure(response);
        }
    }

    @Override
    public void onError(Throwable e) {
        onError();
    }

    protected abstract void onSuccess(T response);
    protected abstract void onError();

    protected void onFailure(ResultResponse response) {
    }

}
