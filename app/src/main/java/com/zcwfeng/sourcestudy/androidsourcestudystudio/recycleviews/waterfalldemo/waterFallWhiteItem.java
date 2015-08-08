package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo;

import android.net.Uri;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;

import kale.adapter.AdapterItem;
import kale.adapter.ViewHolder;


public class waterFallWhiteItem implements AdapterItem<PhotoData> {

    public DynamicHeightSimpleDraweeView contentSdv;

    public TextView descriptionTv;

    public SimpleDraweeView headPicSdv;

    public TextView positionTv;
    

    @Override
    public int getLayoutResId() {
        return R.layout.aaa_waterfall_white_item;
    }

    @Override
    public void initViews(ViewHolder vh, PhotoData photoData, int position) {
        contentSdv = vh.getView(R.id.aaa_wf_item_content_DraweeView);
        descriptionTv = vh.getView(R.id.aaa_wf_item_description_textView);
        headPicSdv = vh.getView(R.id.aaa_wf_item_user_head_draweeView);
        positionTv = vh.getView(R.id.aaa_wf_item_positon_textView);
        setViews(photoData, position);
    }

    private void setViews(PhotoData data,int position) {
        contentSdv.setImageURI(Uri.parse(data.photo.path));

        float picRatio = (float) data.photo.height / data.photo.width;
        contentSdv.setHeightRatio(picRatio);

        descriptionTv.setText(data.msg);
        headPicSdv.setImageURI(Uri.parse("http://wenwen.soso.com/p/20100203/20100203005516-1158326774.jpg"));

        positionTv.setText("No." + position);
    }
}