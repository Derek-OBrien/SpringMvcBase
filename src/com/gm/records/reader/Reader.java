/**
 * 
 */
package com.gm.records.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gm.records.file.FileFinder;
import com.gm.records.data.ActionRecord;
import com.gm.records.data.Records;
import com.gm.records.data.SpecRecord;
import com.gm.records.writer.Writer;

/**
 * @author FZHZJP
 *
 */
public class Reader {

	// index of line to start read on to skip headers
	private static final int STARTLINE = 6;

	// ArrayList to hold data read in
	private static ArrayList<Records> records = new ArrayList<Records>();

	/*
	 * Run the Application
	 * @param: String s : path to folder containing files
	 */
	public void run(String s){
		File[] files = new FileFinder().findFiles(s);

		for(File f : files){
			read(f);
		}
	}
	/*
         * Run the Application
         * @param: List<File> list : list containing the files to read
         */
	public void run(List<File> list){
		for(File f : list) {
			read(f);
		}
	}
	
	/*
	 * Read in file
	 * 
	 * @param : File file : file to read
	 * 
	 */
	private void read(File file) {

		BufferedReader buffer = null;
		Scanner scanner = null;

		String temp = null;
		String data = null;
		int count = 0;

		try {
			buffer = new BufferedReader(new FileReader(file));

			while (buffer.readLine() != null) {
				scanner = new Scanner(buffer);

				// Skip header lines
				skipLines(scanner, STARTLINE);

				while (scanner.hasNext()) {

					// Skip first read after adding Action Record
					// First line of record all ready in temp variable
					if (count == 0) {
						temp = scanner.nextLine();
					}

					if (isFileSeparator(temp)) {
						continue;
					}

					else if (isSpecStart(temp)) {

						// Skip commented Spec Records
						if (isCommented(temp)) {
							continue;
						}

						data = temp;

						while (scanner.hasNextLine()) {
							temp = scanner.nextLine();

							if (isFileSeparator(temp)) {
								break;
							}
							if (isCommented(temp)) {
								isAction(temp);
							}

							if (!(isSpecStart(temp))) {
								data = data + temp;
								count = 0;
								continue;
							}

							if (isSpecStart(temp)) {
								count = 1;
								break;
							}

						}
						addSpecRecord(data);
					} else {
						isAction(temp);
					}

				}
			}
			buffer.close();
		} catch (Exception e) {
			System.out.println("Error :" + e);
		} finally {
			// write data to file
			printRecords(file);
			scanner.close();

		}
	}

	/*
	 * Start reading on specific line. Used to skip file headers
	 * 
	 * @param : Scanner s, scanner for reading
	 * 
	 * @param : Int lineNum, file line number
	 * 
	 * @return: boolean
	 */
	private static void skipLines(Scanner s, int lineNum) {
		for (int i = 0; i < lineNum; i++) {
			if (s.hasNextLine())
				s.nextLine();
		}
	}

	/*
	 * Is this the start of a Spec Record
	 * 
	 * @param : Sting s string to check
	 * 
	 * @return: boolean
	 */
	private static boolean isSpecStart(String s) {
		boolean flag = false;

		if (s.substring(1, 2).matches("\\w")) {
			flag = true;
		}
		return flag;
	}

	/*
	 * Is line a Action Record
	 * 
	 * @param : Sting s string to check
	 * 
	 * @return: boolean
	 */
	private static void isAction(String s) {
		String check = "ORDER";
		String action = null;

		if (isCommented(s)) {
			// is line a Action record. If so read
			if (s.regionMatches(true, 20, check, 0, check.length())) {
				action = " ";
				addActionRecord(s, action);
			} else {
				// Ignore the line
			}
		} else {
			// is line action record
			if (s.regionMatches(true, 20, check, 0, check.length())) {
				action = "Y";
				addActionRecord(s, action);
			}
		}
	}

	/*
	 * Dose the line start with a comment "*"
	 * 
	 * @param : Sting s string to check
	 * 
	 * @return: boolean
	 */
	private static boolean isCommented(String s) {
		boolean flag = false;

		if (s.startsWith("*", 0)) {
			flag = true;
		}

		return flag;
	}

	/*
	 * Is the line a file separator comment
	 * 
	 * @param : Sting s string to check
	 * 
	 * @return: boolean
	 */
	private static boolean isFileSeparator(String s) {
		boolean flag = false;
		if (s.startsWith("**", 1)) {
			// Skip as line is a comment separator line
			flag = true;
		}
		return flag;
	}

	/*
	 * Set and add Action Record to ArrayList
	 * 
	 * @param : Sting data: data to parse
	 * 
	 * @param : String action: is the record active
	 */
	private static void addActionRecord(String data, String action) {
		ActionRecord ar = new ActionRecord();

		ar.setAction(data.substring(26, 30).trim().toCharArray());
		ar.setOrder_num(data.substring(32, 38).trim().toCharArray());
		ar.setSource(data.substring(38, 40).trim().toCharArray());
		ar.setComment(data.substring(41, 71).trim().toCharArray());
		ar.setActive(action.toCharArray());

		records.add(ar);
	}

	/*
	 * Set and add Spec Record to ArrayList
	 * 
	 * @param : String data : data to parse
	 */
	private static void addSpecRecord(String data) {
		SpecRecord sr = new SpecRecord();

		String s = data;

		// Required parts
		sr.setStatementID(s.substring(0, 7).trim().toCharArray());
		sr.setStartDate(s.substring((s.lastIndexOf("DATE") + 4), (s.lastIndexOf("DATE") + 14)).trim().toCharArray());
		sr.setEndDate(s.substring((s.lastIndexOf("DATE") + 15), (s.lastIndexOf("DATE") + 21)).trim().toCharArray());
		sr.setCpl(s.substring((s.lastIndexOf("CPL") + 12), (s.lastIndexOf("CPL") + 15)).trim().toCharArray());
		sr.setComment(s.substring((s.lastIndexOf("SOL") + 22), (s.lastIndexOf("SOL") + 59)).trim().toCharArray());

		// Check if Optional Parts are Present
		if (s.contains("E MODEL")) {
			sr.setE_model(s.substring((s.lastIndexOf("E MODEL") + 12), (s.lastIndexOf("E MODEL") + 49)).trim()
					.replace(",", " ").toCharArray());
		} else {
			sr.setE_model("".toCharArray());
		}

		if (s.contains("OPT")) {
			sr.setOption(s.substring((s.lastIndexOf("OPT") + 12), (s.lastIndexOf("OPT") + 49)).trim().toCharArray());
		} else {
			sr.setOption("".toCharArray());
		}
		if (s.contains("SOURCE")) {
			sr.setSource(
					s.substring((s.lastIndexOf("SOURCE") + 12), (s.lastIndexOf("SOURCE") + 49)).trim().toCharArray());
		} else {
			sr.setSource("".toCharArray());
		}

		records.add(sr);
	}

	/*
	 * Print the data to file
	 * 
	 * @param File file: file to print to
	 */
	private void printRecords(File file) {
		Writer w = new Writer();

		w.writeCsv(records, file);

		// empty data array after write
		records.removeAll(records);

	}
}
