package com.zcwfeng.sourcestudy.androidsourcestudystudio;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bugtags.library.Bugtags;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.CloseMe;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.message.ContactNotificationMessageProvider;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.message.DeAgreedFriendRequestMessage;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.message.DemoCommandNotificationMessage;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.rongyun.DemoContext;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.rongyun.RongCloudEvent;

import org.litepal.LitePalApplication;

import java.util.ArrayList;

import io.rong.imkit.RongIM;
import io.rong.imlib.ipc.RongExceptionHandler;

/**
 * Created by 11111 on 2015/7/9.
 */
public class MyApplication extends LitePalApplication {
    public static MyApplication app;
    public static MyApplication getInstance() {
        return app;
    }
    // 请求队列
    public static RequestQueue requestQueue;

    public static int WIDTH;
    public static int HEIGHT;

    /**
     * 关闭之前activity
     */
    private ArrayList<CloseMe> mCloseMeHistory = new ArrayList<CloseMe>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        // 不必为每一次HTTP请求都创建一个RequestQueue对象，推荐在application中初始化
        requestQueue = Volley.newRequestQueue(this);
        // 初始化fresco库
        Fresco.initialize(this);
        //在这里初始化
//        Bugtags.start("a9a7df7976bd7d1aab5c25bdffb48a2b", this, Bugtags.BTGInvocationEventBubble);

        DisplayMetrics metric = getResources().getDisplayMetrics();
        WIDTH = metric.widthPixels;
        HEIGHT = metric.heightPixels;

        /**
         * 注意：
         *
         * IMKit SDK调用第一步 初始化
         *
         * context上下文
         *
         * 只有两个进程需要初始化，主进程和 push 进程
         */
        if("io.rong.app".equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            RongIM.init(this);

            /**
             * 融云SDK事件监听处理
             *
             * 注册相关代码，只需要在主进程里做。
             */
            if ("io.rong.app".equals(getCurProcessName(getApplicationContext()))) {

                RongCloudEvent.init(this);
                DemoContext.init(this);
                Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));
                try {
                    RongIM.registerMessageType(DemoCommandNotificationMessage.class);
                    RongIM.registerMessageType(DeAgreedFriendRequestMessage.class);
                    RongIM.registerMessageTemplate(new ContactNotificationMessageProvider());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////

    public void notifyCloseMe() {
        for (CloseMe mCloseMe : mCloseMeHistory) {
            mCloseMe.closeMe();
        }
    }



    public void setCloseMe(ArrayList<CloseMe> mCloseMeHistory) {
        this.mCloseMeHistory = mCloseMeHistory;
    }

    public void removeCloseMeHistory(CloseMe mCloseMe) {
        mCloseMeHistory.remove(mCloseMe);
    }


    public void addCloseMeHistory(CloseMe mCloseMe) {
        mCloseMeHistory.add(mCloseMe);
    }


    public void clearHistory() {
        mCloseMeHistory.clear();
    }


    public void showShortToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }









}
