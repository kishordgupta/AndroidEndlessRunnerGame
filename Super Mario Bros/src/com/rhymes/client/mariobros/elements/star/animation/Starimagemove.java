package com.rhymes.client.mariobros.elements.star.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.elements.star.Star;
	

	public class Starimagemove implements Animation {
		
		public static int moveSpeed = 10;
		public static Bitmap[] bitmap=null;
		public static Bitmap bitmap1;
		private Star Statimg1;
 int r=0;
 static boolean flag=true;
		private int speedY ;
		private int speedX = 3;
	   public  static boolean pic =true;
	   public static void setimg(){
	   bitmap=new Bitmap[10];
		bitmap[0] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t11);
		bitmap[0] = Bitmap.createBitmap(bitmap[0], 0, 0, bitmap[0].getWidth(), bitmap[0]
				.getHeight(), null, true);
		
		
		bitmap[1] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t112);
		bitmap[1] = Bitmap.createBitmap(bitmap[1], 0, 0, bitmap[1].getWidth(), bitmap[1]
				.getHeight(), null, true);
		
		bitmap[2] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t117);
		bitmap[2] = Bitmap.createBitmap(bitmap[2], 0, 0, bitmap[2].getWidth(), bitmap[2]
				.getHeight(), null, true);
		
		bitmap[3] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t118);
		bitmap[3] = Bitmap.createBitmap(bitmap[3], 0, 0, bitmap[3].getWidth(), bitmap[3]
				.getHeight(), null, true);
		
		bitmap[4] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t12);
		bitmap[4] = Bitmap.createBitmap(bitmap[4], 0, 0, bitmap[4].getWidth(), bitmap[0]
				.getHeight(), null, true);
		
		bitmap[5] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t15);
		bitmap[5] = Bitmap.createBitmap(bitmap[5], 0, 0, bitmap[5].getWidth(), bitmap[5]
				
		                                                                              .getHeight(), null, true);
		
		bitmap[6] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t13);
		bitmap[6] = Bitmap.createBitmap(bitmap[6], 0, 0, bitmap[6].getWidth(), bitmap[6]
				.getHeight(), null, true);
		
		bitmap[7] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t14);
		bitmap[7] = Bitmap.createBitmap(bitmap[7], 0, 0, bitmap[7].getWidth(), bitmap[7]
				.getHeight(), null, true);
		
		bitmap[8] = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t19);
		bitmap[8] = Bitmap.createBitmap(bitmap[8], 0, 0, bitmap[8].getWidth(), bitmap[8]
				.getHeight(), null, true);
		bitmap1 = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.t2);
		bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1
				.getHeight(), null, true);

	   }
	   
		public Starimagemove(Star star) {
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
//			int r= (int)(Math.random()*7);
//			Statimg1.setmBitmap(bitmap[r]);
//			if(r==7)
//				r=0;
		}

		@Override
		public void stop() {
		
		}

	


		public boolean isFinished() {
			return false;
		}

	}


