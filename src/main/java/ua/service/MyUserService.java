package ua.service;

import java.util.List;

import ua.entity.MyUser;
import ua.form.MyUserForm;

public interface MyUserService {

	void save(MyUserForm form);
	
	MyUser findOne(int id);

	MyUserForm findOneForm(int id);

	List<MyUser> findAll();

	MyUser findByLogin(String login);
	
	MyUser findByEmail(String email);
	
	MyUser findOneCollectionInited(int id);

	MyUser findOneCountryInited(int id);
	
	void saveUser(MyUserForm form, int id);
	
}
