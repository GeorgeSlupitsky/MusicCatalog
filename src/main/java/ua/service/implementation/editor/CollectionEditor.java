package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Collection;
import ua.service.CollectionService;

public class CollectionEditor extends PropertyEditorSupport {

	private final CollectionService collectionService;

	public CollectionEditor(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Collection collection = collectionService.findOne(Integer.valueOf(text));
		setValue(collection);
	}
}
