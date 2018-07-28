package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Collection;
import ua.form.CollectionForm;
import ua.repository.CollectionRepository;
import ua.service.CollectionService;

@Service
@Transactional
public class CollectionServiceImplementation implements CollectionService{

	@Autowired
	CollectionRepository collectionRepository;
	
	@Override
	public Collection addCollection(Collection name) {		
		return collectionRepository.save(name);
	}

	@Override
	public void save(CollectionForm form) {
		Collection collection = new Collection();
		collection.setId(form.getId());
		collection.setName(form.getName());
		collection.setUser(form.getUser());
		collectionRepository.save(collection);
	}

	@Override
	public Collection findByName(String name) {
		return collectionRepository.findByName(name);
	}

	@Override
	public List<Collection> findAll() {
		return collectionRepository.findAll();
	}

	@Override
	public void delete(int id) {
		collectionRepository.delete(id);
		
	}

	@Override
	public CollectionForm findOneForm(int id) {
		Collection collection = collectionRepository.findOneUserInited(id);
		CollectionForm form = new CollectionForm();
		form.setId(collection.getId());
		form.setName(collection.getName());
		form.setUser(collection.getUser());
		return form;
	}

	@Override
	public Page<Collection> findAll(Pageable pageable) {
		return collectionRepository.findAll(pageable);
	}

	@Override
	public Collection findOne(int id) {
		return collectionRepository.findOne(id);
	}

	@Override
	public Collection findOneUserInited(int id) {
		return collectionRepository.findOneUserInited(id);
	}


}
