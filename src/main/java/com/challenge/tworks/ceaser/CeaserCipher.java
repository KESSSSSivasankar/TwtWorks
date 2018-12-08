package com.challenge.tworks.ceaser;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CeaserCipher {

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
	    
	    //System.out.println(encrypt(in.getEncryptedMessage(),in.getKey()));
	    
	   
	    
	    Output out = new Output(encrypt(in.getEncryptedMessage(),in.getKey()).toString());
	    RestTemplate restTemplate1 = new RestTemplate();	     
	    HttpHeaders headers1 = new HttpHeaders();
	    headers1.set("userId", "F1XVYfFzt");
	    headers1.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<Output> request = new HttpEntity<Output>(out, headers);
	    String result1 = restTemplate1.postForObject( posturi,request , String.class);
	 
	    System.out.println(result1);

	}
	
	
	public static StringBuffer encrypt(String text, int s) 
    { 
        StringBuffer result= new StringBuffer(); 
  
        for (int i=0; i<text.length(); i++) 
        { 
            if (Character.isUpperCase(text.charAt(i)) &&  Pattern.matches("[A-Z]",Character.toString(text.charAt(i)))) 
            { 
            	String sh = "";
                char c = (char)(text.charAt(i) - s);
                if ((int)c < 65)
                    sh += (char)(text.charAt(i) + (26 - s));
                else
                    sh += (char)(text.charAt(i) - s);
                result.append(sh); 
            } 
            else
            {                  
                result.append(text.charAt(i)); 
            } 
        } 
        
        return result; 
    }

}
