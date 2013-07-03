package com.rhymes.client.mariobros.interactions.collision;

import android.util.Log;

import com.rhymes.client.mariobros.elements.Element;

public class Collision {
	public static final int SIDE_NONE = 0;

	public static final int SIDE_TOP = 1;
	public static final int SIDE_BOTTOM = 2;
	public static final int SIDE_LEFT = 3;
	public static final int SIDE_RIGHT = 4;

	public static final int SIDE_TOP_LEFT = 5;
	public static final int SIDE_TOP_RIGHT = 6;
	public static final int SIDE_BOTTOM_LEFT = 7;
	public static final int SIDE_BOTTOM_RIGHT = 8;

	public static final int SIDE_EMBEDDING = 9;
	public static final int SIDE_EMBEDDED = 10;

	public static final int SIDE_CROSS_LEFT = 11;
	public static final int SIDE_CROSS_RIGHT = 12;

	public Element element1;
	public Element element2;
	public int sideElement1;
	public int sideElement2;

	public Collision(Element element1, Element element2, int sideElement1,
			int sideElement2) {
		super();
		this.element1 = element1;
		this.element2 = element2;
		this.sideElement1 = sideElement1;
		this.sideElement2 = sideElement2;
	}

	public void print() {
		Log.d("Collision", element1.getClass() + " hit at side: "
				+ sideElement1 + "   with   " + element2.getClass() + " hit at side: "
				+ sideElement2);		
	}
}
