package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.MyApplication;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.drawerlayout.DrawerLayoutDemo;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.fresco.FrescoDemoActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.mprogressbar.ProgressBarActivity_;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.RecycleMainActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview.RecyclerViewTestActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo.WaterFlallMainActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.rongcloud.MessageActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import roboguice.inject.InjectView;

//http://plugins.jetbrains.com/plugin/7369

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.test_stick_nav_layout)
    Button mBtn;
    @ViewById
    Button mEventBusBtn;
    @ViewById(R.id.my_image_view)
    SimpleDraweeView draweeView;
    @ViewById(R.id.mRecycleView)
    Button mRecycleView;
    @ViewById(R.id.mFresco)
    Button mFrescoView;
    @ViewById(R.id.mProgressBar)
    Button mProgressBar;
    long exitTime;//点击返回时间
    @ViewById
    Button mMessage;//RongYun

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().notifyCloseMe();

    }

    @AfterViews
    public void init() {
        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
        draweeView.setImageURI(uri);
        ControllerListener listener = new BaseControllerListener();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(draweeView.getController())
                .setControllerListener(listener)
                .build();
        draweeView.setController(controller);


    }


    @Click(R.id.test_stick_nav_layout)
    public void mBtnClick() {
        Intent intent = new Intent(this, com.zcwfeng.sourcestudy.androidsourcestudystudio.stickynavlayout.StickNavLayoutMainActivity.class);
        startActivity(intent);
    }

    @Click(R.id.mEventBusBtn)
    public void clickEventBusTest() {
        Intent intent = new Intent(MainActivity.this, EventBusActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.mInjectView)
    public void clickRoboGuiceTest(){
        MyApplication.getInstance().showLongToast("还没研究明白InjectView");
//        Intent intent = new Intent(MainActivity.this, RoboDemoActivity.class);
//        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Click(R.id.mRecycleView)
    public void testRecycleView() {
//        Intent intent = new Intent(MainActivity.this, RecycleMainActivity.class);
//        startActivity(intent);

        Intent intent = new Intent(MainActivity.this, WaterFlallMainActivity.class);
        startActivity(intent);
    }

    @Click(R.id.my_recyler_listview)
    public void testRecylerViewListView(){

        Intent intent = new Intent(MainActivity.this, RecyclerViewTestActivity.class);
        startActivity(intent);
    }

    @Click(R.id.mFresco)
    public void testFrescoView() {
        Intent intent = new Intent(MainActivity.this, FrescoDemoActivity.class);
        startActivity(intent);
    }

    @Click(R.id.mProgressBar)
    public void testProgressBarView() {
        Intent intent = new Intent(MainActivity.this, ProgressBarActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.mMessage)
    public void testRontYunMessage(){
        Intent intent = new Intent(MainActivity.this, MessageActivity.class);
        startActivity(intent);
    }

    @Click({R.id.drawer_demo})
    public void testClicks(){
        Intent intent = new Intent(MainActivity.this, DrawerLayoutDemo.class);
        startActivity(intent);
    }

    @Click(R.id.h5_demo)
    public void goHtml5(){
        Intent intent = new Intent(MainActivity.this, HTML5Main.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        //2次点击返回退出
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            MyApplication.getInstance().showLongToast(R.string.exit_tips);
            exitTime = System.currentTimeMillis();
        } else {
            Intent intent = new Intent(this, FirstActivity.class);
            intent.setAction("finish");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
