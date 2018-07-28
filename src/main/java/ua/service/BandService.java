package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Band;
import ua.form.BandFilter;
import ua.form.BandForm;
import ua.form.BandsViewFilter;

public interface BandService {

	Band addBand (Band name);
	
	void save(BandForm form);
	
	Band findByName(String name);
	
	List<Band> findAll();

	void delete(int id);

	BandForm findOneForm(int id);
	
	Band findOne(int id);

	Page<Band> findAll(Pageable pageable);

	Page<Band> findAll(Pageable pageable, BandFilter bandFilter);

	Page<Band> findAll(Pageable pageable, BandsViewFilter bandsViewFilter);
}
