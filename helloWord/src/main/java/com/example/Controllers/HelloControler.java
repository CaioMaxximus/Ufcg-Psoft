package com.example.Controllers;

import java.time.Instant;
import java.time.LocalTime;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.classes.Greeting;

@RestController
public class HelloControler {

	@GetMapping("/hello")
	public String hello(@RequestParam(name = "name",required = false,defaultValue = "World") String name,Model model){
		model.addAttribute("name", name);
		Instant agora = Instant.now();
 //2014-04-08T10:02:52.036Z (formato ISO-8601
		return "Hello " + name + "its " + agora.toString() + " on the side server!";
	}
	
	@GetMapping("/soma")
	public String potencia(@RequestParam(name = "num", required = false,defaultValue = "10")int num) {
		int saida = num * num;
		return "O resultado de " + num + " ao quadrado é: " + saida;
	}
		
	
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
