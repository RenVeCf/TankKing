package com.ipd.tankking.model;

import android.content.Context;

import com.ipd.tankking.api.Api;
import com.ipd.tankking.base.BaseModel;
import com.ipd.tankking.progress.ObserverResponseListener;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class AboutModel<T> extends BaseModel {

    public void getAbout(Context context, boolean isDialog, boolean cancelable,
                         ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().About(map), observerListener);
        nullParamSubscribe(context, Api.getApiService().getAbout(), observerListener, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
