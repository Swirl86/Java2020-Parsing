package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ParseUtils {

	final static String COMMA_DELIMITER = ",";
	
	private List<Group> groups;
	private List<List<String>> columns;

	public ParseUtils() {
		groups = new ArrayList<Group>();
		columns = new ArrayList<>();
	}

	public void readFile() {		
		try (Scanner scanner = new Scanner(new File("sample.csv"))) {

			while (scanner.hasNextLine()) {
				// Save the column data
				List<String> record = getRecordFromLine(scanner.nextLine());
				if(record.get(0).length() > 0 ) { // Don't add empty rows
					columns.add(record);
				}				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Create groups with the columns information
		addGroups();
	}
	
	public List<String> getRecordFromLine(String line) {
		// Save the row data
		List<String> rows = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {

			rowScanner.useDelimiter(COMMA_DELIMITER);

			while (rowScanner.hasNext()) {
				rows.add(rowScanner.next());
			}
			
		}
		return rows;
	}

	public void addGroups() {
		boolean firstRow = true;
		
		// Loop through columns, every row is a group
		for (List<String> rows : columns) {
			if (!firstRow) { // Skip title row
				groups.add(new Group(rows.get(0), rows.get(1), 
						rows.get(2), rows.get(3), rows.get(4), 
						rows.get(5), rows.get(6)));
			} else {
				firstRow = false;
			}
		}
	}

	public void printCSV() {		
		for(List<String> row : columns){ 
			System.out.println(row.toString());  			
		}
		System.out.println();
	}
	
	public void printGroups() {		
		for(Group info : groups){ 
			System.out.println(info);  			
		}
		System.out.println();
	}
	
	public List<Group> getGroups() {
		return groups;
	}

	public String getUniqueTimeStampGroups(String date) {
		// Build a str with the all the names with same TimeStamp as date variable
		String sameTimeStamp = date + ": ";

		for (Group aGroup : groups) {
			if (aGroup.getTimeStamp().equals(date)) {
				sameTimeStamp += aGroup.getNameOne() + "  " + aGroup.getNameTwo() + "  ";
			}
		}
		sameTimeStamp += "\n";

		return sameTimeStamp;
	}
	
	public List<String> getSortedNamesWithEmails() {
		
		List<String> memberList = new ArrayList<>();

		for (Group aGroup : groups) {
			// Add every member and their related email to memberList as a str
			// checkIfDuplicatEmail() adds a related str depending on if email is duplicate or not
			memberList.add(aGroup.getNameOne() + ", " + aGroup.getEmailOne() 
			+ " " + checkIfDuplicatEmail(aGroup.getEmailOne()));
			
			memberList.add(aGroup.getNameTwo() + ", " + aGroup.getEmailTwo() 
			+ " " + checkIfDuplicatEmail(aGroup.getEmailTwo()));
		}
		
		Collections.sort(memberList, String.CASE_INSENSITIVE_ORDER); // Alphabetical order sort
		return memberList;
	}

	public String checkIfDuplicatEmail(String email) {
		int counter = 0;
		for (Group aGroup : groups) {
			if (aGroup.getEmailOne().equals(email) || aGroup.getEmailTwo().equals(email)) {
				counter += 1;
			}
		}
		// There will always be at least 1 email found
		// If duplicate email is found an extra string will be added
		return counter > 1 ? ", NOTE: Duplicat Email!" : "";
	}
}
