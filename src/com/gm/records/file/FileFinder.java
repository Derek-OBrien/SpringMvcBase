package com.gm.records.file;

import java.io.File;
import java.io.FilenameFilter;

public class FileFinder {

	private static File[] files;
	private static File dir;

	public File[] findFiles(String s) {

		// Get folder
		File dir = new File(s);

		// Create output folder
		new File(dir.getPath().replace("dat", "csv")).mkdirs();

		// Get Files in folder
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".dat");
			}
		});

		return files;
	}

}
