package com.rhymes.client.mariobros.world;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.gamehistory.GameHistory;
import com.rhymes.client.mariobros.gamehistory.LevelHistory;
import com.rhymes.client.mariobros.gamehistory.SoundManager;
import com.rhymes.client.mariobros.interactions.controller.InteractionController;
import com.rhymes.client.mariobros.levels.Level;
import com.rhymes.client.mariobros.menu.MainScreen;

public class Camera {
	
	private int MAX_Z_INDEX = 10;

	public Rect cameraRect;
  
	private LinkedList<Element> xSortedWorldElements;
	private LinkedList<LinkedList> zSortedElements;

	private LinkedList<Element> cameraElements;
	private Element leftMostElement;
	private int leftMostElementIndex;
	public static Bitmap bitmap1 =null;
	public static Bitmap bitmap2 =null;
	public static Bitmap bitmap3 =null;
	public static Bitmap bitmap4 =null;
	public static Bitmap bitmap5 =null;
	public static Bitmap bitmap6 =null;
	public GameHistory gs;


	public Camera(ArrayList<Element> worldElements, Rect rect) {
	
		this.cameraRect = rect;
		this.cameraElements = new LinkedList<Element>();
		this.zSortedElements = new LinkedList<LinkedList>();
		for(int i = 0; i < MAX_Z_INDEX; i++)
		{
			zSortedElements.add(new LinkedList<Element>()); 
		}
		
		this.xSortedWorldElements = sortByX(worldElements);
		prepareCameraElements();
		
		
	}
	
	
	

