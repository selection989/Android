package com.thenewboston.terry;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FunWithText extends Activity implements View.OnClickListener {
	EditText etCommand;
	TextView result;
	ToggleButton tbSwitch;
	Button bSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		init();
		tbSwitch.setOnClickListener(this);
		bSubmit.setOnClickListener(this);

	}

	public void init() {
		bSubmit = (Button) findViewById(R.id.bSubmit);
		etCommand = (EditText) findViewById(R.id.etCommand);
		result = (TextView) findViewById(R.id.tvResult);
		tbSwitch = (ToggleButton) findViewById(R.id.tbSwitch);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.bSubmit):
			String command = etCommand.getText().toString();
			result.setText(command);
			if (command.contentEquals("left")) {
				result.setGravity(Gravity.LEFT);
			} else if (command.contentEquals("right")) {
				result.setGravity(Gravity.RIGHT);
			} else if (command.contentEquals("center")) {
				result.setGravity(Gravity.CENTER);
			} else if (command.contentEquals("blue")) {
				result.setTextColor(Color.BLUE);
			} else if (command.contentEquals("WTF")) {
				Random number = new Random();
				result.setTextSize(number.nextInt(75));
				switch (number.nextInt(3)) {
				case 0:
					result.setGravity(Gravity.LEFT);
					break;
				case 1:
					result.setGravity(Gravity.RIGHT);
					break;
				case 2:
					result.setGravity(Gravity.CENTER);
					break;

				}

				result.setTextColor(Color.rgb(number.nextInt(256),
						number.nextInt(256), number.nextInt(256)));
			} else {
				result.setText("Invalid command");
				result.setTextColor(Color.WHITE);
				result.setGravity(Gravity.CENTER);
			}

			break;
		case (R.id.tbSwitch):
			boolean switchValue = tbSwitch.isChecked();
			if (switchValue) {
				etCommand.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				etCommand.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;

		}
	}

}
