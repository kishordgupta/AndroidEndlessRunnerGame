package com.rhymes.client.mariobros.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.gamehistory.SoundManager;

public class About extends Activity {

	//private AdView adView;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
//		MainScreen.currentLevel.stop();
		MainScreen.musicHandlerState = MainScreen.MUSIC_STATE_MENU;
		// PhysicsWorld.pause = false;
		MainScreen.h.sendEmptyMessage(0);
		
		
		// Create the adView
		//adView = (AdView) findViewById(R.id.ad);

		// Lookup your LinearLayout assuming it’s been given
		// the attribute android:id="@+id/mainLayout"
		// LinearLayout layout =
		// (LinearLayout)findViewById(R.id.RelativeLayoutAd);
		//
		// // Add the adView to it
		// layout.addView(adView);

		//AdRequest request = new AdRequest();
		// request.setTesting(true);

		// Initiate a generic request to load it with an ad
	//	adView.loadAd(request);

		Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				SoundManager.playSound(3, 1);
				// Intent myIntent = new Intent(view.getContext(),
				// MainScreen.class);
				// startActivityForResult(myIntent, 0);
				finish();
			}

		});

	}
	

	@Override
	protected void onPause() {
		super.onPause();

//		PhysicsWorld.pause = true;
		MainScreen.h.sendEmptyMessage(0);
		SoundManager.stopAllSound();

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

}
