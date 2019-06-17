package com.ipd.tankking.presenter;

import android.content.Context;

import com.ipd.tankking.bean.AliPayBean;
import com.ipd.tankking.bean.DiamondStoreBean;
import com.ipd.tankking.bean.WeChatPayBean;
import com.ipd.tankking.contract.DiamondStoreContract;
import com.ipd.tankking.model.DiamondStoreModel;
import com.ipd.tankking.progress.ObserverResponseListener;
import com.ipd.tankking.utils.ExceptionHandle;
import com.ipd.tankking.utils.T;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class DiamondStorePresenter extends DiamondStoreContract.Presenter {

    private DiamondStoreModel model;
    private Context context;

    public DiamondStorePresenter(Context context) {
        this.model = new DiamondStoreModel();
        this.context = context;
    }

    @Override
    public void getDiamondStore(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getDiamondStore(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultDiamondStore((DiamondStoreBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    T.Short(ExceptionHandle.handleException(e).message, 2);
                }
            }
        });
    }

    @Override
    public void getAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getAliPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAliPay((AliPayBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    T.Short(ExceptionHandle.handleException(e).message, 2);
                }
            }
        });
    }

    @Override
    public void getWeChatPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getWeChatPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultWeChatPay((WeChatPayBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    T.Short(ExceptionHandle.handleException(e).message, 2);
                }
            }
        });
    }
}