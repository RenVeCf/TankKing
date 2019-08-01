package com.ipd.tankking.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.aliPay.AliPay;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.bean.AliPayBean;
import com.ipd.tankking.bean.DiamondStoreBean;
import com.ipd.tankking.bean.WeChatPayBean;
import com.ipd.tankking.contract.DiamondStoreContract;
import com.ipd.tankking.presenter.DiamondStorePresenter;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.LogUtils;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.DIAMOND;
import static com.ipd.tankking.common.config.IConstants.GOLD;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_97;
import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：钻石商城
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class DiamondStoreActivity extends BaseActivity<DiamondStoreContract.View, DiamondStoreContract.Presenter> implements DiamondStoreContract.View {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.ib_gold)
    ImageButton ibGold;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.ll_vip_title)
    LinearLayout llVipTitle;
    @BindView(R.id.tv_diamond_rule)
    TextView tvDiamondRule;
    @BindView(R.id.tv_diamond1_fee)
    TextView tvDiamond1Fee;
    @BindView(R.id.bt_buy_diamond1)
    Button btBuyDiamond1;
    @BindView(R.id.bt_exchange_diamond1)
    Button btExchangeDiamond1;
    @BindView(R.id.tv_diamond2_fee)
    TextView tvDiamond2Fee;
    @BindView(R.id.bt_buy_diamond2)
    Button btBuyDiamond2;
    @BindView(R.id.bt_exchange_diamond2)
    Button btExchangeDiamond2;
    @BindView(R.id.tv_diamond3_fee)
    TextView tvDiamond3Fee;
    @BindView(R.id.bt_buy_diamond3)
    Button btBuyDiamond3;
    @BindView(R.id.bt_exchange_diamond3)
    Button btExchangeDiamond3;

    private int payType = 0; //支付方式
    private String diamondFee = ""; //选择的购买钻石数

    @Override
    public int getLayoutId() {
        return R.layout.activity_diamond_store;
    }

    @Override
    public DiamondStoreContract.Presenter createPresenter() {
        return new DiamondStorePresenter(this);
    }

    @Override
    public DiamondStoreContract.View createView() {
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

    // 购买钻石
    private void showPopWindow() {
        final TextView tvBuyTitle;
        final ImageView ivAliHook;
        final ImageView ivWeChatHook;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_buy_vip, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, 900, 700, true);
        tvBuyTitle = contentView.findViewById(R.id.tv_buy_title);
        tvBuyTitle.setText("选择支付方式");
        ivAliHook = contentView.findViewById(R.id.iv_ali_hook);
        ivWeChatHook = contentView.findViewById(R.id.iv_wechat_hook);
        contentView.findViewById(R.id.ll_ali_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAliHook.setVisibility(View.VISIBLE);
                ivWeChatHook.setVisibility(View.GONE);
                payType = 1;
            }
        });
        contentView.findViewById(R.id.ll_wechat_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAliHook.setVisibility(View.GONE);
                ivWeChatHook.setVisibility(View.VISIBLE);
                payType = 2;
            }
        });
        contentView.findViewById(R.id.bt_pop_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (payType) {
                    case 1:
                        //支付宝
                        TreeMap<String, String> aliPayMap = new TreeMap<>();
                        aliPayMap.put("uid", SPUtil.get(DiamondStoreActivity.this, USER_ID, "") + "");
                        aliPayMap.put("diamond", diamondFee);
                        aliPayMap.put("payment", "1");
                        getPresenter().getAliPay(aliPayMap, false, false);
                        break;
                    case 2:
                        //微信
                        TreeMap<String, String> weChatMap = new TreeMap<>();
                        weChatMap.put("uid", SPUtil.get(DiamondStoreActivity.this, USER_ID, "") + "");
                        weChatMap.put("diamond", diamondFee);
                        weChatMap.put("payment", "2");
                        getPresenter().getWeChatPay(weChatMap, false, false);
                        break;
                    default:
                        T.Short("请选择支付方式", 0);
                        break;
                }
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

    // 兑换钻石
    private void showPopWindow1(final int diamond) {
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
        tvTitle.setText("确定要兑换 " + diamond + "钻石 吗？");
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText("兑换后，不可撤销");
        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_confirm);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeMap<String, String> diamondStoreMap = new TreeMap<>();
                diamondStoreMap.put("id", SPUtil.get(DiamondStoreActivity.this, USER_ID, "") + "");
                diamondStoreMap.put("diamond", diamond + "");
                getPresenter().getDiamondStore(diamondStoreMap, false, false);
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
            case REQUEST_CODE_97:
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

    @OnClick({R.id.ib_back, R.id.ib_gold, R.id.bt_buy_diamond1, R.id.bt_exchange_diamond1, R.id.bt_buy_diamond2, R.id.bt_exchange_diamond2, R.id.bt_buy_diamond3, R.id.bt_exchange_diamond3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.ib_gold:
                startActivityForResult(new Intent(this, GoldStoreActivity.class), REQUEST_CODE_97);
                break;
            case R.id.bt_buy_diamond1:
                diamondFee = "50";
                showPopWindow();
                break;
            case R.id.bt_exchange_diamond1:
                showPopWindow1(50);
                break;
            case R.id.bt_buy_diamond2:
                diamondFee = "200";
                showPopWindow();
                break;
            case R.id.bt_exchange_diamond2:
                showPopWindow1(200);
                break;
            case R.id.bt_buy_diamond3:
                diamondFee = "500";
                showPopWindow();
                break;
            case R.id.bt_exchange_diamond3:
                showPopWindow1(500);
                break;
        }
    }

    @Override
    public void resultDiamondStore(DiamondStoreBean data) {
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
    public void resultAliPay(AliPayBean data) {
        if (data.getCode() == 200)
            new AliPay(DiamondStoreActivity.this, data.getData().getPay_str(), tvDiamond, diamondFee, 1);
    }

    @Override
    public void resultWeChatPay(WeChatPayBean data) {
        if (data.getCode() == 200) {
            IWXAPI api = WXAPIFactory.createWXAPI(this, null);
            api.registerApp("wx359eeab705dff0ac");
            PayReq req = new PayReq();
            req.appId = data.getData().getAppid();//你的微信appid
            req.partnerId = data.getData().getPartnerid();//商户号
            req.prepayId = data.getData().getPrepayid();//预支付交易会话ID
            req.nonceStr = data.getData().getNoncestr();//随机字符串
            req.timeStamp = data.getData().getTimestamp() + "";//时间戳
            req.packageValue = data.getData().getPackageX(); //扩展字段, 这里固定填写Sign = WXPay
            req.sign = data.getData().getSign();//签名
            //  req.extData         = "app data"; // optional
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            api.sendReq(req);
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
