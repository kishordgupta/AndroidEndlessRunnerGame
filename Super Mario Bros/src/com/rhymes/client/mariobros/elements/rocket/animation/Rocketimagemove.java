package com.rhymes.client.mariobros.elements.rocket.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.elements.rocket.Rocket;
import com.rhymes.client.mariobros.elements.star.Star;
	

	public class Rocketimagemove implements Animation {
		
		public static int moveSpeed = 10;
		public static Bitmap bitmapf=null;
		public static Bitmap bitmapr=null;;
		public static Bitmap bitmaps=null;
		public static Bitmap bitmapl=null;;
		private Rocket Statimg1;
 int r=0;
 static boolean flag=true;
	
	   public  static boolean pic =true;
	   public static void setimg(){
	  
		bitmapf = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.rocket);
		bitmapf = Bitmap.createBitmap(bitmapf, 0, 0, bitmapf.getWidth(), bitmapf
				.getHeight(), null, true);
		
		
		bitmaps = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.rocket_full);
		bitmaps = Bitmap.createBitmap(bitmaps, 0, 0, bitmaps.getWidth(), bitmaps
				.getHeight(), null, true);
		bitmapr = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.rocket_full);
		bitmapr = Bitmap.createBitmap(bitmapr, 0, 0, bitmapr.getWidth(), bitmapr
				.getHeight(), null, true);
		
		
		bitmapl = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.rocket_full);
		bitmapl = Bitmap.createBitmap(bitmapl, 0, 0, bitmapl.getWidth(), bitmapl
				.getHeight(), null, true);
		
		

	   }
	   
		public Rocketimagemove(Rocket star) {
//			if(flag)
//				{setimg();flag=false;}
			Statimg1=star;
			

		}

		

		@Override
		public void init() {
		
			Statimg1.setmBitmap(bitmapf);
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


