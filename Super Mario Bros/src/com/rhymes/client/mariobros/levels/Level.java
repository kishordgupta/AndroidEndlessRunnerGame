package com.rhymes.client.mariobros.levels;

import java.util.ArrayList;

import android.content.Intent;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.interactions.Interaction;
import com.rhymes.client.mariobros.world.GameScreenActivity;
import com.rhymes.client.mariobros.world.World;

public abstract class Level {
public static ArrayList<Element> elements;
	protected ArrayList<Interaction> interactions;
	
	public abstract void start();
	public abstract void init();
	public abstract void stop();

	public void startWorld(Level level) {
		if (Frame.world == null || !Frame.world.isAlive()) {
			Frame.world = new World(elements, interactions, level);			
			Frame.world.start();
		}		
	}
	public void stopWorld() {
        if (Frame.world.isAlive()) {
        	Frame.world.setRunning(false);
        }
	}
	public abstract void postDestroyed(Element e);
	public abstract void tick();
	
}
