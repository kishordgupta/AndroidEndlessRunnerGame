package com.rhymes.client.mariobros.elements.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.*;
import com.rhymes.client.mariobros.animation.Animation;




public class AnimationNormal implements Animation {
	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;
	
	
	private Element element;
	private int speed = 3;
	private Bitmap bitmaps;
	private Bitmap reversebitmaps;
	private int direction;
	

	public AnimationNormal(int number, Element element) {
		
		bitmaps = BitmapFactory.decodeResource(Frame.resources,
				number);
		bitmaps = Bitmap.createBitmap(bitmaps, 0, 0, bitmaps.getWidth(), bitmaps
				.getHeight(), null, true);
		

		Matrix matrix = new Matrix();
		matrix.setScale(-1, 1);
		matrix.postTranslate(bitmaps.getWidth(), 0);

		reversebitmaps = Bitmap.createBitmap(bitmaps, 0, 0, bitmaps
				.getWidth(), bitmaps.getHeight(), matrix, true);
		
//		element.setmBitmap(bitmaps);
		this.element = element;
		

	}
	@Override
	public void init() {
		this.direction = element.DIRECTION_LEFT;
	}
	
	public void init(int direction) {
//		element.setmBitmap(bitmaps);
//		setXDirection(direction);
		this.direction = direction;
	}

	@Override
	public void play(long elapsedTime) {
		if(this.direction== element.DIRECTION_LEFT)
			element.setmBitmap(this.reversebitmaps);
				
		else if(this.direction== element.DIRECTION_RIGHT)
			element.setmBitmap(this.bitmaps);
		
			
	}

	@Override
	public void stop() {
		this.speed = 0;
	}
//	public void setXDirection(int direction) {
//		this.direction = direction;
//	
//	}
	
	
}
