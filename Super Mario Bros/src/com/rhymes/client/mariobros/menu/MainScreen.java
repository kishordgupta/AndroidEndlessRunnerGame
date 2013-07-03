package com.rhymes.client.mariobros.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.destroyer.animation.Destroyerimagemove;
import com.rhymes.client.mariobros.elements.fixeddestroyer.fixeddestroyerimagemove.FixedDestroyerimagemove;
import com.rhymes.client.mariobros.elements.gascollector.animation.GasCollectorimagemove;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.elements.rocket.animation.Rocketimagemove;
import com.rhymes.client.mariobros.elements.sleaper.animation.Sleaperimagemove;
import com.rhymes.client.mariobros.elements.speader.speaderimagemove.Speaderimagemove;
import com.rhymes.client.mariobros.elements.star.animation.Starimagemove;
import com.rhymes.client.mariobros.elements.staticImage.animation.Statimagemove;
import com.rhymes.client.mariobros.gamehistory.BitmapProvider;
import com.rhymes.client.mariobros.gamehistory.DBHistory;
import com.rhymes.client.mariobros.gamehistory.GameHistory;
import com.rhymes.client.mariobros.gamehistory.SoundManager;
import com.rhymes.client.mariobros.gamehistory.SynchronizeMusic;
import com.rhymes.client.mariobros.levels.Level;
import com.rhymes.client.mariobros.levels.NewLevel;
import com.rhymes.client.mariobros.world.Camera;

public class MainScreen extends Activity {
	public static DBHistory dbh;
	public static Context ctx = null;
	public static boolean loadflag = false;
	public static boolean imflag = false;
	public static final int MUSIC_STATE_MENU = 0;
	public static final int MUSIC_STATE_GAME = 1;
	public static final int MUSIC_STATE_NONE = 2;
	public static final int MUSIC_STATE_FINISHED = 3;
	public static int musicHandlerState = MUSIC_STATE_MENU;
	public static boolean musicFlag = true;

	public static Thread musicHandler;
	public static boolean musicChanged = false;

	public static MediaPlayer mediaPlayer;
	public static SynchronizeMusic synchronizeMusic;
	public static Level currentLevel;
	public static int highscore;
	public static Typeface face;


	public static Handler h = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(!musicFlag){
				try {
					if (mediaPlayer != null){
						mediaPlayer.release();
						mediaPlayer = null;				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
						
			if (MainScreen.musicHandlerState == MUSIC_STATE_GAME) {
				if (mediaPlayer != null)
					mediaPlayer.release();
				mediaPlayer = MediaPlayer.create(ctx, R.raw.play);
				mediaPlayer.setVolume(5f, 5f);
				mediaPlayer.setLooping(true);
				mediaPlayer.start();
//				Log.d("MainScreen", "Playing game sournd");
			} else if (MainScreen.musicHandlerState == MUSIC_STATE_MENU) {
				if (mediaPlayer != null)
					mediaPlayer.release();
				mediaPlayer = MediaPlayer.create(ctx, R.raw.menu);
				mediaPlayer.setVolume(5f, 5f);
				mediaPlayer.setLooping(true);
				mediaPlayer.start();
//				Log.d("MainScreen", "Playing menu sound");
			} else if (MainScreen.musicHandlerState == MUSIC_STATE_NONE) {
				try {
					if (mediaPlayer != null)
						mediaPlayer.release();
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
		}

		
	};

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ctx = this;
		setContentView(R.layout.mainscreen);
		
		  //  ad.setTesting(true);
//		MobclixAdView adView = (MobclixAdView)findViewById(R.id.ad);
		//MobclixAdRequest ad = new MobclixAdRequest(adView);
		//    adView.loadAd(ad);
		synchronizeMusic = new SynchronizeMusic();
		BitmapProvider.res = getResources();
		dbh = new DBHistory(MainScreen.this);
		dbh.createRow();
		
        face = Typeface.createFromAsset(getAssets(), "fonts/ARGOS.TTF"); 
		
		ArrayList<GameHistory> levelStates = dbh.fetchAllRows();
		for (GameHistory g : levelStates) {
			highscore = g.highScore;
			
			Log.d("row created",""+g.highScore);

		}
		
		Log.d("Mainscreen", "Created: " + loadflag);
		if (loadflag == false) {
		
			Frame.init(getApplicationContext(), getResources());
			setpic();
			loadflag=true;
		}

		// startMediaPlayer();

		// Create, Initialize and then load the Sound manager
		SoundManager.getInstance();
		SoundManager.initSounds(this);
		SoundManager.loadSounds();

		// initialize music variables
		this.musicHandlerState = MUSIC_STATE_MENU;
		this.musicChanged = true;
    	MainScreen.h.sendEmptyMessage(0);
    	
    	

		Button startGame = (Button) findViewById(R.id.startGame);
		startGame.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				if(currentLevel != null)
			currentLevel.stop();
				
				currentLevel = new NewLevel();
				currentLevel.start();
			}

		});

