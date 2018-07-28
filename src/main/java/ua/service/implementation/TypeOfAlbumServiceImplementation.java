package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.TypeOfAlbum;
import ua.form.TypeOfAlbumFilter;
import ua.repository.TypeOfAlbumRepository;
import ua.service.TypeOfAlbumService;
import ua.service.implementation.specification.TypeOfAlbumFilterSpecification;

@Service
@Transactional
public class TypeOfAlbumServiceImplementation implements TypeOfAlbumService{

	@Autowired
	TypeOfAlbumRepository typeOfAlbumRepository;
	
	@Override
	public TypeOfAlbum addTypeOfAlbum(TypeOfAlbum name) {
		return typeOfAlbumRepository.save(name);
	}

	@Override
	public void save(TypeOfAlbum typeOfAlbum) {
		typeOfAlbumRepository.save(typeOfAlbum);
	}

	@Override
	public TypeOfAlbum findByName(String name) {
		return typeOfAlbumRepository.findByName(name);
	}

	@Override
	public List<TypeOfAlbum> findAll() {
		return typeOfAlbumRepository.findAll();
	}

	@Override
	public void delete(int id) {
		typeOfAlbumRepository.delete(id);		
	}

	@Override
	public TypeOfAlbum findOne(int id) {
		return typeOfAlbumRepository.findOne(id);
	}

	@Override
	public Page<TypeOfAlbum> findAll(Pageable pageable) {
		return typeOfAlbumRepository.findAll(pageable);
	}

	@Override
	public Page<TypeOfAlbum> findAll(Pageable pageable, TypeOfAlbumFilter typeOfAlbumFilter) {
		return typeOfAlbumRepository.findAll(new TypeOfAlbumFilterSpecification(typeOfAlbumFilter), pageable);
	}
}
