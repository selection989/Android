package com.thenewboston.terry;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpExample extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexample);
		TextView tvResults = (TextView) findViewById(R.id.tvResult);
		HttpGetExample test = new HttpGetExample();
		try {
			tvResults.setText(test.executeRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
