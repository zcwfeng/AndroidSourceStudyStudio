package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class ClipView extends View {
    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ClipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        Paint paint = new Paint();
        paint.setColor(0xaa000000);


        int top = ((height -width ) >> 1) + width;

        //top
        canvas.drawRect(0, 0, width, (height -width ) >> 1, paint);
        //bottom
        canvas.drawRect(0, top, width, height, paint);
    }


}
