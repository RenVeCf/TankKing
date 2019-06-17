package com.ipd.tankking.wxapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.LogUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_type;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);

        api = WXAPIFactory.createWXAPI(this, "wx359eeab705dff0ac");
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.errCode == 0) {
            tvPayType.setText("支付成功！");
//            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            }
//            ivPayType.setImageResource(R.drawable.ic_pay_success);
//            tvPayType.setText("支付成功");
//            tvPayTypeTips.setText("您已支付成功，请在约定时间内前往店内取车");
//            llPayType.setVisibility(View.GONE);
        } else {
            tvPayType.setText("支付失败！");
//            ivPayType.setImageResource(R.drawable.ic_pay_fail);
//            tvPayType.setText("支付失败");
//            tvPayTypeTips.setText("很遗憾，您支付失败，请重新支付");
//            llPayType.setVisibility(View.VISIBLE);
        }
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("支付结果");
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
//        }
    }
}
