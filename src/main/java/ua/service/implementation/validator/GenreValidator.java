package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.Genre;
import ua.service.GenreService;

public class GenreValidator implements Validator {

	private final GenreService genreService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9 ]+");
	
	public GenreValidator(GenreService genreService) {
		this.genreService = genreService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return Genre.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Genre genre = (Genre) obj;
		Matcher matcher = pattern.matcher(genre.getName());
		if(!matcher.matches()){
			errors.rejectValue("name", "", "Genre name can't contain such signs or be empty");
		}
		if(genreService.findByName(genre.getName())!=null){
			errors.rejectValue("name", "", "Such genre is already exists");
		}
		Matcher matcher2 = pattern.matcher(genre.getNameRU());
		if(!matcher2.matches()){
			errors.rejectValue("nameRU", "", "Genre name can't contain such signs or be empty");
		}
		if(genreService.findByName(genre.getNameRU())!=null){
			errors.rejectValue("nameRU", "", "Such genre is already exists");
		}
		Matcher matcher3 = pattern.matcher(genre.getNameUA());
		if(!matcher3.matches()){
			errors.rejectValue("nameUA", "", "Genre name can't contain such signs or be empty");
		}
		if(genreService.findByName(genre.getName())!=null){
			errors.rejectValue("nameUA", "", "Such genre is already exists");
		}
	}

}
