package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.TypeOfRecord;
import ua.service.TypeOfRecordService;

public class TypeOfRecordValidator implements Validator{

	private final TypeOfRecordService typeOfRecordService;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє ]+");
	
	public TypeOfRecordValidator(TypeOfRecordService typeOfRecordService) {
		this.typeOfRecordService = typeOfRecordService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return TypeOfRecord.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		TypeOfRecord typeOfRecord = (TypeOfRecord) obj;
		Matcher matcher = pattern.matcher(typeOfRecord.getName());
		if(!matcher.matches()){
			errors.rejectValue("name", "", "Type of record name can't contain such signs or be empty");
		}
		if(typeOfRecordService.findByName(typeOfRecord.getName())!=null){
			errors.rejectValue("name", "", "Such type of record is already exists");
		}
	}

}
