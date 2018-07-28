package ua.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Collection;
import ua.entity.Country;
import ua.form.MyUserForm;
import ua.service.CollectionService;
import ua.service.CountryService;
import ua.service.MailSender;
import ua.service.MyUserService;
import ua.service.implementation.editor.CollectionEditor;
import ua.service.implementation.editor.CountryEditor;
import ua.service.implementation.validator.MyUserRegistrationValidator;

@Controller
public class RegistrationController {

	@Autowired
	private MyUserService service;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CollectionService collectionService;
	
	@Autowired
	private MailSender mailSender;
	
	@InitBinder("user")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.registerCustomEditor(Collection.class, new CollectionEditor(collectionService));
		binder.setValidator(new MyUserRegistrationValidator(service));
	}
	
	@ModelAttribute("user")
	public MyUserForm getForm(){
		return new MyUserForm();
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationGet(Model model, Locale locale){
		model.addAttribute("user", new MyUserForm());
		model.addAttribute("countries",countryService.findAll());
		return "registration";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Valid MyUserForm form, BindingResult br, Model model){
		if(br.hasErrors()){
			model.addAttribute("countries", countryService.findAll());
			return "registration";
		}
		service.save(form);
		mailSender.sendMail("³???? ? ?????????? ?? ???? musicCatalog", form.getEmail(), 
				"??????? "+form.getName()+" "+form.getSurname()+", ????? ? ?????????? ?? ?????? ????."
				+" ?? ??????????? ?? ????: "+form.getLogin()+". ??? ??????????? ?????? ???????? ?? "
				+"??????????: http://localhost:8080/login. ? ??????? ??????????? ????? musicCatalog.");
		return "redirect:/congratulation";
	}

}
