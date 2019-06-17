package com.ipd.tankking.contract;

import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.bean.AddBankBean;
import com.ipd.tankking.bean.DelBankBean;
import com.ipd.tankking.bean.ModifyBankBean;
import com.ipd.tankking.bean.SelectBankBean;
import com.ipd.tankking.bean.WithdrawBankBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface WalletContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultSelectBank(SelectBankBean data);

        void resultAddBank(AddBankBean data);

        void resultModifyBank(ModifyBankBean data);

        void resultDelBank(DelBankBean data);

        void resultWithdrawBank(WithdrawBankBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getSelectBank(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAddBank(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getModifyBank(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getDelBank(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getWithdrawBank(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}