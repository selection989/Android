package com.thenewboston.terry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class StartedActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener {
	RadioGroup rgResponse;
	Button bSend;
	TextView tvMessage;
	String response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.started_activity);
		initialize();

	}

	private void initialize() {
		rgResponse = (RadioGroup) findViewById(R.id.rgResponse);
		bSend = (Button) findViewById(R.id.bSend);
		tvMessage = (TextView) findViewById(R.id.tvMessageReceived);
		try {
			Bundle messageBundle = getIntent().getExtras();
			String message = messageBundle.getString("message");
			tvMessage.setText(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		bSend.setOnClickListener(this);
		rgResponse.setOnCheckedChangeListener(this);

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
		case (R.id.rbAwesome):
			response = "dude";
			break;
		case (R.id.rbNotCool):
			response = "awww";
			break;
		case (R.id.rbOk):
			response = "aight";
			break;

		}
	}

	public void onClick(View v) {
		Bundle responseBundle = new Bundle();
		responseBundle.putString("response", response);
		Intent carrier = new Intent();
		carrier.putExtras(responseBundle);
		setResult(RESULT_OK, carrier);
		finish();

	}

}
