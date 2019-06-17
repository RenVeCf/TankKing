package com.ipd.tankking.contract;

import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.bean.LoginBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface LoginContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultLogin(LoginBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getLogin(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}