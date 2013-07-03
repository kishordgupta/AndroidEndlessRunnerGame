package com.rhymes.client.mariobros.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rhymes.client.gp.pigshotclone.R;

public class SplashScreen extends Activity {
    
 protected boolean _active = true;
 protected int _splashTime = 3000; // time to display the splash screen in ms
 
 /** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.splash);
  
     
     final SplashScreen sPlashScreen = this;   

     Handler timer = new Handler();
     
     Runnable r = new Runnable() {
   
   @Override
   public void run() {
    Intent intent = new Intent();
       intent.setClass(sPlashScreen, MainScreen.class);
       startActivity(intent);
                
       finish();
   }
  };
  
     timer.postDelayed(r, _splashTime);
 }
}