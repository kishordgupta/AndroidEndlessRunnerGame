package com.rhymes.client.mariobros.interactions.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.gamehistory.SoundManager;
import com.rhymes.client.mariobros.interactions.Interaction;
import com.rhymes.client.mariobros.interactions.InteractionCallbacks;
import com.rhymes.client.mariobros.interactions.sensor.InteractionSensorCallbacks;
import com.rhymes.client.mariobros.interactions.touch.InteractionTouchCallbacks;
import com.rhymes.client.mariobros.levels.Generation;
import com.rhymes.client.mariobros.levels.NewLevel;
import com.rhymes.client.mariobros.world.Helper;
import com.rhymes.client.mariobros.world.Screen;

public class InteractionController extends Interaction implements
		InteractionTouchCallbacks , InteractionSensorCallbacks{
	private Rect rectLeft;
	private Rect rectRight;
	private Rect rectJump;
	private Rect rectfall;
	public static Rect UP= new Rect(Frame.screenWidth / 8 * 7, Frame.screenHeight / 8 * 7, Frame.screenWidth,
			Frame.screenHeight);;
	

	public static final int EVENT_NONE = 0;
	public static final int EVENT_LEFT = 1;
	public static final int EVENT_RIGHT = 2;
	public static final int EVENT_JUMP = 3;
	public static final int EVENT_STRAIGHT_JUMP =7;
	public static final int EVENT_LEFT_JUMP = 4;
	public static final int EVENT_RIGHT_JUMP = 5;
	public static final int EVENT_SHOOT_BULLET = 6;
	
	private int event = EVENT_RIGHT;
	private int previousEvent = EVENT_NONE;

	private long compositeEventPeriod = 2000; // 300ms
	private long elapsed = 0;
	private long startTime = 0;

	public InteractionController() {
		rectRight = new Rect(0, 0, Frame.screenWidth , Frame.screenHeight/2);
		rectJump = new Rect(0, 0,
				(Frame.screenWidth*1)/6, (Frame.screenHeight*1));
		rectfall = new Rect((Frame.screenWidth*5)/6, 0,
				Frame.screenWidth, (Frame.screenHeight*1));
		rectLeft = new Rect(0 ,(Frame.screenHeight*1)/2, Frame.screenWidth,
				Frame.screenHeight);
		
	}

	@Override
	public void touch(int x, int y) {	
	
		
		if(icopter.GAMEOVER)
		{
			Frame.world.startAnimate();
		}

		else {
			 if(icopter.stopped)
			 {	this.event = EVENT_SHOOT_BULLET;
			 icopter.stopped=false;
			 }
			 else if(Icoptermove.gas>0){
				SoundManager.playSound(8, 1);

				NewLevel.mario.bubble=true;
				Icoptermove.gas=0;
				Icoptermove.moveSpeed=10;
				
				
			}
			 else
			 {		  
				//if (Helper.insideRegion(x, y, UP))
				//	this.event = EVENT_SHOOT_BULLET;
			if (Helper.insideRegion(x, y, rectJump))
			{	Icoptermove.moveSpeed=Icoptermove.moveSpeed+5;}
			else	if (Helper.insideRegion(x, y, rectfall))
			{	Icoptermove.moveSpeed=Icoptermove.moveSpeed-2;}
			else
			{
				//else 
					if (Helper.insideRegion(x, y, rectLeft))
					this.event = EVENT_LEFT;
			  else if (Helper.insideRegion(x, y, rectRight))
				     this.event = EVENT_RIGHT;
			}
				//else
				//	this.event = EVENT_RIGHT;

				elapsed += (System.currentTimeMillis() - startTime);
				if (elapsed > compositeEventPeriod) {
					previousEvent = EVENT_NONE;
					elapsed = 0;
				}

//				if (event == EVENT_JUMP) {
//					if (previousEvent == EVENT_LEFT)
//						event = EVENT_LEFT_JUMP;
//					else if (previousEvent == EVENT_RIGHT)
//						event = EVENT_RIGHT_JUMP;
//					else
//						event = EVENT_JUMP;
//					previousEvent = EVENT_NONE;
//				}
//				else
//					previousEvent = event;

				startTime = System.currentTimeMillis();}
		
	
	}
	}


	@Override
	public void sensor(float x, float y, int z) {	
	
	if (z==1)
			this.event = EVENT_RIGHT;
	else if(z==2)
		     this.event =EVENT_LEFT;
	else 
		{this.event =EVENT_JUMP;
		// Log.d("s", "s");
		 }

		elapsed += (System.currentTimeMillis() - startTime);
		if (elapsed > compositeEventPeriod) {
			previousEvent = EVENT_NONE;
			elapsed = 0;
		}



		startTime = System.currentTimeMillis();

	}

	
	@Override
	public void checkCondition(long elapsedTime) {
		if (event != EVENT_NONE)
			this.condition = true;
	}

	@Override
	public void takeAction() {
		if (condition) {

			for (InteractionCallbacks ic : this.elements) {
				((InteractionControllerCallbacks) ic).onEvent(event);
			}
			condition = false;
			event = EVENT_NONE;
		}
	}

}
