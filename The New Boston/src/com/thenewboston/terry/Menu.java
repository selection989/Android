package com.thenewboston.terry;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
	String[] list = { "StartView", "FunWithText", "Email", "Camera",
			"ActivityStarter", "GFX", "GFXSurface", "FunWithSoundPool",
			"DrawerTest", "WorkingWithTabhost", "Browser", "Flipper",
			"SharedData", "SharedDataUsingFOSAndFIS",
			"SharedDataUsingFOSAndFISNoThreading", "ExternalData",
			"SQLiteSubmit", "Accellerate", "Maps", "HttpExample",
			"HttpJSONExample", "WeatherXmlExample", "GLExample", "Voice",
			"TextToVoice", "StatusBar", "SeekBarVolume" };

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		try {
			Class myClass = Class.forName("com.thenewboston.terry."
					+ list[position]);
			Intent myIntent = new Intent(Menu.this, myClass);
			startActivity(myIntent);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(Menu.this,
				android.R.layout.simple_list_item_1, list));

	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {

		super.onCreateOptionsMenu(menu);
		MenuInflater balloon = getMenuInflater();
		balloon.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case (R.id.about):
			Intent myIntent = new Intent(Menu.this, Dialogue.class);
			startActivity(myIntent);
			break;

		case (R.id.preferences):
			Intent i = new Intent("com.thenewboston.terry.PREFS");
			startActivity(i);
		case (R.id.exit):
			finish();
			break;

		}
		return (false);
	}

}
