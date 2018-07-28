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

import ua.entity.TypeOfAlbum;
import ua.form.TypeOfAlbumFilter;
import ua.service.TypeOfAlbumService;
import ua.service.implementation.validator.TypeOfAlbumValidator;

@Controller
public class TypeOfAlbumController {

	@Autowired
	private TypeOfAlbumService typeOfAlbumService;
	
	@InitBinder("typeOfAlbum")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new TypeOfAlbumValidator(typeOfAlbumService));;
	}
	
	@ModelAttribute("typeOfAlbum")
	public TypeOfAlbum getTypeOfAlbum(){
		return new TypeOfAlbum();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("/admin/typeOfAlbum")
	public String showTypeOfAlbum(Locale locale, Model model, @PageableDefault(size=5, sort="name") Pageable pageable, @ModelAttribute("filter") TypeOfAlbumFilter typeOfAlbumFilter){
		model.addAttribute("typeOfAlbums", typeOfAlbumService.findAll(pageable, typeOfAlbumFilter));
		model.addAttribute("filter", typeOfAlbumFilter);
		return "adminTypeOfAlbum";
	}
	
	@RequestMapping("/admin/typeOfAlbum/delete/{id}")
	public String deleteTypeOfAlbum(@PathVariable int id){
		typeOfAlbumService.delete(id);
		return "redirect:/admin/typeOfAlbum";
	}
	
	@RequestMapping("/admin/typeOfAlbum/update/{id}")
	public String updateTypeOfAlbum(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("typeOfAlbum", typeOfAlbumService.findOne(id));
		model.addAttribute("typeOfAlbums", typeOfAlbumService.findAll(pageable));
		return "adminTypeOfAlbum";
	}
	
	@RequestMapping(value="/admin/typeOfAlbum", method=RequestMethod.POST)
	public String saveTypeOfAlbum(@ModelAttribute("typeOfAlbum") @Valid TypeOfAlbum typeOfAlbum, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("typeOfAlbums", typeOfAlbumService.findAll(pageable));
			return "adminTypeOfAlbum";
		}
		typeOfAlbumService.save(typeOfAlbum);
		return "redirect:/admin/typeOfAlbum";
	}
}
