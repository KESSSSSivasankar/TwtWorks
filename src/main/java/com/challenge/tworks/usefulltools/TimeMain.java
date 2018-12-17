package com.challenge.tworks.usefulltools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class TimeMain {

	public static void main(String[] args) throws JsonProcessingException, ParseException {
		RestTemplate restTemplate = new RestTemplate();	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("userId", "F1XVYfFzt");
	    ObjectMapper mapper = new ObjectMapper();
		final String geturi = "https://http-hunt.thoughtworks-labs.net/challenge/input";
		final String posturi = "https://http-hunt.thoughtworks-labs.net/challenge/output";
	    
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		//Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));         
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);  
	    //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("input", headers);
	     
	    ResponseEntity<Input> result = restTemplate.exchange(geturi, HttpMethod.GET, entity, Input.class);

	    Input in = result.getBody();
	    
	    System.out.println(mapper.writeValueAsString(in));
    
	    Output out = new Output();
	    out.setToolsSortedOnUsage(getOutput(in.getToolUsage()));
	    
	    
	    System.out.println(mapper.writeValueAsString(out));
	    
	    RestTemplate restTemplate1 = new RestTemplate();	     
	    HttpHeaders headers1 = new HttpHeaders();
	    headers1.set("userId", "F1XVYfFzt");
	    headers1.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<Output> request = new HttpEntity<Output>(out, headers1);
	    String result1 = restTemplate1.postForObject( posturi,request , String.class);
	 
	    System.out.println(result1);

	}
	
	public static List<ToolsSortedOnUsage> getOutput(List<toolUsage> toolUsage) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Map<String,Long> update = new HashMap<String,Long>();
		for(toolUsage tool:toolUsage ) {
			
			if(update.containsKey(tool.getName())) {
				
				
				
				Date startDate = dateFormat.parse(tool.getUseStartTime());
			    Timestamp starttamp = new java.sql.Timestamp(startDate.getTime());
			    
			    Date endDate = dateFormat.parse(tool.getUseEndTime());
			    Timestamp endtamp = new java.sql.Timestamp(endDate.getTime());
				
				Long time = update.get(tool.getName()) + compareTwoTimeStamps(starttamp,endtamp);
				
				update.put(tool.getName(), time);
			}else {
				
				Date startDate = dateFormat.parse(tool.getUseStartTime());
			    Timestamp starttamp = new java.sql.Timestamp(startDate.getTime());
			    
			    Date endDate = dateFormat.parse(tool.getUseEndTime());
			    Timestamp endtamp = new java.sql.Timestamp(endDate.getTime());
				
				
				update.put(tool.getName(), compareTwoTimeStamps(starttamp,endtamp));
			}
			
			
			
			
		}
		
		List<ToolsSortedOnUsage> toolsSortedOnUsage = new ArrayList<ToolsSortedOnUsage>();
		
		for(Map.Entry<String,Long> map: update.entrySet()) {
			ToolsSortedOnUsage tools = new ToolsSortedOnUsage();
			tools.setName(map.getKey());
			tools.setTimeUsedInMinutes(map.getValue().intValue());
			
			toolsSortedOnUsage.add(tools);
		}
		
		Collections.sort(toolsSortedOnUsage);  
		
		return toolsSortedOnUsage;

	}
	
	
	
	public static long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp oldTime)
	{
	  long milliseconds1 = currentTime.getTime();
	  long milliseconds2 = oldTime.getTime();

	  long diff = milliseconds2 - milliseconds1;
	  long diffSeconds = diff / 1000;
	  long diffMinutes = diff / (60 * 1000);
	  long diffHours = diff / (60 * 60 * 1000);
	  long diffDays = diff / (24 * 60 * 60 * 1000);

	    return diffMinutes;
	}

}
