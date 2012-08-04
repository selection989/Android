package com.thenewboston.terry;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedData extends Activity implements OnClickListener {
	final String fileName = "SharedStuff";
	Button bLoad, bSave;
	EditText etSharedData;
	TextView tvResult;
	SharedPreferences sharedData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shareddata);
		setUpVars();

	}

	private void setUpVars() {
		bLoad = (Button) findViewById(R.id.bLoad);
		bSave = (Button) findViewById(R.id.bSave);
		etSharedData = (EditText) findViewById(R.id.etStringData);
		tvResult = (TextView) findViewById(R.id.tvResult);
		bLoad.setOnClickListener(this);
		bSave.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bLoad:
			sharedData = getSharedPreferences(fileName, MODE_PRIVATE);
			String data = sharedData.getString("sharedString",
					"Data unavailable");
			tvResult.setText(data);
			break;
		case R.id.bSave:
			sharedData = getSharedPreferences(fileName, MODE_PRIVATE);
			String dataToSave = etSharedData.getText().toString();
			SharedPreferences.Editor editor = sharedData.edit();
			editor.putString("sharedString", dataToSave);
			editor.commit();
			break;

		}

	}

}
