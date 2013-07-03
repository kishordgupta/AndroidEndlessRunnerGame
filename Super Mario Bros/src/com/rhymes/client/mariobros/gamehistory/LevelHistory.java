package com.rhymes.client.mariobros.gamehistory;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.menu.MainScreen;

public class LevelHistory extends Activity {

	public Button reload ;
	public Button levelmenu ;
	public TextView score;
	public TextView highscore;

//	public ImageView starview;
	public Bitmap[] bmp = null;
	public Typeface face;
	public int lvl;
	public GameHistory gs;
//	private MobclixAdView adView;

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelhistory);
        MainScreen.currentLevel.stop();
		MainScreen.musicHandlerState = MainScreen.MUSIC_STATE_MENU;
//    	PhysicsWorld.pause = false;
    	MainScreen.h.sendEmptyMessage(0);
    	
        score = (TextView) findViewById(R.id.scoreview);
        highscore = (TextView) findViewById(R.id.highscoreview);
        face = Typeface.createFromAsset(getAssets(), "fonts/ARGOS.TTF"); 
        score.setTypeface(face); 
        highscore.setTypeface(face); 
           
 
        
       				
        			score.setText("Score : "+Icoptermove.point) ;
        			
        			if(Icoptermove.point >= MainScreen.highscore)
        				highscore.setText("New Record!! : "+Icoptermove.point) ;
					
        			else
        				highscore.setText("Best : "+MainScreen.highscore) ;
        				
			
				

				 // Create the adView
		//		adView = (MobclixAdView)findViewById(R.id.adView);


			    // Lookup your LinearLayout assuming it’s been given
			    // the attribute android:id="@+id/mainLayout"
//			    LinearLayout layout = (LinearLayout)findViewById(R.id.RelativeLayoutAd);
//
//			    // Add the adView to it
//			    layout.addView(adView);

			    
			//    AdRequest request = new AdRequest();
//			    request.setTesting(true);
			    

			    // Initiate a generic request to load it with an ad
			//    adView.loadAd(request);


        
        
        
        reload = (Button) findViewById(R.id.reload);
//        music.setText(MainScreen.musicFlag?"Music: ON" : "Music: OFF");
        reload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	SoundManager.playSound(3, 1);
            	 MainScreen.currentLevel.start();
            	//Frame.world.startAnimate();
                
            }

        });
        
              
        
         levelmenu = (Button) findViewById(R.id.levelmenu);
         levelmenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	SoundManager.playSound(3, 1);
//            	TestGame.shallFinish = true;
            	Intent myIntent = new Intent(view.getContext(),
						MainScreen.class);
				startActivityForResult(myIntent, 0);
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
