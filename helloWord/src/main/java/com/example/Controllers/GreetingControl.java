package com.example.Controllers;

import java.time.LocalTime;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.classes.Greeting;

public class GreetingControl {

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(name = "name",required = false,defaultValue = "Bom dia")String name, Model model) {
		
		Greeting saida = new Greeting(name, saudacao());
		return saida;
		
	}

	private String saudacao() {
		LocalTime time = LocalTime.now();
		return time.toString();
	}
	
}
