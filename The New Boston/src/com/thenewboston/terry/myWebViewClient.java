package com.thenewboston.terry;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class myWebViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);

		return true;
	}

}
