package com.rhymes.client.mariobros.elements.sleaper;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.elements.sleaper.animation.Sleaperimagemove;
import com.rhymes.client.mariobros.gamehistory.SoundManager;
import com.rhymes.client.mariobros.interactions.collision.Collision;
import com.rhymes.client.mariobros.interactions.collision.InteractionCollisionCallbacks;
import com.rhymes.client.mariobros.levels.NewLevel;

public class sleaper extends Element implements InteractionCollisionCallbacks {

	// private static Bitmap mBitmap = BitmapFactory.decodeResource(
	// Frame.resources, R.drawable.cloud_triple);

	private static Sleaperimagemove statimagemove;
	private final int STATE_NORMAL = 0;
	private final int STATE_DESTROYED = 2;

	private int state = STATE_NORMAL;

	public sleaper(int x, int y, int width, int height) {
		super(x, y, width, height);
		initAnimations();
		// ((NewLevel) Frame.world.level).subscribeInteractionCollision(this);
	}

	public void reset() {
		this.state = STATE_NORMAL;
		this.died = false;

		((NewLevel) Frame.world.level).subscribeInteractionCollision(this);
	}

	private void initAnimations() {
		// TODO Auto-generated method stub

		sleaper.statimagemove = new Sleaperimagemove(this);
		sleaper.statimagemove.init();
	}

	private boolean died = false;

	int count = 5;

	@Override
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		if (state != STATE_DESTROYED) {
			this.setmBitmap(Sleaperimagemove.bitmap1);}
	else
		this.setmBitmap(Sleaperimagemove.bitmap);
		
		if (!died) {
			// Log.d("ss", "beta moreno");
			if ((this.mX + width) < Frame.world.getCamera().cameraRect.left) {
				// Frame.world.postDestroyed(this);
				((NewLevel) Frame.world.level)
						.unsubscribeInteractionCollision(this);
				// Log.d("ss", "beta moicche");
				died = true;
			}
		}

	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// if( state!= STATE_DESTROYED)
		canvas.drawBitmap(mBitmap, null, new Rect(mX, mY, this.width + mX,
				this.height + mY), null);
	}

	@Override
	public void doDraw(Canvas canvas, int newX, int newY) {
		// TODO Auto-generated method stub
		// if( state!= STATE_DESTROYED)
		canvas.drawBitmap(mBitmap, null, new Rect(newX, newY,
				this.width + newX, this.height + newY), null);
	}

	@Override
	public void onCollision(Collision collision) {
		// TODO Auto-generated method stub
		Element otherElement;
		int mySide;

		if (collision.element1 instanceof sleaper) {
			otherElement = collision.element2;
			mySide = collision.sideElement1;
		} else {
			otherElement = collision.element1;
			mySide = collision.sideElement2;
		}

		if (otherElement instanceof icopter) {
			if(Icoptermove.moveSpeed>6)
			Icoptermove.moveSpeed=Icoptermove.moveSpeed-5;
			state = STATE_DESTROYED;
			SoundManager.playSound(5, 1);
			Icoptermove.point=Icoptermove.point-10;
			return;
		}

	}
}
