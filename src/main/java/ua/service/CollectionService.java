package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Collection;
import ua.form.CollectionForm;

public interface CollectionService {

	Collection addCollection (Collection name);
	
	void save(CollectionForm form);
	
	Collection findByName(String name);
	
	List<Collection> findAll();

	void delete(int id);

	CollectionForm findOneForm(int id);
	
	Collection findOne(int id);

	Page<Collection> findAll(Pageable pageable);

	Collection findOneUserInited(int id);

}
