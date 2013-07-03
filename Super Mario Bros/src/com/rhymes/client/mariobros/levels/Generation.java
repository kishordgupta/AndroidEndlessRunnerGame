package com.rhymes.client.mariobros.levels;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;
import android.util.Log;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.destroyer.Destroyer;
import com.rhymes.client.mariobros.elements.fixeddestroyer.FixedDestroyer;
import com.rhymes.client.mariobros.elements.gascollector.GasCollector;
import com.rhymes.client.mariobros.elements.mario2.animation.Icoptermove;
import com.rhymes.client.mariobros.elements.rocket.Rocket;
import com.rhymes.client.mariobros.elements.sleaper.sleaper;
import com.rhymes.client.mariobros.elements.speader.Speader;
import com.rhymes.client.mariobros.elements.star.Star;
import com.rhymes.client.mariobros.elements.staticImage.Statimg;
import com.rhymes.client.mariobros.levels.Level;
import com.rhymes.client.mariobros.world.Camera;
import com.rhymes.client.mariobros.world.World;

public class Generation {
	float xw = (float)Frame.screenWidth/800 ;
	float yh = (float)Frame.screenHeight/480 ;
	float gererationBufferCheckPoint = 400*xw;
	float lastGenearationCheckPoint = 0;
	Statimg[] tripplecloud1 = new Statimg[100];
	Star[] star = new Star[30];
	Destroyer[] destroyer = new Destroyer[20];
	Speader[] speader = new Speader[10];
	sleaper[] sleap = new sleaper[10];
	public static Rocket rocket = null;
	public static GasCollector gascollector = null;
	FixedDestroyer[] fixeddestroyer = new FixedDestroyer[10];
	int sleapnumber = 0;
	int starnumber = 0;
	int statnumber = 0;
	int destroyernumber = 0;
	int fixeddestroyernumber = 0;
	int speadernumber = 0;
	int rocketnumber = 0;
	static int rangeindex = 1;
	float range = gererationBufferCheckPoint;

	static int lastrange = 0;
	int currenty = 0;
	

	public Generation() {
		// this.elements = new ArrayList<Element>();
		rangeindex = 1;
		lastrange = 0;

	}

	public void generate() {
		for (int i = 0; i < 100; i++) {
			tripplecloud1[i] = new Statimg(0, 0, (int) (50 * xw),
					(int) (100 * yh));
		}
		for (int i = 0; i < 30; i++) {
			star[i] = new Star(0, 0, (int) (80 * xw), (int) (80 * yh));
		}
		for (int i = 0; i < 20; i++) {
			destroyer[i] = new Destroyer(0, 0, (int) (50 * xw), (int) (30 * yh));
		}
		for (int i = 0; i < 10; i++) {
			fixeddestroyer[i] = new FixedDestroyer(0, 0, (int) (90 * xw),
					(int) (70 * yh));
		}
		for (int i = 0; i < 10; i++) {
			speader[i] = new Speader(0, 0, (int) (90 * xw), (int) (70 * yh));
		}
		for (int i = 0; i < 10; i++) {
			sleap[i] = new sleaper(0, 0, (int) (90 * xw), (int) (70 * yh));
		}


		rocket = new Rocket(0, 0, (int) (140 * xw), (int) (60 * yh));
		gascollector = new GasCollector(0, 0, (int) (80 * xw), (int) (80 * yh));

	}

	public void checkGeneration(int y) {
		y = World.camera.cameraRect.right;
		if ((y + gererationBufferCheckPoint) > lastGenearationCheckPoint) {
			createworld();
//			Log.d("Generation", "World size:"
//					+ Frame.world.getElements().size());
			lastGenearationCheckPoint = lastGenearationCheckPoint
					+ gererationBufferCheckPoint;
			rangeindex++;
		}
	}

	private int lastCP = 0;

