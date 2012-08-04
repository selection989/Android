package com.thenewboston.terry;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	MyBringBackSurface surface;
	float x = 0;
	float y = 0;
	float sx, sy, fx, fy, scaledX, scaledY, dX, dY, aniX, aniY;
	public Bitmap bball, rball;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		surface = new MyBringBackSurface(this);
		surface.setOnTouchListener(this);
		setContentView(surface);
		sx = 0;
		sy = 0;
		fx = 0;
		fy = 0;
		dX = dY = scaledX = scaledY = aniX = aniY = 0;
		bball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		rball = BitmapFactory.decodeResource(getResources(), R.drawable.rball);
	}

	@Override
	protected void onPause() {

		super.onPause();
		surface.pause();
	}

	@Override
	protected void onResume() {

		super.onResume();
		surface.resume();
	}

	public boolean onTouch(View v, MotionEvent event) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = event.getX();
		y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sx = event.getX();
			sy = event.getY();
			dX = dY = scaledX = scaledY = aniX = aniY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fx = event.getX();
			fy = event.getY();
			dX = fx - sx;
			dY = fy - sy;
			scaledX = dX / 30;
			scaledY = dY / 30;
			x = y = 0;
			break;
		}
		return true;
	}

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

				if (x != 0 && y != 0) {

					canvas.drawBitmap(bball, x - bball.getWidth() / 2, y
							- bball.getWidth() / 2, null);
				}

				if (sx != 0 && sy != 0) {
					canvas.drawBitmap(rball, sx - rball.getWidth() / 2, sy
							- rball.getWidth() / 2, null);
				}

				if (fx != 0 && fy != 0) {
					canvas.drawBitmap(bball, fx - bball.getWidth() / 2 - aniX,
							fy - bball.getWidth() / 2 - aniY, null);
					canvas.drawBitmap(rball, fx - rball.getWidth() / 2, fy
							- rball.getWidth() / 2, null);
				}
				aniX = aniX + scaledX;
				aniY = aniY + scaledY;

				holder.unlockCanvasAndPost(canvas);

			}

		}
	}

}