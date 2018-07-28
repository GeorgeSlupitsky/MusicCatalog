package ua.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Collection;
import ua.entity.MyUser;
import ua.entity.Role;
import ua.form.MyUserForm;
import ua.repository.AlbumRepository;
import ua.repository.MyUserRepository;
import ua.service.MyUserService;

@Service("userDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService, MyUserService{
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if(Pattern.matches("^[0-9]{1,8}$", username)){
			return myUserRepository.findOne(Integer.valueOf(username));
		}
		return myUserRepository.findByLogin(username);
	}
	
	@PostConstruct
	public void postConstruct() {
	MyUser user = myUserRepository.findOne(1);
		if (user == null) {
			user = new MyUser();
			user.setId(1);
			user.setName("George");
			user.setSurname("Slupitsky");
			user.setEmail("postpunk91@gmail.com");
			user.setLogin("admin");
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(encoder.encode("1111"));
			myUserRepository.save(user);
		}
	}

	@Override
	@Transactional
	public void save(MyUserForm form) {
		MyUser user = new MyUser();
		user.setName(form.getName());
		user.setSurname(form.getSurname());
		user.setEmail(form.getEmail());
		user.setCountry(form.getCountry());
		user.setLogin(form.getLogin());
		user.setVersion(form.getVersion());
		user.setPath(form.getPath());
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(form.getPassword()));
		user.setCollections(form.getCollections());
		myUserRepository.saveAndFlush(user);
		if(form.getFile()!=null && !form.getFile().isEmpty()){
			int index = form.getFile().getOriginalFilename().lastIndexOf(".");
			String extention = form.getFile().getOriginalFilename().substring(index);
			String path = System.getProperty("catalina.home")+"/images/myUser/";
			File file = new File(path);
			if(!file.exists())file.mkdirs();
			file = new File(file, user.getId()+extention);
			try {
				form.getFile().transferTo(file);
			} catch (IllegalStateException | IOException e) {
			}
			user.setPath(extention);
			user.setVersion(form.getVersion()+1);
			myUserRepository.save(user);
		}
	}

	@Override
	public MyUser findOne(int id) {
		return myUserRepository.findOne(id);
	}
	
	@Override
	public MyUserForm findOneForm(int id) {
		MyUser user = myUserRepository.findOneCountryInited(id);
		MyUserForm form = new MyUserForm();
		form.setId(user.getId());
		form.setName(user.getName());
		form.setSurname(user.getSurname());
		form.setEmail(user.getEmail());
		form.setCountry(user.getCountry());
		form.setPath(user.getPath());
		form.setVersion(user.getVersion());
		form.setCollections(user.getCollections());
		return form;
	}

	@Override
	public List<MyUser> findAll() {
		return myUserRepository.findAll();
	}


	@Override
	public MyUser findByLogin(String login) {
		return myUserRepository.findByLogin(login);
	}
	
	@Override
	public MyUser findByEmail(String email) {
		return myUserRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	@Override
	public MyUser findOneCollectionInited(int id) {
		MyUser user = myUserRepository.findOneCollectionInited(id);
		for (Collection collection: user.getCollections()){
			collection.setAlbums(albumRepository.findByCollectionId(collection.getId()));
		}
		return user;
	}

	@Override
	public MyUser findOneCountryInited(int id) {
		return myUserRepository.findOneCountryInited(id);
	}

	@Override
	public void saveUser(MyUserForm form, int id) {
		MyUser user = findOne(id);
		user.setName(form.getName());
		user.setSurname(form.getSurname());
		user.setEmail(form.getEmail());
		user.setCountry(form.getCountry());
		user.setLogin(form.getLogin());
		user.setPassword(encoder.encode(form.getPassword()));
		user.setCollections(form.getCollections());
		myUserRepository.saveAndFlush(user);
		if(form.getFile()!=null && !form.getFile().isEmpty()){
			user.setVersion(form.getVersion());
			user.setPath(form.getPath());
			int index = form.getFile().getOriginalFilename().lastIndexOf(".");
			String extention = form.getFile().getOriginalFilename().substring(index);
			String path = System.getProperty("catalina.home")+"/images/myUser/";
			File file = new File(path);
			if(!file.exists())file.mkdirs();
			file = new File(file, user.getId()+extention);
			try {
				form.getFile().transferTo(file);
			} catch (IllegalStateException | IOException e) {
			}
			user.setPath(extention);
			user.setVersion(form.getVersion()+1);
			myUserRepository.save(user);
		}		
	}
}
