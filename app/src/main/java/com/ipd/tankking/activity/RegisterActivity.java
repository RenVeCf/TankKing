package com.ipd.tankking.activity;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.bean.CaptchaBean;
import com.ipd.tankking.bean.RegisterBean;
import com.ipd.tankking.contract.RegisterContract;
import com.ipd.tankking.presenter.RegisterPresenter;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.T;
import com.ipd.tankking.utils.VerifyUtils;
import com.ipd.tankking.utils.isClickUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：注册
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/5/31.
 */
public class RegisterActivity extends BaseActivity<RegisterContract.View, RegisterContract.Presenter> implements RegisterContract.View {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.tv_get_captcha)
    TextView tvGetCaptcha;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_check)
    TextView tvCheck;
    @BindView(R.id.tv_user_pact)
    TextView tvUserPact;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterContract.Presenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public RegisterContract.View createView() {
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

    // 用户协议
    private void showPopWindow() {
        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_user_pact, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, 1500, 1000, true);
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

    @OnClick({R.id.tv_get_captcha, R.id.tv_user_pact, R.id.tv_check, R.id.ib_back, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_captcha:
                if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                    TreeMap<String, String> captchaMap = new TreeMap<>();
                    captchaMap.put("mobile", etPhone.getText().toString().trim());
                    getPresenter().getCaptcha(captchaMap, true, false);
                }
                break;
            case R.id.tv_user_pact:
                showPopWindow();
                break;
            case R.id.tv_check:
                if (cbCheck.isChecked())
                    cbCheck.setChecked(false);
                else
                    cbCheck.setChecked(true);
                break;
            case R.id.ib_back:
                if (this instanceof Activity && isClickUtil.isFastClick()) {
                    this.finish();
                    if (this.getCurrentFocus() != null) {
                        ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
            case R.id.bt_register:
                if (cbCheck.isChecked()) {
                    if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                        TreeMap<String, String> registerMap = new TreeMap<>();
                        registerMap.put("mobile", etPhone.getText().toString().trim());
                        registerMap.put("code", etCaptcha.getText().toString().trim());
                        registerMap.put("password", etPwd.getText().toString().trim());
                        registerMap.put("sub", "");
                        getPresenter().getRegister(registerMap, true, false);
                    } else {
                        T.Long("手机号码格式错误", 2);
                    }
                } else
                    T.Long("请勾选用户注册协议", 0);
                break;
        }
    }

    @Override
    public void resultRegister(RegisterBean data) {
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
