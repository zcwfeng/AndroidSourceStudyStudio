package com.zcwfeng.sourcestudy.androidsourcestudystudio.rongcloud;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;

import org.androidannotations.annotations.EActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.rongcloud Copyright (C), 2015,zcw<br/>
 * @date 2015/7/28 <br/>
 */
@EActivity(R.layout.rongcloud_message_activity)
public class MessageActivity extends BaseActivity{

    final String Token ="d6bCQsXiupB/4OyGkh+TOrI6ZiT8q7s0UEaMPWY0lMxmHdi1v/AAJxOma4aYXyaivfPIJjNHdE+FMH9kV/Jrxg==";//test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * IMKit SDK调用第二步
         *
         * 建立与服务器的连接
         *
         */
        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MessageActivity", "——onSuccess— -"+userId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MessageActivity", "——onError— -"+errorCode);
            }
        });
    }
}
