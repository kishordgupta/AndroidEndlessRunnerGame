package com.rhymes.client.mariobros.elements.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.elements.*;
import com.rhymes.client.mariobros.world.Helper;
import com.rhymes.client.mariobros.animation.Animation;




public class AnimationDestroyed implements Animation {
	private Element element;

	public AnimationDestroyed (Element element) 
	{
		this.element = element;
	}

	@Override
	public void init() {
	}


	public void play(long elapsedTime)
	{
		    element.setmY(element.getmY()+(element.getHeight()/10));
			element.setHeight(element.getHeight()-(element.getHeight()/16));
            
			
			
	}
	

	@Override
	public void stop() {
//		this.speedY = 0;
	}
}