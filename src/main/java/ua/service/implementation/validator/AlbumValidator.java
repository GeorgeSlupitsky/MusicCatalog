package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.form.AlbumForm;
//import ua.service.AlbumService;

public class AlbumValidator implements Validator{

//	private final AlbumService albumService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9, ]+");
	
//	public AlbumValidator(AlbumService albumService) {
//		this.albumService = albumService;
//	}

	@Override
	public boolean supports(Class<?> arg0) {
		return AlbumForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		AlbumForm form = (AlbumForm) obj;
		if(form.getId()==0){
			Matcher matcher = pattern.matcher(form.getName());
			if(!matcher.matches()){
				errors.rejectValue("name", "", "Album name can't contain such signs or be empty");
			}
		}
	}

	
}
