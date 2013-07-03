package com.rhymes.client.mariobros.world;

import com.rhymes.client.mariobros.elements.Element;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Screen extends SurfaceView implements SurfaceHolder.Callback {

    public static int screenWidth;
    public static int screenHeight;
    public MotionEvent event;
    public Object eventSynchronization = new Object();
    public Screen(Context context, int backColor) {
        super(context);
        isSurfaceCreated = false;
        getHolder().addCallback(this);
    }
    
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        screenWidth = width;
        screenHeight = height;
    	//Log.d("Screen", "Screen width: " + this.screenWidth + "\nScreen height: " + this.screenHeight);
    }
    
    public static boolean isSurfaceCreated = false;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    	isSurfaceCreated = true;
    	Log.d("Screen", "Surface created");
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	synchronized (this.eventSynchronization) {
    		this.event = event;
//    		Log.d("Screen", "Touch event occured at X: " + event.getX() + " Y: " + event.getY());
		}    	
        return super.onTouchEvent(event);
    }
}
