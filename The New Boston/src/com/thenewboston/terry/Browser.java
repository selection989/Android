package com.thenewboston.terry;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Browser extends Activity implements OnClickListener {
	Button bLoad;
	Button bForward;
	Button bBack;
	Button bRefresh;
	EditText etURL;
	WebView myWebView;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.simplebrowser);
		myWebView = (WebView) findViewById(R.id.wvMyWebView);
		myWebView.setWebViewClient(new myWebViewClient());
		myWebView.loadUrl("http://www.cnn.com");
		bLoad = (Button) findViewById(R.id.bLoad);
		bForward = (Button) findViewById(R.id.bForward);
		bBack = (Button) findViewById(R.id.bBack);
		bRefresh = (Button) findViewById(R.id.bRefresh);
		etURL = (EditText) findViewById(R.id.etURL);
		bLoad.setOnClickListener(this);
		bForward.setOnClickListener(this);
		bBack.setOnClickListener(this);
		bRefresh.setOnClickListener(this);
		mp = MediaPlayer.create(this, R.raw.explosion);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bLoad:
			String myURL = etURL.getText().toString();
			myWebView.loadUrl(myURL);
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(etURL.getWindowToken(), 0);

			break;
		case R.id.bBack:
			if (myWebView.canGoBack()) {
				myWebView.goBack();
			}
			break;
		case R.id.bRefresh:
			myWebView.reload();
			break;
		case R.id.bForward:
			if (myWebView.canGoForward()) {
				myWebView.goForward();
			}
			break;

		}

	}

}
