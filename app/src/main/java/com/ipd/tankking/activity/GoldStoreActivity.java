package com.ipd.tankking.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.bean.GoldStoreBean;
import com.ipd.tankking.contract.GoldStoreContract;
import com.ipd.tankking.presenter.GoldStorePresenter;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.DIAMOND;
import static com.ipd.tankking.common.config.IConstants.GOLD;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_96;
import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：金币商城
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/2.
 */
public class GoldStoreActivity extends BaseActivity<GoldStoreContract.View, GoldStoreContract.Presenter> implements GoldStoreContract.View {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.ib_diamond)
    ImageButton ibDiamond;
    @BindView(R.id.ll_vip_title)
    LinearLayout llVipTitle;
    @BindView(R.id.tv_gold_rule)
    TextView tvGoldRule;
    @BindView(R.id.tv_gold1_fee)
    TextView tvGold1Fee;
    @BindView(R.id.bt_buy_gold1)
    Button btBuyGold1;
    @BindView(R.id.tv_gold2_fee)
    TextView tvGold2Fee;
    @BindView(R.id.bt_buy_gold2)
    Button btBuyGold2;
    @BindView(R.id.tv_gold3_fee)
    TextView tvGold3Fee;
    @BindView(R.id.bt_buy_gold3)
    Button btBuyGold3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gold_store;
    }

    @Override
    public GoldStoreContract.Presenter createPresenter() {
        return new GoldStorePresenter(this);
    }

    @Override
    public GoldStoreContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);

        tvGold.setText(SPUtil.get(this, GOLD, "") + "");
        tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    // 兑换金币
    private void showPopWindow(final int gold) {
        final TextView tvTitle;
        final TextView tvContent;
        final Button btPopConfirm;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_not_gold, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, 900, 500, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvTitle.setText("确定要兑换 " + gold + "金币 吗？");
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText("兑换后，不可撤销");
        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_confirm);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeMap<String, String> goldStoreMap = new TreeMap<>();
                goldStoreMap.put("id", SPUtil.get(GoldStoreActivity.this, USER_ID, "") + "");
                goldStoreMap.put("coin", gold + "");
                getPresenter().getGoldStore(goldStoreMap, false, false);
                window.dismiss();
            }
        });
        // 设置PopupWindow的背景
//        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bg_user_pact)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//        window.showAsDropDown(view, 0, 0);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        // window.showAtLocation(parent, gravity, x, y);
        window.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.animTranslate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_96:
                tvGold.setText(SPUtil.get(this, GOLD, "") + "");
                tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ib_back, R.id.ib_diamond, R.id.bt_buy_gold1, R.id.bt_buy_gold2, R.id.bt_buy_gold3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.ib_diamond:
                startActivityForResult(new Intent(this, DiamondStoreActivity.class), REQUEST_CODE_96);
                break;
            case R.id.bt_buy_gold1:
                showPopWindow(100);
                break;
            case R.id.bt_buy_gold2:
                showPopWindow(500);
                break;
            case R.id.bt_buy_gold3:
                showPopWindow(1000);
                break;
        }
    }

    @Override
    public void resultGoldStore(GoldStoreBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            SPUtil.put(this, GOLD, data.getData().getMember_coin() + "");
            SPUtil.put(this, DIAMOND, data.getData().getMember_diamonds() + "");
            tvGold.setText(data.getData().getMember_coin() + "");
            tvDiamond.setText(data.getData().getMember_diamonds() + "");
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
