package com.rhymes.client.mariobros.interactions;

import java.util.ArrayList;

import android.util.Log;

import com.rhymes.client.mariobros.elements.Element;

public abstract class Interaction {
	protected ArrayList<InteractionCallbacks> elements;
	protected boolean condition = false;
	public void subscribe(InteractionCallbacks element)
	{
		if(elements != null)
			elements.add(element);
		else
		{
			elements = new ArrayList<InteractionCallbacks>();
			elements.add(element);
		}
		
//		Log.d("Interactoin", "Subsribed new element: " + element + ": " + element.hashCode() );
	}
	public void unSubscribe(InteractionCallbacks element)
	{
		if(elements != null)
			elements.remove(element);
//		Log.d("Interactoin", "Unsubsribed element: " + element + ": " + element.hashCode() );
	}
	public abstract void checkCondition(long elapsedTime);
	public abstract void takeAction();
}
