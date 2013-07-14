package com.blabbbr.blabbbrandroid;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.blabbr.blabbrandroid.R;

public class LoadBlabList extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blab_list_view);
		final String blabbbrAppUrl = getString(R.string.blabbbr_app_url); 
		getWebView().loadUrl(blabbbrAppUrl);
	}
	
	private WebView getWebView() {
		return (WebView)findViewById(R.id.webView1);
	}
}
