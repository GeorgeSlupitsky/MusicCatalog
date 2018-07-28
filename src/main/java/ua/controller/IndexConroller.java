package ua.controller;

import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexConroller {
	
	@RequestMapping("/")
	public String showIndex(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
		}else{
			System.out.println("principal is null");
		}
		return "index";
	}
	
}
