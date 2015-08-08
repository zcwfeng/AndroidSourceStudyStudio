package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo;

import com.android.volley.Response;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.MyApplication;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/5
 */
public class GsonDecode<T> {

    /**
     * 向服务器请求数据，进行回调
     */
    public void getGsonData(String url, Class<T> cls, Response.Listener<T> listener,
                            Response.ErrorListener errorListener) {

        GsonRequest<T> gsonRequest = new GsonRequest<T>(url,cls, listener, errorListener);
        MyApplication.requestQueue.add(gsonRequest);
    }
}