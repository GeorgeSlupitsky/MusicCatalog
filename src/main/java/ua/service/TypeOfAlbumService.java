package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.TypeOfAlbum;
import ua.form.TypeOfAlbumFilter;

public interface TypeOfAlbumService {

	TypeOfAlbum addTypeOfAlbum (TypeOfAlbum name);
	
	void save(TypeOfAlbum typeOfAlbum);
	
	TypeOfAlbum findByName(String name);
	
	List<TypeOfAlbum> findAll();

	void delete(int id);

	TypeOfAlbum findOne(int id);

	Page<TypeOfAlbum> findAll(Pageable pageable);

	Page<TypeOfAlbum> findAll(Pageable pageable, TypeOfAlbumFilter typeOfAlbumFilter);
}
