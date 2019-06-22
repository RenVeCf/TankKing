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
import com.ipd.tankking.bean.WeChatPayBean;
import com.ipd.tankking.contract.VipPayContract;
import com.ipd.tankking.presenter.VipPayPresenter;
import com.ipd.tankking.utils.ApplicationUtil;
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
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_94;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_95;
import static com.ipd.tankking.common.config.IConstants.USER_ID;
import static com.ipd.tankking.common.config.IConstants.VIP_LV;

/**
 * Description ：会员
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/2.
 */
public class VipActivity extends BaseActivity<VipPayContract.View, VipPayContract.Presenter> implements VipPayContract.View {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.ib_gold)
    ImageButton ibGold;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.ib_diamond)
    ImageButton ibDiamond;
    @BindView(R.id.tv_not_vip)
    TextView tvNotVip;
    @BindView(R.id.iv_vip_lv)
    ImageView ivVipLv;
    @BindView(R.id.tv_vip_lv)
    TextView tvVipLv;
    @BindView(R.id.ll_is_vip)
    LinearLayout llIsVip;
    @BindView(R.id.bt_vip_rule)
    Button btVipRule;
    @BindView(R.id.tv_vip1_fee)
    TextView tvVip1Fee;
    @BindView(R.id.tv_vip1_gift)
    TextView tvVip1Gift;
    @BindView(R.id.bt_buy_vip1)
    Button btBuyVip1;
    @BindView(R.id.tv_vip2_fee)
    TextView tvVip2Fee;
    @BindView(R.id.tv_vip2_gift)
    TextView tvVip2Gift;
    @BindView(R.id.bt_buy_vip2)
    Button btBuyVip2;
    @BindView(R.id.tv_vip3_fee)
    TextView tvVip3Fee;
    @BindView(R.id.tv_vip3_gift)
    TextView tvVip3Gift;
    @BindView(R.id.bt_buy_vip3)
    Button btBuyVip3;

    private int payType = 0; // 支付方式
    private int vipLv = 0; // 会员等级
    private String diamondFee = ""; // 送的钻石数

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    public VipPayContract.Presenter createPresenter() {
        return new VipPayPresenter(this);
    }

    @Override
    public VipPayContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);

        tvGold.setText(SPUtil.get(this, GOLD, "") + "");
        tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
        vipLv = getIntent().getIntExtra("vip_lv", 0);
        switch (vipLv) {
            case 0:
                tvNotVip.setVisibility(View.VISIBLE);
                llIsVip.setVisibility(View.GONE);
                break;
            case 1:
                tvNotVip.setVisibility(View.GONE);
                llIsVip.setVisibility(View.VISIBLE);
                ivVipLv.setImageResource(R.mipmap.ic_big_vip1);
                tvVipLv.setText("您当前是 铂金会员，可升级为 至尊会员");
                btBuyVip1.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip1.setEnabled(false);
                btBuyVip2.setBackgroundResource(R.mipmap.bg_vip_buy);
                btBuyVip3.setBackgroundResource(R.mipmap.bg_vip_buy);
                break;
            case 2:
                tvNotVip.setVisibility(View.GONE);
                llIsVip.setVisibility(View.VISIBLE);
                ivVipLv.setImageResource(R.mipmap.ic_big_vip2);
                tvVipLv.setText("您当前是 至尊会员，可升级为 豪享会员");
                btBuyVip1.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip1.setEnabled(false);
                btBuyVip2.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip2.setEnabled(false);
                btBuyVip3.setBackgroundResource(R.mipmap.bg_vip_buy);
                break;
            case 3:
                tvNotVip.setVisibility(View.GONE);
                llIsVip.setVisibility(View.VISIBLE);
                ivVipLv.setImageResource(R.mipmap.ic_big_vip3);
                tvVipLv.setText("您当前是 豪享会员");
                btBuyVip1.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip1.setEnabled(false);
                btBuyVip2.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip2.setEnabled(false);
                btBuyVip3.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip3.setEnabled(false);
                break;
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        vipLv = Integer.parseInt(SPUtil.get(this, VIP_LV, "") + "");
        switch (vipLv) {
            case 0:
                tvNotVip.setVisibility(View.VISIBLE);
                llIsVip.setVisibility(View.GONE);
                break;
            case 1:
                tvNotVip.setVisibility(View.GONE);
                llIsVip.setVisibility(View.VISIBLE);
                ivVipLv.setImageResource(R.mipmap.ic_big_vip1);
                tvVipLv.setText("您当前是 铂金会员，可升级为 至尊会员");
                btBuyVip1.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip1.setEnabled(false);
                btBuyVip2.setBackgroundResource(R.mipmap.bg_vip_buy);
                btBuyVip3.setBackgroundResource(R.mipmap.bg_vip_buy);
                break;
            case 2:
                tvNotVip.setVisibility(View.GONE);
                llIsVip.setVisibility(View.VISIBLE);
                ivVipLv.setImageResource(R.mipmap.ic_big_vip2);
                tvVipLv.setText("您当前是 至尊会员，可升级为 豪享会员");
                btBuyVip1.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip1.setEnabled(false);
                btBuyVip2.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip2.setEnabled(false);
                btBuyVip3.setBackgroundResource(R.mipmap.bg_vip_buy);
                break;
            case 3:
                tvNotVip.setVisibility(View.GONE);
                llIsVip.setVisibility(View.VISIBLE);
                ivVipLv.setImageResource(R.mipmap.ic_big_vip3);
                tvVipLv.setText("您当前是 豪享会员");
                btBuyVip1.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip1.setEnabled(false);
                btBuyVip2.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip2.setEnabled(false);
                btBuyVip3.setBackgroundResource(R.mipmap.bg_vip_not_buy);
                btBuyVip3.setEnabled(false);
                break;
        }
    }

    // 会员规则
    private void showPopWindow(String title, String content) {
        final TextView tvTitle;
        final TextView tvContent;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_vip_rule, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, 1500, 1000, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText(content);

        // 设置PopupWindow的背景
//        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bg_user_pact)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        window.setFocusable(true);
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

    // 购买会员
    private void showPopWindow1(String vipName, final int vipLv) {
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
        tvBuyTitle.setText("购买" + vipName);
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
                        TreeMap<String, String> vipAliMap = new TreeMap<>();
                        vipAliMap.put("uid", SPUtil.get(VipActivity.this, USER_ID, "") + "");
                        vipAliMap.put("type", vipLv + "");
                        vipAliMap.put("payment", "1");
                        getPresenter().getVipAliPay(vipAliMap, true, false);
                        break;
                    case 2:
                        //微信
                        TreeMap<String, String> vipWeChatMap = new TreeMap<>();
                        vipWeChatMap.put("uid", SPUtil.get(VipActivity.this, USER_ID, "") + "");
                        vipWeChatMap.put("type", vipLv + "");
                        vipWeChatMap.put("payment", "2");
                        getPresenter().getVipWeChatPay(vipWeChatMap, true, false);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_94:
                tvGold.setText(SPUtil.get(this, GOLD, "") + "");
                tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
                break;
            case REQUEST_CODE_95:
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

    @OnClick({R.id.ib_back, R.id.ib_gold, R.id.ib_diamond, R.id.bt_vip_rule, R.id.bt_buy_vip1, R.id.bt_buy_vip2, R.id.bt_buy_vip3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.ib_gold:
                startActivityForResult(new Intent(this, GoldStoreActivity.class), REQUEST_CODE_94);
                break;
            case R.id.ib_diamond:
                startActivityForResult(new Intent(this, DiamondStoreActivity.class), REQUEST_CODE_95);
                break;
            case R.id.bt_vip_rule:
                showPopWindow("会员规则", "欢迎使用坦克大战为您提供的坦克大战服务。\n" +
                        "为了保障您(以下简称”用户”)的权益，请在使用坦克大战服务之前，详细阅读此服务协议(以下简称”本协议”)的所有内容，特别是加粗部分。\n" +
                        "当用户阅读并勾选《用户注册协议》，即表示用户同意并签署了本协议，并同意遵守本协议的约定。该协议构成用户与坦克大战达成的协议，具有法律效力。\n" +
                        "本协议是《坦克大战用户注册协议》的补充协议，是其不可分割的组成部分，与其构成统一整体。本协议与上述内容存在冲突的，以本协议为准。\n" +
                        "第一条定义：\n" +
                        "坦克大战：指在澳洲出行平台签署本协议并根据坦克大战公布的收费标准支付相应的费用后获取的特殊资格的用\n" +
                        "户以及申请会员免费体验服务且在会员试用期内的用户(以下简称”会员”)。\n" +
                        "第二条坦克大战服务协议的修订\n" +
                        "1.坦克大战有权以网站公告等方式进行不定期地制定、修改本协议及/或相关服务规则。用户在使用澳洲出\n" +
                        "行会画板素材采集自用户等下一个黎明素加载更多推荐\n\n");
                break;
            case R.id.bt_buy_vip1:
                diamondFee = "20";
                showPopWindow1("铂金会员", 1);
                break;
            case R.id.bt_buy_vip2:
                diamondFee = "80";
                showPopWindow1("至尊会员", 2);
                break;
            case R.id.bt_buy_vip3:
                diamondFee = "190";
                showPopWindow1("豪享会员", 3);
                break;
        }
    }

    @Override
    public void resultVipAliPay(AliPayBean data) {
        if (data.getCode() == 200)
            new AliPay(VipActivity.this, data.getData().getPay_str(), tvDiamond, diamondFee, 2);
    }

    @Override
    public void resultVipWeChatPay(WeChatPayBean data) {
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
