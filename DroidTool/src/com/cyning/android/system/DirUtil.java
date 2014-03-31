package com.cyning.android.system;

import java.io.File;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;

public class DirUtil {
	/**
	 *  得到缓存文件 若sd可用
	 * @param context
	 * @param uniqueName
	 * @return
	 */
	public static File getDiskCacheDir(Context context, String uniqueName) {
		// Check if media is mounted or storage is built-in, if so, try and use
		// external cache dir
		// otherwise use internal cache dir
		final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState()) || !isExternalStorageRemovable() ? getExternalCacheDir(
				context).getPath()
				: context.getCacheDir().getPath();

		return new File(cachePath + File.separator + uniqueName);
	}

	@TargetApi(9)
	public static boolean isExternalStorageRemovable() {
		if (StrictModeUtil.hasGingerbread()) {
			return Environment.isExternalStorageRemovable();
		}
		return true;
	}

	@TargetApi(8)
	public static File getExternalCacheDir(Context context) {
		if (StrictModeUtil.hasFroyo()) {
			return context.getExternalCacheDir();
		}

		// Before Froyo we need to construct the external cache dir ourselves
		final String cacheDir = "/Android/data/" + context.getPackageName()
				+ "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath()
				+ cacheDir);
	}

}
