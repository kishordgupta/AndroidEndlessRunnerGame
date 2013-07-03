package com.rhymes.client.mariobros;

import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.world.World;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;


public class Frame {
	public static Context context;
	public static Resources resources;
	public static GameController gameController;
	public static World world;
	
	public static int screenHeight;
	public static int screenWidth;
	
	public static void init(Context ctx, Resources res) {
		context = ctx;
		resources = res;
		gameController = new GameController(ctx);	
		
		Display display = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE))
		.getDefaultDisplay();
		screenHeight = display.getWidth();
		screenWidth = display.getHeight();
		Log.d("Frame", "Screen width: " + screenWidth + "\nScreen height: " + screenHeight);
	}

}
