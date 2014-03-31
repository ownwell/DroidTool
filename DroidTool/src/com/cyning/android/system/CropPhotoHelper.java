package com.cyning.android.system;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * author : 桥下一粒砂 (chenyoca@gmail.com) date : 2013-07-16 裁剪图片辅助类
 */
public class CropPhotoHelper {

	private final Activity context;

	public CropPhotoHelper(Activity context) {
		this.context = context;
	}

	public void buildCropIntent(Intent intent, Uri resourceUri, Uri outputUri,
			int aspectX, int aspectY, int outputX, int outputY, int requestCode) {
		if (resourceUri == null) {
			intent.setType("image/*");
		} else {
			intent.setDataAndType(resourceUri, "image/*");
		}
		intent.setDataAndType(resourceUri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		boolean returnData = outputUri == null;
		if (!returnData) {
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
		}
		intent.putExtra("return-data", returnData);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		context.startActivityForResult(intent, requestCode);
	}

	/**
	 * 获取从裁剪图片结果中返回图片路径。
	 * 
	 * @param data
	 *            裁剪图片结果返回的数据
	 * @param imageOutputPath
	 *            如果裁剪结果不直接返回地址，需要指定一个临时保存路径
	 * @return
	 */
	public String extraPhotoPath(Intent data, String imageOutputPath) {
		String imagePath = null;
		Uri uri = data.getData();
		if (uri != null) {
			String[] columns = { MediaStore.Images.Media.DATA };
			Cursor cursor = context
					.managedQuery(uri, columns, null, null, null);
			if (cursor != null) {
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				imagePath = cursor.getString(column_index);
			} else {
				String extraPath = uri.toString();
				if (extraPath != null)
					imagePath = extraPath.substring(7);
			}
		} else {
			Bitmap img = data.getParcelableExtra("data");
			if (img == null) {
				Bundle extras = data.getExtras();
				img = (Bitmap) (extras == null ? null : extras.get("data"));
			}
			imagePath = saveCropImageToTempFile(img, imageOutputPath) ? imageOutputPath
					: null;
		}
		return imagePath;
	}

	public boolean hasImageCaptureBug() {
		// list of known devices that have the bug
		ArrayList<String> devices = new ArrayList<String>();
		devices.add("android-devphone1/dream_devphone/dream");
		devices.add("generic/sdk/generic");
		devices.add("vodafone/vfpioneer/sapphire");
		devices.add("tmobile/kila/dream");
		devices.add("verizon/voles/sholes");
		devices.add("google_ion/google_ion/sapphire");
		return devices.contains(android.os.Build.BRAND + "/"
				+ android.os.Build.PRODUCT + "/" + android.os.Build.DEVICE);
	}

	private boolean saveCropImageToTempFile(Bitmap img, String imageOutputPath) {
		if (img == null || imageOutputPath == null) {
			Log.e("SAVE_CROP", "Image(bitmap) or OutputPath is NULL !");
			return false;
		}
		boolean result = true;
		File cropFile = new File(imageOutputPath);
		try {
			FileOutputStream os = new FileOutputStream(cropFile);
			img.compress(Bitmap.CompressFormat.PNG, 100, os);
			os.flush();
			os.close();
		} catch (Exception exp) {
			exp.printStackTrace();
			result = false;
		}
		return result;
	}
}
