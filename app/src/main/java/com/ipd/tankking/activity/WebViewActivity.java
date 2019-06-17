package com.ipd.tankking.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.base.BasePresenter;
import com.ipd.tankking.base.BaseView;
import com.ipd.tankking.common.view.TopView;
import com.ipd.tankking.utils.SPUtil;

import butterknife.BindView;

import static com.ipd.tankking.common.config.IConstants.USER_ID;
import static com.ipd.tankking.common.config.UrlConfig.BASE_URL;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.tv_webview_top)
    TopView tvWebviewTop;
    @BindView(R.id.tv_top_title)
    TextView ivTopTitle;
    @BindView(R.id.wv_content)
    WebView wvContent;

    private String h5Url = "";//H5链接
    private int h5Type = 0;//H5type

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
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
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wvContent.loadUrl(h5Url);
    }

    @Override
    public void initListener() {

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
    public void initData() {
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
}