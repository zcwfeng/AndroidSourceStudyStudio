package com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.MyRecyclerAdapter;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.recycleviews.recylerlistview Copyright (C), 2015,zcw<br/>
 * @date 2015/8/3 <br/>
 */
public class MSimpleAdapter extends RecyclerView.Adapter<MSimpleAdapter.ViewHolder> {

    public ArrayList<String> mDatas;
    public LayoutInflater mInflater;

    public MSimpleAdapter(ArrayList<String> mDatas, Context context) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MSimpleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_listview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position).toString());

    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.s);
        }

    }
}
