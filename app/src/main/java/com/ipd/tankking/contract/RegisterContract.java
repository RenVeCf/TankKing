package com.ipd.tankking.contract;

import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.bean.CaptchaBean;
import com.ipd.tankking.bean.RegisterBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface RegisterContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultRegister(RegisterBean data);

        void resultCaptcha(CaptchaBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getRegister(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getCaptcha(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}