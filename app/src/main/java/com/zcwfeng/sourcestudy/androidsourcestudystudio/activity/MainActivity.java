package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.UiThread;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
<<<<<<< HEAD
import com.zcwfeng.sourcestudy.androidsourcestudystudio.mprogressbar.RubberIndicator;
=======
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.RecycleMainActivity;
>>>>>>> 368628ec2ab829b06c08e8d59bce3d6498150dc5

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;
//http://plugins.jetbrains.com/plugin/7369

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements RubberIndicator.OnMoveListener {

    @ViewById(R.id.focus_position)
    TextView mTextView;

    @ViewById(R.id.rubber)
    RubberIndicator mRubberIndicator;
    @ViewById(R.id.test_stick_nav_layout)
    Button mBtn;
    @ViewById
    Button mEventBusBtn;
    @ViewById(R.id.my_image_view)
    SimpleDraweeView draweeView;

<<<<<<< HEAD
    private GestureDetectorCompat mDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
=======
    @ViewById(R.id.mRecycleView)
    Button mRecycleView;
>>>>>>> 368628ec2ab829b06c08e8d59bce3d6498150dc5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set gesture detector
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }




    @AfterViews
    public void init() {

        mRubberIndicator.setCount(5, 2);
        mRubberIndicator.setOnMoveListener(this);
        updateFocusPosition();

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
    public void clickEventBusTest(){
        Intent intent = new Intent(MainActivity.this,EventBusActivity_.class);
        startActivity(intent);
    }

<<<<<<< HEAD

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void moveToRight(View view) {
        mRubberIndicator.moveToRight();
    }

    public void moveToLeft(View view) {
        mRubberIndicator.moveToLeft();
    }

    @Override
    public void onMovedToLeft() {
        updateFocusPosition();
    }

    @Override
    public void onMovedToRight() {
        updateFocusPosition();
    }

    private void updateFocusPosition() {
        mTextView.setText("Focusing on " + mRubberIndicator.getFocusPosition());
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String TAG = "Gestures";

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.d(TAG, "swipe left");
                    moveToLeft(null);
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.d(TAG, "swipe right");
                    moveToRight(null);
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
=======
    @Click(R.id.mRecycleView)
    public void testRecycleView(){
        Intent intent = new Intent(MainActivity.this,RecycleMainActivity.class);
        startActivity(intent);
>>>>>>> 368628ec2ab829b06c08e8d59bce3d6498150dc5
    }

}
