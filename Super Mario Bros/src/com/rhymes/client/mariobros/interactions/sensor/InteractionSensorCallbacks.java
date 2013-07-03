package com.rhymes.client.mariobros.interactions.sensor;

import com.rhymes.client.mariobros.interactions.InteractionCallbacks;

public interface InteractionSensorCallbacks extends InteractionCallbacks{
	public void sensor(float x, float y, int z);
}
