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

import ua.entity.Genre;
import ua.form.GenreFilter;
import ua.service.GenreService;
import ua.service.implementation.validator.GenreValidator;

@Controller
public class GenreController {

	@Autowired
	private GenreService genreService;
	
	@InitBinder("genre")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new GenreValidator(genreService));;
	}
	
	@ModelAttribute("genre")
	public Genre getGenre(){
		return new Genre();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("/admin/genre")
	public String showGenre(Locale locale, Model model, @PageableDefault(size=5) Pageable pageable, @ModelAttribute("filter") GenreFilter genreFilter){
		model.addAttribute("genres", genreService.findAll(pageable, genreFilter));
		model.addAttribute("filter", genreFilter);
		return "adminGenre";
	}
	
	@RequestMapping("/admin/genre/delete/{id}")
	public String deleteGenre(@PathVariable int id){
		genreService.delete(id);
		return "redirect:/admin/genre";
	}
	
	@RequestMapping("/admin/genre/update/{id}")
	public String updateGenre(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("genre", genreService.findOne(id));
		model.addAttribute("genres", genreService.findAll(pageable));
		return "adminGenre";
	}
	
	@RequestMapping(value="/admin/genre", method=RequestMethod.POST)
	public String saveGenre(@ModelAttribute("genre") @Valid Genre genre, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("genres", genreService.findAll(pageable));
			return "adminGenre";
		}
		genreService.save(genre);
		return "redirect:/admin/genre";
	}
}
