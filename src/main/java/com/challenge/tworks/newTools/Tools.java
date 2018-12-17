package com.challenge.tworks.newTools;

import com.challenge.tworks.usefulltools.ToolsSortedOnUsage;

public class Tools implements Comparable<Tools>{
	
	String name;
	int weight;
	int value;
	
	
	public int compareTo(Tools st){    
		 if(value==st.value)    
		 return 0;    
		 else if(value<st.value)    
		 return 1;    
		 else    
		 return -1;    
		 }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	} 

}
