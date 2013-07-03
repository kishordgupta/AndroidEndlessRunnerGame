package com.rhymes.client.mariobros.elements.gascollector;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.gascollector.animation.GasCollectorimagemove;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.gamehistory.SoundManager;
import com.rhymes.client.mariobros.interactions.collision.Collision;
import com.rhymes.client.mariobros.interactions.collision.InteractionCollisionCallbacks;
import com.rhymes.client.mariobros.levels.NewLevel;

public class GasCollector extends Element implements InteractionCollisionCallbacks {

//	private static Bitmap mBitmap = BitmapFactory.decodeResource(
//			Frame.resources, R.drawable.cloud_triple);

	private static GasCollectorimagemove statimagemove;
	private final int STATE_NORMAL = 0;
	private final int STATE_DESTROYED = 2;
	
	
	
	
	
	private int state = STATE_NORMAL;
	public GasCollector(int x, int y, int width, int height) {
		super(x, y, width, height);
		initAnimations();
		//((NewLevel) Frame.world.level).subscribeInteractionCollision(this);
	}
	public void reset() {
		this.state = STATE_NORMAL;
		this.died = false;

		((NewLevel) Frame.world.level).subscribeInteractionCollision(this);
	}
	private void initAnimations() {
		// TODO Auto-generated method stub

		GasCollector.statimagemove= new GasCollectorimagemove(this);	
		GasCollector.statimagemove.init();
	}

	private boolean died = false;

	@Override
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		if(	state!= STATE_DESTROYED){	
		this.setmBitmap(GasCollectorimagemove.bitmap);}
		
		
		GasCollector.statimagemove.play(elapsedTime);
		if (!died) {
			
			if ((this.mX + width) < Frame.world.getCamera().cameraRect.left) {
			
				((NewLevel)Frame.world.level).unsubscribeInteractionCollision(this);
				
				died = true;
			}
		}

	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//if(	state!= STATE_DESTROYED)
		if(	state!= STATE_DESTROYED)
		canvas.drawBitmap(mBitmap, null, new Rect(mX, mY, this.width + mX,
				this.height + mY), null);
	}

	@Override
	public void doDraw(Canvas canvas, int newX, int newY) {
		// TODO Auto-generated method stub
		//if(	state!= STATE_DESTROYED)
		if(	state!= STATE_DESTROYED)
		canvas.drawBitmap(mBitmap, null, new Rect(newX, newY,
				this.width + newX, this.height + newY), null);
	}

	@Override
	public void onCollision(Collision collision) {
		// TODO Auto-generated method stub
		Element otherElement;
		int mySide;

		if (collision.element1 instanceof GasCollector) {
			otherElement = collision.element2;
			mySide = collision.sideElement1;
		} else {
			otherElement = collision.element1;
			mySide = collision.sideElement2;
		}
		
		
		if (otherElement instanceof icopter) {
			SoundManager.playSound(7, 1);

			state= STATE_DESTROYED;
		
				Icoptermove.gas=10;
			return;
		}

	
	}
}
