package com.blabbbr.blabbbrandroid;

import android.media.MediaRecorder;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;

public class Recorder {

	private static final String TAG = Recorder.class.toString();
	public static final int BLAB_LENGTH_SECONDS = 6;

	public static int AUDIO_SOURCE = MediaRecorder.AudioSource.CAMCORDER;
	public static int AUDIO_OUTPUT_FORMAT = MediaRecorder.OutputFormat.THREE_GPP;
	public static int AUDIO_ENCODER = MediaRecorder.AudioEncoder.AMR_WB;
	public static float LATITUDE = 0;
	public static float LONGITUDE = 0;

	private MediaRecorder recorder;

	public Recorder(final String filePath) {
		super();
		setupAudio(filePath);
	}

	private void setupAudio(final String filePath) {
		final Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		final String fileLocation = filePath + "/"
				+ String.valueOf(today.toMillis(false)) + "_blab.3gp";

		recorder = new MediaRecorder();
		try {
			recorder.setAudioSource(AUDIO_SOURCE);
			recorder.setOutputFormat(AUDIO_OUTPUT_FORMAT);
			recorder.setAudioEncoder(AUDIO_ENCODER);
			recorder.setOutputFile(fileLocation);
			if (LATITUDE != 0 && LONGITUDE != 0) {
				recorder.setLocation(LATITUDE, LONGITUDE);
			}
			recorder.prepare();
			Log.d(TAG, "ready");
		} catch (Exception e) {
			Log.d(TAG, e.toString());
		}
	}

	private Boolean isRecording = false;

	Boolean getIsRecording() {
		return isRecording;
	}

	public void startRecording() {
		if (isRecording) {
			stopRecording();
		}
		recorder.start();
		isRecording = true;
		stopRecordingAfterInterval();
		if (onStartRecordingCallback != null) {
			onStartRecordingCallback.run();
		}
		Log.d(TAG, "recording");
	}

	public void stopRecording() {
		recorder.stop();
		recorder.reset();
		recorder.release();
		isRecording = false;
		if (handler != null) {
			handler.removeCallbacks(stopRecordingRunnable);
		}
		if (onStopRecordingCallback != null) {
			onStopRecordingCallback.run();
		}
		Log.d(TAG, "stopped");
	}

	public void toggleRecording() {
		if (getIsRecording()) {
			stopRecording();
		} else {
			startRecording();
		}
	}

	private Runnable onStopRecordingCallback;

	public void setOnStopRecordingCallback(final Runnable runnable) {
		this.onStopRecordingCallback = runnable;
	}

	private Runnable onStartRecordingCallback;

	public void setOnStartRecordingCallback(final Runnable runnable) {
		this.onStartRecordingCallback = runnable;
	}

	private Handler handler;
	private final Runnable stopRecordingRunnable = new Runnable() {
		@Override
		public void run() {
			stopRecording();
		}
	};

	private void stopRecordingAfterInterval() {
		handler = new Handler();
		handler.postDelayed(stopRecordingRunnable, (BLAB_LENGTH_SECONDS * 1000));
	}
}
