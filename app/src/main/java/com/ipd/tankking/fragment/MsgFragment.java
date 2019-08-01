package com.ipd.tankking.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.tankking.R;
import com.ipd.tankking.adapter.MsgAdapter;
import com.ipd.tankking.base.BaseFragment;
import com.ipd.tankking.bean.MsgBean;
import com.ipd.tankking.contract.MsgContract;
import com.ipd.tankking.presenter.MsgPresenter;
import com.ipd.tankking.utils.SPUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：消息中心
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class MsgFragment extends BaseFragment<MsgContract.View, MsgContract.Presenter> implements MsgContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_msg)
    RecyclerView rvMsg;
    @BindView(R.id.srl_msg)
    SwipeRefreshLayout srlMsg;

    private MsgAdapter msgAdapter;
    private List<MsgBean.DataBeanX.DataBean> msgBean;
    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_msg;
    }

    @Override
    public MsgContract.Presenter createPresenter() {
        return new MsgPresenter(mContext);
    }

    @Override
    public MsgContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMsg.setLayoutManager(layoutManager);
        rvMsg.setHasFixedSize(true);
        rvMsg.setItemAnimator(new DefaultItemAnimator());

        msgBean = new ArrayList<>();
        msgAdapter = new MsgAdapter(msgBean);
        rvMsg.setAdapter(msgAdapter);
    }

    @Override
    public void initListener() {
        srlMsg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData();
                srlMsg.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> msgMap = new TreeMap<>();
        msgMap.put("id", SPUtil.get(getActivity(), USER_ID, "") + "");
        msgMap.put("page", page + "");
        getPresenter().getMsg(msgMap, false, false);
    }

    @Override
    public void resultMsg(MsgBean data) {
        if (page == 1) {
            msgBean.clear();
            msgBean.addAll(data.getData().getData());
            msgAdapter.setNewData(msgBean);
            if (data.getData().getTotal() > 0) {
                page += 1;
                msgAdapter.setOnLoadMoreListener(this, rvMsg);
            } else {
                msgAdapter.loadMoreEnd();
            }
        } else {
            if (data.getData().getData().size() > 0) {
                page += 1;
                msgAdapter.addData(data.getData().getData());
                msgAdapter.loadMoreComplete();
            } else {
                msgAdapter.loadMoreEnd();
            }
        }
        //空数据时的页面
        msgAdapter.setEmptyView(R.layout.null_data, rvMsg);
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
