package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

@SuppressLint("NewApi")
public class PhotoUtil {

	public static boolean getScale(ScalePhoto scalePhoto, int reqWidth,
			int reqHeight) {
		try {
			int degree = readPictureDegree(scalePhoto.getPhotoPath());
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			float scale;
			float wBH;
			BitmapFactory.decodeFile(scalePhoto.getPhotoPath(), options);
			if (options.outHeight >= options.outWidth) {
				if (options.outWidth <= reqWidth)
					scale = 1.0f;
				else
					scale = (float) options.outWidth / (float) reqWidth;
			} else {
				if (options.outHeight <= reqHeight)
					scale = 1.0f;
				else
					scale = (float) options.outHeight / (float) reqHeight;
			}

			wBH = (float) options.outWidth / (float) options.outHeight;
			if (scale >= 2.0f || wBH > 2 || wBH < 0.5) {
				if (options.outHeight > options.outWidth) {
					options.inSampleSize = calculateInSampleSizeByScale(
							options, reqWidth, (int) (reqWidth / wBH));
				} else {
					options.inSampleSize = calculateInSampleSizeByScale(
							options, (int) (reqHeight * wBH), reqHeight);
				}
				options.inJustDecodeBounds = false;
				scalePhoto.setBitmap(BitmapFactory.decodeFile(
						scalePhoto.getPhotoPath(), options));
			}

			if (scalePhoto.getBitmap() == null)
				scalePhoto.setBitmap(BitmapFactory.decodeFile(scalePhoto
						.getPhotoPath()));
			scalePhoto.setScale(scale);
			if (scalePhoto.getBitmap() == null) {
				return false;
			} else {
				if (degree != 0) {
					Bitmap newbitmap = rotaingImageView(degree,
							scalePhoto.getBitmap());
					scalePhoto.setBitmap(newbitmap);
				}
			}
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public static boolean getCutPhoto(Context context, PhotoBean photoBean,
			float width, float height) {
		Uri uri = Uri.fromFile(new File(photoBean.getPhotoPath()));
		InputStream is;
		try {
			int degree = readPictureDegree(photoBean.getPhotoPath());
			is = context.getContentResolver().openInputStream(uri);
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(is, null, options);
			int temp = 0;
			if (degree == 90 || degree == 270) {
				temp = options.outHeight;
				options.outHeight = options.outWidth;
				options.outWidth = temp;
			}
			is.close();
			RectF rectF = photoBean.getRectF();
			Rect outPad = new Rect();
			float scale = options.outWidth
					/ (Math.abs(rectF.left) + rectF.right);
			if (rectF.left < 0)
				outPad.left = (int) (Math.abs(rectF.left) * scale);
			if (rectF.top < 0)
				outPad.top = (int) (Math.abs(rectF.top) * scale);
			float cutWidth = width * scale;
			outPad.right = (int) (Math.abs(rectF.left) * scale + cutWidth);
			outPad.bottom = (int) (Math.abs(rectF.top) * scale + cutWidth);
			int widthInt = (int) cutWidth;
			is = context.getContentResolver().openInputStream(uri);
			options.inJustDecodeBounds = false;
			Bitmap orBitmap = BitmapFactory.decodeStream(is, null, options);
			if (degree != 0) {
				Bitmap newbitmap = rotaingImageView(degree, orBitmap);
				orBitmap = newbitmap;
			}
			Bitmap cutBitmap = Bitmap.createBitmap(orBitmap, outPad.left,
					outPad.top, (int) cutWidth, (int) cutWidth, null, false);
			if (!(outPad.left == 0 && outPad.top == 0
					&& orBitmap.getWidth() == widthInt && orBitmap.getHeight() == widthInt)) {
				orBitmap.recycle();
				orBitmap = null;
			}
			if (cutBitmap != null && cutBitmap.getWidth() > (int) width) {
				cutBitmap = getScaledBitmap(cutBitmap, (int) width,
						(int) height);
			}
			photoBean.setBitmap(cutBitmap);
			is.close();
			return true;
		} catch (FileNotFoundException e) {
			Log.v("PhotoUtil", "File not found:" + uri.toString());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			Log.v("PhotoUtil", "I/O exception with file:" + uri.toString());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取压缩后的图片
	 * 
	 * @param reqWidth
	 *            所需图片压缩尺寸最小宽度
	 * @param reqHeight
	 *            所需图片压缩尺寸最小高度
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromFile(String filePath,
			int reqWidth, int reqHeight) {

		// 首先不加载图片,仅获取图片尺寸
		final BitmapFactory.Options options = new BitmapFactory.Options();
		// 当inJustDecodeBounds设为true时,不会加载图片仅获取图片尺寸信息
		options.inJustDecodeBounds = true;
		// 此时仅会将图片信息会保存至options对象内,decode方法不会返回bitmap对象
		BitmapFactory.decodeFile(filePath, options);

		// 计算压缩比例,如inSampleSize=4时,图片会压缩成原图的1/4
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// 当inJustDecodeBounds设为false时,BitmapFactory.decode...就会返回图片对象了
		options.inJustDecodeBounds = false;
		// 利用计算的比例值获取压缩后的图片对象
		return BitmapFactory.decodeFile(filePath, options);
	}
	
	public static Bitmap decodeBitmapFromFileAbsSize(String filePath,
			int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		Bitmap reBitmap = BitmapFactory.decodeFile(filePath, options);
		reBitmap = getScaledBitmap(reBitmap, reqWidth, reqWidth);
		return reBitmap;
	}

	public static Bitmap decodeSampledBitmapFromInputStream(InputStream is,
			int reqWidth, int reqHeight) {
		if (is == null)
			return null;
		// final BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inJustDecodeBounds = true;
		// BitmapFactory.decodeStream(is, null, options);
		// options.inSampleSize = calculateInSampleSize(options, reqWidth,
		// reqHeight);
		// options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		return bitmap;
	}

	public static Bitmap decodeSampledBitmapFromRes(Resources res, int id,
			int reqWidth, int reqHeight) {
		if (id == 0)
			return null;
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, id, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeResource(res, id, options);
		return bitmap;
	}

	public static int calculateInSampleSizeByScale(
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		float wBH = (float) width / (float) height;
		if (2 < wBH || wBH < 0.5) {
			if (height > reqHeight || width > reqWidth) {
				final int halfHeight = height / 2;
				final int halfWidth = width / 2;
				inSampleSize *= 2;
				while ((halfHeight / inSampleSize) >= reqHeight
						|| (halfWidth / inSampleSize) >= reqWidth) {
					inSampleSize *= 2;
				}
			}
			return inSampleSize;
		}
		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) >= reqHeight
					&& (halfWidth / inSampleSize) >= reqWidth) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) >= reqHeight
					&& (halfWidth / inSampleSize) >= reqWidth) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}

