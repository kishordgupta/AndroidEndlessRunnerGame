package com.rhymes.client.mariobros.elements.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.*;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.world.Helper;

public class AnimationCollision implements Animation {
	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;

	private Bitmap bitmaps[];
	private Bitmap reverseBitmaps[];
	private Element element;

	private int speedX = 3;
	private int direction;

	private int count = 0;
	private int max;

	private int bmpIndex = 0;

	public AnimationCollision(int[] numbers, Element element) {
				
		this.bitmaps=new Bitmap[numbers.length];
		for(int i=0; i<bitmaps.length;i++)
		{bitmaps[i] = Helper.createBitmapFromResource(numbers[i]);}
		this.reverseBitmaps = new Bitmap[bitmaps.length];
		for(int i=0; i<bitmaps.length;i++)
		{reverseBitmaps[i] = Helper.mirrorImage(bitmaps[i]);}

		
		

		this.element = element;
		this.max = 14;

	}

	public void init() {
		this.direction = element.DIRECTION_LEFT;
		this.speedX = 10;
		this.bmpIndex = 0;
	}


	public void init(int direction, int speed) {
		this.direction = direction;
		this.speedX = speed;
		this.bmpIndex = 0;
	}

	@Override
	public void play(long elapsedTime) {
		if(this.direction == element.DIRECTION_LEFT)
		{
			element.setmX(element.getmX() - speedX);
			element.setmBitmap(this.reverseBitmaps[bmpIndex]);
			
		}
		else if(this.direction == element.DIRECTION_RIGHT)
		{
			element.setmX(element.getmX() + speedX);
			element.setmBitmap(this.bitmaps[bmpIndex]);
		}
		bmpIndex++;
		if(bmpIndex == bitmaps.length)
			bmpIndex = 0;
	}

	@Override
	public void stop() {
		this.speedX = 0;
	}

	public void setXDirection(int direction) {
		this.direction = direction;
		if (direction == DIRECTION_LEFT) {
			this.speedX = -7;
		} else if (direction == DIRECTION_RIGHT) {
			this.speedX = 7; 
		}
	}

	public boolean isFinished() {
		if (this.count > max)
			return true;
		else
			return false;
	}

}