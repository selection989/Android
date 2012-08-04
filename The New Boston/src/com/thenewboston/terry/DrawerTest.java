package com.thenewboston.terry;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class DrawerTest extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnDrawerOpenListener {
	Button handle1, handle2, handle3, handle4, handle;
	CheckBox cb1;
	SlidingDrawer sd;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.slidingdrawer);
		sd = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		handle = (Button) findViewById(R.id.handle);
		handle1 = (Button) findViewById(R.id.handle1);
		handle2 = (Button) findViewById(R.id.handle2);
		handle3 = (Button) findViewById(R.id.handle3);
		handle4 = (Button) findViewById(R.id.handle4);
		cb1 = (CheckBox) findViewById(R.id.cb1);
		handle.setOnClickListener(this);
		handle1.setOnClickListener(this);
		handle2.setOnClickListener(this);
		handle3.setOnClickListener(this);
		handle4.setOnClickListener(this);
		sd.setOnDrawerOpenListener(this);
		cb1.setOnCheckedChangeListener(this);
		mp = MediaPlayer.create(this, R.raw.explosion);

	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			sd.lock();
		} else {
			sd.unlock();
		}

	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.handle:
			break;
		case R.id.handle1:
			sd.open();
			break;
		case R.id.handle2:
			sd.close();
			break;
		case R.id.handle3:
			sd.toggle();
			break;
		case R.id.handle4:
			break;

		}
	}

	public void onDrawerOpened() {
		mp.start();
	}

}
