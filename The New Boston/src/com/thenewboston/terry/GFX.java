package com.thenewboston.terry;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {
	MyBringBack view;
	WakeLock WLock;
	PowerManager pM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		WLock = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Terry");
		super.onCreate(savedInstanceState);
		view = new MyBringBack(this);
		setContentView(view);

	}

	@Override
	protected void onPause() {

		super.onPause();
		WLock.release();
	}

	@Override
	protected void onResume() {

		super.onResume();
		WLock.acquire();
	}

}
