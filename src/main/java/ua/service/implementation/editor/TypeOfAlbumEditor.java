package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.TypeOfAlbum;
import ua.service.TypeOfAlbumService;

public class TypeOfAlbumEditor extends PropertyEditorSupport {

	private final TypeOfAlbumService typeOfAlbumService;
	
	public TypeOfAlbumEditor(TypeOfAlbumService typeOfAlbumService) {
		this.typeOfAlbumService = typeOfAlbumService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TypeOfAlbum typeOfAlbum = typeOfAlbumService.findOne(Integer.valueOf(text));
		setValue(typeOfAlbum);
	}
}