	public void createworld() {
		// TODO Auto-generated method stub
		int totalcloud = (int) (Math.random() * 30);
		// tripplecloud1=new Statimg[totalcloud];

		for (int i = 0; i < (totalcloud); i++) {
			int x = 0;
			int y = 0;
			x = (int) ((Math.random() * range) + lastrange);
			if ((i * 2) > totalcloud) {
				y = ((int) (Math.random() * 20) + (Frame.screenHeight - 100));

			} else
				y = (int) (Math.random() * 20);

			// tripplecloud1[i] = new Statimg((int) (x * xw), (int) (y * yh),
			// (int) (60 * xw), (int) (110 * yh));
			tripplecloud1[statnumber].reset();
			tripplecloud1[statnumber].setmX(x);
			tripplecloud1[statnumber].setmY(y);
			tripplecloud1[statnumber].setZIndex(0);
			if (!Frame.world.getElements().contains(tripplecloud1[statnumber])) {
				Frame.world.addElement(tripplecloud1[statnumber]);
				World.camera.addElement(tripplecloud1[statnumber]);
			}
			statnumber++;
			if (statnumber == 99)
				statnumber = 0;
		}

		int totalstar = (int) (Math.random() * 6);
		// star=new Star[totalstar];

		lastCP = 0;
		for (int i = 0; i < totalstar; i++) {
			int x = 0;
			int y = 0;
			// x = (int)( (Math.random() * range) + lastrange);
			x = (int) (Math.random() * 50) + lastCP + 50 + lastrange;
			lastCP = x;
			y = (int) (Math.random() * (Frame.screenHeight - 100))+20;

			// star[i] = new Star((int) (x * xw), (int) (y * yh),
			// (int) (60 * xw), (int) (60 * yh), 3);
			star[starnumber].reset();
			star[starnumber].setmX(x);
			star[starnumber].setmY(y);
			star[starnumber].setZIndex(0);
			if (!Frame.world.getElements().contains(star[starnumber])) {
				Frame.world.addElement(star[starnumber]);
				World.camera.addElement(star[starnumber]);
			}
			starnumber++;
			if (starnumber == 29)
				starnumber = 0;
		}
		if (rangeindex % 4 == 0){
		int Destroynumber = (int) (Math.random() * 2);
		
		lastCP = 0;
		for (int i = 0; i < Destroynumber; i++) {
			int x = 0;
			int y = 0;
			
			x = (int) (Math.random() * 50) + lastCP + 50 + lastrange;
		
			lastCP = x;

			y = (int) (Math.random() * (Frame.screenHeight - 100));

			destroyer[destroyernumber].reset();
			destroyer[destroyernumber].setmX(x);
			destroyer[destroyernumber].setmY(y);
			destroyer[destroyernumber].setZIndex(0);
			if (!Frame.world.getElements().contains(destroyer[destroyernumber])) {
				Frame.world.addElement(destroyer[destroyernumber]);
				World.camera.addElement(destroyer[destroyernumber]);
			}
			destroyernumber++;
			if (destroyernumber == 19)
				destroyernumber = 0;
		}}
		
		{
			int sleapernumber = (int) (Math.random() * 2);
			
			lastCP = 0;
			for (int i = 0; i < sleapernumber; i++) {
				int x = 0;
				int y = 0;
				
				x = (int) (Math.random() * 50) + lastCP + 50 + lastrange;
			
				lastCP = x;

				y = (int) (Math.random() * (Frame.screenHeight - 100));

				sleap[sleapnumber].reset();
				sleap[sleapnumber].setmX(x);
				sleap[sleapnumber].setmY(y);
				sleap[sleapnumber].setZIndex(0);
				if (!Frame.world.getElements().contains(sleap[sleapnumber])) {
					Frame.world.addElement(sleap[sleapnumber]);
					World.camera.addElement(sleap[sleapnumber]);
				}
				sleapnumber++;
				if (sleapnumber == 9)
					sleapnumber = 0;
			}}
		if (rangeindex % 4 == 0){
		int fixedDestroynumber = (int) (Math.random() * 2);
		// destroyer=new Destroyer[Destroyernumber];
		lastCP = 0;
		for (int i = 0; i < fixedDestroynumber; i++) {
			int x = 0;
			int y = 0;
			// x = (int)( (Math.random() * range) + lastrange);
			x = (int) (Math.random() * 50) + lastCP + 50 + lastrange;

			lastCP = x;

			y = (int) (Math.random() * (Frame.screenHeight - 100));

			fixeddestroyer[fixeddestroyernumber].reset();
			fixeddestroyer[fixeddestroyernumber].setmX(x);
			fixeddestroyer[fixeddestroyernumber].setmY(y);
			fixeddestroyer[fixeddestroyernumber].setZIndex(0);
			if (!Frame.world.getElements().contains(
					fixeddestroyer[fixeddestroyernumber])) {
				Frame.world.addElement(fixeddestroyer[fixeddestroyernumber]);
				World.camera.addElement(fixeddestroyer[fixeddestroyernumber]);
			}
			fixeddestroyernumber++;
			if (fixeddestroyernumber == 9)
				fixeddestroyernumber = 0;
		}}
		if (rangeindex % 3 == 0){
		int speadnumber = (int) (Math.random() * 2);

		lastCP = 0;
		for (int i = 0; i < speadnumber; i++) {
			int x = 0;
			int y = 0;
		
			x = (int) (Math.random() * 50) + lastCP + 50 + lastrange;
		
			lastCP = x;

			y = (int) (Math.random() * (Frame.screenHeight - 100));

			speader[speadernumber].reset();
			speader[speadernumber].setmX(x);
			speader[speadernumber].setmY(y);
			speader[speadernumber].setZIndex(0);
			if (!Frame.world.getElements().contains(speader[speadernumber])) {
				Frame.world.addElement(speader[speadernumber]);
				World.camera.addElement(speader[speadernumber]);
			}
			speadernumber++;
			if (speadernumber == 9)
				speadernumber = 0;
		}
		}
		if (rangeindex % 22 == 0) {
			rocket.reset();
			rocket.setmX(lastrange);
			rocket.setmY((int) (Frame.screenHeight / 2));
			rocket.setZIndex(0);
			if (!Frame.world.getElements().contains(rocket)) {
				Frame.world.addElement(rocket);
				World.camera.addElement(rocket);
			}
			
		}
		if (rangeindex % 6 == 0) {
			Camera.drawbk();
		gascollector.reset();
		gascollector.setmX(lastrange);
		int y = (int) (Math.random() * (Frame.screenHeight - 100));
		gascollector.setmY(y);
		gascollector.setZIndex(0);
		if (!Frame.world.getElements().contains(gascollector)) {
			Frame.world.addElement(gascollector);
			World.camera.addElement(gascollector);
		}}

		lastrange = (int) (lastrange + range);

	}

	public void removeElement(Element e) {
		// this.elements.remove(e);
	}

	public void addElement(Element e) {
		// this.elements.add(e);
	}
}
