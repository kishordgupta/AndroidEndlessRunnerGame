package com.rhymes.client.mariobros.elements.mario2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.elements.rocket.Rocket;
import com.rhymes.client.mariobros.elements.staticImage.Statimg;
import com.rhymes.client.mariobros.interactions.collision.Collision;
import com.rhymes.client.mariobros.interactions.collision.InteractionCollisionCallbacks;
import com.rhymes.client.mariobros.interactions.controller.InteractionController;
import com.rhymes.client.mariobros.interactions.controller.InteractionControllerCallbacks;
import com.rhymes.client.mariobros.interactions.touch.InteractionTouchCallbacks;
import com.rhymes.client.mariobros.levels.Generation;
import com.rhymes.client.mariobros.levels.NewLevel;
import com.rhymes.client.mariobros.world.Helper;

public class icopter extends Element implements  InteractionControllerCallbacks,InteractionCollisionCallbacks {

	// state constants
	public static int STATE_UP = 0;
	public static int STATE_DOWN = 1;
	public static int STATE_DESTROYED = 2;
	public static int STATE_NONE = 3;
	public static boolean bubble = false;
	int step=0;
	public static boolean stopped=false;
	public static boolean GAMEOVER=false;
	int totallife=10000;
	private Bitmap mBitmap;
	public   static int state = STATE_DOWN;
	
	Icoptermove icoptermove;
	
	public Bitmap getmBitmap() {
		return mBitmap;
	}

	public void setmBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}
	
	public icopter(int x, int y, int width, int height) {
		super(x, y, width, height);		
		
		
		initAnimations();
		((NewLevel)Frame.world.currentLevel).subscribeInteractionCollision(this);
	}
	
	public icopter(int x, int y, int width, int height, int direction) {
		super(x, y, width, height);
		//this.mBitmap= this.getmBitmap();
		this.Direction = direction;
		this.state = STATE_DOWN;
		initAnimations();
		((NewLevel)Frame.world.currentLevel).subscribeInteractionCollision(this);
		((NewLevel) Frame.world.level).subscribeInteractionTouchollision(this);
	}
	
	
	

	
	
	private void initAnimations()
	{
	
		
		this.icoptermove = new Icoptermove(this);	
		this.icoptermove.init();

	
	
	}

	public void doDraw(Canvas canvas) {
		// canvas.drawBitmap(mBitmap, mX, mY, null);
		canvas.drawBitmap(mBitmap, null, new Rect(mX, mY, this.width + mX,
				this.height + mY), null);
	}
	
	public void doDraw(Canvas canvas,int newX,int newY) {
		// canvas.drawBitmap(mBitmap, mX, mY, null);
		if(!stopped)
		canvas.drawBitmap(mBitmap, null, new Rect(newX, newY, this.width + newX,
				this.height + newY), null);
	}

	/**
	 * @param elapsedTime
	 *            in ms.
	 */
	public void animate(long elapsedTime) {

	
	if(!stopped){
		if(step>24){
			if(Icoptermove.moveSpeed>3)
				Icoptermove.moveSpeed=Icoptermove.moveSpeed-1;
			step=0;
			}
			step++;
		if(this.state != STATE_DESTROYED)
		{
			this.icoptermove.play(elapsedTime);
		}
		else 
		{
			this.icoptermove.play(elapsedTime);
		}
	}
	}

	
	public void reset()
	{
		this.setStateNormal();
		this.mX = 0;
		this.mY = 0;
		((NewLevel)Frame.world.level).subscribeInteractionCollision(this);
		this.state = STATE_UP;
	}

	private void setStateNormal() {
		
		this.state = STATE_UP;
		
	                               }
	
	//@ Override
	public void onEvent(int event) {
	
		if (event == InteractionController.EVENT_SHOOT_BULLET) {
//			if(Icoptermove.gas>0)
//				{Icoptermove.moveSpeed=Icoptermove.moveSpeed+25;
//				Icoptermove.gas=0;
//				}
			
			stopped=false;
			Icoptermove.moveSpeed=15;
			Rocket.position=0;
			if(Rocket.direction==0)
				this.state = STATE_NONE;
//			if(Rocket.direction==1)
//				this.setmY((int) (this.getmY() - 5 ));;;
//			if(Rocket.direction==2)
//				this.setmY((int) (this.getmY() + 5 ));
		}
			if (event == InteractionController.EVENT_RIGHT) {
				this.icoptermove.init();
				this.state = STATE_DOWN;
			
			}
			else if (event == InteractionController.EVENT_LEFT){
				this.icoptermove.init();
				this.state = STATE_UP;;
				
				}
			else if (event == InteractionController.EVENT_JUMP){
				this.icoptermove.init();
				this.state = STATE_NONE;
			
				}
		
	}
	
	
	
	@Override
	public void onCollision(Collision collision) {
		Element otherElement;
		int mySide;
		
		if (collision.element1 instanceof icopter) {
			otherElement = collision.element2;
			mySide = collision.sideElement1;
		} else {
			otherElement = collision.element1;
			mySide = collision.sideElement2;
		}
//		
//		
		if(otherElement instanceof Rocket)
		{
			Icoptermove.moveSpeed=0;
             stopped=true;
		}
		

//		
		
		
	}

	
}
