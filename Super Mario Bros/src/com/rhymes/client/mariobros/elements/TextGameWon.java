package com.rhymes.client.mariobros.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.world.Screen;

public class TextGameWon extends Element {
	private Bitmap mBitmap;

	public static final int ORIENTATION_NORMAL = 0;
	public static final int ORIENTATION_ROTATED = 1;
	 private Paint mPaint = new Paint();
	 
	public TextGameWon(int x, int y) {
		super(x, y);
		mPaint.setColor(Color.WHITE);
//
//			mBitmap = BitmapFactory.decodeResource(Frame.resources,
//					R.drawable.game_over);
//		mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
//				mBitmap.getHeight(), null, true);
	}

	public void doDraw(Canvas canvas) {
		// canvas.drawBitmap(mBitmap, mX, mY, null);	
		canvas.drawText("Game Won", mX, mY, mPaint);
//		canvas.drawBitmap(mBitmap, null, new Rect(mX, mY, this.width + mX,
//				this.height + mY), null);
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
