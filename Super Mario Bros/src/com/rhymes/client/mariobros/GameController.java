package com.rhymes.client.mariobros;

import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.levels.Level;
import com.rhymes.client.mariobros.levels.NewLevel;

import com.rhymes.client.mariobros.world.GameScreenActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GameController {
	private final String tag = "GameController";
	
	private Level currentLevel;
	
	private Context context;
	
	public GameController(Context context) {
		this.context = context;
	}
	public void showMenu()
	{
		
	}
	public void startGame()
	{
		
	}
	public void test()
	{
		if(currentLevel != null)
			this.currentLevel.stop();
		Log.d("GameController", "Starting Test");
		currentLevel = new NewLevel();
		currentLevel.start();
	}
}
