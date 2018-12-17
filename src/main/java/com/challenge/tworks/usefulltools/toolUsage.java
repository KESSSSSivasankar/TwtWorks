package com.challenge.tworks.usefulltools;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class toolUsage {
	
	String name;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	String  useStartTime;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	String  useEndTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUseStartTime() {
		return useStartTime;
	}
	public void setUseStartTime(String useStartTime) {
		this.useStartTime = useStartTime;
	}
	public String getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}
	

}
