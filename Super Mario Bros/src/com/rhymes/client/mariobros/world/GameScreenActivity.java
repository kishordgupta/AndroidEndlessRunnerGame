package com.rhymes.client.mariobros.world;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.levels.NewLevel;

public class GameScreenActivity extends Activity {

	private SensorManager sm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);


	   
		setContentView(Frame.world.getScreen());
	
		sm = (SensorManager) Frame.context.getSystemService(SENSOR_SERVICE);
		sm.registerListener(NewLevel.interactionSensor, sm
				.getDefaultSensor(SensorManager.SENSOR_ORIENTATION
						| SensorManager.SENSOR_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		
		Log.d("GameScreenActivity", "On create");
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		

		Frame.world.setRunning(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors

		sm.registerListener(NewLevel.interactionSensor, sm
				.getDefaultSensor(SensorManager.SENSOR_ORIENTATION
						| SensorManager.SENSOR_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		Frame.world.startAnimate();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Frame.world.stopAnimate();
	}

	@Override
	protected void onStop() {
		// unregister listener
		sm.unregisterListener(NewLevel.interactionSensor);
		super.onStop();
		System.gc();
	}

}