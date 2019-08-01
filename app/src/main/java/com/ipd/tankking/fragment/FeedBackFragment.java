package com.ipd.tankking.fragment;

import android.widget.EditText;
import android.widget.ImageButton;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseFragment;
import com.ipd.tankking.bean.FeedBackBean;
import com.ipd.tankking.contract.FeedBackContract;
import com.ipd.tankking.presenter.FeedBackPresenter;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：意见反馈
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class FeedBackFragment extends BaseFragment<FeedBackContract.View, FeedBackContract.Presenter> implements FeedBackContract.View {
    @BindView(R.id.et_feed_back)
    EditText etFeedBack;
    @BindView(R.id.ib_feed_back)
    ImageButton ibFeedBack;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_feed_back;
    }

    @Override
    public FeedBackContract.Presenter createPresenter() {
        return new FeedBackPresenter(mContext);
    }

    @Override
    public FeedBackContract.View createView() {
        return this;
    }

    @Override
    public void init() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.ib_feed_back)
    public void onViewClicked() {
        if (!etFeedBack.getText().toString().trim().equals("")) {
            TreeMap<String, String> feedBackMap = new TreeMap<>();
            feedBackMap.put("id", SPUtil.get(getActivity(), USER_ID, "") + "");
            feedBackMap.put("feed", etFeedBack.getText().toString().trim());
            getPresenter().getFeedBack(feedBackMap, false, false);
        } else
            T.Short("请填写意见", 0);
    }

    @Override
    public void resultFeedBack(FeedBackBean data) {
        if (data.getCode().equals("200"))
            T.Short(data.getMsg(), 1);
        else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @Override
    public void initImmersionBar() {

    }
}
