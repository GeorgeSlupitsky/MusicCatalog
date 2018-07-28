package ua.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.NewsService;

@Controller
public class NewsViewController {

	@Autowired
	private NewsService newsService;
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping("/news")
	public String showNews(Locale locale, Model model, @PageableDefault(sort="id") Pageable pageable){
		model.addAttribute("newses", newsService.findAll(pageable));
		return "news";
	}
	
	@RequestMapping("/news/delete/{id}")
	public String deleteNews(@PathVariable int id){
		newsService.delete(id);
		return "redirect:/news";
	}
}
