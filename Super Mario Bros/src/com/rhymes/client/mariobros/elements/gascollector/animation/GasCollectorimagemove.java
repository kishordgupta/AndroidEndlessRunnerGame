package com.rhymes.client.mariobros.elements.gascollector.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.gp.pigshotclone.R;
import com.rhymes.client.mariobros.animation.Animation;
import com.rhymes.client.mariobros.elements.gascollector.GasCollector;
	

	public class GasCollectorimagemove implements Animation {
		
		public static int moveSpeed = 10;
		public static Bitmap bitmap=null;
		public static Bitmap bitmap1;
		private GasCollector Statimg1;
 
		int r=0;
 		static boolean flag=true;
		private int speedY ;
		private int speedX = 3;
	   public  static boolean pic =true;
	   public static void setimg(){
	  
		
		bitmap = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.gas_collector);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap
				.getHeight(), null, true);
		bitmap1 = BitmapFactory.decodeResource(Frame.resources,
				R.drawable.gas_collector);
		bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1.getHeight(), null, true);

	   }
	   
		public GasCollectorimagemove(GasCollector gas) {
//			if(flag)
//				{setimg();flag=false;}
			Statimg1=gas;
			

		}

		

		@Override
		public void init() {
		
			Statimg1.setmBitmap(bitmap);
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


