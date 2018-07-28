package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Genre;
import ua.form.GenreFilter;

public interface GenreService {

	Genre addGenre (Genre name);
	
	void save(Genre genre);
	
	Genre findByName(String name);
	
	List<Genre> findAll();

	void delete(int id);

	Genre findOne(int id);

	Page<Genre> findAll(Pageable pageable);

	Page<Genre> findAll(Pageable pageable, GenreFilter genreFilter);
}
