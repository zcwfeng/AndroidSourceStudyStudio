package com.zcwfeng.sourcestudy.androidsourcestudystudio.utils;

/**
 * ==========================================
 * Created by David Zhang on 2015/08/30.
 * Description：
 * Copyright © 2015 张传伟. All rights reserved.
 * Modified by:
 * Modified Content:
 * ==========================================
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
