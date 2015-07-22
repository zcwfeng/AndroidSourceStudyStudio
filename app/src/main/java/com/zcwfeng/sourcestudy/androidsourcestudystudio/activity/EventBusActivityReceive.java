package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

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

@EActivity(R.layout.eventbus_main_receive)
public class EventBusActivityReceive extends AppCompatActivity {


    @ViewById(R.id.eventbus_receive)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    public void onEventMainThread(CommonEvents event) {
        mTv.setText("event bus 事件总线接受成功");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
