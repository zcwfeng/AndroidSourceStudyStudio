package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.os.Bundle;
import android.webkit.WebView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_h5_main);
        mWebView = (WebView) findViewById(R.id.h5_entrerance);

        initH5Property();
        initH5Data();
    }

    private void initH5Data() {

    }

    private void initH5Property() {

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
