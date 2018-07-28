package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.form.NewsForm;
import ua.service.NewsService;

public class NewsValidator implements Validator{

	private final NewsService newsService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9 ]+");

	public NewsValidator(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@Override
	public boolean supports(Class<?> arg0) {
		return NewsForm.class.isAssignableFrom(arg0);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		NewsForm form = (NewsForm) obj;
		if(form.getId()==0){
			Matcher matcher = pattern.matcher(form.getName());
			if(!matcher.matches()){
				errors.rejectValue("name", "", "News name can't contain such signs or be empty");
			}
			if(newsService.findByName(form.getName())!=null){
				errors.rejectValue("name", "", "Such news is already exists");
			}
			Matcher matcher2 = pattern.matcher(form.getNameRU());
			if(!matcher2.matches()){
				errors.rejectValue("nameRU", "", "News name can't contain such signs or be empty");
			}
			if(newsService.findByName(form.getNameRU())!=null){
				errors.rejectValue("nameRU", "", "Such news is already exists");
			}
			Matcher matcher3 = pattern.matcher(form.getNameUA());
			if(!matcher3.matches()){
				errors.rejectValue("nameUA", "", "News name can't contain such signs or be empty");
			}
			if(newsService.findByName(form.getNameUA())!=null){
				errors.rejectValue("nameUA", "", "Such news is already exists");
			}
		}
	}

	
}
