package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.TypeOfRecord;
import ua.service.TypeOfRecordService;

public class TypeOfRecordEditor extends PropertyEditorSupport {

	private final TypeOfRecordService typeOfRecordService;

	public TypeOfRecordEditor(TypeOfRecordService typeOfRecordService) {
		this.typeOfRecordService = typeOfRecordService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TypeOfRecord typeOfRecord = typeOfRecordService.findOne(Integer.valueOf(text));
		setValue(typeOfRecord);
	}
}
