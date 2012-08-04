package com.thenewboston.terry;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Voice extends Activity implements OnClickListener {

	ListView lv;
	Button bVoice;
	static final int check = 1111;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice);
		lv = (ListView) findViewById(R.id.lvVoiceReturn);
		bVoice = (Button) findViewById(R.id.bVoice);
		bVoice.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == check && resultCode == RESULT_OK) {
			ArrayList<String> results = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			lv.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, results));

		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	public void onClick(View v) {
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak up son");
		startActivityForResult(i, check);
	}
}
