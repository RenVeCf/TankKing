package com.ipd.tankking.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseFragment;
import com.ipd.tankking.bean.ShareBean;
import com.ipd.tankking.contract.ShareContract;
import com.ipd.tankking.presenter.SharePresenter;
import com.ipd.tankking.utils.SPUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：推荐给好友
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class ShareFragment extends BaseFragment<ShareContract.View, ShareContract.Presenter> implements ShareContract.View {
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.bt_share)
    Button btShare;

    private String shareUrl = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    public ShareContract.Presenter createPresenter() {
        return new SharePresenter(mContext);
    }

    @Override
    public ShareContract.View createView() {
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
        TreeMap<String, String> shareMap = new TreeMap<>();
        shareMap.put("uid", SPUtil.get(getActivity(), USER_ID, "") + "");
        getPresenter().getShare(shareMap, false, false);
    }

    /**
     * contentEtString：字符串内容
     * w：图片的宽
     * h：图片的高
     * logo：不需要logo的话直接传null
     */
    private void qrCode(String qrCode) {
        // 加logo
//        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap bitmap = CodeCreator.createQRCode(qrCode, (int) getResources().getDimension(R.dimen.x450), (int) getResources().getDimension(R.dimen.y450), null);
        ivQrCode.setImageBitmap(bitmap);
    }

    // 分享至
    private void showPopWindow() {
        final TextView tvTitle;
        final LinearLayout llShareWechat;
        final LinearLayout llShareCircleOfFriends;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_share, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window = new PopupWindow(contentView, 900, 500, true);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvTitle.setText("分享至");
        llShareWechat = contentView.findViewById(R.id.ll_share_wechat);
        llShareWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(shareUrl, Wechat.NAME);
            }
        });
        llShareCircleOfFriends = contentView.findViewById(R.id.ll_share_circle_of_friends);
        llShareCircleOfFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare1(shareUrl, WechatMoments.NAME);
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

    // 分享微信好友
    private void showShare(String url, String platform) {
        OnekeyShare oks = new OnekeyShare();
        if (platform != null) {
            oks.setPlatform(platform);
        }
        oks.disableSSOWhenAuthorize();
        oks.setTitle("坦克来袭，推广有奖!");
        oks.setText("快来陪我一起激情PK吧，不来不理你哟");
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getApplicationContext().getResources(), R.mipmap.ic_logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        oks.setUrl(url);
        oks.setComment("很棒，值得分享！！");
        oks.show(getActivity());
    }

    // 分享微信朋友圈
    private void showShare1(String url, String platform) {
        OnekeyShare oks = new OnekeyShare();
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // text是分享文本，所有平台都需要这个字段
        oks.setText("快来陪我一起激情PK吧，不来不理你哟");
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getApplicationContext().getResources(), R.mipmap.ic_logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("很棒，值得分享！！");
        // 启动分享GUI
        oks.show(getActivity());
    }

    @OnClick(R.id.bt_share)
    public void onViewClicked() {
        showPopWindow();
    }

    @Override
    public void resultShare(ShareBean data) {
        if (data.getCode() == 200) {
            qrCode(data.getData().getQr_code());
            shareUrl = data.getData().getShare_url();
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @Override
    public void initImmersionBar() {

    }
}
