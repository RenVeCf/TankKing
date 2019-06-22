package com.ipd.tankking.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.tankking.R;
import com.ipd.tankking.activity.DiamondStoreActivity;
import com.ipd.tankking.activity.GoldStoreActivity;
import com.ipd.tankking.adapter.MyBankAdapter;
import com.ipd.tankking.adapter.PopBankAdapter;
import com.ipd.tankking.base.BaseFragment;
import com.ipd.tankking.bean.AddBankBean;
import com.ipd.tankking.bean.DelBankBean;
import com.ipd.tankking.bean.ModifyBankBean;
import com.ipd.tankking.bean.SelectBankBean;
import com.ipd.tankking.bean.WithdrawBankBean;
import com.ipd.tankking.contract.WalletContract;
import com.ipd.tankking.presenter.WalletPresenter;
import com.ipd.tankking.utils.MD5Utils;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.DIAMOND;
import static com.ipd.tankking.common.config.IConstants.GOLD;
import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：钱包
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class WalletFragment extends BaseFragment<WalletContract.View, WalletContract.Presenter> implements WalletContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.ib_buy)
    ImageButton ibBuy;
    @BindView(R.id.ib_withdraw)
    ImageButton ibWithdraw;
    @BindView(R.id.ib_exchange)
    ImageButton ibExchange;
    @BindView(R.id.ll_wallet_main)
    LinearLayout llWalletMain;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ll_select_bank)
    LinearLayout llSelectBank;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_my_bank)
    TextView tvMyBank;
    @BindView(R.id.et_withdraw_fee)
    EditText etWithdrawFee;
    @BindView(R.id.tv_diamond_num)
    TextView tvDiamondNum;
    @BindView(R.id.tv_withdraw_rmb_num)
    TextView tvWithdrawRmbNum;
    @BindView(R.id.bt_withdraw_rule)
    Button btWithdrawRule;
    @BindView(R.id.ib_confirm_withdraw)
    ImageButton ibConfirmWithdraw;
    @BindView(R.id.ll_wallet_withdraw)
    LinearLayout llWalletWithdraw;
    @BindView(R.id.ib_my_bank_back)
    ImageButton ibMyBankBack;
    @BindView(R.id.bt_add_bank)
    Button btAddBank;
    @BindView(R.id.rv_my_bank)
    RecyclerView rvMyBank;
    @BindView(R.id.srl_my_bank)
    SwipeRefreshLayout srlMyBank;
    @BindView(R.id.ll_wallet_my_bank)
    LinearLayout llWalletMyBank;
    @BindView(R.id.tv_diamond)
    TextView tvDiamond;
    @BindView(R.id.tv_gold)
    TextView tvGold;

    private PopBankAdapter popBankAdapter;
    private MyBankAdapter myBankAdapter;
    private List<SelectBankBean.DataBean> bankListBean;
    private TextView tvTitle;
    private RecyclerView rvPopBank;
    private Button btPopConfirm;
    private int selectBankType = 0;
    private int position = 0;
    private PopupWindow window1;
    private int bankId = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wallet;
    }

    @Override
    public WalletContract.Presenter createPresenter() {
        return new WalletPresenter(mContext);
    }

    @Override
    public WalletContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMyBank.setLayoutManager(layoutManager);
        rvMyBank.setHasFixedSize(true);
        rvMyBank.setItemAnimator(new DefaultItemAnimator());

        bankListBean = new ArrayList<>();
        myBankAdapter = new MyBankAdapter(bankListBean);
        rvMyBank.setAdapter(myBankAdapter);

        tvGold.setText(SPUtil.get(getActivity(), GOLD, "") + "");
        tvDiamond.setText(SPUtil.get(getActivity(), DIAMOND, "") + "");
        tvDiamondNum.setText(SPUtil.get(getActivity(), DIAMOND, "") + "");
        tvWithdrawRmbNum.setText(SPUtil.get(getActivity(), DIAMOND, "") + "元");
        popBankAdapter = new PopBankAdapter(bankListBean);
    }

    @Override
    public void initListener() {
        srlMyBank.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                selectBankType = 1;
                selectBankList();
                srlMyBank.setRefreshing(false);
            }
        });

        myBankAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ib_edit_bank:
                        showPopWindow3(1, position);
                        break;
                    case R.id.ib_del_bank:
                        WalletFragment.this.position = position;
                        showPopWindow4(bankListBean.get(position).getId());
                        break;
                }
            }
        });

        popBankAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < bankListBean.size(); i++) {
                    bankListBean.get(i).setShow(false);
                }
                bankListBean.get(position).setShow(true);
                popBankAdapter.notifyDataSetChanged();
                WalletFragment.this.position = position;
            }
        });
    }

    @Override
    public void initData() {

    }

    // 选择银行卡
    private void showPopWindow() {
        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_list, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        window1 = new PopupWindow(contentView, 1000, 800, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvTitle.setText("选择银行卡");
        rvPopBank = contentView.findViewById(R.id.rv_pop_bank);

        // 设置管理器
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rvPopBank.setLayoutManager(layoutManager1);
        rvPopBank.setHasFixedSize(true);
        rvPopBank.setItemAnimator(new DefaultItemAnimator());

        rvPopBank.setAdapter(popBankAdapter);

        selectBankType = 2;
        selectBankList();

        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_confirm);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankId = bankListBean.get(position).getId();
                tvBank.setText(bankListBean.get(position).getBank_name() + " " + bankListBean.get(position).getBank());
                tvBank.setTextColor(Color.WHITE);
                window1.dismiss();
            }
        });
        // 设置PopupWindow的背景
