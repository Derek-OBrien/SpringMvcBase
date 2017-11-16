package com.gm.records.writer;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.gm.records.data.Records;

public class Writer {

	public void writeCsv(ArrayList<Records> data, File file) {

		PrintWriter writerAction = null;
		PrintWriter writerSpec = null;
		PrintWriter writer = null;


		String baseFilepath = file.getPath().replace("dat", "csv");
        String baseFileName = file.getName().replace("dat", "csv");

		File fileFull =
				new File(baseFilepath.substring(0, baseFilepath.lastIndexOf('\\'))+"full\\"+ "Full-"+baseFileName);
		new File(baseFilepath.substring(0, baseFilepath.lastIndexOf('\\'))+"full").mkdirs();

        File fileSpec =
                new File(baseFilepath.substring(0, baseFilepath.lastIndexOf('\\'))+"spec\\"+ "Spec-"+baseFileName);
		new File(baseFilepath.substring(0, baseFilepath.lastIndexOf('\\'))+"spec").mkdirs();


		File fileAction=
                new File(baseFilepath.substring(0, baseFilepath.lastIndexOf('\\'))+"action\\"+"Action-"+baseFileName);
		new File(baseFilepath.substring(0, baseFilepath.lastIndexOf('\\'))+"action").mkdirs();




		try {
			writer = new PrintWriter(fileFull.getPath(), "UTF-8");
            writerSpec = new PrintWriter(fileSpec.getPath(), "UTF-8");
            writerAction = new PrintWriter(fileAction.getPath(), "UTF-8");


            // Print spec records
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).getType() == 0) {
					writer.println(data.get(i).toString());
                    writerSpec.println(data.get(i).toString());
				}
			}

			// Print action records
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).getType() == 1) {
					writer.println(data.get(i).toString());
                    writerAction.println(data.get(i).toString());
				}
			}

		} catch (IOException e) {
			System.out.println("Error : " + e);
		} finally {
            writerSpec.close();
            writer.close();
            writerAction.close();
		}


	}
}
