package com.challenge.tworks.usefulltools;

public class ToolsSortedOnUsage  implements Comparable<ToolsSortedOnUsage> {

	
	String name;
	int timeUsedInMinutes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimeUsedInMinutes() {
		return timeUsedInMinutes;
	}
	public void setTimeUsedInMinutes(int timeUsedInMinutes) {
		this.timeUsedInMinutes = timeUsedInMinutes;
	}
	
	
	
	public int compareTo(ToolsSortedOnUsage st){    
		 if(timeUsedInMinutes==st.timeUsedInMinutes)    
		 return 0;    
		 else if(timeUsedInMinutes<st.timeUsedInMinutes)    
		 return 1;    
		 else    
		 return -1;    
		 } 
}
