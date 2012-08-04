package com.thenewboston.terry;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class WorkingWithTabhost extends Activity implements OnClickListener {
	TabHost th;
	MediaPlayer mp;
	long startTime, stopTime;
	TextView tvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.tabs);
		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec specs = th.newTabSpec("stopWatch");
		specs.setContent(R.id.tab1);
		specs.setIndicator("StopWatch");
		th.addTab(specs);
		specs = th.newTabSpec("Tab2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);
		specs = th.newTabSpec("addTab");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a tab");
		th.addTab(specs);
		Button bAddTab = (Button) findViewById(R.id.bAddTab);
		bAddTab.setOnClickListener(this);
		Button bStart = (Button) findViewById(R.id.bStart);
		bStart.setOnClickListener(this);
		Button bStop = (Button) findViewById(R.id.bStop);
		bStop.setOnClickListener(this);
		tvResult = (TextView) findViewById(R.id.tvTime);
		mp = MediaPlayer.create(this, R.raw.explosion);
		startTime = 0;

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bAddTab:
			TabSpec ourSpecs = th.newTabSpec("Tag1");
			ourSpecs.setContent(new TabHost.TabContentFactory() {

				public View createTabContent(String tag) {
					TextView tabTextView = new TextView(WorkingWithTabhost.this);
					tabTextView.setText("New Tab");
					return tabTextView;
				}

			});
			ourSpecs.setIndicator("New Tab");
			th.addTab(ourSpecs);
			mp.start();
			break;
		case R.id.bStart:
			startTime = System.currentTimeMillis();
			break;
		case R.id.bStop:
			stopTime = System.currentTimeMillis();

			if (startTime != 0) {

				long result = stopTime - startTime;
				tvResult.setText(Long.toString(result));

			}

			break;
		}
	}
}
