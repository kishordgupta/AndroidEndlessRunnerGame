package com.rhymes.client.mariobros.interactions.sensor;

import android.util.Log;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.SensorListener;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.interactions.Interaction;
import com.rhymes.client.mariobros.interactions.InteractionCallbacks;

public class InteractionSensor extends Interaction implements
		SensorEventListener {

	private float lasty = 0;
	private float lastx = 0;
	private float sensorx = 0;
	private float sensory = 0;
    public int z=0;
	@Override
	public void checkCondition(long elapsedTime) {

	}

	@Override
	public void takeAction() {
		// Log.d("InteractionSensor", "Taking action");
		for (InteractionCallbacks ic : this.elements) {

			if (ic instanceof Element) {

				((InteractionSensorCallbacks) ic).sensor(sensorx,
						sensory,z);
			}

			else if (ic instanceof Interaction) {
				((InteractionSensorCallbacks) ic).sensor(sensorx,
						sensory,z);
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	Object obj = new Object();

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (obj) {
			// TODO Auto-generated method stub
			sensorx = event.values[0] - lastx;
			sensory = event.values[1] - lasty;
			int p=(int)event.values[2];
			int a=Math.abs(p);
			if(a>=5 && a<=10)
			{Icoptermove.slidingSpeed=3;}
			else if(a>10 && a<=15)
			{Icoptermove.slidingSpeed=6;}
			else if(a>15 && a<=20)
			{Icoptermove.slidingSpeed=9;}
			else if(a>20 && a<=25)
			{Icoptermove.slidingSpeed=12;}
			else if(a>25 && a<=30)
			{Icoptermove.slidingSpeed=15;}
			else if(a>30 && a<=35)
			{Icoptermove.slidingSpeed=18;}
			else if(a>35)
			{Icoptermove.slidingSpeed=21;}
			
			if(p<-5)
				z=1;
			 else if(p>5)
				z=2;
			 else
			 { z=0;
			}
			lastx = event.values[0];
			lasty = event.values[1];
//			Log.d("sdf", "onSensorChanged: " + event.values[2] + ", x: "
//					+ ", z: " + event.values[2]);
		}
	}
}
