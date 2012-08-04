package com.thenewboston.terry;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteSubmit extends Activity implements OnClickListener {

	EditText etName, etHotness, etRowId;
	Button bSubmit, bView, bGetInfo, bEdit, bDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqllitepost);
		initialize();

	}

	private void initialize() {
		bDelete = (Button) findViewById(R.id.bDelete);
		bSubmit = (Button) findViewById(R.id.bSubmit);
		bView = (Button) findViewById(R.id.bView);
		etName = (EditText) findViewById(R.id.etName);
		etHotness = (EditText) findViewById(R.id.etHotness);
		bSubmit.setOnClickListener(this);
		bView.setOnClickListener(this);
		bGetInfo = (Button) findViewById(R.id.bGetInfo);
		bEdit = (Button) findViewById(R.id.bEdit);
		bSubmit = (Button) findViewById(R.id.bSubmit);
		etRowId = (EditText) findViewById(R.id.etRowId);
		bGetInfo.setOnClickListener(this);
		bEdit.setOnClickListener(this);
		bDelete.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSubmit:
			boolean isWorking = true;
			String name = etName.getText().toString();
			String hotness = etHotness.getText().toString();
			try {
				HotOrNot entry = new HotOrNot(this);
				entry.open();
				System.out.println(entry.getInfo());
				entry.createEntry(name, hotness);

				entry.close();

			} catch (Exception e) {
				isWorking = false;
				e.printStackTrace();
			} finally {
				if (isWorking) {
					Dialog d = new Dialog(this);
					TextView tv = new TextView(this);
					tv.setText("Database write sucessful");
					d.setTitle("Success!!");
					d.setContentView(tv);
					d.show();

				}

			}

			break;
		case R.id.bView:
			Intent myIntent = new Intent("com.thenewboston.terry.SQLITEVIEW");
			startActivity(myIntent);
			break;
		case R.id.bGetInfo:
			String rowId = etRowId.getText().toString();
			long l = Long.parseLong(rowId);
			HotOrNot data = new HotOrNot(this);
			data.open();
			name = data.getName(l);
			hotness = data.getHotness(l);
			data.close();
			etName.setText(name);
			etHotness.setText(hotness);
			break;
		case R.id.bEdit:
			String mRowId = etRowId.getText().toString();
			long mlRow = Long.parseLong(mRowId);
			String mName = etName.getText().toString();
			String mHotNess = etHotness.getText().toString();
			HotOrNot modifiedData = new HotOrNot(this);
			modifiedData.open();
			modifiedData.modifyEntry(mlRow, mName, mHotNess);
			modifiedData.close();
			break;
		case R.id.bDelete:
			mRowId = etRowId.getText().toString();
			mlRow = Long.parseLong(mRowId);
			HotOrNot deleteData = new HotOrNot(this);
			deleteData.open();
			deleteData.deleteEntry(mlRow);
			deleteData.close();
			break;
		}
	}
}
