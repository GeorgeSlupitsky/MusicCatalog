package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CongratulationController {
	
	@RequestMapping("/congratulation")
	public String congratulation(){
		return "congratulation";
	}
}
