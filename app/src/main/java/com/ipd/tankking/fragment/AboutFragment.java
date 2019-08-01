package com.ipd.tankking.fragment;

import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseFragment;
import com.ipd.tankking.bean.AboutBean;
import com.ipd.tankking.contract.AboutContract;
import com.ipd.tankking.presenter.AboutPresenter;
import com.trello.rxlifecycle2.android.FragmentEvent;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：关于我们
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class AboutFragment extends BaseFragment<AboutContract.View, AboutContract.Presenter> implements AboutContract.View {
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public AboutContract.Presenter createPresenter() {
        return new AboutPresenter(mContext);
    }

    @Override
    public AboutContract.View createView() {
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
        getPresenter().getAbout(false, false);
    }

    @Override
    public void resultAbout(AboutBean data) {
        tvContent.setText(data.getData().getAbout());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @Override
    public void initImmersionBar() {

    }
}
