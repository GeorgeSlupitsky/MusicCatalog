package ua.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import ua.form.CountryFilter;
import ua.form.CountryForm;
import ua.service.CountryService;
import ua.service.implementation.validator.CountryValidator;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@InitBinder("country")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new CountryValidator(countryService));;
	}
	
	@ModelAttribute("country")
	public CountryForm getForm(){
		return new CountryForm();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("/admin/country")
	public String showCountry(Locale locale, Model model, @PageableDefault(size=5, sort="name") Pageable pageable, @ModelAttribute("filter") CountryFilter countryFilter){
		model.addAttribute("countries", countryService.findAll(pageable, countryFilter));
		model.addAttribute("filter", countryFilter);
		return "adminCountry";
	}
	
	@RequestMapping("/admin/country/delete/{id}")
	public String deleteCountry(@PathVariable int id){
		countryService.delete(id);
		return "redirect:/admin/country";
	}
	
	@RequestMapping("/admin/country/update/{id}")
	public String updateCountry(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("country", countryService.findOneForm(id));
		model.addAttribute("countries", countryService.findAll(pageable));
		return "adminCountry";
	}
	
	@RequestMapping(value="/admin/country", method=RequestMethod.POST)
	public String saveCountry(@ModelAttribute("country") @Valid CountryForm form, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("countries", countryService.findAll(pageable));
			return "adminCountry";
		}
		countryService.save(form);
		return "redirect:/admin/country";
	}
	
}
