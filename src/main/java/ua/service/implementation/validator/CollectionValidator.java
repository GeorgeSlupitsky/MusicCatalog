package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.form.CollectionForm;

public class CollectionValidator implements Validator {

	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9 ]+");
	
	@Override
	public boolean supports(Class<?> arg0) {
		return CollectionForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		CollectionForm form = (CollectionForm) obj;
		Matcher matcher = pattern.matcher(form.getName());
		if(!matcher.matches()){
			errors.rejectValue("name", "", "Collection name can't contain such signs or be empty");
		}
		
	}

}
