package com.zcwfeng.sourcestudy.androidsourcestudystudio.receiver.litepal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.service.litepal.AutoUpdateService;

public class AutoUpdateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent i = new Intent(context, AutoUpdateService.class);
		context.startService(i);
	}

}