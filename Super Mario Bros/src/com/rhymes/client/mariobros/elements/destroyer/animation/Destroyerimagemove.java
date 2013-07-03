package com.rhymes.client.mariobros.elements.destroyer.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.elements.destroyer.Destroyer;
	

	public class Destroyerimagemove implements Animation {
		int speed=5;
		public static int moveSpeed = 10;
		public static Bitmap[] bitmap=null;
		public static Bitmap bitmap1;
		private Destroyer Statimg1;
 
		int r=0;
		static boolean flag=true;
		private int speedY ;
		private int speedX = 3;
	   public  static boolean pic =true;
	   public static void setimg(){
	   
		   bitmap=new Bitmap[10];
		
		bitmap[0] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon1);
		bitmap[0] = Bitmap.createBitmap(bitmap[0], 0, 0, bitmap[0].getWidth(), bitmap[0]
				.getHeight(), null, true);
		
		
		bitmap[1] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon2);
		bitmap[1] = Bitmap.createBitmap(bitmap[1], 0, 0, bitmap[1].getWidth(), bitmap[1]
				.getHeight(), null, true);
		
		bitmap[2] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon3);
		bitmap[2] = Bitmap.createBitmap(bitmap[2], 0, 0, bitmap[2].getWidth(), bitmap[2]
				.getHeight(), null, true);
		
		bitmap[3] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon4);
		bitmap[3] = Bitmap.createBitmap(bitmap[3], 0, 0, bitmap[3].getWidth(), bitmap[3]
				.getHeight(), null, true);
		
		bitmap[4] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon5);
		bitmap[4] = Bitmap.createBitmap(bitmap[4], 0, 0, bitmap[4].getWidth(), bitmap[4]
				.getHeight(), null, true);
		
		bitmap[5] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon6);
		bitmap[5] = Bitmap.createBitmap(bitmap[5], 0, 0, bitmap[5].getWidth(), bitmap[5]
				
		                                                                              .getHeight(), null, true);
		
		bitmap[6] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon7);
		bitmap[6] = Bitmap.createBitmap(bitmap[6], 0, 0, bitmap[6].getWidth(), bitmap[6]
				.getHeight(), null, true);
		
		bitmap[7] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon6);
		bitmap[7] = Bitmap.createBitmap(bitmap[7], 0, 0, bitmap[7].getWidth(), bitmap[7]
				.getHeight(), null, true);
		
		bitmap[8] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon5);
		bitmap[8] = Bitmap.createBitmap(bitmap[8], 0, 0, bitmap[8].getWidth(), bitmap[8]
				.getHeight(), null, true);
		bitmap1 = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.dragon7);
		bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1
				.getHeight(), null, true);

	   }
	   
		public Destroyerimagemove(Destroyer star) {
//			if(flag)
//				{setimg();flag=false;}
			Statimg1=star;
			

		}

		

		@Override
		public void init() {
		
			Statimg1.setmBitmap(bitmap[0]);
		}

	

		@Override
		public void play(long elapsedTime) {
			
			
			
		
		}

		@Override
		public void stop() {
		
		}

	


		public boolean isFinished() {
			return false;
		}

	}


