package com;

public class Group { // A group is one row in the CSV file
	
	private String timeStamp;
	private String nameOne;
	private String nameTwo;
	private String emailOne;
	private String emailTwo;
	private String androidUser;
	private String work;
	
	
	
	public Group(String timeStamp, String nameOne, String nameTwo, 
			String emailOne, String emailTwo, String androidUser, String work) {
		this.timeStamp = timeStamp;
		this.nameOne = nameOne;
		this.nameTwo = nameTwo;
		this.emailOne = emailOne;
		this.emailTwo = emailTwo;
		this.androidUser = androidUser;
		this.work = work;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public String getNameOne() {
		return nameOne;
	}

	public String getNameTwo() {
		return nameTwo;
	}
	
	public String getEmailOne() {
		return emailOne;
	}

	public String getEmailTwo() {
		return emailTwo;
	}

	public String getAndroidUser() {
		return androidUser;
	}
	
	public String getWork() {
		return work;
	}

	@Override
	public String toString() {
		return timeStamp + " " + nameOne + " " + nameTwo + " "
				+ emailOne + " " + emailTwo + " " + androidUser + " " + work;
	}	
	
	
}
