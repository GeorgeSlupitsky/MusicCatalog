package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Album;
import ua.service.AlbumService;

public class AlbumEditor extends PropertyEditorSupport {

	private final AlbumService albumService;

	public AlbumEditor(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Album album = albumService.findOne(Integer.valueOf(text));
		setValue(album);
	}
}
