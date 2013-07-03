package com.rhymes.client.mariobros.interactions.collision;

import java.util.ArrayList;

import android.graphics.Rect;
import android.util.Log;

import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.elements.mario2.icopter;
import com.rhymes.client.mariobros.interactions.Interaction;
import com.rhymes.client.mariobros.interactions.InteractionCallbacks;

public class InteractionCollision extends Interaction {
	private ArrayList<Collision> collidedElements;
	private icopter mainChar;
	public InteractionCollision(icopter main) {
		this.collidedElements = new ArrayList<Collision>();
		this.elements = new ArrayList<InteractionCallbacks>();
		this.mainChar = main;
	}

	@Override
	public void checkCondition(long elapsedTime) {
		Object[] aElements = this.elements.toArray();
		Collision collision;

		collidedElements.clear();
//		Log.d("Collision", "element size: " + this.elements.size());
		for (int i = 0; i < aElements.length - 1; i++) {
//			for (int j = i + 1; j < aElements.length; j++) {
//				 Log.d("InteractionCollision", "Checking collision i: " + i +
//				 " j: " + j);
				collision = checkCollision((Element) aElements[i],
						this.mainChar);
				if (collision != null) {
					this.elements.remove(aElements[i]);
					collidedElements.add(collision);
//					 Log.d("InteractionCollision", "Collision occured " + i
//					 + " j: " + j);
//						printRect(collision.element1.getRegion(), "Rect 1: ");
//						printRect(collision.element2.getRegion(), "Rect 2: ");	
				}
			}
//		}
		if (collidedElements.size() != 0)
			condition = true;
	}

	private void printRect(Rect r, String prefix) {
		Log.d("InteractionCollision", prefix + "\nLeft:" + r.left + " Right: "
				+ r.right + " Bottom: " + r.bottom + " Top: " + r.top);
	}

	@Override
	public void takeAction() {
		if (condition) {
			for (Collision c : this.collidedElements) {
				((InteractionCollisionCallbacks) c.element1).onCollision(c);
				((InteractionCollisionCallbacks) c.element2).onCollision(c);
			}
			condition = false;
		}
	}

	private Collision checkCollision(Element e1, Element e2) {
		Rect r1 = e1.getRegion();
		Rect r2 = e2.getRegion();

		// printRect(r1, "Element 1:");
		// printRect(r2, "Element 2:");

		if (r1.left > r2.left) {
			// Log.d("InteractionCollision", "Rect Swaped");
			Rect temp = r1;
			r1 = r2;
			r2 = temp;
			Element e = e1;
			e1 = e2;
			e2 = e;
		}

		if (r1.right < r2.left)
			return null;
		else { // r1.right >= r2.left
			if (r1.top <= r2.top && r1.bottom >= r2.bottom) {
				return new Collision(e1, e2, Collision.SIDE_EMBEDDING,
						Collision.SIDE_EMBEDDED);
			}
			if (r1.top > r2.top && r1.bottom < r2.bottom && r1.right > r2.right) {
				return new Collision(e1, e2, Collision.SIDE_CROSS_LEFT,
						Collision.SIDE_CROSS_RIGHT);
			}
			
			if (r1.right < r2.right) {
				if (r1.top <= r2.top && r1.bottom >= r2.bottom) {
					return new Collision(e1, e2, Collision.SIDE_RIGHT,
							Collision.SIDE_LEFT);
				}
				if (r1.top >= r2.top && r1.bottom <= r2.bottom) {
					return new Collision(e1, e2, Collision.SIDE_RIGHT,
							Collision.SIDE_LEFT);
				}
				if (r1.top < r2.top && r1.bottom < r2.bottom && r1.bottom >= r2.top) {
					return new Collision(e1, e2, Collision.SIDE_BOTTOM_RIGHT,
							Collision.SIDE_TOP_LEFT);
				}
				if (r1.top > r2.top && r1.bottom > r2.bottom && r1.top <= r2.bottom) {
					return new Collision(e1, e2, Collision.SIDE_TOP_RIGHT,
							Collision.SIDE_BOTTOM_LEFT);
				}
			} else {//r1.right >= r2.right
				if (r1.top <= r2.top && r1.bottom >= r2.top ) {
					return new Collision(e1, e2, Collision.SIDE_BOTTOM,
							Collision.SIDE_TOP);
				}
				if (r1.top >= r2.top && r1.top <= r2.bottom ) {
					return new Collision(e1, e2, Collision.SIDE_TOP,
							Collision.SIDE_BOTTOM);
				}
			}
		}
		return null;
	}

}
