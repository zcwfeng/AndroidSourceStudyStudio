package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews;

import android.view.View;

public interface OnRecyclerViewItemClickListener<Model> {
    public void onItemClick(View view, Model model);
}