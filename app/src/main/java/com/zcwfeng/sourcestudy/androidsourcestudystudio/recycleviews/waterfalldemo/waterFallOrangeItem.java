package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.waterfalldemo;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;

import android.net.Uri;
import android.widget.TextView;

import kale.adapter.AdapterItem;
import kale.adapter.ViewHolder;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/10
 */
public class waterFallOrangeItem implements AdapterItem<PhotoData> {

    /** 内容主体的图片 */
    public DynamicHeightSimpleDraweeView contentSdv;

    /** 图片下方的描述文字 */
    public TextView descriptionTv;

    /** 用户的头像 */
    public SimpleDraweeView headPicSdv;

    /**
     * 标识位置的textView
     */
    public TextView positionTv;


    @Override
    public int getLayoutResId() {
        return R.layout.aaa_waterfall_orange_item;
    }

    @Override
    public void initViews(ViewHolder viewHolder, PhotoData data, int i) {
        contentSdv = viewHolder.getView(R.id.aaa_wf_item_content_DraweeView);
        descriptionTv = viewHolder.getView(R.id.aaa_wf_item_description_textView);
        headPicSdv = viewHolder.getView(R.id.aaa_wf_item_user_head_draweeView);
        positionTv = viewHolder.getView(R.id.aaa_wf_item_positon_textView);
        setViews(data,i);
    }

    public void setViews(PhotoData data, int position) {
        contentSdv.setImageURI(Uri.parse(data.photo.path));
        
        float picRatio = (float) data.photo.height / data.photo.width;
        contentSdv.setHeightRatio(picRatio);

        descriptionTv.setText(data.msg);
        // 必须设置加载的uri
        headPicSdv.setImageURI(Uri.parse("http://wenwen.soso.com/p/20100203/20100203005516-1158326774.jpg"));

        positionTv.setText("No." + position);
    }
}