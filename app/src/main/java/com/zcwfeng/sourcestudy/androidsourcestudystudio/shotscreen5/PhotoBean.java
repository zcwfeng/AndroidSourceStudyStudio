package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen5;

import java.io.Serializable;


import android.graphics.Bitmap;
import android.graphics.RectF;

@SuppressWarnings("serial")
public class PhotoBean implements Serializable {
	private Bitmap bitmap;
	private RectF rectF;
	private String photoPath;

//	private FilterType filterType = null;
	private int filterAlpha = 0;

	public RectF getRectF() {
		return rectF;
	}

	public void setRectF(RectF rectF) {
		this.rectF = rectF;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
//
//	public FilterType getFilterType() {
//		return filterType;
//	}
//
//	public void setFilterType(FilterType filterType) {
//		this.filterType = filterType;
//	}

	public int getFilterAlpha() {
		return filterAlpha;
	}

	public void setFilterAlpha(int filterAlpha) {
		this.filterAlpha = filterAlpha;
	}

	public void recycle() {
		if (bitmap != null) {
			bitmap.recycle();
		}
		bitmap = null;
	}

}
