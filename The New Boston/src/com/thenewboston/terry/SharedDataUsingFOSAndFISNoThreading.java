package com.thenewboston.terry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedDataUsingFOSAndFISNoThreading extends Activity implements
		OnClickListener {
	final String FILE_NAME = "SharedStuff";
	Button bLoad, bSave;
	EditText etSharedData;
	TextView tvResult;
	FileOutputStream fos;

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
			String collected = null;
			FileInputStream fis = null;
			try {
				fis = openFileInput(FILE_NAME);
				byte[] data = new byte[fis.available()];
				while (fis.read(data) != -1) {
					collected = new String(data);

				}

			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IOException");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println(collected);
			tvResult.setText(collected);

			break;
		case R.id.bSave:
			String dataToSave = etSharedData.getText().toString();
			try {
				fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
				fos.write(dataToSave.getBytes());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			break;

		}

	}

}
