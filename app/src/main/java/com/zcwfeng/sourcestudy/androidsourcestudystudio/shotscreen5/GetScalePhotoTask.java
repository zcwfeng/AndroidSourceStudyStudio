package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen5;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.MyApplication;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;

public  class GetScalePhotoTask extends AsyncTask<Void, Void, Boolean> {

	private ProgressDialog dialog;
	private ScalePhoto scalePhoto;
	private Context context;
	private OnPhotoInterface onPhotoInterface;

	public GetScalePhotoTask(Context context, ScalePhoto scalePhoto,
			OnPhotoInterface onPhotoInterface) {
		this.scalePhoto = scalePhoto;
		this.context = context;
		this.onPhotoInterface = onPhotoInterface;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		dialog.dismiss();
		if (onPhotoInterface != null) {
			if (result) {
				onPhotoInterface.complateSuccess();
			} else {
				onPhotoInterface.complateFailed();
			}
		}
	}

	@Override
	protected void onPreExecute() {
		String str = context.getString(R.string.tip_photo_scale);
		dialog = ProgressDialog.show(context, "", str);
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		boolean reBl = PhotoUtil.getScale(scalePhoto, MyApplication.WIDTH,
				MyApplication.WIDTH);
		return reBl;
	}
}
