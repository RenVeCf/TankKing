package com.ipd.tankking.model;

import android.content.Context;

import com.ipd.tankking.api.Api;
import com.ipd.tankking.base.BaseModel;
import com.ipd.tankking.progress.ObserverResponseListener;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class DiamondStoreModel<T> extends BaseModel {

    public void getDiamondStore(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                       ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().DiamondStore(map), observerListener);
        paramSubscribe(context, Api.getApiService().getDiamondStore(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getAliPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().AliPay(map), observerListener);
        paramSubscribe(context, Api.getApiService().getDiamondAliPay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getWeChatPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                             ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().AliPay(map), observerListener);
        paramSubscribe(context, Api.getApiService().getDiamondWeChatPay(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
