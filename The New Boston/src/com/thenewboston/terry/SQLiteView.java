package com.thenewboston.terry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLiteView extends Activity {
	TextView tvResults;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlliteview);
		tvResults = (TextView) findViewById(R.id.tvResults);
		HotOrNot data = new HotOrNot(this);
		data.open();
		tvResults.setText(data.getInfo());
		data.close();

	}

}
