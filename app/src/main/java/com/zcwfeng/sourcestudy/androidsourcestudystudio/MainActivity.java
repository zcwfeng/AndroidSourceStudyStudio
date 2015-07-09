package com.zcwfeng.sourcestudy.androidsourcestudystudio;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;
//http://plugins.jetbrains.com/plugin/7369

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.test_stick_nav_layout)
    Button mBtn;

    @ViewById(R.id.my_image_view)
    SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Background
        // Executed in a background thread
    void translateInBackground(String textToTranslate) {
        String translatedText = callGoogleTranslate(textToTranslate);
        showResult(translatedText);
    }

    public String callGoogleTranslate(String txt) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "demo";
    }

    @UiThread
    void showResult(String translatedText) {
        mBtn.setText(translatedText);
    }

    @AfterViews
    public void testFreso() {
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

}
