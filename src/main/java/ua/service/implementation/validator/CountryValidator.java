package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.form.CountryForm;
import ua.service.CountryService;

public class CountryValidator implements Validator {

	private final CountryService countryService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9 ]+");
	
	public CountryValidator(CountryService countryService) {
		this.countryService = countryService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return CountryForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		CountryForm form = (CountryForm) obj;
		if(form.getId()==0){
			Matcher matcher = pattern.matcher(form.getAbbreviation());
			if(!matcher.matches()){
				errors.rejectValue("abbreviation", "", "Abbreviation name can't contain such signs or be empty");
			}
			Matcher matcher2 = pattern.matcher(form.getName());
			if(!matcher2.matches()){
				errors.rejectValue("name", "", "Country name can't contain such signs or be empty");
			}
			if(countryService.findByName(form.getName())!=null){
				errors.rejectValue("name", "", "Such country is already exists");
			}
			Matcher matcher3 = pattern.matcher(form.getNameRU());
			if(!matcher3.matches()){
				errors.rejectValue("nameRU", "", "Country name can't contain such signs or be empty");
			}
			if(countryService.findByName(form.getNameRU())!=null){
				errors.rejectValue("nameRU", "", "Such country is already exists");
			}
			Matcher matcher4 = pattern.matcher(form.getNameUA());
			if(!matcher4.matches()){
				errors.rejectValue("nameUA", "", "Country name can't contain such signs or be empty");
			}
			if(countryService.findByName(form.getNameUA())!=null){
				errors.rejectValue("nameUA", "", "Such country is already exists");
			}
		}
	}
}
