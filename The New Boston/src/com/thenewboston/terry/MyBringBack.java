package com.thenewboston.terry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View {
	Bitmap bball;
	float y;
	Typeface myFont;

	public MyBringBack(Context context) {
		super(context);
		bball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		y = 0;
		myFont = Typeface.createFromAsset(context.getAssets(), "gunit.TTF");

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		canvas.drawColor(Color.RED);
		Paint textPaint = new Paint();
		textPaint.setColor(Color.GREEN);
		textPaint.setTypeface(myFont);
		textPaint.setTextSize(20);

		canvas.drawText("dude", canvas.getWidth() / 2, canvas.getHeight() / 2,
				textPaint);

		canvas.drawBitmap(bball,
				(canvas.getWidth() / 2 - bball.getWidth() / 2), y, null);
		Paint myPaint = new Paint();
		myPaint.setColor(Color.BLUE);
		canvas.drawRect(200, 400, 400, 800, myPaint);

		if (y < canvas.getHeight()) {
			y += 20;
		} else {
			y = 0;
		}
		invalidate();

	}

}
