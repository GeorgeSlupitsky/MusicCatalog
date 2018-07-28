package ua.form;

import ua.entity.MyUser;

public class CollectionForm {

	private int id;
	
	private String name;
	
	private MyUser user;

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
