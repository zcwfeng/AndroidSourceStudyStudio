package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.CommonEvents;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.EventBus;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.EventBusFlag;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
//http://plugins.jetbrains.com/plugin/7369

public class EventBusActivityReceive2 extends Activity {


    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.eventbus_main_receive);
        mTv = (TextView) findViewById(R.id.eventbus_receive);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(new CommonEvents(EventBusFlag.TEST, EventBusFlag.TEST));

    }

    public void onEventMainThread(CommonEvents event) {
        mTv.setText("测试");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
