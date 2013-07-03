package com.rhymes.client.mariobros.interactions.touch;

import android.util.Log;
import android.view.MotionEvent;

import com.rhymes.client.mariobros.Frame;
import com.rhymes.client.mariobros.elements.Element;
import com.rhymes.client.mariobros.interactions.Interaction;
import com.rhymes.client.mariobros.interactions.InteractionCallbacks;
import com.rhymes.client.mariobros.world.Helper;

public class InteractionTouch extends Interaction {
	private MotionEvent event;

	@Override
	public void checkCondition(long elapsedTime) {
		if (Frame.world.getScreen().event != null) {
			synchronized (Frame.world.getScreen().eventSynchronization) {
//				Log.d("InteractionTouch", "Touch interaction occured.");
				condition = true;
				this.event = Frame.world.getScreen().event;
				Frame.world.getScreen().event = null;
			}
		} else
			condition = false;
	}

	@Override
	public void takeAction() {
		if (condition) {
//			Log.d("InteractionTouch", "Taking Action of type: "
//					+ event.getAction());
			for (InteractionCallbacks ic : this.elements) {

				if (ic instanceof Element) {
					Element e = ((Element) ic);
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						if (Helper.insideRegion((int) event.getX(), (int) event
								.getY(), e.getRegion()))
							((InteractionTouchCallbacks) ic).touch((int) event
									.getX(), (int) event.getY());
					}
				}
				else if (ic instanceof Interaction) {
					((InteractionTouchCallbacks) ic).touch((int) event
							.getX(), (int) event.getY());
				}
			}
		}
	}
}
