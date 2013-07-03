package com.rhymes.client.mariobros.elements.rocket;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.elements.rocket.animation.Rocketimagemove;
import com.rhymes.client.mariobros.gamehistory.SoundManager;
import com.rhymes.client.mariobros.interactions.collision.Collision;
import com.rhymes.client.mariobros.interactions.collision.InteractionCollisionCallbacks;
import com.rhymes.client.mariobros.interactions.controller.InteractionController;
import com.rhymes.client.mariobros.interactions.controller.InteractionControllerCallbacks;
import com.rhymes.client.mariobros.interactions.touch.InteractionTouchCallbacks;
import com.rhymes.client.mariobros.levels.NewLevel;

public class Rocket extends Element implements InteractionCollisionCallbacks,
		InteractionControllerCallbacks {

	// private static Bitmap mBitmap = BitmapFactory.decodeResource(
	// Frame.resources, R.drawable.cloud_triple);

	private static Rocketimagemove statimagemove;
	private final static int STATE_NORMAL = 0;
	private final static int STATE_DESTROYED = 2;
	private final static int POSITION_NORMAL = 0;
	private final static int POSITION_STRAIGHT = 1;
	private final static int POSITION_LEFT = 2;
	private final static int POSITION_RIGHT = 3;

	public static int state = STATE_NORMAL;
	public static int position = POSITION_NORMAL;
 public  static int direction=0;
	public Rocket(int x, int y, int width, int height) {
		super(x, y, width, height);
		initAnimations();
		// ((NewLevel) Frame.world.level).subscribeInteractionCollision(this);
	}

	public void reset() {
		this.state = STATE_NORMAL;
		this.died = false;
		this.position = POSITION_NORMAL;
		((NewLevel) Frame.world.level).subscribeInteractionCollision(this);
		((NewLevel) Frame.world.level).subscribeInteractionTouchollision(this);
		((NewLevel) Frame.world.level).subscribeInteractioncontroller(this);
	}

	private void initAnimations() {
		// TODO Auto-generated method stub

		Rocket.statimagemove = new Rocketimagemove(this);
		Rocket.statimagemove.init();
	}

	private boolean died = false;

	int count = 5;

	@Override
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		if (position == POSITION_STRAIGHT) {
			direction=0;
			this.setmBitmap(Rocketimagemove.bitmaps);
		} else if (position == POSITION_NORMAL) {
			this.setmBitmap(Rocketimagemove.bitmapf);
		} else if (position == POSITION_LEFT) {
			this.setmBitmap(Rocketimagemove.bitmapl);
			direction=1;
		} else if (position == POSITION_RIGHT) {
			this.setmBitmap(Rocketimagemove.bitmapr);
			direction=2;
		}
		count--;

		if (!died) {
			if ((this.mX + width) < Frame.world.getCamera().cameraRect.left) {
		
				((NewLevel) Frame.world.level)
						.unsubscribeInteractionCollision(this);
	
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
				this.width + newX+20, this.height + newY+20), null);
	}

	// @ Override
	public void onEvent(int event) {
		// TODO Auto-generated method stub
		// Log.d("Mario", "Mario got an event: " + event);
		
		//SoundManager.playSound(1, 1);


		if (event == InteractionController.EVENT_RIGHT_JUMP) {

			this.position = POSITION_LEFT;

		} else if (event == InteractionController.EVENT_LEFT_JUMP) {

			this.position = POSITION_RIGHT;

		} else if (event == InteractionController.EVENT_STRAIGHT_JUMP) {

			this.position = POSITION_STRAIGHT;

		} else if (event == InteractionController.EVENT_SHOOT_BULLET) {

			this.position = POSITION_NORMAL;

		}

	}

	@Override
	public void onCollision(Collision collision) {
		// TODO Auto-generated method stub
		Element otherElement;
		int mySide;

		if (collision.element1 instanceof Rocket) {
			otherElement = collision.element2;
			mySide = collision.sideElement1;
		} else {
			otherElement = collision.element1;
			mySide = collision.sideElement2;
		}

		if (otherElement instanceof icopter) {
			//SoundManager.playSound(2, 1);

			position = POSITION_STRAIGHT;
			return;
		}

	}
}
