package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Band;
import ua.service.BandService;

public class BandEditor extends PropertyEditorSupport {

	private final BandService bandService;

	public BandEditor(BandService bandService) {
		this.bandService = bandService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Band band = bandService.findOne(Integer.valueOf(text));
		setValue(band);
	}
	
}