	static public Bitmap getScaledBitmap(Bitmap srcImg, int desWidth,
			int desHeight) {
		if (srcImg == null || desWidth == 0 || desHeight == 0)
			return null;
		if (srcImg.getWidth() != desWidth || srcImg.getHeight() != desHeight) {
			Bitmap retBitmap = Bitmap.createScaledBitmap(srcImg, desWidth,
					desHeight, true);
			srcImg.recycle();
			srcImg = null;
			return retBitmap;
		} else {
			return srcImg;
		}
	}

	static public Bitmap getScaledBitmapNoRecycle(Bitmap srcImg, int desWidth,
			int desHeight) {
		if (srcImg == null)
			return null;
		if (srcImg.getWidth() != desWidth || srcImg.getHeight() != desHeight) {
			Bitmap retBitmap = Bitmap.createScaledBitmap(srcImg, desWidth,
					desHeight, true);
			return retBitmap;
		} else {
			return srcImg;
		}
	}

	public static String getImageAbsolutePath(Context context, Uri imageUri) {
		if (context == null || imageUri == null)
			return null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT
				&& DocumentsContract.isDocumentUri(context, imageUri)) {
			if (isExternalStorageDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}
			} else if (isDownloadsDocument(imageUri)) {
				String id = DocumentsContract.getDocumentId(imageUri);
				Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			} else if (isMediaDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				String selection = MediaStore.Images.Media._ID + "=?";
				String[] selectionArgs = new String[] { split[1] };
				return getDataColumn(context, contentUri, selection,
						selectionArgs);
			}
		} // MediaStore (and general)
		else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
			// Return the remote address
			if (isGooglePhotosUri(imageUri))
				return imageUri.getLastPathSegment();
			return getDataColumn(context, imageUri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
			return imageUri.getPath();
		}
		return null;
	}

	public static String getDataColumn(Context context, Uri uri,
			String selection, String[] selectionArgs) {
		Cursor cursor = null;
		String column = MediaStore.Images.Media.DATA;
		String[] projection = { column };
		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}



	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/*
	 * 旋转图片
	 * 
	 * @param angle
	 * 
	 * @param bitmap
	 * 
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		Log.e("angle", "" + angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		bitmap.recycle();
		return resizedBitmap;
	}

	@SuppressWarnings("hiding")
	public static boolean saveBitmap(Bitmap bitmap, String filePath,
			boolean isPNG) {
		try {
			FileOutputStream out;
			out = new FileOutputStream(filePath);
			if (isPNG)
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			else
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			bitmap.recycle();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
