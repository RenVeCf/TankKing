package com.ipd.tankking.presenter;

import android.content.Context;

import com.ipd.tankking.bean.AboutBean;
import com.ipd.tankking.contract.AboutContract;
import com.ipd.tankking.model.AboutModel;
import com.ipd.tankking.progress.ObserverResponseListener;
import com.ipd.tankking.utils.ExceptionHandle;
import com.ipd.tankking.utils.T;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class AboutPresenter extends AboutContract.Presenter {

    private AboutModel model;
    private Context context;

    public AboutPresenter(Context context) {
        this.model = new AboutModel();
        this.context = context;
    }

    @Override
    public void getAbout(boolean isDialog, boolean cancelable) {
        model.getAbout(context, isDialog, cancelable, new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAbout((AboutBean) o);
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