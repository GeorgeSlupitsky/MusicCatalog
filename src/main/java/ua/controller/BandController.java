package ua.controller;

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

import ua.entity.Country;
import ua.form.BandFilter;
import ua.form.BandForm;
import ua.service.BandService;
import ua.service.CountryService;
import ua.service.implementation.editor.CountryEditor;
import ua.service.implementation.validator.BandValidator;

@Controller
public class BandController {

	@Autowired
	private BandService bandService;
	
	@Autowired
	private CountryService countryService;
	

	@InitBinder("band")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
		binder.setValidator(new BandValidator(bandService));
	}
	
	@ModelAttribute("band")
	public BandForm getForm(){
		return new BandForm();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("admin/band")
	public String showBands(Model model, @PageableDefault(size=5, sort="name") Pageable pageable, @ModelAttribute("filter") BandFilter bandFilter){
		model.addAttribute("bands", bandService.findAll(pageable, bandFilter));
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("filter", bandFilter);
		return "adminBand";
	}
	
	@RequestMapping(value="admin/band", method=RequestMethod.POST)
	public String save(@ModelAttribute ("band") @Valid BandForm form, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("bands", bandService.findAll(pageable));
			model.addAttribute("countries", countryService.findAll());
			return "adminBand";
		}
		bandService.save(form);
		return "redirect:/admin/band";
	}
	
	@RequestMapping(value="admin/band/delete/{id}")
	public String delete(@PathVariable int id){
		bandService.delete(id);
		return "redirect:/admin/band";
	}
	
	@RequestMapping(value="admin/band/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("band", bandService.findOneForm(id));
		model.addAttribute("bands", bandService.findAll(pageable));
		model.addAttribute("countries", countryService.findAll());
		return "adminBand";
	}
}
