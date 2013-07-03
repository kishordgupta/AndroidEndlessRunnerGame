package com.rhymes.client.mariobros.elements.fixeddestroyer.fixeddestroyerimagemove;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.elements.fixeddestroyer.FixedDestroyer;
	

	public class FixedDestroyerimagemove implements Animation {
		int speed=5;
		public static int moveSpeed = 10;
		public static Bitmap bitmap=null;
		public static Bitmap bitmap1;
		private FixedDestroyer Statimg1;
 int r=0;
 static boolean flag=true;
		private int speedY ;
		private int speedX = 3;
	   public  static boolean pic =true;
	   public static void setimg(){

		bitmap1 = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.bat);
		bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1
				.getHeight(), null, true);
		bitmap = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.bat);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), null, true);
	   }
	   
		public FixedDestroyerimagemove(FixedDestroyer star) {
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
			
			
			
		
		}

		@Override
		public void stop() {
		
		}

	


		public boolean isFinished() {
			return false;
		}

	}


