package com.ipd.tankking.presenter;

import android.content.Context;

import com.ipd.tankking.bean.AliPayBean;
import com.ipd.tankking.bean.WeChatPayBean;
import com.ipd.tankking.contract.VipPayContract;
import com.ipd.tankking.model.VipPayModel;
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
public class VipPayPresenter extends VipPayContract.Presenter {

    private VipPayModel model;
    private Context context;

    public VipPayPresenter(Context context) {
        this.model = new VipPayModel();
        this.context = context;
    }

    @Override
    public void getVipAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getVipAliPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultVipAliPay((AliPayBean) o);
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
    public void getVipWeChatPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getVipWeChatPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultVipWeChatPay((WeChatPayBean) o);
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