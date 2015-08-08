package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo;


import kale.adapter.AdapterModel;

public class PhotoData implements AdapterModel {

    public static final int FIRST = 1;
    public static final int Second = 2;
    
    public int favorite_count;

    public String msg;

    public Photo photo;

    @Override
    public int getDataTypeCount() {
        return 2;
    }

    @Override
    public Object getDataType() {
        return 1 + (int) (Math.random() * 2);
    }
}