/*
 * Copyright 2014 http://Bither.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bither.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class BitmapCompressUtil {

	private static int DEFAULT_QUALITY = 95;

	public static void compressBitmap(Bitmap bit, String fileName,
			boolean optimize,int zoom) {
		compressBitmap(bit, DEFAULT_QUALITY, fileName, optimize,zoom);

	}

	public static void compressBitmap(Bitmap bit, int quality, String fileName,boolean optimize,int zoom) {
		Log.d("native", "compress of native");

		if (bit.getConfig() != Config.ARGB_8888) {
			Bitmap result = null;
			result = Bitmap.createBitmap(bit.getWidth()/zoom, bit.getHeight()/zoom, Config.ARGB_8888);// 缩小3倍
			Canvas canvas = new Canvas(result);
			int width = bit.getWidth() / zoom;
			int height = bit.getHeight() / zoom;
			Rect rect = new Rect(0, 0, width, height);
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			canvas.drawBitmap(bit, null, rect, paint);
			saveBitmap(result, quality, fileName, optimize);
			result.recycle();
		} else {
			saveBitmap(bit, quality, fileName, optimize);
		}

	}

	/**
	 *调用这个即可
	 * @param fileName (保存的文件名)
	 */
	public static void saveBitmap(Bitmap bit, int quality, String fileName,
			boolean optimize) {

		compressBitmap(bit, bit.getWidth(), bit.getHeight(), quality,
				fileName.getBytes(), optimize);

	}

	private static native String compressBitmap(Bitmap bit, int w, int h,
			int quality, byte[] fileNameBytes, boolean optimize);

	static {
		System.loadLibrary("jpegbither");
		System.loadLibrary("bitherjni");
	}

}
