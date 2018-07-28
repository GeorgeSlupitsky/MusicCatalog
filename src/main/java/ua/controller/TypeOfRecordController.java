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

import ua.entity.TypeOfRecord;
import ua.form.TypeOfRecordFilter;
import ua.service.TypeOfRecordService;
import ua.service.implementation.validator.TypeOfRecordValidator;

@Controller
public class TypeOfRecordController {

	@Autowired
	private TypeOfRecordService typeOfRecordService;
	
	@InitBinder("typeOfRecord")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new TypeOfRecordValidator(typeOfRecordService));;
	}
	
	@ModelAttribute("typeOfRecord")
	public TypeOfRecord getTypeOfRecord(){
		return new TypeOfRecord();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("/admin/typeOfRecord")
	public String showTypeOfRecord(Model model, @PageableDefault(size=5, sort="name") Pageable pageable, @ModelAttribute("filter") TypeOfRecordFilter typeOfRecordFilter){
		model.addAttribute("typeOfRecords", typeOfRecordService.findAll(pageable, typeOfRecordFilter));
		model.addAttribute("filter", typeOfRecordFilter);
		return "adminTypeOfRecord";
	}
	
	@RequestMapping("/admin/typeOfRecord/delete/{id}")
	public String deleteTypeOfRecord(@PathVariable int id){
		typeOfRecordService.delete(id);
		return "redirect:/admin/typeOfRecord";
	}
	
	@RequestMapping("/admin/typeOfRecord/update/{id}")
	public String updateTypeOfRecord(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("typeOfRecord", typeOfRecordService.findOne(id));
		model.addAttribute("typeOfRecords", typeOfRecordService.findAll(pageable));
		return "adminTypeOfRecord";
	}
	
	@RequestMapping(value="/admin/typeOfRecord", method=RequestMethod.POST)
	public String saveTypeOfRecord(@ModelAttribute("typeOfRecord") @Valid TypeOfRecord typeOfRecord, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("typeOfRecords", typeOfRecordService.findAll(pageable));
			return "adminTypeOfRecord";
		}
		typeOfRecordService.save(typeOfRecord);
		return "redirect:/admin/typeOfRecord";
	}
}
