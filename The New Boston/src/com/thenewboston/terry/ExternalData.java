package com.thenewboston.terry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener,
		OnClickListener {
	boolean canR, canW;
	Spinner spinner;
	String[] folders = { "Pictures", "Downloads", "Music" };
	File folderPath;
	File fullPath;
	Button bSaveFile, bConfirm;
	EditText etFileName;
	TextView tvCanRead, tvCanWrite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		tvCanRead = (TextView) findViewById(R.id.tvCanRead);
		tvCanWrite = (TextView) findViewById(R.id.tvCanWrite);
		getReadWritePermission();
		ArrayAdapter adapter = new ArrayAdapter<String>(ExternalData.this,
				android.R.layout.simple_spinner_item, folders);

		spinner = (Spinner) findViewById(R.id.spFolder);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		bSaveFile = (Button) findViewById(R.id.bSave);
		bConfirm = (Button) findViewById(R.id.bConfirm);
		bSaveFile.setOnClickListener(this);
		bConfirm.setOnClickListener(this);
		etFileName = (EditText) findViewById(R.id.etFileName);
	}

	private void getReadWritePermission() {
		String sdCardState = Environment.getExternalStorageState();
		if (sdCardState.equals(Environment.MEDIA_MOUNTED)) {
			tvCanRead.setText("Can Read");
			tvCanWrite.setText("Can Write");
			canW = true;
			canR = true;
		} else if (sdCardState.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			tvCanRead.setText("Can Read");
			tvCanWrite.setText("Cant Write");
			canR = true;
			canW = false;
		} else {
			tvCanRead.setText("Cant Read");
			tvCanWrite.setText("Cant Write");
			canR = false;
			canW = false;
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

		switch (arg2) {
		case 1:
			folderPath = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		case 0:
			folderPath = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			folderPath = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		}
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSave:
			Toast dialog;
			String fileName = etFileName.getText().toString();
			fullPath = new File(folderPath, fileName + ".png");
			folderPath.mkdirs();
			InputStream fis = getResources().openRawResource(R.drawable.splash);
			if (canR && canW) {
				try {

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(etFileName.getWindowToken(), 0);
					OutputStream fos = new FileOutputStream(fullPath);
					byte[] byteArray = new byte[fis.available()];
					fis.read(byteArray);
					fos.write(byteArray);
					fis.close();
					fos.close();
					dialog = Toast.makeText(ExternalData.this,
							"File successfully saved", Toast.LENGTH_LONG);

				} catch (FileNotFoundException e) {
					dialog = Toast.makeText(ExternalData.this,
							"FileNotFoundException", Toast.LENGTH_LONG);
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					dialog = Toast.makeText(ExternalData.this, "IOException",
							Toast.LENGTH_LONG);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog.show();
				MediaScannerConnection.scanFile(ExternalData.this,
						new String[] { fullPath.toString() }, null,
						new MediaScannerConnection.OnScanCompletedListener() {

							public void onScanCompleted(String path, Uri uri) {
							}
						});

			}

			break;
		case R.id.bConfirm:
			bSaveFile.setVisibility(Button.VISIBLE);
			break;

		}
	}
}
