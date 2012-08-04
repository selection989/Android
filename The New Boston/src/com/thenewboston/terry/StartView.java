package com.thenewboston.terry;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartView extends Activity {
	Button add, sub;
	TextView display;
	static int counter = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_view);
		display = (TextView) findViewById(R.id.tvDisplay);
		add = (Button) findViewById(R.id.bAdd);
		sub = (Button) findViewById(R.id.bSubtract);
		display.setText("The total is:" + counter);
		add.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				counter++;
				display.setText("The total is:" + counter);
			}
		});

		sub.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				counter--;
				display.setText("The total is:" + counter);
			}
		});
	}
}
