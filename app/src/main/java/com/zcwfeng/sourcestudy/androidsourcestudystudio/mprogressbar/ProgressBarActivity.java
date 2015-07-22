package com.zcwfeng.sourcestudy.androidsourcestudystudio.mprogressbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.mprogressbar Copyright (C), 2015,zcw<br/>
 * @date 2015/7/22 <br/>
 */
@EActivity(R.layout.progressbar_demo_layout)
public class ProgressBarActivity extends Activity  implements RubberIndicator.OnMoveListener{

    private GestureDetectorCompat mDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @ViewById(R.id.focus_position)
    TextView mTextView;
    @ViewById(R.id.rubber)
    RubberIndicator mRubberIndicator;


    @AfterViews
    public void init(){
        // set gesture detector
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        mRubberIndicator.setCount(5, 2);
        mRubberIndicator.setOnMoveListener(this);
        updateFocusPosition();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onMovedToLeft() {
        updateFocusPosition();
    }

    @Override
    public void onMovedToRight() {
        updateFocusPosition();
    }
    public void moveToRight(View view) {
        mRubberIndicator.moveToRight();
    }

    public void moveToLeft(View view) {
        mRubberIndicator.moveToLeft();
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
    }

}
