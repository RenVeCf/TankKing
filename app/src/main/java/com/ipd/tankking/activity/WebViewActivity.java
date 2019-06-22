package com.ipd.tankking.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.SslError;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.bean.ShareBean;
import com.ipd.tankking.common.view.TopView;
import com.ipd.tankking.contract.ShareContract;
import com.ipd.tankking.presenter.SharePresenter;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.ObservableTransformer;

import static com.ipd.tankking.common.config.IConstants.USER_ID;
import static com.ipd.tankking.common.config.UrlConfig.BASE_URL;

public class WebViewActivity extends BaseActivity<ShareContract.View, ShareContract.Presenter> implements ShareContract.View {

    @BindView(R.id.tv_webview_top)
    TopView tvWebviewTop;
    @BindView(R.id.tv_top_title)
    TextView ivTopTitle;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.bt_share)
    Button btShare;

    private String h5Url = "";//H5链接
    private int h5Type = 0;//H5type
    private String shareUrl = "";//分享链接

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public ShareContract.Presenter createPresenter() {
        return new SharePresenter(this);
    }

    @Override
    public ShareContract.View createView() {
        return this;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void init() {
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvWebviewTop);

        h5Type = getIntent().getIntExtra("h5Type", 0);
        switch (h5Type) {
            case 0: //正装待发
                h5Url = BASE_URL + "tk_frontend/html/match/match.html?uid=" + SPUtil.get(this, USER_ID, "") + "&type=1";
                break;
            case 1: //热血战场
                h5Url = BASE_URL + "tk_frontend/html/match/match.html?uid=" + SPUtil.get(this, USER_ID, "") + "&type=2";
                break;
            case 2: //风云九州
                h5Url = BASE_URL + "tk_frontend/html/match/match.html?uid=" + SPUtil.get(this, USER_ID, "") + "&type=3";
                break;
            case 3: //名动天下
                h5Url = BASE_URL + "tk_frontend/html/match/match.html?uid=" + SPUtil.get(this, USER_ID, "") + "&type=4";
                break;
            case 4: //战绩排行
                h5Url = BASE_URL + "tk_frontend/account/militaryExploits.html?uid=" + SPUtil.get(this, USER_ID, "");
                break;
            case 5: //我的战队
                h5Url = BASE_URL + "tk_frontend/account/myTeam.html?uid=" + SPUtil.get(this, USER_ID, "");
                break;
            case 6: //钻石抽奖
                h5Url = BASE_URL + "tk_frontend/html/diamo-su/diamo_su.html?uid=" + SPUtil.get(this, USER_ID, "");
                break;
        }
        WebSettings webSettings = wvContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvContent.addJavascriptInterface(new JsCallBack(), "JsCallBack");
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wvContent.clearCache(true);
        wvContent.loadUrl(h5Url);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        if (h5Type == 5) {
            TreeMap<String, String> shareMap = new TreeMap<>();
            shareMap.put("uid", SPUtil.get(this, USER_ID, "") + "");
            getPresenter().getShare(shareMap, true, false);
        }

        //设置客户端，让点击跳转操作在当前应用内存进行操作
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                if (dialogUtils != null) {
//                    dialogUtils.dismissProgress();
//                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //当发生证书认证错误时，采用默认的处理方法handler.cancel()，停止加载问题页面
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.cancel();
            }
        });

        wvContent.setWebChromeClient(new WebChromeClient() {
            //返回当前加载网页的进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            //获取当前网页的标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                ivTopTitle.setText(title);
            }
        });
    }

    @OnClick(R.id.bt_share)
    public void onViewClicked() {
        showPopWindow();
    }

    // 分享至
    private void showPopWindow() {
        final TextView tvTitle;
        final LinearLayout llShareWechat;
        final LinearLayout llShareCircleOfFriends;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_share, null, false);
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
                if (!shareUrl.equals(""))
                    showShare(shareUrl, Wechat.NAME);
                else
                    T.Short("请稍后点击...", 0);
            }
        });
        llShareCircleOfFriends = contentView.findViewById(R.id.ll_share_circle_of_friends);
        llShareCircleOfFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!shareUrl.equals(""))
                    showShare1(shareUrl, WechatMoments.NAME);
                else
                    T.Short("请稍后点击...", 0);
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

    // 分享微信好友
    private void showShare(String url, String platform) {
        OnekeyShare oks = new OnekeyShare();
        if (platform != null) {
            oks.setPlatform(platform);
        }
        oks.disableSSOWhenAuthorize();
        oks.setTitle("坦克王者");
        oks.setText("");
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        oks.setUrl(url);
        oks.setComment("很棒，值得分享！！");
        oks.show(this);
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
        oks.setText("文本内容");
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("很棒，值得分享！！");
        // 启动分享GUI
        oks.show(this);
    }

    // H5的返回方法（供JS调用）
    class JsCallBack {

        @JavascriptInterface
        public void javaMethod() {
            finish();
            if (WebViewActivity.this.getCurrentFocus() != null) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
        if (WebViewActivity.this.getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void resultShare(ShareBean data) {
        if (data.getCode() == 200) {
            shareUrl = data.getData().getShare_url();
            btShare.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}