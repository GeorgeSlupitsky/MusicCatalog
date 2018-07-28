package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Genre;
import ua.form.GenreFilter;
import ua.repository.GenreRepository;
import ua.service.GenreService;
import ua.service.implementation.specification.GenreFilterSpecification;

@Service
@Transactional
public class GenreServiceImplementation implements GenreService {

	@Autowired
	GenreRepository genreRepository;
	
	@Override
	public Genre addGenre(Genre name) {
		return genreRepository.save(name);
	}

	@Override
	public void save(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	public Genre findByName(String name) {
		return genreRepository.findByName(name);
	}

	@Override
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	@Override
	public void delete(int id) {
		genreRepository.delete(id);		
	}

	@Override
	public Genre findOne(int id) {
		return genreRepository.findOne(id);
	}

	@Override
	public Page<Genre> findAll(Pageable pageable) {
		return genreRepository.findAll(pageable);
	}

	@Override
	public Page<Genre> findAll(Pageable pageable, GenreFilter genreFilter) {
		return genreRepository.findAll(new GenreFilterSpecification(genreFilter), pageable);
	}

}
