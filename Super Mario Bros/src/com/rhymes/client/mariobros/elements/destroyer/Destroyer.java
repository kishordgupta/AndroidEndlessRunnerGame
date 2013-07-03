package com.rhymes.client.mariobros.elements.destroyer;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.destroyer.animation.Destroyerimagemove;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.interactions.collision.Collision;
import com.rhymes.client.mariobros.interactions.collision.InteractionCollisionCallbacks;
import com.rhymes.client.mariobros.levels.NewLevel;

public class Destroyer extends Element implements InteractionCollisionCallbacks {

//	private static Bitmap mBitmap = BitmapFactory.decodeResource(
//			Frame.resources, R.drawable.cloud_triple);

	private static Destroyerimagemove statimagemove;
	private final int STATE_NORMAL = 0;
	private final int STATE_DESTROYED = 2;
	
	
	private int state = STATE_NORMAL;
	public Destroyer(int x, int y, int width, int height) {
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

    	Destroyer.statimagemove= new Destroyerimagemove(this);	
		Destroyer.statimagemove.init();
	}

	private boolean died = false;
	int speed=3;
	@Override
	public void animate(long elapsedTime) {
		// TODO Auto-generated method stub
		
		if(	state!= STATE_DESTROYED){
			if(this.getmY()>Frame.screenHeight-100 )
				speed=-3;
			else if(this.getmY()<0)
					speed=3;
			this.setmY((int) (this.getmY() + speed));;
		int r= (int)(Math.random()*7);
		this.setmBitmap(Destroyerimagemove.bitmap[r]);}
		else
			this.setmBitmap(Destroyerimagemove.bitmap1);
		
		
		if (!died) {
			// Log.d("ss", "beta moreno");
			if ((this.mX + width) < Frame.world.getCamera().cameraRect.left) {
//				Frame.world.postDestroyed(this);
				((NewLevel)Frame.world.level).unsubscribeInteractionCollision(this);
				// Log.d("ss", "beta moicche");
				died = true;
			}
		}

	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//if(	state!= STATE_DESTROYED)
		canvas.drawBitmap(mBitmap, null, new Rect(mX, mY, this.width + mX+40,
				this.height + mY+40), null);
	}

	@Override
	public void doDraw(Canvas canvas, int newX, int newY) {
	
		canvas.drawBitmap(mBitmap, null, new Rect(newX, newY,
				this.width + newX+40, this.height + newY+40), null);
	}

	@Override
	public void onCollision(Collision collision) {
		// TODO Auto-generated method stub
		Element otherElement;
		int mySide;

		if (collision.element1 instanceof Destroyer) {
			otherElement = collision.element2;
			mySide = collision.sideElement1;
		} else {
			otherElement = collision.element1;
			mySide = collision.sideElement2;
		}
		
		
		if (otherElement instanceof icopter) {
	         
			state= STATE_DESTROYED;
//			if(Icoptermove.moveSpeed>3)
				//Icoptermove.moveSpeed=Icoptermove.moveSpeed+3;
		//	Icoptermove.point=Icoptermove.point-10;
			icopter.GAMEOVER=true;
			return;
		}

	
	}
}
