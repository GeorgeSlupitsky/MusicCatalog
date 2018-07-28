package ua.controller;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Collection;
import ua.entity.Country;
import ua.form.MyUserForm;
import ua.service.CollectionService;
import ua.service.CountryService;
import ua.service.MyUserService;
import ua.service.implementation.editor.CollectionEditor;
import ua.service.implementation.editor.CountryEditor;
import ua.service.implementation.validator.MyUserUpdateValidator;

@Controller
public class UserPanelController {

	@Autowired
	private MyUserService service;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CollectionService collectionService;
	
	@InitBinder("user")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Collection.class, new CollectionEditor(collectionService));
		binder.setValidator(new MyUserUpdateValidator(service));
	}
	
	@ModelAttribute("user")
	public MyUserForm getForm(){
		return new MyUserForm();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/userPanel/{id}")
	public String userPanelUpdate(@PathVariable int id, MyUserForm user, Model model, Locale locale) {
		model.addAttribute("user", service.findOneForm(id));
		model.addAttribute("countries", countryService.findAll());
		return "userPanel";
	}

	@RequestMapping(value = "/userPanel", method = RequestMethod.POST)
	public String userPanelSave (@ModelAttribute("user") @Valid MyUserForm user, BindingResult br, Principal principal, Model model) {
		int idPrincipal= Integer.parseInt(principal.getName());
		if(br.hasErrors()){
			model.addAttribute("countries", countryService.findAll());
			return "userPanel";
		}
		service.saveUser(user, idPrincipal);
		return "redirect:/";
	}

}
