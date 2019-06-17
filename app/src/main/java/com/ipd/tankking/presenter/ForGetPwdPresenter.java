package com.ipd.tankking.presenter;

import android.content.Context;

import com.ipd.tankking.bean.CaptchaBean;
import com.ipd.tankking.bean.ForGetPwdBean;
import com.ipd.tankking.contract.ForGetPwdContract;
import com.ipd.tankking.model.ForGetPwdModel;
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
public class ForGetPwdPresenter extends ForGetPwdContract.Presenter {

    private ForGetPwdModel model;
    private Context context;

    public ForGetPwdPresenter(Context context) {
        this.model = new ForGetPwdModel();
        this.context = context;
    }

    @Override
    public void getForGetPwd(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getForGetPwd(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultForGetPwd((ForGetPwdBean) o);
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
    public void getCaptcha(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getCaptcha(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultCaptcha((CaptchaBean) o);
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