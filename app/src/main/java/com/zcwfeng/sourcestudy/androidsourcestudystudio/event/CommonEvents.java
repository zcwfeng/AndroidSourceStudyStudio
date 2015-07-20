package com.zcwfeng.sourcestudy.androidsourcestudystudio.event;

/**
 * Created by lipeng on 15-6-29.
 */
public class CommonEvents<T> {

    /**事件类型*/
    private String mEventsName;
    /**事件结果*/
    private T mEventsResult;
    public String getEventsName() {
        return mEventsName;
    }
    public T getEventsResult() {
        return mEventsResult;
    }

    public CommonEvents(String name,T result){
        this.mEventsName = name;
        this.mEventsResult = result;
    }
}