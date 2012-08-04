package com.thenewboston.terry;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarVolume extends Activity implements OnSeekBarChangeListener {
	SeekBar sbVolume;
	MediaPlayer mp;
	AudioManager am;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbar);
		sbVolume = (SeekBar) findViewById(R.id.sbVolume);
		mp = MediaPlayer.create(this, R.raw.explosion);
		mp.start();
		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int maxV = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int curV = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		sbVolume.setMax(maxV);
		sbVolume.setProgress(curV);
		sbVolume.setOnSeekBarChangeListener(this);
	}

	@Override
	protected void onPause() {

		super.onPause();
		mp.release();
	}

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
	}

}
