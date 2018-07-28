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

import ua.form.NewsFilter;
import ua.form.NewsForm;
import ua.service.NewsService;
import ua.service.implementation.validator.NewsValidator;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@InitBinder("news")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new NewsValidator(newsService));;
	}
	
	@ModelAttribute("news")
	public NewsForm getForm(){
		return new NewsForm();
	}
	
	@Secured(value="ROLE_ADMIN")
	@RequestMapping("/admin/news")
	public String showNews(Locale locale, Model model, @PageableDefault(size=5, sort="id") Pageable pageable, @ModelAttribute("filter") NewsFilter newsFilter){
		model.addAttribute("newses", newsService.findAll(pageable, newsFilter));
		model.addAttribute("filter", newsFilter);
		return "adminNews";
	}
	
	@RequestMapping("/admin/news/delete/{id}")
	public String deleteNews(@PathVariable int id){
		newsService.delete(id);
		return "redirect:/admin/news";
	}
	
	@RequestMapping("/admin/news/update/{id}")
	public String updateNews(@PathVariable int id, Model model, @PageableDefault(size=5) Pageable pageable){
		model.addAttribute("news", newsService.findOneForm(id));
		model.addAttribute("newses", newsService.findAll(pageable));
		return "adminNews";
	}
	
	@RequestMapping(value="/admin/news", method=RequestMethod.POST)
	public String saveNews(@ModelAttribute("news") @Valid NewsForm form, BindingResult br, Model model, @PageableDefault(size=5) Pageable pageable){
		if(br.hasErrors()){
			model.addAttribute("newses", newsService.findAll(pageable));
			return "adminNews";
		}
		newsService.save(form);
		return "redirect:/admin/news";
	}
	
}
