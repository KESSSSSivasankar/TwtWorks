package com.challenge.tworks.gibbrish;

import java.util.List;

public class Output {
   
	List<String> toolsFound;
	
	public Output(List toolsFound) {
		this.toolsFound = toolsFound;
	}
	
	public List<String> getTools() {
		return toolsFound;
	}
	public void setTools(List<String> tools) {
		this.toolsFound = tools;
	}
	
}
