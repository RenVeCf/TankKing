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
public class WalletModel<T> extends BaseModel {

    public void getSelectBank(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                              ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().DelBank(map), observerListener);
        paramSubscribe(context, Api.getApiService().getSelectBank(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getAddBank(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                           ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().DelBank(map), observerListener);
        paramSubscribe(context, Api.getApiService().getAddBank(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getModifyBank(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                              ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().DelBank(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyBank(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getDelBank(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                           ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().DelBank(map), observerListener);
        paramSubscribe(context, Api.getApiService().getDelBank(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getWithdrawBank(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().DelBank(map), observerListener);
        paramSubscribe(context, Api.getApiService().getWithdrawBank(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
