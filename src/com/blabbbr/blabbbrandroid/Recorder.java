package com.blabbbr.blabbbrandroid;

import android.media.MediaRecorder;
import android.text.format.Time;
import android.util.Log;

public class Recorder {
	
	public Recorder() {
		super();
		setupAudio();
	}
	
    MediaRecorder recorder;
    private void setupAudio() {
    	Time today = new Time(Time.getCurrentTimezone());
    	today.setToNow();
    	String fileLocation = "" + today.toString();
    	
    	// W00T!!!
    	recorder = new MediaRecorder();
    	try {
    		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    		recorder.setOutputFile(fileLocation);
    		recorder.prepare();
    	} catch (Exception e) {
    		Log.d(Recorder.class.toString(), e.toString());
    		// don't care
    	}
    }
    
    private Boolean isRecording = false;
    Boolean getIsRecording() {
    	return isRecording;
    }
    
    void startRecording() {
    	if (isRecording) {
    		stopRecording();
    	}
    	recorder.start();
    	isRecording = true;
    }
    
    void stopRecording() {
    	recorder.stop();
    	recorder.release();
    	isRecording = false;
    }
}
