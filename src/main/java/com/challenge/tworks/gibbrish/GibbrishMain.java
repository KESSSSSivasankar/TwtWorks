package com.challenge.tworks.gibbrish;

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

public class GibbrishMain {

	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("userId", "F1XVYfFzt");
		
		final String geturi = "https://http-hunt.thoughtworks-labs.net/challenge/input";
		final String posturi = "https://http-hunt.thoughtworks-labs.net/challenge/output";
	    
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("input", headers);
	     
	    ResponseEntity<Input> result = restTemplate.exchange(geturi, HttpMethod.GET, entity, Input.class);
		
		
	    Input in = result.getBody();
		
		/*System.out.println();
	    System.out.println(in.getHiddenTools());
	    System.out.println(in.getTools());*/
	   
	    
	    Output out = new Output(outputs(in.getHiddenTools(),in.getTools()));
	    //System.out.println(out.getTools().toString());
	    RestTemplate restTemplate1 = new RestTemplate();	     
	    HttpHeaders headers1 = new HttpHeaders();
	    headers1.set("userId", "F1XVYfFzt");
	    //headers.add("Content-Type", "application/json");
	    //headers1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));*/
	    //headers1.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<Output> request = new HttpEntity<Output>(out, headers1);
	    String result1 = restTemplate1.postForObject( posturi,request , String.class);
	 
	    System.out.println(result1);

	}
	 public static List<String> outputs(String s1,List<String> samples){
		 List<String> output = new ArrayList<String>();
		 char[] ch = s1.toCharArray();
		 
		 for(String sam: samples) {
			 char[] ch1 = sam.toCharArray();
			 StringBuffer sb = new StringBuffer();
			 int index =0;
			 for(int j=0 ;j <ch1.length;j++) {
				 char cur = ch1[j];
				 
				 while(index <ch.length) {
					 if(cur == ch[index]) {
						 sb.append(ch[index]);						 
						 break;
					 }else {
						 index++;
					 }
				 }

			 }		 
			 if(sam.equals(sb.toString()))
				 output.add(sam);
			 
		 }
		 
		 
		 
		return output;
		
	}
	 
	/* public static Map<Character, Integer> charCount (String s) {
	 * 
	 *  List ch1  = null;
		 for(String sam:samples) {
			 ch1 = Arrays.asList(sam.toCharArray());
			 for(int i=0;i<ch1.size();i++) {
				 ch.get("k");
						 
			 }
	 * ch1 = sam.toCharArray();
			 int count =0;
			 for(int i=0;i<ch1.length;i++) {
				 if(scrambledletters.containsKey(ch1[i]))
					 count++;
			  }
		     if(count == ch1.length)
		    	 output.add(sam); 
		    Map<Character, Integer> result = new HashMap<>();
		    for (int i = 0; i < s.length(); ++i) {
		        Character c = s.charAt(i);
		        Integer cnt = result.get(c);
		        if (cnt == null) {
		            cnt = 1;
		        } else {
		            cnt = cnt + 1;
		        }
		        result.put (c, cnt);
		    }
		    return result; 
		}*/

}
