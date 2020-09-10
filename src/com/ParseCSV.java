package com;

import java.util.HashMap;
import java.util.List;

public class ParseCSV {
	
   
	public static void main(String[] args) {
		
		ParseUtils CSVinfo = new ParseUtils(); 
		
		CSVinfo.readFile(); // CSVinfo - all information, columns. 
		// CSVinfo.printCSV(); // Check if file info been added correctly
		
		List<Group> groups = CSVinfo.getGroups();  // groups - row information, one row = one group.
		//CSVinfo.printGroups();  // Check if group info been added correctly		
		
		countNummberOfNamesWithA(groups);
		groupUniqueTimeStamps(groups, CSVinfo);
		countNumberOfAndroidPpl(groups);
		sortNamesAlphabeticaly(CSVinfo);
				
	}

	public static void countNummberOfNamesWithA(List<Group> groups) {
		int counter = 0;
		
		for (Group aGroup : groups) {
			// Counts both A and a 
			if(aGroup.getNameOne().toLowerCase().contains("a")) {
				counter++;
			}
			
			if(aGroup.getNameTwo().toLowerCase().contains("a")) {
				counter++;
			}
		}
		
		System.out.println("Number of ppl with [Aa] in their name: " + counter + "\n");
	}
	
	public static void groupUniqueTimeStamps(List<Group> groups, ParseUtils CSVinfo) {
		
		// HashMap key is unique. Add duplicate key and it will be overwritten.
		// The key in this case is timeStamps 
		HashMap<String, Integer> uniqueTimeStamps = new HashMap<String, Integer>();
		
		for (Group aGroup : groups) {			
			uniqueTimeStamps.put(aGroup.getTimeStamp(), 0);			
		}
		
		System.out.print("Groupt members by timeStamp:\n");
		
		for (String key : uniqueTimeStamps.keySet()) {			
			System.out.print(CSVinfo.getUniqueTimeStampGroups(key));		
		}		
	}
	
	public static void countNumberOfAndroidPpl(List<Group> groups) {
		int counter = 0;
		
		for (Group aGroup : groups) {
			if(aGroup.getWork().equals("Android App")) {
				counter++;
			}			
		}
		
		System.out.println("\nNumber of groups who will work with Android: " + counter 
				+ "\nNumber of ppl who will work with Android: " + (counter*2) + "\n");
	}

	public static void sortNamesAlphabeticaly(ParseUtils CSVinfo) {
		
		List<String> sorted = CSVinfo.getSortedNamesWithEmails();
		
		System.out.println("All members sorted in alphabetical order with related email: ");
		
		for(String member: sorted){ 
			System.out.println(member);  
		}
	}
}
