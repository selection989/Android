package com.thenewboston.terry;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Dialogue extends Activity {
	TextView tvMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogue);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		tvMessage = (TextView) findViewById(R.id.tvDialogue);
		String text = prefs.getString("name", "Terry");
		String value = prefs.getString("lvalues", "4");
		if (value == "4") {
			tvMessage.setText(text);
		}
	}

}
