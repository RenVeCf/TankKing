package com.ipd.tankking.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.tankking.R;
import com.ipd.tankking.bean.SelectBankBean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/4.
 */
public class MyBankAdapter extends BaseQuickAdapter<SelectBankBean.DataBean, BaseViewHolder> {

    public MyBankAdapter(@Nullable List<SelectBankBean.DataBean> data) {
        super(R.layout.adapter_my_bank, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectBankBean.DataBean item) {
        helper.setText(R.id.tv_bank_name, item.getBank_name())
                .setText(R.id.tv_bank_code, item.getBank())
                .addOnClickListener(R.id.ib_edit_bank)
                .addOnClickListener(R.id.ib_del_bank);
    }
}
