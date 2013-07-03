package com.rhymes.client.mariobros.elements.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.*;
import com.rhymes.client.mariobros.animation.Animation;


public class AnimationFalling implements Animation {
	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;

	private Bitmap bitmaps;
	
	private Element element;

	
	private int speedY=10 ;
	private int speedX=10;

	private int count = 0;
	private int max;

	

	public AnimationFalling(int number, Element element) {
				
		bitmaps = BitmapFactory.decodeResource(Frame.resources,
				number);
		bitmaps = Bitmap.createBitmap(bitmaps, 0, 0, bitmaps.getWidth(), bitmaps
				.getHeight(), null, true);

		
		

		this.element = element;
		this.max = 14;

	}

	public AnimationFalling(Element element) {
		this.element = element;
		// TODO Auto-generated constructor stub
	}

	public void init() {
		
		this.speedY = 10;
		
	}


	public void init(int direction, int speed) {
		
		this.speedY = speed;
		
	}

	@Override
	public void play(long elapsedTime) {
		
		element.setmY((int) (element.getmY() + speedY));
		//element.setmX((int) (element.getmX() + speedX));
			element.setmBitmap(this.bitmaps);
		
	}

	@Override
	public void stop() {
		this.speedY = 0;
	}

	public void setXDirection(int direction) {
		
	}

	public boolean isFinished() {
		if (this.count > max)
			return true;
		else
			return false;
	}

}
