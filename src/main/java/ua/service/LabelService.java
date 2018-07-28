package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Label;
import ua.form.LabelFilter;
import ua.form.LabelForm;

public interface LabelService {

	Label addLabel (Label name);
	
	void save(LabelForm form);
	
	Label findByName(String name);
	
	List<Label> findAll();

	void delete(int id);

	LabelForm findOneForm(int id);
	
	Label findOne(int id);

	Page<Label> findAll(Pageable pageable);

	Page<Label> findAll(Pageable pageable, LabelFilter labelFilter);
}
