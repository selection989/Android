package com.thenewboston.terry;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accellerate extends Activity implements SensorEventListener {
	float x, y, sensorX, sensorY;
	Bitmap ball;
	SensorManager sm;

	public class MyBringBackSurface extends SurfaceView implements Runnable {
		SurfaceHolder holder;
		Thread myThread = null;
		boolean isRunning = false;

		public MyBringBackSurface(Context context) {
			super(context);
			holder = getHolder();

		}

		public void resume() {
			myThread = new Thread(this);
			myThread.start();
			isRunning = true;

		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					myThread.join();

				} catch (InterruptedException e) {

				}

				break;
			}
			myThread = null;

		}

		public void run() {
			while (isRunning) {
				if (!holder.getSurface().isValid())
					continue;
				Canvas canvas = holder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				float centerX = canvas.getWidth() / 2;
				float centerY = canvas.getHeight() / 2;
				canvas.drawBitmap(ball, centerX + sensorX * 65, centerY
						+ sensorY * 65, null);
				holder.unlockCanvasAndPost(canvas);

			}

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyBringBackSurface mySurface = new MyBringBackSurface(this);
		mySurface.resume();
		setContentView(mySurface);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);

		}
		x = y = sensorX = sensorY = 0;
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensorX = event.values[0];
		sensorY = event.values[1];

	}

	@Override
	protected void onPause() {

		super.onPause();
		sm.unregisterListener(this);
	}

}
