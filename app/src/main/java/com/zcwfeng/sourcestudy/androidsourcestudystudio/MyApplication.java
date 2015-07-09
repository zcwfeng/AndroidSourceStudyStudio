package com.zcwfeng.sourcestudy.androidsourcestudystudio;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 11111 on 2015/7/9.
 */
public class MyApplication extends Application {

    public static MyApplication app;

    public MyApplication getInstance() {

        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Fresco.initialize(this);
    }
}
