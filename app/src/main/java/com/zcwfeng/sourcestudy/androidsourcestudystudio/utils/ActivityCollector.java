package com.zcwfeng.sourcestudy.androidsourcestudystudio.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public class ActivityCollector {

    //初始化活动集合
    public static List<Activity> activities = new ArrayList<Activity>();
    //添加新的活动
    public static void addActivity(Activity activity) {
        //先判断list集合里是否已有该活动
        if(!activities.contains(activity)){
            activities.add(activity);
        }

    }
    //移除指定的活动
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }
    //移除所有的活动
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
