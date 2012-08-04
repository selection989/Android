package com.thenewboston.terry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityStarter extends Activity implements OnClickListener {
	public Button bSA, bSAFR;
	public EditText etMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitystarter);
		initialize();

	}

	private void initialize() {
		bSA = (Button) findViewById(R.id.bSA);
		bSAFR = (Button) findViewById(R.id.bSAFR);
		etMessage = (EditText) findViewById(R.id.etMessage);
		bSA.setOnClickListener(this);
		bSAFR.setOnClickListener(this);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.bSA):
			String message = etMessage.getText().toString();
			Bundle myBundle = new Bundle();
			myBundle.putString("message", message);
			Intent messageIntent = new Intent(ActivityStarter.this,
					StartedActivity.class);
			messageIntent.putExtras(myBundle);
			startActivity(messageIntent);
			break;
		case (R.id.bSAFR):
			Intent responseIntent = new Intent(ActivityStarter.this,
					StartedActivity.class);
			startActivityForResult(responseIntent, 0);
			break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			etMessage.setText(data.getExtras().getString("response"));
		}
	}

}
