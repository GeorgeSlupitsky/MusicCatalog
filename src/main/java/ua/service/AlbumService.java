package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Album;
import ua.form.AlbumFilter;
import ua.form.AlbumForm;

public interface AlbumService {

	Album addAlbum (Album name);
	
	void save (AlbumForm form);
	
	Album findByName (String name);
	
	Album findByBand (String name);
	
	Album findByTypeOfRecord (String name);
	
	Album findByCountry (String name);
	
	List<Album> findAll();
	
	void delete (int id);

	AlbumForm findOneForm(int id);
	
	Album findOne(int id);

	Page<Album> findAll(Pageable pageable);

	Page<Album> findAll(Pageable pageable, AlbumFilter albumFilter);
	
	boolean addAlbumToCollection(int colId, int id);
	
	void removeAlbumFromCollection (int colId, int id);

	List<Album> findAll(AlbumFilter albumFilter);
	
}
