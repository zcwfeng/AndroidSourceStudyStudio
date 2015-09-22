package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen5;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.MyApplication;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;
import com.zcwfeng.sourcestudy.androidsourcestudystudio.basic.BaseActivity;

public class CutActivity extends BaseActivity implements
		OnPhotoInterface, View.OnClickListener {
	public static final String EDIT_PHOTO_DATA = "edit_photo_data";
	public static final String PHOTO_PATH = "photo_path";

	private Context iContext;
	private String photoPath;
	private CutView imageView;
	private RelativeLayout rlCenter;
	private ScalePhoto scalePhoto;
	private GetScalePhotoTask getScalePhotoTask;
	private RectF cutRectF;
	private boolean jumpNextAct = false;


	final int REQUEST_CODE_PICK_IMAGE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		if (IApplication.fullScreen)
//			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_cut);
		iContext = CutActivity.this;
		getAlbumPic();
	}

	private void getAlbumPic() {

		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");//相片类型
		startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK ) {
			if(requestCode == REQUEST_CODE_PICK_IMAGE) {





//				photoPath = getIntent().getExtras().getString(PHOTO_PATH);
				Uri uri = data.getData();
				Uri originalUri = data.getData();//得到图片的URI

				String []imgs={MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
				Cursor cursor=this.managedQuery(originalUri, imgs, null, null, null);
				int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				String img_url=cursor.getString(index);
				Log.d("Infor", "img_url:"+img_url);
				photoPath =img_url;
				findView();
				setAction();
			}
		}
	}

	private void findView() {
		imageView = (CutView) findViewById(R.id.img_photo);
		rlCenter = (RelativeLayout) findViewById(R.id.rl_center);
	}

	private void setAction() {
//		iActionBar.setDisplayHomeAsUpEnabled(true);
//		iActionBar.setDisplayShowHomeEnabled(false);
//		iActionBar.setDisplayShowTitleEnabled(true);
//		iActionBar.setTitle(R.string.title_eidt);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlCenter
				.getLayoutParams();
		params.height = MyApplication.WIDTH;
		params.width = MyApplication.WIDTH;
		rlCenter.setLayoutParams(params);
		scalePhoto = new ScalePhoto();
		Log.e("photopath", photoPath);

		scalePhoto.setPhotoPath(photoPath);
		getScalePhotoTask = new GetScalePhotoTask(iContext, scalePhoto, this);
		getScalePhotoTask.execute();
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (scalePhoto != null && scalePhoto.getBitmap() == null) {
			getScalePhotoTask = new GetScalePhotoTask(iContext, scalePhoto,
					this);
			getScalePhotoTask.execute();
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		if(jumpNextAct){
			jumpNextAct = false;
			imageView.setImageBitmap(null);
			rlCenter.setVisibility(View.GONE);
			scalePhoto.recycle();
		}
		super.onStop();
	}


	private void setImageBitmap() {
		rlCenter.setVisibility(View.VISIBLE);
		imageView.setImageBitmap(scalePhoto.getBitmap());
		imageView.setMaxScale(scalePhoto.getScale());
	}

	@Override
	public void complateSuccess() {
		setImageBitmap();
		getScalePhotoTask = null;
	}

	@Override
	public void complateFailed() {
		getScalePhotoTask = null;
	}

//	private void startNextAct(PhotoDataS photoDataS) {
//		imageView.clearScale();
//		Intent intent = new Intent(this, FilterActivity.class);
//		intent.putExtra(IMarkConstant.EDIT_PHOTO_DATA, photoDataS);
//		startActivity(intent);
//	}

	@Override
	protected void onDestroy() {
		imageView.setImageBitmap(null);
		rlCenter.setVisibility(View.GONE);
		scalePhoto.recycle();
		super.onDestroy();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
//		case R.id.menu_text_id:
//			cutRectF = imageView.getRectF();
//			PhotoDataS photoDataS = new PhotoDataS();
//			photoDataS.setPhotoPath(scalePhoto.getPhotoPath());
//			photoDataS.rectLeft = cutRectF.left;
//			photoDataS.rectTop = cutRectF.top;
//			photoDataS.rectBottom = cutRectF.bottom;
//			photoDataS.rectRight = cutRectF.right;
//			jumpNextAct = true;
//			startNextAct(photoDataS);
//			break;
		default:
			break;
		}
	}
	
	private void goToBack(){
		finish();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			goToBack();
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

}