//        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bg_user_pact)));
        // 设置PopupWindow是否能响应外部点击事件
        window1.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window1.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//        window.showAsDropDown(view, 0, 0);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        // window.showAtLocation(parent, gravity, x, y);
        window1.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        window1.setAnimationStyle(R.style.animTranslate);
    }

    // 钻石提现规则
    private void showPopWindow1(String title, String content) {
        final TextView tvTitle;
        final TextView tvContent;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_vip_rule, null, false);
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
        window.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.animTranslate);
    }

    // 提现申请已提交
    private void showPopWindow2() {
        final TextView tvTitle;
        final TextView tvContent;
        final Button btPopConfirm;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_not_gold, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, 900, 500, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvTitle.setText("提现申请已提交");
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText("提现申请提交后，请等待客服处理");
        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_i_know);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                withdrawBank();
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
        window.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.animTranslate);
    }

    // 添加/修改 银行卡
    private void showPopWindow3(final int bankType, final int position) {
        final TextView tvTitle;
        final EditText etBankName;
        final EditText etUserName;
        final EditText etUserPhone;
        final EditText etIdCard;
        final EditText etBankCode;
        final Button btPopConfirm;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_edit_bank, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, 1200, 1000, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        if (bankType == 0)
            tvTitle.setText("添加银行卡");
        else
            tvTitle.setText("修改银行卡");
        etBankName = contentView.findViewById(R.id.et_bank_name);
        etUserName = contentView.findViewById(R.id.et_user_name);
        etUserPhone = contentView.findViewById(R.id.et_user_phone);
        etIdCard = contentView.findViewById(R.id.et_id_card);
        etBankCode = contentView.findViewById(R.id.et_bank_code);
        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_confirm);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bankType == 0) {
                    // 添加银行卡
                    addBank(etBankName.getText().toString().trim(), etUserName.getText().toString().trim(), etUserPhone.getText().toString().trim(), etIdCard.getText().toString().trim(), etBankCode.getText().toString().trim());
                } else {
                    // 修改银行卡
                    modifyBank(etBankName.getText().toString().trim(), etUserName.getText().toString().trim(), etUserPhone.getText().toString().trim(), etIdCard.getText().toString().trim(), etBankCode.getText().toString().trim(), bankListBean.get(position).getId());
                    WalletFragment.this.position = position;
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
        window.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.animTranslate);
    }

    // 删除银行卡
    private void showPopWindow4(final int bankId) {
        final TextView tvTitle;
        final TextView tvContent;
        final Button btPopConfirm;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_not_gold, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, 900, 500, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvTitle.setText("确定要删除该银行卡吗？");
        tvContent = contentView.findViewById(R.id.tv_content);
        tvContent.setText("删除后，不可撤销");
        btPopConfirm = contentView.findViewById(R.id.bt_pop_confirm);
        btPopConfirm.setBackgroundResource(R.mipmap.ic_i_know);
        btPopConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeMap<String, String> delBankMap = new TreeMap<>();
                delBankMap.put("uid", SPUtil.get(getActivity(), USER_ID, "") + "");
                delBankMap.put("id", bankId + "");
                getPresenter().getDelBank(delBankMap, true, false);
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
        window.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.animTranslate);
    }

    // 查看银行卡请求
    private void selectBankList() {
        TreeMap<String, String> selectBankMap = new TreeMap<>();
        selectBankMap.put("uid", SPUtil.get(getActivity(), USER_ID, "") + "");
        getPresenter().getSelectBank(selectBankMap, true, false);
    }

    // 提现请求
    private void withdrawBank() {
        String id = SPUtil.get(getActivity(), USER_ID, "") + "";
        String bank = bankListBean.get(position).getBank();
        String money = etWithdrawFee.getText().toString().trim();
        String timestamp = String.format("%010d", System.currentTimeMillis() / 1000);

        TreeMap<String, String> withdrawBankMap = new TreeMap<>();
        withdrawBankMap.put("id", id);
        withdrawBankMap.put("bank", bank);
        withdrawBankMap.put("money", money);
        withdrawBankMap.put("timestamp", timestamp);
        withdrawBankMap.put("sign", MD5Utils.encodeMD5("bank=" + bank + "&id=" + id + "&money=" + money + "&timestamp=" + timestamp + "&key=4QrcOUm6Wau+VuBX8g+IPg=="));
        getPresenter().getWithdrawBank(withdrawBankMap, true, false);
    }

    // 添加银行卡请求
    private void addBank(String name, String uname, String phone, String idCard, String bank) {
        TreeMap<String, String> addBankMap = new TreeMap<>();
        addBankMap.put("uid", SPUtil.get(getActivity(), USER_ID, "") + "");
        addBankMap.put("name", name);
        addBankMap.put("uname", uname);
        addBankMap.put("mobile", phone);
        addBankMap.put("id_card", idCard);
        addBankMap.put("bank", bank);
        getPresenter().getAddBank(addBankMap, true, false);
    }

    // 修改银行卡请求
    private void modifyBank(String name, String uname, String phone, String idCard, String bank, int bankId) {
        TreeMap<String, String> addBankMap = new TreeMap<>();
        addBankMap.put("uid", SPUtil.get(getActivity(), USER_ID, "") + "");
        addBankMap.put("name", name);
        addBankMap.put("uname", uname);
        addBankMap.put("mobile", phone);
        addBankMap.put("id_card", idCard);
        addBankMap.put("bank", bank);
        addBankMap.put("id", bankId + "");
        getPresenter().getModifyBank(addBankMap, true, false);
    }

    @OnClick({R.id.ib_buy, R.id.ib_withdraw, R.id.ib_exchange, R.id.ib_back, R.id.tv_my_bank, R.id.ll_select_bank, R.id.bt_withdraw_rule, R.id.ib_confirm_withdraw, R.id.ib_my_bank_back, R.id.bt_add_bank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_buy:
                startActivity(new Intent(getActivity(), DiamondStoreActivity.class));
                break;
            case R.id.ib_withdraw:
                llWalletMain.setVisibility(View.GONE);
                llWalletWithdraw.setVisibility(View.VISIBLE);
                llWalletMyBank.setVisibility(View.GONE);
                break;
            case R.id.ib_exchange:
                startActivity(new Intent(getActivity(), GoldStoreActivity.class));
                break;
            case R.id.ib_back:
                llWalletMain.setVisibility(View.VISIBLE);
                llWalletWithdraw.setVisibility(View.GONE);
                llWalletMyBank.setVisibility(View.GONE);
                break;
            case R.id.tv_my_bank:
                llWalletMain.setVisibility(View.GONE);
                llWalletWithdraw.setVisibility(View.GONE);
                llWalletMyBank.setVisibility(View.VISIBLE);
                selectBankType = 1;
                selectBankList();
                break;
            case R.id.ll_select_bank:
                showPopWindow();
                break;
            case R.id.bt_withdraw_rule:
                showPopWindow1("钻石奖励规则", "所有用户满50钻石以及50的整数倍即可获得公司推广奖励。\n" +
                        "每笔奖励扣除10元手续费。 \n" +
                        "青铜战队直推团队累计30人(自己的一二级会员累计) 所有提现金额累计的2%\n" +
                        "白银战队直推团队累计100人 所有提现金额累计的4% \n" +
                        "黄金战队直推团队累计500人 所有提现金额累计的6%\n" +
                        "铂金战队直推团队累计2000人 所有提现金额累计的8%\n" +
                        "钻石战队直推团队累计10000人 所有提现金额累计的10%\n\n");
                break;
            case R.id.ib_confirm_withdraw:
                if (!tvBank.getText().toString().trim().equals("请选择到账银行卡")) {
                    showPopWindow2();
                } else
                    T.Short("请至'我的银行卡'中添加", 0);
                break;
            case R.id.ib_my_bank_back:
                llWalletMain.setVisibility(View.VISIBLE);
                llWalletWithdraw.setVisibility(View.GONE);
                llWalletMyBank.setVisibility(View.GONE);
                break;
            case R.id.bt_add_bank:
                showPopWindow3(0, 0);
                break;
        }
    }

    @Override
    public void resultSelectBank(SelectBankBean data) {
        switch (selectBankType) {
            case 1:
                bankListBean.clear();
                bankListBean.addAll(data.getData());
                myBankAdapter.setNewData(bankListBean);
                myBankAdapter.loadMoreEnd();
                //空数据时的页面
                myBankAdapter.setEmptyView(R.layout.null_bank_data, rvMyBank);
                break;
            case 2:
                bankListBean.clear();
                bankListBean.addAll(data.getData());
                for (int i = 0; i < bankListBean.size(); i++) {
                    if (bankListBean.get(i).getId() == bankId)
                        bankListBean.get(i).setShow(true);
                    else
                        bankListBean.get(i).setShow(false);
                }
                popBankAdapter.setNewData(bankListBean);
                popBankAdapter.loadMoreEnd();
                //空数据时的页面
                popBankAdapter.setEmptyView(R.layout.null_bank_data, rvPopBank);
                break;
        }
    }

    @Override
    public void resultAddBank(AddBankBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            SelectBankBean.DataBean selectBankBean = new SelectBankBean.DataBean();
            selectBankBean.setBank(data.getData().getBank());
            selectBankBean.setBank_name(data.getData().getBank_name());
            bankListBean.add(selectBankBean);
            myBankAdapter.notifyDataSetChanged();
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public void resultModifyBank(ModifyBankBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            bankListBean.get(position).setBank(data.getData().getBank());
            bankListBean.get(position).setBank_name(data.getData().getBank_name());
            myBankAdapter.notifyDataSetChanged();
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public void resultDelBank(DelBankBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            bankListBean.remove(position);
            myBankAdapter.notifyItemRemoved(position);
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public void resultWithdrawBank(WithdrawBankBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            SPUtil.put(getActivity(), DIAMOND, data.getData().getMember_diamonds() + "");
            tvDiamond.setText(data.getData().getMember_diamonds() + "");
            tvDiamondNum.setText(data.getData().getMember_diamonds() + "");
            tvWithdrawRmbNum.setText(data.getData().getMember_diamonds() + "元");
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @Override
    public void onLoadMoreRequested() {
        initData();
    }

    @Override
    public void initImmersionBar() {

    }
}
