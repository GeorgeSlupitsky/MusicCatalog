package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.form.LabelForm;
import ua.service.LabelService;

public class LabelValidator implements Validator {

	private final LabelService labelService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9 ]+");

	public LabelValidator(LabelService labelService) {
		this.labelService = labelService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return LabelForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		LabelForm form = (LabelForm) obj;
		if(form.getId()==0){
			Matcher matcher = pattern.matcher(form.getName());
			if(!matcher.matches()){
				errors.rejectValue("name", "", "Label name can't contain such signs or be empty");
			}
			if(labelService.findByName(form.getName())!=null){
				errors.rejectValue("name", "", "Such label is already exists");
			}
		}
	}

}
