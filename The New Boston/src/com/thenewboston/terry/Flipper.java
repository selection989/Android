package com.thenewboston.terry;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener {
	ViewFlipper vfFlipper;
	TextView tv1;
	TextView tv2;
	TextView tv3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper);
		vfFlipper = (ViewFlipper) findViewById(R.id.vfFlipper);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv3 = (TextView) findViewById(R.id.textView3);
		vfFlipper.setFlipInterval(500);
		vfFlipper.startFlipping();
		vfFlipper.setOnClickListener(this);

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		vfFlipper.showNext();

	}
}
