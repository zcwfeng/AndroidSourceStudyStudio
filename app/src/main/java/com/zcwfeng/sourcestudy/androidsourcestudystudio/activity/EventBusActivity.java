package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.CommonEvents;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.EventBus;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.event.EventBusFlag;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;
//http://plugins.jetbrains.com/plugin/7369

@EActivity(R.layout.eventbus_main)
public class EventBusActivity extends BaseActivity {

    @ViewById(R.id.test_event_bus)
    Button mBtn;
    @ViewById(R.id.test_event_bus_2)
    Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Click(R.id.test_event_bus)
    public void mBtnClick(){
//        EventBus.getDefault().postSticky(new CommonEvents(EventBusFlag.TEST, EventBusFlag.TEST));

    }

    @Click(R.id.test_event_bus_2)
    public void mBtnClick2(){
        Intent intent = new Intent(this,EventBusActivityReceive2.class);
        startActivity(intent);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EventBus.getDefault().post(new CommonEvents(EventBusFlag.TEST,EventBusFlag.TEST));
    }

    public void onEventMainThread(CommonEvents event) {
        if (EventBusFlag.TEST.equalsIgnoreCase(event.getEventsResult() + "")) {
//            mTv.setText("event bus 事件总线接受成功");
            Log.e("zcw", "onEventMainThread");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
