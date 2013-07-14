package com.blabbbr.blabbbrandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class BlabActivity extends Activity {

	static final String TAG = "BlabActivity";
	private static Recorder recorder;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blab);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if (recorder.getIsRecording()) {
            		recorder.stopRecording();
            	} else {
            		recorder.startRecording();
            	}
            }
        });
        recorder = new Recorder();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blab, menu);
        return true;
    }
    

}
