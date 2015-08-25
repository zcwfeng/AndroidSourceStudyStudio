package com.zcwfeng.sourcestudy.androidsourcestudystudio.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @author zcw
 * @version 1.0
 * @Description:com.zcwfeng.sourcestudy.androidsourcestudystudio.views Copyright (C), 2015,zcw<br/>
 * @date 2015/8/25 <br/>
 */
public class PolygonView extends android.view.View{

    public PolygonView(Context context) {
        super(context);
    }

    public PolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);





        //设置抗锯齿。
        paint.setAntiAlias(true);
        Path path = new Path();


        double height = 100.0f * Math.abs(Math.sin(30));


        path.moveTo(50, 0);
        path.lineTo(150, 0);
        path.lineTo(200, (float) height);
        path.lineTo(150, (float) (2d * height));
        path.lineTo(50, (float) (2d * height));
        path.lineTo(0, (float) height);


        float x = 200 - 5;
        float y = (float) (100 * Math.abs(Math.sin(30)));
        RectF oval1=new RectF(x-6,y-6,x+6,y+6);

        canvas.drawArc(oval1,300,120,false,paint);


//        //封闭前面点所绘制的路径
        path.close();


//        ShapeDrawable mDrawables = new ShapeDrawable(new PathShape(path, 100, 100));
        canvas.drawPath(path,paint);
    }
}
