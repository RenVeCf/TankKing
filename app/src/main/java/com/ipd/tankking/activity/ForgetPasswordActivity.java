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
import com.ipd.tankking.bean.CaptchaBean;
import com.ipd.tankking.bean.ForGetPwdBean;
import com.ipd.tankking.contract.ForGetPwdContract;
import com.ipd.tankking.presenter.ForGetPwdPresenter;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.T;
import com.ipd.tankking.utils.VerifyUtils;
import com.ipd.tankking.utils.isClickUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：忘记密码
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/5/31.
 */
public class ForgetPasswordActivity extends BaseActivity<ForGetPwdContract.View, ForGetPwdContract.Presenter> implements ForGetPwdContract.View {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.tv_get_captcha)
    TextView tvGetCaptcha;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.bt_forget_pwd)
    Button btForgetPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public ForGetPwdContract.Presenter createPresenter() {
        return new ForGetPwdPresenter(this);
    }

    @Override
    public ForGetPwdContract.View createView() {
        return this;
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

    @OnClick({R.id.tv_get_captcha, R.id.ib_back, R.id.bt_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_captcha:
                TreeMap<String, String> captchaMap = new TreeMap<>();
                captchaMap.put("mobile", etPhone.getText().toString().trim());
                getPresenter().getCaptcha(captchaMap, true, false);
                break;
            case R.id.ib_back:
                if (this instanceof Activity && isClickUtil.isFastClick()) {
                    this.finish();
                    if (this.getCurrentFocus() != null) {
                        ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
            case R.id.bt_forget_pwd:
                if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                    TreeMap<String, String> registerMap = new TreeMap<>();
                    registerMap.put("mobile", etPhone.getText().toString().trim());
                    registerMap.put("code", etCaptcha.getText().toString().trim());
                    registerMap.put("password", etNewPwd.getText().toString().trim());
                    getPresenter().getForGetPwd(registerMap, true, false);
                } else {
                    T.Long("手机号码格式错误", 2);
                }
                break;
        }
    }

    @Override
    public void resultForGetPwd(ForGetPwdBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            finish();
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public void resultCaptcha(CaptchaBean data) {
        if (data.getCode().equals("200"))
            T.Short(data.getMsg(), 1);
        else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
