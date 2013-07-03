package com.rhymes.client.mariobros.levels;

import java.util.ArrayList;

import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;

import com.rhymes.client.mariobros.elements.TextGameOver;
import com.rhymes.client.mariobros.elements.TextGameWon;

import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.staticImage.Statimg;

import com.rhymes.client.mariobros.interactions.Interaction;
import com.rhymes.client.mariobros.interactions.InteractionCallbacks;
import com.rhymes.client.mariobros.interactions.collision.InteractionCollision;
import com.rhymes.client.mariobros.interactions.controller.InteractionController;
import com.rhymes.client.mariobros.interactions.sensor.InteractionSensor;
import com.rhymes.client.mariobros.interactions.touch.InteractionTouch;

public class NewLevel extends Level{
	//InteractionCollision interactionCollision ;
	
	private final String tag = "LevelNewLevel";

	private Generation generation=null;

	InteractionTouch interactionTouch;
	InteractionController interactionController;
	InteractionCollision interactionCollision;
	public static InteractionSensor interactionSensor;
	static public icopter mario=null;



		
	@Override
	public void init() {
		
		Frame.world.currentLevel = this;

		this.elements = new ArrayList<Element>();
		this.interactions = new ArrayList<Interaction>();
		generation = new Generation();
		generation.generate();
		float xw = (float)Frame.screenWidth/800 ;
		float yh = (float)Frame.screenHeight/480 ;


		mario = new icopter((int)(Frame.screenWidth/4 *xw), (int)(100*yh), (int)(100*xw), (int)(80*yh));
		Level.elements.add(mario);
		mario.setZIndex(3);
		
		interactionTouch = new InteractionTouch();
		interactionController = new InteractionController();
		interactionCollision = new InteractionCollision(this.mario);
		interactionSensor = new InteractionSensor();
		
		this.interactions.add(interactionTouch);
		this.interactions.add(interactionController);
		this.interactions.add(interactionCollision);
	//	this.interactions.add(interactionSensor);
		

      //  interactionSensor.subscribe(interactionController);
		interactionTouch.subscribe(interactionController);
		interactionController.subscribe(mario);


	}
	public void start() {
		init();
		startWorld(this);
	}

	@Override
	public void stop() {
		stopWorld();
	}
	@Override
	public void postDestroyed(Element e) {
	
	}
	private void gameWon() {
		Log.d("NewLevel", "Game Won");
		TextGameWon textGameWon = new TextGameWon(
				(Frame.screenWidth - 70) / 2, (Frame.screenHeight - 10) / 2);
		Frame.world.addElement(textGameWon);

		Frame.world.stopAnimate();
	}

	@Override
	public void tick() {
		
		
		generation.checkGeneration((int)mario.getmX());
		//Log.d("NewLevel", ""+(int)mario.getmX());
//
//		if (marioLife < 0)
//			gameOver();
	}

	private void gameOver() {
		Log.d("NewLevel", "GameOVer");
		TextGameOver textGameOver = new TextGameOver(
				(Frame.screenWidth - 70) / 2, (Frame.screenHeight - 10) / 2,
				70, 10);
		Frame.world.addElement(textGameOver);
        
		Frame.world.stopAnimate();

	}
	
	public void unsubscribeInteractionCollision(Element e) {
		this.interactionCollision.unSubscribe((InteractionCallbacks) e);
	}

	public void subscribeInteractionCollision(Element e) {
		if(this.interactionCollision != null)
			this.interactionCollision.subscribe((InteractionCallbacks) e);
		else
			Log.d("New Level", "interaction collision is null");
	}
	

	public void subscribeInteractionTouchollision(Element e) {
		if(this.interactionTouch != null)
			this.interactionTouch.subscribe((InteractionCallbacks) e);
	
	}
	public void subscribeInteractioncontroller(Element e) {
		
			this.interactionController.subscribe((InteractionCallbacks) e);
	
	}

	
}
