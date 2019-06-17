package com.ipd.tankking.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.T;
import com.ipd.tankking.utils.VerifyUtils;
import com.ipd.tankking.utils.isClickUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：绑定手机号
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/5/31.
 */
public class BindPhoneActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.tv_get_captcha)
    TextView tvGetCaptcha;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.bt_bing_phone)
    Button btBingPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
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
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_get_captcha, R.id.ib_back, R.id.bt_bing_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_captcha:
                break;
            case R.id.ib_back:
                if (this instanceof Activity && isClickUtil.isFastClick()) {
                    this.finish();
                    if (this.getCurrentFocus() != null) {
                        ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
            case R.id.bt_bing_phone:
                if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                    finish();
                } else {
                    T.Long("手机号码格式错误", 2);
                }
                break;
        }
    }
}
