package com.blabbbr.blabbbrandroid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class BlabActivity extends Activity {

	static final String TAG = BlabActivity.class.toString();
	private static Recorder recorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blab);
		final Button recordButton = (Button) findViewById(R.id.button1);
		recordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				recorder.toggleRecording();
			}
		});

		final String filePath = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_RINGTONES).getAbsolutePath();
		Log.d(TAG, filePath);
		recorder = new Recorder(filePath);

		recorder.setOnStartRecordingCallback(new Runnable() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						recordButton.setBackgroundColor(Color.RED);
					}
				});
			}
		});
		recorder.setOnStopRecordingCallback(new Runnable() {

			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						recordButton.setBackgroundColor(Color.GRAY);
					}
				});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blab, menu);
		return true;
	}

}
