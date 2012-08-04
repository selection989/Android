package com.thenewboston.terry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Email extends Activity implements OnClickListener {
	Button bSend;
	TextView tvName, tvAddress, tvDateOfBirth, tvShoeColor, tvNoOfSiblings,
			tvDateOfAvailability;
	EditText etName, etAddress, etDateOfBirth, etShoeColor, etNoOfSiblings,
			etDateOfAvailability;
	String name, address, dateOfBirth, shoeColor, noOfSiblings, email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		InitializeGuis();
	}

	private void InitializeGuis() {
		tvName = (TextView) findViewById(R.id.tvName);
		tvAddress = (TextView) findViewById(R.id.tVAddress);
		tvDateOfBirth = (TextView) findViewById(R.id.tvDateOfBirth);
		tvNoOfSiblings = (TextView) findViewById(R.id.tvNoOfSiblings);
		tvDateOfAvailability = (TextView) findViewById(R.id.tvDateOfAvailability);
		tvShoeColor = (TextView) findViewById(R.id.tVShoeColor);
		etName = (EditText) findViewById(R.id.etName);
		etAddress = (EditText) findViewById(R.id.etAddress);
		etDateOfBirth = (EditText) findViewById(R.id.etDateOfBirth);
		etNoOfSiblings = (EditText) findViewById(R.id.etNoOfSlings);
		etDateOfAvailability = (EditText) findViewById(R.id.etDateOfAvailability);
		etShoeColor = (EditText) findViewById(R.id.etShoeColor);
		bSend = (Button) findViewById(R.id.bSend);
		bSend.setOnClickListener(this);

	}

	private void getTextFieldValues() {
		name = etName.getText().toString();
		address = etAddress.getText().toString();
		dateOfBirth = etDateOfBirth.getText().toString();
		noOfSiblings = etNoOfSiblings.getText().toString();
		shoeColor = etShoeColor.getText().toString();
		email = etDateOfAvailability.getText().toString();

	}

	public void onClick(View v) {
		getTextFieldValues();
		String emailArray[] = { email };
		Intent myIntent = new Intent(android.content.Intent.ACTION_SEND);
		myIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailArray);
		myIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shoeColor);
		myIntent.setType("plain/text");
		myIntent.putExtra(android.content.Intent.EXTRA_TEXT, (dateOfBirth
				+ noOfSiblings + name));
		startActivity(myIntent);
	}

	public void onPause() {
		super.onPause();
		finish();

	}
}
