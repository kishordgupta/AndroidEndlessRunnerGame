package com.rhymes.client.mariobros.elements;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.world.Screen;

public abstract class Element {
	public int getmX() {
		return mX;
	}

	public void setmX(int mX) {
		this.mX = mX;
	}

	public int getmY() {
		return mY;
	}

	public void setmY(int mY) {
		this.mY = mY;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getZIndex() {
		return zIndex;
	}

	public void setZIndex(int index) {
		this.zIndex = index;
	}


	public Rect getRegion() {
		return new Rect(mX, mY, mX + width, mY + height);
	}

	protected int mX;
	protected int mY;
	protected int height;
	protected int width;
	protected int zIndex;
	protected boolean showingOnCamera = false;
	protected Bitmap mBitmap;
	protected Bitmap[] mbitmap;
	public static final int DIRECTION_LEFT = 0;
	public static final int DIRECTION_RIGHT = 1;
	public int Direction = DIRECTION_LEFT;
	
	public Bitmap getmBitmap() {
		return mBitmap;
	}

	public void setmBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}
	
	public int Direction() {
		return Direction;
	}

	public void setDirection(int direction) {
		this.Direction = direction;
	}

	public Element(int x, int y, int width, int height) {
		this.mX = x;
		this.mY = y;
		this.width = width;
		this.height = height;
		this.Direction = DIRECTION_RIGHT;
	}
	public Element(int x, int y, int width, int height, int direction) {
		this.mX = x;
		this.mY = y;
		this.width = width;
		this.height = height;
		this.Direction = direction;
	}	

	public Element(int x, int y) {
		this.mX = x;
		this.mY = y;
		this.width = 0;
		this.height = 0;
		this.Direction = DIRECTION_RIGHT;
	}

	public Element() {
		this.mX = 0;
		this.mY = 0;
		this.width = 0;
		this.height = 0;
		this.Direction = DIRECTION_RIGHT;
	}

	//public abstract void doDraw(Canvas canvas, int i, int j);

	public abstract void doDraw(Canvas canvas);
	public abstract void doDraw(Canvas canvas, int newX, int newY);
	
	public abstract void animate(long elapsedTime);

	public String print(String prefix) {
		String s = prefix + "mX: " + mX + "\n mY: " + mY + "\n width: "
				+ this.width + "\n height: " + this.height;
		Log.d("Element", s);
		return s;
	}
	
}
