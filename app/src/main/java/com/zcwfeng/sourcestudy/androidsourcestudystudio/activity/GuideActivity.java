package com.zcwfeng.sourcestudy.androidsourcestudystudio.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.MyApplication;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.CloseMe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.activity 引导页面 Copyright (C), 2015,zcw<br/>
 *
 *
 * @date 2015/7/24 <br/>
 */
@EActivity(R.layout.guide_activity)
public class GuideActivity extends BaseActivity implements  CloseMe {
    ArrayList<View> pageViews;
    ImageView[] imageViews;
    @ViewById(R.id.textview01)
    TextView textview01;
    @ViewById(R.id.guidePages)
    ViewPager viewPager;
    @ViewById(R.id.guideExperience)
    TextView guideExperience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void init(){
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.item_guide_adapter03, null));
        pageViews.add(inflater.inflate(R.layout.item_guide_adapter02, null));
        pageViews.add(inflater.inflate(R.layout.item_guide_adapter, null));

        View guideView = inflater.inflate(R.layout.item_guide_adapter, null);
        pageViews.add(guideView);
        imageViews = new ImageView[pageViews.size()];
        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());

        MyApplication.getInstance().addCloseMeHistory(this);
    }

    @Override
    public void closeMe() {
    }



    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            if (arg0 == 2) guideExperience.setVisibility(View.VISIBLE);
            else guideExperience.setVisibility(View.GONE);
        }

    }

    class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(pageViews.get(arg1));
            return pageViews.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }
}
