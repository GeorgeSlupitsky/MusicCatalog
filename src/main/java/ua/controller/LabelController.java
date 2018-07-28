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

import ua.form.LabelFilter;
import ua.form.LabelForm;
import ua.service.LabelService;
import ua.service.implementation.validator.LabelValidator;

@Controller
public class LabelController {
	
	@Autowired
	private LabelService labelService;
	
	@InitBinder("label")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new LabelValidator(labelService));
	}
	
	@ModelAttribute("label")
	public LabelForm getForm(){
		return new LabelForm();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("/admin/label")
	public String showLabel(Model model, @PageableDefault(size=5, sort="name") Pageable pageable,  @ModelAttribute("filter") LabelFilter labelFilter){
		model.addAttribute("labels", labelService.findAll(pageable, labelFilter));
		model.addAttribute("filter", labelFilter);
		return "adminLabel";
	}
	
	@RequestMapping("/admin/label/delete/{id}")
	public String deleteLabel(@PathVariable int id){
		labelService.delete(id);
		return "redirect:/admin/label";
	}
	
	@RequestMapping("/admin/label/update/{id}")
	public String updateLabel(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("label", labelService.findOneForm(id));
		model.addAttribute("labels", labelService.findAll(pageable));
		return "adminLabel";
	}
	
	@RequestMapping(value="/admin/label", method=RequestMethod.POST)
	public String saveLabel(@ModelAttribute("label") @Valid LabelForm form, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("labels", labelService.findAll(pageable));
			return "adminLabel";
		}
		labelService.save(form);
		return "redirect:/admin/label";
	}
}
