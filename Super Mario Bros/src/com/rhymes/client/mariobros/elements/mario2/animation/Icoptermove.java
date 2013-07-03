package com.rhymes.client.mariobros.elements.mario2.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.elements.mario2.icopter;
	

	public class Icoptermove implements Animation {
		
		public static int moveSpeed = 25;
		public static int slidingSpeed = 5;
		public static int gas=0;;
		public static int  point=0;
		public static int Life = Frame.screenHeight;
		public static Bitmap[] bitmap;
		public static Bitmap bitmap1;
		private icopter icopter1;
        int picr=0;
        int bubbletime=0;
        public static int gameovertime=0;
	
	   public  static boolean pic =true;
     public static void setimg(){bitmap = new Bitmap[6];
		bitmap[0] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.untitled1);
		bitmap[0] = Bitmap.createBitmap(bitmap[0], 0, 0, bitmap[0].getWidth(), bitmap[0]
				.getHeight(), null, true);
		bitmap[1] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.untitled2);
		bitmap[1] = Bitmap.createBitmap(bitmap[1], 0, 0, bitmap[1].getWidth(), bitmap[1]
				.getHeight(), null, true);
		bitmap[2] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.untitled3);
		bitmap[2] = Bitmap.createBitmap(bitmap[2], 0, 0, bitmap[2].getWidth(), bitmap[2]
				.getHeight(), null, true);
		bitmap[3] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.untitled4);
		bitmap[3] = Bitmap.createBitmap(bitmap[3], 0, 0, bitmap[3].getWidth(), bitmap[3]
				.getHeight(), null, true);
		bitmap[4] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.untitled5);
		bitmap[4] = Bitmap.createBitmap(bitmap[4], 0, 0, bitmap[4].getWidth(), bitmap[4]
				.getHeight(), null, true);

		bitmap[5] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.untitled6);
		bitmap[5] = Bitmap.createBitmap(bitmap[5], 0, 0, bitmap[5].getWidth(), bitmap[5]
				.getHeight(), null, true);
		
		bitmap1 = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.bubblewithchar);
		bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1
				.getHeight(), null, true);
	}
		public Icoptermove(icopter mario) {
			moveSpeed = 10;
			
			icopter1=mario;

		}

		

		@Override
		public void init() {
		
			icopter1.setmBitmap(bitmap1);
		}

	

		@Override
		public void play(long elapsedTime) {
		if(icopter.state==icopter.STATE_UP)
			{if(icopter1.getmY()>0)
			icopter1.setmY((int) (icopter1.getmY() - slidingSpeed ));
			//icopter1.setmY((int) (icopter1.getmX() +5 ));
			
			}
		else if(icopter.state==icopter.STATE_DOWN)
		{	if(icopter1.getmY()<Frame.screenHeight-100)
			icopter1.setmY((int) (icopter1.getmY() + slidingSpeed ));
		
		}
		else if(icopter.state==icopter.STATE_NONE)
		{	 

		}
		if(!icopter.bubble){
		picr++;
		icopter1.setmBitmap(bitmap[picr]);
		if(picr==5)
			{picr=0;
			
			}
		}
		else
		{icopter1.setmBitmap(bitmap1);
		
		bubbletime++;
		if(bubbletime>60)
		{bubbletime=0;
		icopter.bubble=false;
		}
		}
		
		icopter1.setmX((int) (icopter1.getmX() + moveSpeed  ));
		
		if(moveSpeed<4)
		{
			gameovertime++;
		}
		else if(moveSpeed>=4)
		{
			gameovertime=0;
		}
		
		if(gameovertime>250)
		{
			//gameovertime=0;
			
		}
		
		}

		@Override
		public void stop() {
		
		}

	


		public boolean isFinished() {
			return false;
		}

	}


