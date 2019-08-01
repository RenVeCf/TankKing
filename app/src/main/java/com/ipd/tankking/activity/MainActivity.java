package com.ipd.tankking.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.bean.GetUserInfoBean;
import com.ipd.tankking.common.view.CircleImageView;
import com.ipd.tankking.contract.GetUserInfoContract;
import com.ipd.tankking.presenter.GetUserInfoPresenter;
import com.ipd.tankking.service.MusicService;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.LogUtils;
import com.ipd.tankking.utils.NavigationBarUtil;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.AVATAR;
import static com.ipd.tankking.common.config.IConstants.DIAMOND;
import static com.ipd.tankking.common.config.IConstants.GOLD;
import static com.ipd.tankking.common.config.IConstants.NAME;
import static com.ipd.tankking.common.config.IConstants.PERMISSIONS_REQUEST_CODE;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_90;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_91;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_92;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_93;
import static com.ipd.tankking.common.config.IConstants.REQUEST_CODE_98;
import static com.ipd.tankking.common.config.IConstants.USER_ID;
import static com.ipd.tankking.common.config.IConstants.VIP_LV;

/**
 * Description ：主页
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/5/31.
 */
public class MainActivity extends BaseActivity<GetUserInfoContract.View, GetUserInfoContract.Presenter> implements GetUserInfoContract.View {

    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.ib_vip)
    ImageButton ibVip;
    @BindView(R.id.tv_gold)
    TextView tvGold;
    @BindView(R.id.ib_gold)
    ImageButton ibGold;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.ib_diamond)
    ImageButton ibDiamond;
    @BindView(R.id.ib_game1)
    ImageButton ibGame1;
    @BindView(R.id.ib_game2)
    ImageButton ibGame2;
    @BindView(R.id.ib_game3)
    ImageButton ibGame3;
    @BindView(R.id.ib_game4)
    ImageButton ibGame4;
    @BindView(R.id.ib_battle_ranking)
    ImageButton ibBattleRanking;
    @BindView(R.id.ib_team)
    ImageButton ibTeam;
    @BindView(R.id.ib_lottery)
    ImageButton ibLottery;

    private long firstTime = 0;
    private int vipLv = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public GetUserInfoContract.Presenter createPresenter() {
        return new GetUserInfoPresenter(this);
    }

    @Override
    public GetUserInfoContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //适配虚拟按键
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);

        Glide.with(this).load(SPUtil.get(this, AVATAR, "") + "").apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(civHead);
        tvUserName.setText(SPUtil.get(this, NAME, "") + "");

        requestPermission(); //支付权限申请

        startService(new Intent(this, MusicService.class));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> getUserInfoMap = new TreeMap<>();
        getUserInfoMap.put("id", SPUtil.get(this, USER_ID, "") + "");
        getPresenter().getGetUserInfo(getUserInfoMap, false, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_90:
                Glide.with(this).load(SPUtil.get(this, AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(civHead);
                tvUserName.setText((String) SPUtil.get(this, NAME, ""));
                tvGold.setText(SPUtil.get(this, GOLD, "") + "");
                tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
                break;
            case REQUEST_CODE_91:
                vipLv = Integer.parseInt(SPUtil.get(this, VIP_LV, "") + "");
                switch (vipLv) {
                    case 0:
                        ibVip.setImageResource(R.mipmap.ic_small_vip0);
                        break;
                    case 1:
                        ibVip.setImageResource(R.mipmap.ic_small_vip1);
                        break;
                    case 2:
                        ibVip.setImageResource(R.mipmap.ic_small_vip2);
                        break;
                    case 3:
                        ibVip.setImageResource(R.mipmap.ic_small_vip3);
                        break;
                }
                tvGold.setText(SPUtil.get(this, GOLD, "") + "");
                tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
                break;
            case REQUEST_CODE_92:
                tvGold.setText(SPUtil.get(this, GOLD, "") + "");
                tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
                break;
            case REQUEST_CODE_93:
                tvGold.setText(SPUtil.get(this, GOLD, "") + "");
                tvDiamond.setText(SPUtil.get(this, DIAMOND, "") + "");
                break;
            case REQUEST_CODE_98:
                initData();
                break;
        }
    }

    /**
     * 检查支付宝 SDK 所需的权限，并在必要的时候动态获取。
     * 在 targetSDK = 23 以上，READ_PHONE_STATE 和 WRITE_EXTERNAL_STORAGE 权限需要应用在运行时获取。
     * 如果接入支付宝 SDK 的应用 targetSdk 在 23 以下，可以省略这个步骤。
     */
    private void requestPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     * 权限获取回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                // 用户取消了权限弹窗
                if (grantResults.length == 0) {
                    T.Short(getString(R.string.permission_rejected), 2);
                    return;
                }

                // 用户拒绝了某些权限
                for (int x : grantResults) {
                    if (x == PackageManager.PERMISSION_DENIED) {
                        T.Short(getString(R.string.permission_rejected), 2);
                        return;
                    }
                }
                // 所需的权限均正常获取
//                ToastUtil.showShortToast(getString(R.string.permission_granted));
                break;
        }
    }

    // 金币不足
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
        tvTitle.setText("您的金币不足，是否兑换？");
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText("需先购买钻石兑换金币后，才可进入游戏");
        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_exchange_gold);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @OnClick({R.id.civ_head, R.id.ib_vip, R.id.ib_gold, R.id.ib_diamond, R.id.ib_game1, R.id.ib_game2, R.id.ib_game3, R.id.ib_game4, R.id.ib_battle_ranking, R.id.ib_team, R.id.ib_lottery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_head:
                startActivityForResult(new Intent(this, PersonalCenterActivity.class), REQUEST_CODE_90);
                break;
            case R.id.ib_vip:
                startActivityForResult(new Intent(this, VipActivity.class).putExtra("vip_lv", vipLv), REQUEST_CODE_91);
                break;
            case R.id.ib_gold:
                startActivityForResult(new Intent(this, GoldStoreActivity.class), REQUEST_CODE_92);
                break;
            case R.id.ib_diamond:
                startActivityForResult(new Intent(this, DiamondStoreActivity.class), REQUEST_CODE_93);
                break;
            case R.id.ib_game1:
                if (Integer.parseInt(SPUtil.get(this, GOLD, "") + "") < 10)
                    showPopWindow();
                else
                    startActivityForResult(new Intent(this, WebViewActivity.class).putExtra("h5Type", 0), REQUEST_CODE_98);
                break;
            case R.id.ib_game2:
                if (Integer.parseInt(SPUtil.get(this, GOLD, "") + "") < 20)
                    showPopWindow();
                else
                    startActivityForResult(new Intent(this, WebViewActivity.class).putExtra("h5Type", 1), REQUEST_CODE_98);
                break;
            case R.id.ib_game3:
                if (Integer.parseInt(SPUtil.get(this, GOLD, "") + "") < 50)
                    showPopWindow();
                else
                    startActivityForResult(new Intent(this, WebViewActivity.class).putExtra("h5Type", 2), REQUEST_CODE_98);
                break;
            case R.id.ib_game4:
                if (Integer.parseInt(SPUtil.get(this, GOLD, "") + "") < 100)
                    showPopWindow();
                else
                    startActivityForResult(new Intent(this, WebViewActivity.class).putExtra("h5Type", 3), REQUEST_CODE_98);
                break;
            case R.id.ib_battle_ranking:
                startActivityForResult(new Intent(this, WebViewActivity.class).putExtra("h5Type", 4), REQUEST_CODE_98);
                break;
            case R.id.ib_team:
                startActivityForResult(new Intent(this, WebViewActivity.class).putExtra("h5Type", 5), REQUEST_CODE_98);
                break;
            case R.id.ib_lottery:
                startActivityForResult(new Intent(this, WebViewActivity.class).putExtra("h5Type", 6), REQUEST_CODE_98);
                break;
        }
    }

    @Override
    public void resultGetUserInfo(GetUserInfoBean data) {
        tvGold.setText(data.getData().getMember_coin());
        tvDiamond.setText(data.getData().getMember_diamonds());
        SPUtil.put(this, GOLD, data.getData().getMember_coin());
        SPUtil.put(this, DIAMOND, data.getData().getMember_diamonds());
        SPUtil.put(this, AVATAR, data.getData().getMember_img());
        Glide.with(this).load(data.getData().getMember_img()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(civHead);
        vipLv = data.getData().getMember_lv();
        SPUtil.put(this, VIP_LV, vipLv + "");
        switch (vipLv) {
            case 0:
                ibVip.setImageResource(R.mipmap.ic_small_vip0);
                break;
            case 1:
                ibVip.setImageResource(R.mipmap.ic_small_vip1);
                break;
            case 2:
                ibVip.setImageResource(R.mipmap.ic_small_vip2);
                break;
            case 3:
                ibVip.setImageResource(R.mipmap.ic_small_vip3);
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
