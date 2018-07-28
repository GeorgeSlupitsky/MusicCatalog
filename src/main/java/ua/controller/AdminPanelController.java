package ua.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPanelController {
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("/admin")
	public String showAdmin() {
		return "adminPanel";
	}
}
