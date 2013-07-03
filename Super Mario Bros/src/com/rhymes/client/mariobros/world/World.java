package com.rhymes.client.mariobros.world;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.elements.rocket.Rocket;
import com.rhymes.client.mariobros.interactions.Interaction;
import com.rhymes.client.mariobros.levels.Level;
import com.rhymes.client.mariobros.levels.NewLevel;
import com.rhymes.client.mariobros.menu.MainScreen;

public class World extends Thread {
	private Screen screen;
	private SurfaceHolder mHolder;
	public static Camera camera;

	private boolean worldRunning = false;
	private boolean animate = true;
	private boolean interact = true;

	private long mStartTime;
	private long mElapsed;

	private ArrayList<Element> elements;
	private ArrayList<Interaction> interactions;
	private ArrayList<Element> toBeRemovedElements;
	

	public Level level;
	public static Level currentLevel;
	
	private final String tag = "World";
	private int i = 20;

	public World(ArrayList<Element> elements,
			ArrayList<Interaction> interactions, Level level) {
		this.elements = elements;
		toBeRemovedElements = new ArrayList<Element>();
		this.interactions = interactions;
		this.level = level;

		this.screen = new Screen(Frame.context, Color.BLUE);
		mHolder = screen.getHolder();
		World.camera = new Camera(elements, new Rect(0,0, Frame.screenWidth, Frame.screenHeight));
	}

	public void setRunning(boolean run) {
		worldRunning = run;
	}

	
	@Override
	public void run() {
		Canvas canvas = null;
		mStartTime = System.currentTimeMillis();

		Intent intent = new Intent(Frame.context, GameScreenActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Frame.context.startActivity(intent);
         
		while (!worldRunning || !Frame.world.getScreen().isSurfaceCreated)
			continue;
		Log.d(tag, "World is running...");
		long time = 0;
		while (worldRunning) {
//			Log.d(tag, "Locking canvas...");
			canvas = mHolder.lockCanvas();
//			Log.d(tag, "Locked canvas...");
			if (canvas != null) {
				try {
				//	Log.d("World", "time" + (System.currentTimeMillis() - time));					
					time = System.currentTimeMillis();					
//					Log.d(tag, "in loop");
//					camera.moveRight(5);
//					Log.d(tag, "Camera moved");
					this.level.tick();
					
				//	mElapsed = System.currentTimeMillis() - mStartTime;
					//mStartTime = System.currentTimeMillis();
					checkInteractions(mElapsed);
					camera.moveRight(Icoptermove.moveSpeed);
//					Log.d(tag, "check");
					InteractionsTakeActions();
//					Log.d(tag, "Take Action");
					animate(mElapsed);
//					Log.d(tag, "animated");
					drawScreen(mElapsed, canvas);
//					Log.d(tag, "drawn");
					
					i--;
					if(i < 1 ){
						i = 40;
						cleanWorld();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				mHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

	private void checkInteractions(long elapsedTime) {
		if (!interact)
			return;
		for (Interaction i : this.interactions)
			i.checkCondition(elapsedTime);
	}

	private void InteractionsTakeActions() {
		if (!interact)
			return;
		for (Interaction i : this.interactions)
			i.takeAction();
	}

	public void animate(long elapsedTime) {
		if (!animate)
			return;
		synchronized (elements) {
			for (Element element : elements) {
				element.animate(elapsedTime);
			}
		}
	}


	public void drawScreen(long elapsed, Canvas canvas) {
		//canvas.drawColor(Color.BLACK);
		this.camera.draw(canvas);
	}


	public Screen getScreen() {
		return this.screen;
	}

	public void stopAnimate() {
		
	//	this.interact = false;
		this.animate = false;
	}

	public void startAnimate() {
		icopter.GAMEOVER=false;
		icopter.state=icopter.STATE_DOWN;
        icopter.stopped=false;
	    Icoptermove.gameovertime=0; 
	    Icoptermove.gas=0;
	    if(NewLevel.mario.getmX()/Frame.screenHeight>Frame.screenHeight/2)
	    NewLevel.mario.setmX(NewLevel.mario.getmX()-25);
	    Icoptermove.moveSpeed=15;
	    Icoptermove.point=0;
	    Rocket.state=0;
	     MainScreen.dbhigscorereset();
		this.interact = true;
		this.animate = true;
	}

	public void removeElement(Element e) {
		synchronized (this.elements) {
			this.elements.remove(e);
		}
	}

	public void addElement(Element e) {
		synchronized (this.elements) {
			this.elements.add(e);
		}
	}
	
	public ArrayList<Element> getElements(){
		return elements;
	}
	
	public Camera getCamera() {
		return this.camera;
	}
	
boolean toogle = false;	
	private void cleanWorld()
	{

		h.post(r);
	}
	Handler h = new Handler();
	Runnable r = new Runnable() {
		
		@Override
		public void run() {
			System.gc();
		}
	};
	
	
	public void postDestroyed(Element e)
	{
		//Log.d("howa", "mucchi");
		this.toBeRemovedElements.add(e);			
	}

}