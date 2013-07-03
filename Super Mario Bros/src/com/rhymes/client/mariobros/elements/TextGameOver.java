package com.rhymes.client.mariobros.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.world.Screen;

public class TextGameOver extends Element {
	private Bitmap mBitmap;

	public static final int ORIENTATION_NORMAL = 0;
	public static final int ORIENTATION_ROTATED = 1;

	public TextGameOver(int x, int y, int width, int height) {
		super(x, y, width, height);

			
	}

	public void doDraw(Canvas canvas) {
		// canvas.drawBitmap(mBitmap, mX, mY, null);
		canvas.drawBitmap(mBitmap, null, new Rect(mX, mY, this.width + mX,
				this.height + mY), null);
	}

	/**
	 * @param elapsedTime
	 *            in ms.
	 */
	public void animate(long elapsedTime) {
	}

	@Override
	public void doDraw(Canvas canvas, int newX, int newY) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(mBitmap, null, new Rect(newX, newY, this.width + newX,
				this.height + newY), null);
	}
}
