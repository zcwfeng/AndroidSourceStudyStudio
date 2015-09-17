package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

    public static float mWidth;
    public static float mHeight;

    private ViewThread mThread;
    private ArrayList<Element> mElements = new ArrayList<Element>();
    private int mElementNumber = 0;

    private Paint mPaint = new Paint();
    private String mScreenshotPath = Environment.getExternalStorageDirectory() + "/droidnova";

    public Panel(Context context) {
        super(context);
        getHolder().addCallback(this);
        mThread = new ViewThread(this);
        mPaint.setColor(Color.WHITE);
    }

    public void doDraw(long elapsed, Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        synchronized (mElements) {
            for (Element element : mElements) {
                element.doDraw(canvas);
            }
        }
        canvas.drawText("FPS: " + Math.round(1000f / elapsed) + " Elements: " + mElementNumber, 10,
                10, mPaint);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!mThread.isAlive()) {
            mThread = new ViewThread(this);
            mThread.setRunning(true);
            mThread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mThread.isAlive()) {
            mThread.setRunning(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (mElements) {
            mElements.add(new Element(getResources(), (int) event.getX(), (int) event.getY()));
            mElementNumber = mElements.size();
        }
        return super.onTouchEvent(event);
    }

    /**
     * If called, creates a screenshot and saves it as a JPG in the folder "droidnova" on the sdcard.
     */
    public void saveScreenshot() {
        if (ensureSDCardAccess()) {
            Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            doDraw(1, canvas);
            File file = new File(mScreenshotPath + "/" + System.currentTimeMillis() + ".jpg");
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.e("Panel", "FileNotFoundException", e);
            } catch (IOException e) {
                Log.e("Panel", "IOEception", e);
            }
        }
    }
    
    /**
     * Helper method to ensure that the given path exists.
     * TODO: check external storage state
     */
    private boolean ensureSDCardAccess() {
        File file = new File(mScreenshotPath);
        if (file.exists()) {
            return true;
        } else if (file.mkdirs()) {
            return true;
        }
        return false;
    }
}