	private void prepareCameraElements() {

		
		
		for(int i = leftMostElementIndex; i>=0; i--)
		{
			try {
				if(xSortedWorldElements.get(i).getRegion().right >= cameraRect.left){
					leftMostElementIndex--;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				leftMostElementIndex--;
			}
		}
		
		if(leftMostElementIndex < 0)
			leftMostElementIndex = 0;
		leftMostElement = xSortedWorldElements.get(leftMostElementIndex);
				
		Element ce = null;
		int curIndex = leftMostElementIndex;
		if (this.cameraElements == null)
			this.cameraElements = new LinkedList<Element>();
//		else
//			this.cameraElements.clear();
		if (leftMostElement == null)
			ce = this.xSortedWorldElements.get(0);
		else
			ce = this.leftMostElement;
		Rect r;
		leftMostElement = null;
		
		for (; ce.getmX() < this.cameraRect.right;) {
			r = ce.getRegion();
			if(Rect.intersects(r, cameraRect)){
				if(!this.cameraElements.contains(ce)){
					this.cameraElements.add(ce);
					if (this.leftMostElement == null) {
						this.leftMostElement = ce;
						this.leftMostElementIndex = curIndex;
					}
				}
			}
			curIndex++;
			if (curIndex >= xSortedWorldElements.size())
				break;
			ce = this.xSortedWorldElements.get(curIndex);

		}
//		Log.d("Camera", "prepareZSortedList start: " + (System.currentTimeMillis()-time));
//		time = System.currentTimeMillis();
		this.zSortedElements = prepareZSortedList(this.cameraElements);
//		Log.d("Camera", "prepareZSortedList stop: " + (System.currentTimeMillis()-time));
	}

	
	private LinkedList<LinkedList> prepareZSortedList(
			LinkedList<Element> elements) {
		LinkedList<Element> l;
		

		boolean used = false;
		for (Element e : elements) {
			try {
				for(int i = 0; i < MAX_Z_INDEX; i++)
				{
					if(zSortedElements.get(i).contains(e)){
						used = true;
					}
				}
				if(used){
					used = false;
					continue;
				}

				if (e.getZIndex() <= zSortedElements.size()) {
					zSortedElements.get(e.getZIndex()).add(e);
				} else {
					Log.d("Camera", "Created a new list for zIndex: " + e.getZIndex());
					for (int i = zSortedElements.size()+1; i <= e.getZIndex(); i++) {
						l = new LinkedList<Element>();
						zSortedElements.add(i, l);
					}
					zSortedElements.get(e.getZIndex()).add(e);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				Log.d("Camera", "Error occured for: " + e);
				e1.printStackTrace();
			}
		}
		
//		Log.d("Camera", "ZSortedElements: ");
//		for(LinkedList<Element> ll : zSortedElements)
//			for(Element e: ll)
//				e.print("zSortedElement" + e);
		return zSortedElements;
		
	}

	
	private LinkedList<Element> sortByX(ArrayList<Element> worldElements) {
		LinkedList<Element> temp = null;
		temp = new LinkedList<Element>();
		boolean added = false;
		try {
			for (Element e : worldElements) {
				added = false;
				if (temp.size() == 0) {
					// Log.d("Camera", "Adding element at empty temp");
					temp.add(e);
					continue;
				}
				// Log.d("Camera", "temp size: " + temp.size());
				for (Element l : temp) {
					if (l.getmX() >= e.getmX()) {
						// Log.d("Camera", "Added at: " + temp.indexOf(l));
						temp.add(temp.indexOf(l), e);
						added = true;
						break;
					}
				}
				if (!added) {
					// Log.d("Camera", "Added at the last of temp");
					temp.addLast(e);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;

	}

	
	private LinkedList<Element> sortByX(LinkedList<Element> worldElements) {		
//		LinkedList<Element> temp = null;
//		temp = new LinkedList<Element>();
		boolean added = false;
		try {
			for (Element e : worldElements) {
				if(xSortedWorldElements.contains(e))
					continue;
				added = false;
				if (xSortedWorldElements.size() == 0) {
					// Log.d("Camera", "Adding element at empty temp");
					xSortedWorldElements.add(e);
					continue;
				}
				// Log.d("Camera", "temp size: " + temp.size());
				for (Element l : xSortedWorldElements) {
					if (l.getmX() >= e.getmX()) {
						// Log.d("Camera", "Added at: " + temp.indexOf(l));
						xSortedWorldElements.add(xSortedWorldElements.indexOf(l), e);
						added = true;
						break;
					}
				}
				if (!added) {
					// Log.d("Camera", "Added at the last of temp");
					xSortedWorldElements.addLast(e);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xSortedWorldElements;

	}

	public void resetEleemnts() {
		sortByX(this.cameraElements);
		prepareCameraElements();
//		System.gc();
	}

	public void reset() {
//		this.xSortedWorldElements = sortByX(elements);
		for(int i = 0; i < MAX_Z_INDEX; i++)
		{
			zSortedElements.add(new LinkedList<Element>()); 
		}
		
		this.xSortedWorldElements = sortByX(Level.elements);
		prepareCameraElements();
		resetEleemnts();
	}
	public void moveLeft(int howMuchPixel) {
//		 Log.d("Camera", "Camera: Moving left");
		cameraRect.set(cameraRect.left - howMuchPixel, cameraRect.top,
				cameraRect.right - howMuchPixel, cameraRect.bottom);
//		Helper.printRect(this.cameraRect, "New Camera Rect:");
		resetEleemnts();
	}

	public void moveRight(int howMuchPixel) {
//		 Log.d("Camera", "Camera: Moving Right");
		cameraRect.set(cameraRect.left + howMuchPixel, cameraRect.top,
				cameraRect.right + howMuchPixel, cameraRect.bottom);
//		Helper.printRect(this.cameraRect, "New Camera Rect:");
		resetEleemnts();
	}

	public void moveTop(int howMuchPixel) {
		// Log.d("Camera", "Camera: Moving left");
		cameraRect.set(cameraRect.left, cameraRect.top - howMuchPixel,
				cameraRect.right, cameraRect.bottom - howMuchPixel);
		Helper.printRect(this.cameraRect, "New Camera Rect:");
		resetEleemnts();
	}

	public void moveBottom(int howMuchPixel) {
		// Log.d("Camera", "Camera: Moving Right");
		cameraRect.set(cameraRect.left, cameraRect.top + howMuchPixel,
				cameraRect.right, cameraRect.bottom + howMuchPixel);
		Helper.printRect(this.cameraRect, "New Camera Rect:");
		resetEleemnts();
	}

	public void addElementX(Element element) {
		boolean added = false;
		if (xSortedWorldElements == null)
			xSortedWorldElements = new LinkedList<Element>();
		for (Element l : this.xSortedWorldElements) {
			if (l.getmX() >= element.getmX()) {
				xSortedWorldElements.add(xSortedWorldElements.indexOf(l),
						element);
				added = true;
				break;
			}
		}
		if (!added) {
			xSortedWorldElements.addLast(element);
		}
		prepareCameraElements();
		xSortedWorldElements.remove(element);
		prepareCameraElements();
	}

	public void addElement(Element element) {
		if (cameraElements == null)
			cameraElements = new LinkedList<Element>();
		this.cameraElements.add(element);

	}

	public void removeElement(Element element) {
;
		cameraElements.remove(element);
		this.xSortedWorldElements.remove(element);
//		this.zSortedElements = prepareZSortedList(this.cameraElements);
		for(LinkedList<Element> ll : zSortedElements)
			ll.remove(element);
	}

	public Rect getCameraRegion() {
		return this.cameraRect;
	}

	public void drawBitmapRelative(Canvas canvas, Bitmap mBitmap, int curX,
			int curY, int width, int height) {		
		canvas.drawBitmap(mBitmap, null, new Rect(curX - this.cameraRect.left,
				curY - this.cameraRect.top, width + curX - this.cameraRect.left, height+ curY - this.cameraRect.top),
				null);
	}
	public static int i=0;
	public static void drawbk(){   i=(int)(Math.random()*5);}
	public void draw(Canvas canvas) {
   
        if(i<1)
        {
		 canvas.drawBitmap(bitmap2, null, new Rect(0, 0,
		 Frame.screenWidth, Frame.screenHeight), null);}
        else if(i<2)       	
        	{
        	canvas.drawBitmap(bitmap3, null, new Rect(0, 0,
        			 Frame.screenWidth, Frame.screenHeight), null);
        	}
        else if(i<3)       	
    	{
          	canvas.drawBitmap(bitmap4, null, new Rect(0, 0,
       			 Frame.screenWidth, Frame.screenHeight), null);
    	}
        else if(i<4)       	
    	{
          	canvas.drawBitmap(bitmap5, null, new Rect(0, 0,
       			 Frame.screenWidth, Frame.screenHeight), null);
    	}
        else if(i<5)       	
    	{
          	canvas.drawBitmap(bitmap6, null, new Rect(0, 0,
       			 Frame.screenWidth, Frame.screenHeight), null);
    	}
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		
		canvas.save();

		canvas.rotate(180, Frame.screenWidth / 2, Frame.screenHeight / 2);
		//canvas.drawColor(Color.BLACK);
		synchronized (xSortedWorldElements) {

			for (LinkedList<Element> l : this.zSortedElements) {
				for (Element element : l) {
					element.doDraw(canvas, element.getmX()
							- this.cameraRect.left, element.getmY()
							- this.cameraRect.top);

				}
				
			}

			paint.setTypeface(MainScreen.face);
			paint.setColor(Color.GREEN);

			canvas.drawRect(0, Icoptermove.moveSpeed * 10, 10, 0, paint);
			paint.setColor(Color.BLUE);
			if (Icoptermove.gas > 0) {
				canvas.drawRect(10, Frame.screenHeight, 20, 0, paint);
			}
			paint.setTextSize(10);

			paint.setColor(Color.RED);
			canvas.drawRect(20, Icoptermove.gameovertime * 2, 30, 0, paint);
	
			canvas.save();
			paint.setTextAlign(Align.RIGHT);
			canvas.rotate(90, Frame.screenWidth / 2, Frame.screenHeight / 2);
			canvas.drawText("Speed " + Icoptermove.moveSpeed,
					Frame.screenHeight + 150, -125, paint);
			paint.setColor(Color.GREEN);
			;
			paint.setTextAlign(Align.LEFT);
			canvas.drawText("Score " + Icoptermove.point, 225, -125, paint);
			paint.setColor(Color.BLUE);
			;
			canvas.drawText("Best " + MainScreen.highscore, 225, -100, paint);
			paint.setTextSize(20);
			paint.setTextAlign(Align.CENTER);
			
			if (icopter.GAMEOVER) {
				Icoptermove.moveSpeed = 0;
				SoundManager.playSound(10, 1);
				Frame.world.stopAnimate();
				paint.setColor(Color.RED);
				gs = new GameHistory(1, Icoptermove.point);
				MainScreen.dbh.updatelevel(gs);
				
				gameoverscrn(canvas);
			
//				canvas.drawBitmap(bitmap1, null, new Rect(0, 0,
//					 Frame.screenWidth/2,	Frame.screenHeight/2), null);
				
//				canvas.drawText("GAMEOVER", Frame.screenWidth / 2,
//						Frame.screenHeight / 2, paint);
				paint.setTypeface(MainScreen.face);

				paint.setColor(Color.BLUE);
				canvas.drawText("Score " + Icoptermove.point, Frame.screenWidth / 2,
						Frame.screenHeight / 2 + 100, paint);
				paint.setColor(Color.RED);
				canvas.drawText("Best " + MainScreen.highscore,
						Frame.screenWidth / 2, Frame.screenHeight / 2 + 200,
						paint);
				
				
			}
			else if (Icoptermove.gameovertime > 250) {
				Icoptermove.moveSpeed = 0;
				SoundManager.playSound(10, 1);
				Frame.world.stopAnimate();
				paint.setColor(Color.RED);
				gs = new GameHistory(1, Icoptermove.point);
				MainScreen.dbh.updatelevel(gs);
				icopter.GAMEOVER = true;
				
				gameoverscrn(canvas);
				
//				canvas.drawText("GAMEOVER", Frame.screenWidth / 2,
//						Frame.screenHeight / 2, paint);

				
				paint.setTypeface(MainScreen.face);
				paint.setColor(Color.BLUE);
				canvas.drawText("Score " + Icoptermove.point, Frame.screenWidth / 2,
						Frame.screenHeight / 2 + 100, paint);
				paint.setColor(Color.RED);
				canvas.drawText("Best " + MainScreen.highscore,
						Frame.screenWidth / 2, Frame.screenHeight / 2 + 200,
						paint);
			}

			canvas.restore();
			canvas.restore();
		}
	
				
	}
	
	
	public void gameoverscrn(Canvas canvas){
		canvas.save();
		canvas.rotate(90, Frame.screenWidth / 2, Frame.screenHeight / 2);


		canvas.drawBitmap(bitmap1, null, new Rect(0, 0,
			 Frame.screenWidth,	Frame.screenHeight), null);
		
		canvas.restore();

		
	}

}
