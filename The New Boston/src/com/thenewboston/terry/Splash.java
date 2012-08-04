package com.thenewboston.terry;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {
	MediaPlayer ourSong;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_view);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		if (prefs.getBoolean("music", true)) {
			ourSong = MediaPlayer.create(Splash.this, R.raw.splash);
			ourSong.start();
		}

		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {

					startActivity(new Intent("com.thenewboston.terry.MENU"));
				}

			}

		};

		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		ourSong.release();
		finish();
	}

}
