package com.rhymes.client.mariobros.elements.sleaper.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.elements.sleaper.sleaper;
import com.rhymes.client.mariobros.elements.speader.Speader;
	

	public class Sleaperimagemove implements Animation {
		
		public static int moveSpeed = 10;
		public static Bitmap bitmap=null;
		public static Bitmap bitmap1;
		private  sleaper Statimg1;
 int r=0;
 static boolean flag=true;
		private int speedY ;
		private int speedX = 3;
	   public  static boolean pic =true;
	   public static void setimg(){
//	 
		bitmap1 = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.sample_2);
		bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1
				.getHeight(), null, true);
		bitmap = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.sample_0);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), null, true);

	   }
	   
		public Sleaperimagemove( sleaper star) {
//			if(flag)
//				{setimg();flag=false;}
			Statimg1=star;
			

		}

		

		@Override
		public void init() {
		
			Statimg1.setmBitmap(bitmap1);
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


