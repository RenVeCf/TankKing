package com.ipd.tankking.presenter;

import android.content.Context;

import com.ipd.tankking.bean.ModifyHeadImgBean;
import com.ipd.tankking.bean.ModifyUserNameBean;
import com.ipd.tankking.contract.ModifyPersonalDataContract;
import com.ipd.tankking.model.ModifyPersonalDataModel;
import com.ipd.tankking.progress.ObserverResponseListener;
import com.ipd.tankking.utils.ExceptionHandle;
import com.ipd.tankking.utils.T;

import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class ModifyPersonalDataPresenter extends ModifyPersonalDataContract.Presenter {

    private ModifyPersonalDataModel model;
    private Context context;

    public ModifyPersonalDataPresenter(Context context) {
        this.model = new ModifyPersonalDataModel();
        this.context = context;
    }

    @Override
    public void getModifyUserName(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getModifyUserName(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyUserName((ModifyUserNameBean) o);
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
    public void getModifyHeadImg(TreeMap<String, RequestBody> map, String userId, boolean isDialog, boolean cancelable) {
        model.getModifyHeadImg(context, map, userId, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyHeadImg((ModifyHeadImgBean) o);
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