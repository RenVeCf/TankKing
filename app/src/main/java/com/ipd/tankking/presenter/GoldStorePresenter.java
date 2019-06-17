package com.ipd.tankking.presenter;

import android.content.Context;

import com.ipd.tankking.bean.GoldStoreBean;
import com.ipd.tankking.contract.GoldStoreContract;
import com.ipd.tankking.model.GoldStoreModel;
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
public class GoldStorePresenter extends GoldStoreContract.Presenter {

    private GoldStoreModel model;
    private Context context;

    public GoldStorePresenter(Context context) {
        this.model = new GoldStoreModel();
        this.context = context;
    }

    @Override
    public void getGoldStore(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getGoldStore(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultGoldStore((GoldStoreBean) o);
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