		Button options = (Button) findViewById(R.id.options);
		options.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				Intent myIntent = new Intent(view.getContext(),
						OptionMenuScreen.class);
				startActivityForResult(myIntent, 0);
				// finish();
			}

		});
		Button about = (Button) findViewById(R.id.aboutthegamebutton);
		about.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				Intent myIntent = new Intent(view.getContext(),
						About.class);
				startActivityForResult(myIntent, 0);
				// finish();
			}

		});
		Button exit = (Button) findViewById(R.id.exit);
		exit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				finish();
				// moveTaskToBack(true);
			}

		});
	}

//	public static void endsc(){
//				
//		endscrn = BitmapFactory.decodeResource(Frame.resources,
//				R.drawable.halloweenbat);
//		endscrn = Bitmap.createBitmap(endscrn, 0, 0, endscrn.getWidth(), endscrn
//				.getHeight(), null, true);
//	}

	public void startMediaPlayer() {
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		}
		mediaPlayer = MediaPlayer.create(MainScreen.this, R.raw.menu);
		mediaPlayer.setLooping(true);
		try {
			mediaPlayer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/*
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { super.onActivityResult(requestCode,
	 * resultCode, data); startMediaPlayer(); }
	 */

	@Override
	public void onDestroy() {
		super.onDestroy();
		SoundManager.cleanup();

		MainScreen.musicHandlerState = MainScreen.MUSIC_STATE_FINISHED;

		if (mediaPlayer != null)
			mediaPlayer.release();
		

		System.gc();
//		loadflag = false;
	}
	@Override
	protected void onPause() {
		super.onPause();
//
////		PhysicsWorld.pause = true;
//		MainScreen.h.sendEmptyMessage(0);
//		SoundManager.stopAllSound();

	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		PhysicsWorld.pause = false;
		MainScreen.musicHandlerState = MainScreen.MUSIC_STATE_MENU;
		MainScreen.h.sendEmptyMessage(0);
	}

	@Override
	protected void onStop() {
		super.onStop();
	
	}
	public void setpic() {
		Statimagemove.setimg();
		Starimagemove.setimg();
		Destroyerimagemove.setimg();
		FixedDestroyerimagemove.setimg();
		Rocketimagemove.setimg();
		GasCollectorimagemove.setimg();
		Speaderimagemove.setimg();
		Sleaperimagemove.setimg();
		Icoptermove.setimg();
		Camera.bitmap1 = BitmapFactory.decodeResource(Frame.resources,R.drawable.gmover);
		Camera.bitmap2 = BitmapFactory.decodeResource(Frame.resources,R.drawable.waterlandmix);
		Camera.bitmap3 = BitmapFactory.decodeResource(Frame.resources,R.drawable.waterskymix);
		Camera.bitmap4 = BitmapFactory.decodeResource(Frame.resources,R.drawable.sky);
		Camera.bitmap5 = BitmapFactory.decodeResource(Frame.resources,R.drawable.grass);
		Camera.bitmap6 = BitmapFactory.decodeResource(Frame.resources,R.drawable.water);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if (keyCode == KeyEvent.KEYCODE_BACK) {

	     //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR

	     return true;

	     }

	     return super.onKeyDown(keyCode, event);    
	}
	public static void  dbhigscorereset()
	{

	dbh.createRow();
	
	ArrayList<GameHistory> levelStates = dbh.fetchAllRows();
	for (GameHistory g : levelStates) {
		highscore = g.highScore;
		

	}}


}