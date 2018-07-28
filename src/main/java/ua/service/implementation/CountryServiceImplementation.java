package ua.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Country;
import ua.form.CountryFilter;
import ua.form.CountryForm;
import ua.repository.CountryRepository;
import ua.service.CountryService;
import ua.service.implementation.specification.CountryFilterSpecification;

@Service
@Transactional
public class CountryServiceImplementation implements CountryService {

	@Autowired
	CountryRepository countryRepository;
	
	@Override
	public Country addCountry(Country name) {
		return countryRepository.save(name);
	}

	@Override
	@Transactional
	public void save(CountryForm form) {
		Country country = new Country();
		country.setId(form.getId());
		country.setAbbreviation(form.getAbbreviation());
		country.setName(form.getName());
		country.setNameRU(form.getNameRU());
		country.setNameUA(form.getNameUA());
		country.setPath(form.getPath());
		country.setVersion(form.getVersion());
		country.setAlbums(form.getAlbums());
		country.setBands(form.getBands());
		country.setUsers(form.getUsers());
		countryRepository.saveAndFlush(country);
		if(form.getFile()!=null && !form.getFile().isEmpty()){
			int index = form.getFile().getOriginalFilename().lastIndexOf(".");
			String extention = form.getFile().getOriginalFilename().substring(index);
			String path = System.getProperty("catalina.home")+"/images/country/";
			File file = new File(path);
			if(!file.exists())file.mkdirs();
			file = new File(file, country.getId()+extention);
			try {
				form.getFile().transferTo(file);
			} catch (IllegalStateException | IOException e) {
			}
			country.setPath(extention);
			country.setVersion(form.getVersion()+1);
			countryRepository.save(country);
		}
	}

	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public void delete(int id) {
		countryRepository.delete(id);		
	}

	@Override
	public CountryForm findOneForm(int id) {
		Country country = countryRepository.findOne(id);
		CountryForm form = new CountryForm();
		form.setId(country.getId());
		form.setAbbreviation(country.getAbbreviation());
		form.setName(country.getName());
		form.setNameRU(country.getNameRU());
		form.setNameUA(country.getNameUA());
		form.setPath(country.getPath());
		form.setVersion(country.getVersion());
		form.setAlbums(country.getAlbums());
		form.setBands(country.getBands());
		form.setUsers(country.getUsers());
		return form;
	}

	@Override
	public Page<Country> findAll(Pageable pageable) {
		return countryRepository.findAll(pageable);
	}

	@Override
	public Page<Country> findAll(Pageable pageable, CountryFilter countryFilter) {
		return countryRepository.findAll(new CountryFilterSpecification(countryFilter), pageable);
	}

	@Override
	public Country findOne(int id) {
		return countryRepository.findOne(id);
	}

}
