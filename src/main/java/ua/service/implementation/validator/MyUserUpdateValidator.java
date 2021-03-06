package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.entity.MyUser;
import ua.form.MyUserForm;
import ua.service.MyUserService;

public class MyUserUpdateValidator implements Validator{

	private final MyUserService service;
	private final Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє]+");
	private final Pattern pattern2 = Pattern.compile("[a-zA-Zа-яА-ЯІіЇїЄє0-9]+");
	private final Pattern pattern3 = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public MyUserUpdateValidator(MyUserService service) {
		this.service = service;
	}



	@Override
	public boolean supports(Class<?> arg0) {
		return MyUserForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		int id = Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
//		MyUser user = (MyUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MyUser user = service.findOne(id);
		MyUserForm form = (MyUserForm) obj;
		Matcher matcher = pattern.matcher(form.getName());
		if(!matcher.matches()){
			errors.rejectValue("name", "", "User name can't contain such signs or be empty");
		}
		Matcher matcher2 = pattern.matcher(form.getSurname());
		if(!matcher2.matches()){
			errors.rejectValue("surname", "", "User surname can't contain such signs or be empty");
		}
		Matcher matcher3 = pattern2.matcher(form.getLogin());
		if(!form.getLogin().equalsIgnoreCase(user.getLogin())){
			if(service.findByLogin(form.getLogin())!=null){
				errors.rejectValue("login", "", "Such login is already exists");
			}	
		}
		if(!matcher3.matches()){
			errors.rejectValue("login", "", "User login can't contain such signs or be empty");
		}
		Matcher matcher4 = pattern3.matcher(form.getEmail());
		if(!form.getEmail().equalsIgnoreCase(user.getEmail())){
			if(service.findByEmail(form.getLogin())!=null){
				errors.rejectValue("email", "", "Such login is already exists");
			}	
		}
		if(!matcher4.matches()){
			errors.rejectValue("email", "", "This field can't contain such signs or be empty");
		}
	}
}
