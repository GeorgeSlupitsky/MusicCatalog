package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.TypeOfAlbum;
import ua.service.TypeOfAlbumService;

public class TypeOfAlbumValidator implements Validator  {

	private final TypeOfAlbumService typeOfAlbumService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє ]+");
	
	public TypeOfAlbumValidator(TypeOfAlbumService typeOfAlbumService) {
		this.typeOfAlbumService = typeOfAlbumService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return TypeOfAlbum.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		TypeOfAlbum typeOfAlbum = (TypeOfAlbum) obj;
		Matcher matcher = pattern.matcher(typeOfAlbum.getName());
		if(!matcher.matches()){
			errors.rejectValue("name", "", "Type of album name can't contain such signs or be empty");
		}
		if(typeOfAlbumService.findByName(typeOfAlbum.getName())!=null){
			errors.rejectValue("name", "", "Such type of album is already exists");
		}
		Matcher matcher2 = pattern.matcher(typeOfAlbum.getNameRU());
		if(!matcher2.matches()){
			errors.rejectValue("nameRU", "", "Type of album name can't contain such signs or be empty");
		}
		if(typeOfAlbumService.findByName(typeOfAlbum.getNameRU())!=null){
			errors.rejectValue("nameRU", "", "Such type of album is already exists");
		}
		Matcher matcher3 = pattern.matcher(typeOfAlbum.getNameUA());
		if(!matcher3.matches()){
			errors.rejectValue("nameUA", "", "Type of album name can't contain such signs or be empty");
		}
		if(typeOfAlbumService.findByName(typeOfAlbum.getNameUA())!=null){
			errors.rejectValue("nameUA", "", "Such type of album is already exists");
		}
	}

}
