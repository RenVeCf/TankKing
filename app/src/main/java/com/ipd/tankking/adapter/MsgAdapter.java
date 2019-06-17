package com.ipd.tankking.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.tankking.R;
import com.ipd.tankking.bean.MsgBean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/4.
 */
public class MsgAdapter extends BaseQuickAdapter<MsgBean.DataBeanX.DataBean, BaseViewHolder> {

    public MsgAdapter(@Nullable List<MsgBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgBean.DataBeanX.DataBean item) {

    }
}
