package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;

import com.bugtags.library.Bugtags;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.MyApplication;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.activity.litepal.ChooseAreaActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.activity.litepal.WeatherActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.drawerlayout.DrawerLayoutDemo;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.floatactionbutton.NewAndroidWidgetDemo;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.fresco.FrescoDemoActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.mprogressbar.ProgressBarActivity_;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview.RecyclerViewTestActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo.WaterFlallMainActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.views.CustomViewTestActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.litepal.tablemanager.Connector;

//http://plugins.jetbrains.com/plugin/7369

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().notifyCloseMe();

//        testLitePal();

        testOutlineFabView();

    }

    private void testOutlineFabView() {
       View fabView = findViewById(R.id.fab_add);
        fabView.setOutlineProvider(viewOutline);
    }

    ViewOutlineProvider viewOutline = new ViewOutlineProvider() {
        @Override
        public void getOutline(View view, Outline outline) {
            int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
            outline.setOval(-4,-4,fabSize,fabSize);
        }
    };

    private void testLitePal() {
        SQLiteDatabase db = Connector.getDatabase();
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
    public void clickRoboGuiceTest() {
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
    public void testRecylerViewListView() {

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


    @Click({R.id.drawer_demo})
    public void testClicks() {
        Intent intent = new Intent(MainActivity.this, DrawerLayoutDemo.class);
        startActivity(intent);
    }

    @Click(R.id.h5_demo)
    public void goHtml5() {
        Intent intent = new Intent(MainActivity.this, HTML5Main.class);
        startActivity(intent);
    }

    @Click(R.id.btn_new_widget)
    public void newWidget() {
        Intent intent = new Intent(MainActivity.this, NewAndroidWidgetDemo.class);
        startActivity(intent);
    }


    @Click(R.id.custom_view)
    public void customView() {
        Intent intent = new Intent(MainActivity.this, CustomViewTestActivity.class);
        startActivity(intent);
    }

    @Click(R.id.weather_forecast)
    public void forecast() {
        Intent intent = new Intent(MainActivity.this, ChooseAreaActivity.class);
        startActivity(intent);
    }

    @Click(R.id.asimplecache)
    public void testAsimpleCache() {
        Intent intent = new Intent(MainActivity.this, com.zcwfeng.sourcestudy.androidsourcestudystudio.asimplecache.MainActivity.class);
        startActivity(intent);
    }

    @Click(R.id.custom_shotscreen)
    public void testShotScreen() {
//        Intent intent = new Intent(MainActivity.this, com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen.MainActivity.class);
//        Intent intent = new Intent(MainActivity.this, com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen2.ScreenshotActivity.class);
//        Intent intent = new Intent(MainActivity.this, com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen3.ClipPictureActivity.class);
        Intent intent = new Intent(MainActivity.this, com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen5.CutActivity.class);

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

    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, ev);
        return super.dispatchGenericMotionEvent(ev);
    }
}
