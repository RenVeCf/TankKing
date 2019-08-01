package com.ipd.tankking.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.bean.LoginBean;
import com.ipd.tankking.contract.LoginContract;
import com.ipd.tankking.presenter.LoginPresenter;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;
import com.ipd.tankking.utils.VerifyUtils;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.AVATAR;
import static com.ipd.tankking.common.config.IConstants.IS_LOGIN;
import static com.ipd.tankking.common.config.IConstants.NAME;
import static com.ipd.tankking.common.config.IConstants.PHONE;
import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：登录
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/5/31.
 */
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_get_pwd)
    TextView tvGetPwd;
    @BindView(R.id.ib_wechat_login)
    ImageButton ibWechatLogin;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.bt_login)
    Button btLogin;

    private long firstTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //自动登录
        if (!SPUtil.get(this, IS_LOGIN, "").equals("")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    // 双击退出程序
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            T.Short(getResources().getString(R.string.click_out_again), 0);
            firstTime = secondTime;
        } else {
            ApplicationUtil.getManager().exitApp();
        }
    }

    @OnClick({R.id.tv_get_pwd, R.id.ib_wechat_login, R.id.bt_register, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_pwd:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.ib_wechat_login:
                startActivity(new Intent(this, BindPhoneActivity.class));
                break;
            case R.id.bt_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.bt_login:
                if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                    TreeMap<String, String> loginMap = new TreeMap<>();
                    loginMap.put("mobile", etPhone.getText().toString().trim());
                    loginMap.put("password", etPwd.getText().toString().trim());
                    getPresenter().getLogin(loginMap, false, false);
                } else {
                    T.Long("手机号码格式错误", 2);
                }
                break;
        }
    }

    @Override
    public void resultLogin(LoginBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            SPUtil.put(this, IS_LOGIN, "true");
            SPUtil.put(this, USER_ID, data.getData().getId() + "");
            SPUtil.put(this, NAME, data.getData().getMember_name());
            SPUtil.put(this, PHONE, data.getData().getMember_mobile());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
