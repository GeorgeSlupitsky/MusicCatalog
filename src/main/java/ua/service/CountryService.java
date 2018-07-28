package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Country;
import ua.form.CountryFilter;
import ua.form.CountryForm;

public interface CountryService {

	Country addCountry (Country name);
	
	void save(CountryForm form);
	
	Country findByName(String name);
	
	List<Country> findAll();

	void delete(int id);

	CountryForm findOneForm(int id);
	
	Country findOne(int id);

	Page<Country> findAll(Pageable pageable);

	Page<Country> findAll(Pageable pageable, CountryFilter countryFilter);
}
