package com.challenge.tworks;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.tworks.gibbrish.GibbrishMain;
import com.challenge.tworks.gibbrish.Input;
import com.challenge.tworks.gibbrish.Output;

@RestController
public class ActiveController {
	
	@PostMapping
	public Output getCodes(Input in){
		
		Output out = new Output(GibbrishMain.outputs(in.getHiddenTools(),in.getTools()));
		return out;
	}
	

}
