package com.ipd.tankking.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

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
public class PopBankAdapter extends BaseQuickAdapter<SelectBankBean.DataBean, BaseViewHolder> {
    private ImageView ivSelectBank;

    public PopBankAdapter(@Nullable List<SelectBankBean.DataBean> data) {
        super(R.layout.adapter_pop_bank, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectBankBean.DataBean item) {
        ivSelectBank = helper.getView(R.id.iv_select_bank);
        helper.setText(R.id.tv_bank_name, item.getBank_name())
                .setText(R.id.tv_bank_code, item.getBank());
        if (item.isShow()) {
            ivSelectBank.setVisibility(View.VISIBLE);
        } else {
            ivSelectBank.setVisibility(View.GONE);
        }
    }
}
