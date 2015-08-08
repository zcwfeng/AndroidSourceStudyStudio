package com.zcwfeng.sourcestudy.androidsourcestudystudio.fresco;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.fresco Copyright (C), 2015,zcw<br/>
 * @date 2015/7/22 <br/>
 */
public class FrescoDemoActivity extends Activity implements View.OnTouchListener {
    private SimpleDraweeView mSimpleDraweeView;
    private SimpleDraweeView mSimpleDraweeView2;
    private SimpleDraweeView mSimpleDraweeView3;
    private String imageUri1 = "http://img.ptcms.csdn.net/article/201503/30/5519091be9a85_middle.jpg?_=30474";
    private String imageUri2 = "http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif";
    private String imageUri3 = "http://p5.qhimg.com/t01d0e0384b952ed7e8.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresco_demo_layout);
        setImage();
    }

    /**
     * 设置自动播放图片 三张
     */
    private void setImage() {
        Uri uri = Uri.parse(imageUri1);
        Uri uri2 = Uri.parse(imageUri2);
        Uri uri3 = Uri.parse(imageUri3);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.frsco_img1);

        DraweeController draweeController1 = Fresco.newDraweeControllerBuilder().setUri(uri).setAutoPlayAnimations(true).build();
        mSimpleDraweeView.setController(draweeController1);
        mSimpleDraweeView.setOnTouchListener(this);
//      Uri uri2 = Uri.parse(imageUri2);
        DraweeController draweeController2 = Fresco.newDraweeControllerBuilder().setUri(uri2).setAutoPlayAnimations(true).build();
        mSimpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.frsco_img2);
        mSimpleDraweeView2.setController(draweeController2);
        RoundingParams mRoundParams2 = mSimpleDraweeView2.getHierarchy().getRoundingParams();
        mRoundParams2.setRoundAsCircle(true);
        mSimpleDraweeView2.getHierarchy().setRoundingParams(mRoundParams2);
        DraweeController draweeController3 = Fresco.newDraweeControllerBuilder().setUri(uri3).setAutoPlayAnimations(true).build();
        mSimpleDraweeView3 = (SimpleDraweeView) findViewById(R.id.frsco_img3);
        mSimpleDraweeView3.setController(draweeController3);
        RoundingParams mRoundParams3 = mSimpleDraweeView3.getHierarchy().getRoundingParams();
        mRoundParams3.setRoundAsCircle(true);
        mSimpleDraweeView3.getHierarchy().setRoundingParams(mRoundParams3);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mSimpleDraweeView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                return true;
//                break;
            case MotionEvent.ACTION_UP:
                mSimpleDraweeView.clearColorFilter();
                return true;
//                break;
        }
        return super.onTouchEvent(event);
    }

}
