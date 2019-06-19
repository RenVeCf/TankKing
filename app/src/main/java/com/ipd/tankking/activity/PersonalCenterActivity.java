package com.ipd.tankking.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.fragment.AboutFragment;
import com.ipd.tankking.fragment.FeedBackFragment;
import com.ipd.tankking.fragment.MsgFragment;
import com.ipd.tankking.fragment.PersonalFragment;
import com.ipd.tankking.fragment.ShareFragment;
import com.ipd.tankking.fragment.WalletFragment;
import com.ipd.tankking.service.MusicService;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：个人中心
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class PersonalCenterActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ll_out)
    LinearLayout llOut;
    @BindView(R.id.ll_vip_title)
    LinearLayout llVipTitle;
    @BindView(R.id.rb_personal)
    RadioButton rbPersonal;
    @BindView(R.id.rb_wallet)
    RadioButton rbWallet;
    @BindView(R.id.rb_msg)
    RadioButton rbMsg;
    @BindView(R.id.rb_share)
    RadioButton rbShare;
    @BindView(R.id.rb_about)
    RadioButton rbAbout;
    @BindView(R.id.rb_feedback)
    RadioButton rbFeedback;
    @BindView(R.id.ll_personal)
    LinearLayout llPersonal;
    @BindView(R.id.vp_personal)
    ViewPager vpPersonal;
    @BindView(R.id.ib_setting)
    ImageButton ibSetting;
    private Switch shMusic;

    private Fragment currentFragment = new Fragment();
    private PersonalFragment personalFragment = new PersonalFragment();
    private WalletFragment walletFragment = new WalletFragment();
    private MsgFragment msgFragment = new MsgFragment();
    private ShareFragment shareFragment = new ShareFragment();
    private AboutFragment aboutFragment = new AboutFragment();
    private FeedBackFragment feedBackFragment = new FeedBackFragment();

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_center;
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

        switchFragment(personalFragment).commit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    //Fragment优化
    public FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.ll_personal, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    // 退出登录
    private void showPopWindow() {
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
        tvTitle.setText("确定要退出当前账号吗？");
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText("退出后，需重新登录才能进行游戏");
        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_confirm);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                startActivity(new Intent(PersonalCenterActivity.this, LoginActivity.class));
                window.dismiss();
                finish();
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

    // 设置
    private void showPopWindow1() {
        final TextView tvTitle;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_setting, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, 900, 500, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvTitle.setText("设置");
        shMusic = contentView.findViewById(R.id.sh_music);
        shMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 开启音乐
                    startService(new Intent(PersonalCenterActivity.this, MusicService.class));
                } else {
                    // 关闭音乐
                    stopService(new Intent(PersonalCenterActivity.this, MusicService.class));
                }
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
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ib_setting, R.id.ib_back, R.id.ll_out, R.id.rb_personal, R.id.rb_wallet, R.id.rb_msg, R.id.rb_share, R.id.rb_about, R.id.rb_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_setting:
                showPopWindow1();
                break;
            case R.id.ib_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.ll_out:
                showPopWindow();
                break;
            case R.id.rb_personal:
                switchFragment(personalFragment).commit();
                break;
            case R.id.rb_wallet:
                switchFragment(walletFragment).commit();
                break;
            case R.id.rb_msg:
                switchFragment(msgFragment).commit();
                break;
            case R.id.rb_share:
                switchFragment(shareFragment).commit();
                break;
            case R.id.rb_about:
                switchFragment(aboutFragment).commit();
                break;
            case R.id.rb_feedback:
                switchFragment(feedBackFragment).commit();
                break;
        }
    }
}
