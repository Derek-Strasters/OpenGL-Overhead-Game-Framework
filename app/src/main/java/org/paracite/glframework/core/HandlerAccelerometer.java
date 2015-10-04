package org.paracite.glframework.core;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class HandlerAccelerometer implements SensorEventListener {
	float accelX;
	float accelY;
	float accelZ;

	public HandlerAccelerometer(Context context) {
		SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

		if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
		}
	}

	public void onAccuracyChanged(Sensor mSensor, int accuracy) {

	}

	public void onSensorChanged(SensorEvent event) {
		accelX = event.values[0];
		accelY = event.values[1];
		accelZ = event.values[2];
	}

	public float getAccelX() {
		return accelX;
	}

	public float getAccelY() {
		return accelY;
	}

	public float getAccelZ() {
		return accelZ;
	}
}
