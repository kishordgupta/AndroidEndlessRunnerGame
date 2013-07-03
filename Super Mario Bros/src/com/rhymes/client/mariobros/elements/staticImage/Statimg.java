package com.rhymes.client.mariobros.elements.staticImage;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.elements.star.Star;
import com.rhymes.client.mariobros.elements.staticImage.animation.Statimagemove;
import com.rhymes.client.mariobros.gamehistory.SoundManager;
import com.rhymes.client.mariobros.interactions.collision.Collision;
import com.rhymes.client.mariobros.interactions.collision.InteractionCollisionCallbacks;
import com.rhymes.client.mariobros.levels.NewLevel;

public class Statimg extends Element implements InteractionCollisionCallbacks {

	// private static Bitmap mBitmap = BitmapFactory.decodeResource(
	// Frame.resources, R.drawable.cloud_triple);

	private static Statimagemove statimagemove;
	private final int STATE_NORMAL = 0;
	private final int STATE_DESTROYED = 2;

	private int state = STATE_NORMAL;

	public Statimg(int x, int y, int width, int height) {
		super(x, y, width, height);
		initAnimations();
	}

	private void initAnimations() {
		// TODO Auto-generated method stub

		Statimg.statimagemove = new Statimagemove(this);
		Statimg.statimagemove.init();
	}

	private boolean died = false;

	public int count = 5;
	@Override
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		
		if(count < 1){
			if (state != STATE_DESTROYED) {
				int r = (int) (Math.random() * 7);
				this.setmBitmap(Statimagemove.bitmap[r]);
			} else
				this.setmBitmap(Statimagemove.bitmap1);
			count = 5;
		}
		count--;
		
		Statimg.statimagemove.play(elapsedTime);
		if (!died) {
			// 
			if ((this.mX + width) < Frame.world.getCamera().cameraRect.left) {
				// Frame.world.postDestroyed(this);
				((NewLevel) Frame.world.level)
						.unsubscribeInteractionCollision(this);
				// Log.d("ss", "beta moicche");
				died = true;
			}
		}

	}

	public void reset() {
		this.state = STATE_NORMAL;
		this.died = false;

		((NewLevel) Frame.world.level).subscribeInteractionCollision(this);
	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// if( state!= STATE_DESTROYED)
		canvas.drawBitmap(mBitmap, null, new Rect(mX, mY, this.width + mX+10,
				this.height + mY+10), null);
	}

	@Override
	public void doDraw(Canvas canvas, int newX, int newY) {
		// TODO Auto-generated method stub
		// if( state!= STATE_DESTROYED)
		canvas.drawBitmap(mBitmap, null, new Rect(newX, newY,
				this.width + newX+10, this.height + newY+10), null);
	}

	@Override
	public void onCollision(Collision collision) {
		// TODO Auto-generated method stub
		Element otherElement;
		int mySide;

		if (collision.element1 instanceof Star) {
			otherElement = collision.element2;
			mySide = collision.sideElement1;
		} else {
			otherElement = collision.element1;
			mySide = collision.sideElement2;
		}

		if (otherElement instanceof icopter) {
			SoundManager.playSound(6, 1);

			if(Icoptermove.moveSpeed>4)
			Icoptermove.moveSpeed=Icoptermove.moveSpeed-3;
			state = STATE_DESTROYED;
			return;
		}

	}
}
