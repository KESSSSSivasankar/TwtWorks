package com.challenge.tworks.newTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToolsMain {

	public static void main(String[] args) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("userId", "F1XVYfFzt");
	    ObjectMapper mapper = new ObjectMapper();
		final String geturi = "https://http-hunt.thoughtworks-labs.net/challenge/input";
		final String posturi = "https://http-hunt.thoughtworks-labs.net/challenge/output";
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("input", headers);
	     
	    ResponseEntity<Input> result = restTemplate.exchange(geturi, HttpMethod.GET, entity, Input.class);

	    Input in = result.getBody();
	    
	    System.out.println(mapper.writeValueAsString(in));
	    
	    
    
	    Output out = new Output();
	    out.setToolsToTakeSorted(getOutput(in));
	    
	    System.out.println(mapper.writeValueAsString(in));
	    System.out.println(mapper.writeValueAsString(out));
	    
	    RestTemplate restTemplate1 = new RestTemplate();	     
	    HttpHeaders headers1 = new HttpHeaders();
	    headers1.set("userId", "F1XVYfFzt");
	    headers1.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<Output> request = new HttpEntity<Output>(out, headers);
	    String result1 = restTemplate1.postForObject( posturi,request , String.class);
	 
	    System.out.println(result1);

	}
	
	
	static List<String> getOutput(Input in){
		List<String> output = new ArrayList<String>();
		List<Tools> intput = in.getTools();
		Collections.sort(intput);
		int maxweight = 0;
		for(Tools tool :intput) {
			
			output.add(tool.getName());
			
			maxweight = maxweight + tool.getWeight();
			
			if(maxweight > in.getMaximumWeight()) {
				output.remove(tool.getName());
				maxweight = maxweight -tool.getWeight();
			}
		}
		
		
		return output;
	}

}
