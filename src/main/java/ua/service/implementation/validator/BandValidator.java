package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.form.BandForm;
import ua.service.BandService;

public class BandValidator implements Validator{

	private final BandService bandService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9 ]+");
	
	public BandValidator(BandService bandService) {
		this.bandService = bandService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return BandForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		BandForm form = (BandForm) obj;
		if(form.getId()==0){
			Matcher matcher = pattern.matcher(form.getName());
			if(!matcher.matches()){
				errors.rejectValue("name", "", "Band name can't contain such signs or be empty");
			}
			if(bandService.findByName(form.getName())!=null){
				errors.rejectValue("name", "", "Such band is already exists");
			}
		}
		
	}

}
