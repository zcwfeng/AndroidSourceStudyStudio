package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen5;

import android.graphics.Bitmap;

public class ScalePhoto {
	private Bitmap bitmap;
	private float scale;
	private String photoPath;

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public void recycle() {
		if (bitmap != null) {
			bitmap.recycle();
		}
		bitmap = null;
	}

}