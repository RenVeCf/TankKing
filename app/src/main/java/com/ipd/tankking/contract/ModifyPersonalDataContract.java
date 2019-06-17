package com.ipd.tankking.contract;

import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.bean.ModifyHeadImgBean;
import com.ipd.tankking.bean.ModifyUserNameBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface ModifyPersonalDataContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultModifyUserName(ModifyUserNameBean data);

        void resultModifyHeadImg(ModifyHeadImgBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getModifyUserName(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getModifyHeadImg(TreeMap<String, RequestBody> map, String userId, boolean isDialog, boolean cancelable);
    }
}