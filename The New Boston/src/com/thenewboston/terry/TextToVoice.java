package com.thenewboston.terry;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TextToVoice extends Activity implements OnClickListener {

	static final String[] texts = { "Whatsup!!", "You Smell!"

	};

	TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.texttospeach);
		Button bTextToSpeach = (Button) findViewById(R.id.bTextToVoice);
		bTextToSpeach.setOnClickListener(this);
		tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

			public void onInit(int status) {
				if (status != TextToSpeech.ERROR) {
					tts.setLanguage(Locale.US);

				}
			}
		});

	}

	public void onClick(View v) {
		Random r = new Random();
		String random = texts[r.nextInt(2)];
		tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);

	}

	@Override
	protected void onPause() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();

		}

		super.onPause();
	}

	@Override
	protected void onResume() {

		super.onResume();
	}

}
