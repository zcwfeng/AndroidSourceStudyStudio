package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.fresco.FrescoDemoActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.mprogressbar.ProgressBarActivity_;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.RecycleMainActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo.WaterFlallMainActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
//http://plugins.jetbrains.com/plugin/7369

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
