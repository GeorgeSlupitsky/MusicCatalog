package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Label;
import ua.service.LabelService;

public class LabelEditor extends PropertyEditorSupport {

	private final LabelService labelService;

	public LabelEditor(LabelService labelService) {
		this.labelService = labelService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Label label = labelService.findOne(Integer.valueOf(text));
		setValue(label);
	}
}
