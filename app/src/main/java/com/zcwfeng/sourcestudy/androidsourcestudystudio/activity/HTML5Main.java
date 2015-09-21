package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.activity Copyright (C), 2015,zcw<br/>
 * @date 2015/8/10 <br/>
 */
public class HTML5Main extends BaseActivity {
    private WebView mWebView;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_h5_main);
        mWebView = (WebView) findViewById(R.id.h5_entrerance);

        initH5Property();

    }

    private void initH5Data() {

    }

    @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
    private void initH5Property() {
// 加上这句话控制窗口属性，支持输入手机号码的弹出域，避免覆盖无法输入的情况
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String url = "file:///android_asset/www/weixin.html";
        if (TextUtils.isEmpty(url) || url.equals("\"\"") ) {
            url = "file:///android_asset/www/err.html";
        }

        WebSettings ws = mWebView.getSettings();
        ws.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");
        ws.setJavaScriptEnabled(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 使页面获得焦点
        mWebView.requestFocus();
        mWebView.setLongClickable(true);
        mWebView.loadUrl(url);
        mWebView.addJavascriptInterface(this, "close_obj"); // 处理window.close_obj.xxx()的标志
        mWebView.addJavascriptInterface(this, "common_back_obj");
        mWebView.addJavascriptInterface(this, "share_obj");// 分享
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("提示");
        progressDialog.show();
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressDialog != null) {
                    progressDialog.dismiss();

                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (errorCode == 404) {
                    // 用javascript隐藏系统定义的404页面信息
                    String data = "出错了亲，稍后重试，按返回键返回！";
                    try {
                        view.loadData("javascript:document.body.innerHTML=\"" + data + "\"", "text/html", "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                try {
                    progressDialog.setMessage("正在加载" + progress + "%");
                    setProgress(progress * 1000);
                    getWindow().setFeatureInt(Window.FEATURE_PROGRESS, progress * 100);
                    if (progress == 100) {
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.onProgressChanged(view, progress);
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }




}
