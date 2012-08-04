package com.thenewboston.terry;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class FunWithSoundPool extends Activity implements OnClickListener,
		OnLongClickListener {
	int explosion = 0;
	SoundPool sp;
	boolean loaded = false;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(this, R.raw.collision, 1);
		sp.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});
		View v = new View(this);
		mp = MediaPlayer.create(this, R.raw.explosion);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);

	}

	public void onClick(View v) {
		if (loaded) {
			sp.play(explosion, 1, 1, 0, 0, 1);
		}
	}

	public boolean onLongClick(View v) {
		mp.start();

		return false;
	}
}
