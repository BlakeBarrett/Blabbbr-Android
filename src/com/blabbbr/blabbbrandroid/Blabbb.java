package com.blabbbr.blabbbrandroid;

import java.io.File;

import android.location.Location;

public class Blabbb {

	private final File file;
	private final Location loc;
	private final String description;

	public Blabbb(final Recorder rec, final String desc) {
		this.loc = Recorder.LOCATION;
		this.description = desc;
		final String fileLocation = rec.getFileLocation();
		file = new File(fileLocation);
	}

	@Override
	public String toString() {
		final StringBuilder resultant = new StringBuilder();
		resultant.append("File: ");
		resultant.append(file.getPath());
		resultant.append(" ");
		resultant.append("Latitude: ");
		resultant.append(String.valueOf(loc.getLatitude()));
		resultant.append(" ");
		resultant.append("Longitude: ");
		resultant.append(String.valueOf(loc.getLongitude()));
		resultant.append(" ");
		resultant.append("Description: ");
		resultant.append(description);

		return resultant.toString();
	}
}
