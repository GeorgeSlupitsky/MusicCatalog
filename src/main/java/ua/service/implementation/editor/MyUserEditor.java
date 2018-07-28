package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.MyUser;
import ua.service.MyUserService;

public class MyUserEditor extends PropertyEditorSupport{

	private final MyUserService myUserService;

	public MyUserEditor(MyUserService myUserService) {
		this.myUserService = myUserService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MyUser user = myUserService.findOne(Integer.valueOf(text));
		setValue(user);
	}
}
