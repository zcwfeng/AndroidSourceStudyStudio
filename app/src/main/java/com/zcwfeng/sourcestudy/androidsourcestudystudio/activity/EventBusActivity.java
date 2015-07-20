package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.CommonEvents;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.EventBus;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.EventBusFlag;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;
//http://plugins.jetbrains.com/plugin/7369

@EActivity(R.layout.eventbus_main)
public class EventBusActivity extends AppCompatActivity {

    @ViewById(R.id.test_event_bus)
    Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.test_event_bus)
    public void mBtnClick(){
        EventBus.getDefault().post(EventBusFlag.TEST);
    }


}
