package com.ipd.tankking.model;

import android.content.Context;

import com.ipd.tankking.api.Api;
import com.ipd.tankking.base.BaseModel;
import com.ipd.tankking.progress.ObserverResponseListener;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class ModifyPersonalDataModel<T> extends BaseModel {

    public void getModifyUserName(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                  ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().SupplementInfo(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyUserName(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getModifyHeadImg(Context context, TreeMap<String, RequestBody> map, String userId, boolean isDialog, boolean cancelable,
                                 ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyHeadImg(map, userId), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
