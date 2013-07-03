package com.rhymes.client.mariobros.world;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.interactions.touch.InteractionTouchCallbacks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

public class Helper {
	public static boolean insideRegion(int x, int y, Rect region)
	{
		if (x > region.left
				&& x < region.right) {
			if (y > region.top
					&& y < region.bottom) {
				return true;
			}
		}		
		return false;
	}
	
	public static void printRect(Rect r, String prefix) {
		Log.d("Helper", prefix + "\nLeft:" + r.left + " Right: "
				+ r.right + " Bottom: " + r.bottom + " Top: " + r.top);
	}
	
	public static Bitmap createBitmapFromResource(int resID)
	{
		Bitmap bmp;
		bmp = BitmapFactory.decodeResource(Frame.resources,
				resID);
		return bmp = Bitmap.createBitmap(bmp, 0, 0, bmp
				.getWidth(), bmp.getHeight(), null, true);
		
	}
	
	public static Bitmap mirrorImage(Bitmap bmp)
	{

		Matrix matrix = new Matrix();
		matrix.setScale(-1, 1);
		matrix.postTranslate(bmp.getWidth(), 0);

		return Bitmap.createBitmap(bmp, 0, 0, bmp
				.getWidth(), bmp.getHeight(), matrix, true);
	}
}
