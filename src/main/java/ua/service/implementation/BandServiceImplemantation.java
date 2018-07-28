package ua.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Band;
import ua.form.BandFilter;
import ua.form.BandForm;
import ua.form.BandsViewFilter;
import ua.repository.BandRepository;
import ua.service.BandService;
import ua.service.implementation.specification.BandFilterAdapter;
import ua.service.implementation.specification.BandsViewFilterSpecification;

@Service
@Transactional
public class BandServiceImplemantation implements BandService {

	@Autowired
	BandRepository bandRepository;
	
	@Override
	public Band addBand(Band name) {
		return bandRepository.save(name);
	}

	@Override
	@Transactional
	public void save(BandForm form) {
		Band band = new Band();
		band.setId(form.getId());
		band.setCountry(form.getCountry());
		band.setName(form.getName());
		band.setFoundationYear(Integer.parseInt(form.getFoundationYear()));
		band.setPath(form.getPath());
		band.setPath2(form.getPath2());
		band.setVersion(form.getVersion());
		band.setVersion2(form.getVersion2());
		band.setAlbums(form.getAlbums());
		bandRepository.saveAndFlush(band);
		if(form.getFile()!=null && !form.getFile().isEmpty()){
			int index = form.getFile().getOriginalFilename().lastIndexOf(".");
			String extention = form.getFile().getOriginalFilename().substring(index);
			String path = System.getProperty("catalina.home")+"/images/band/";
			File file = new File(path);
			if(!file.exists())file.mkdirs();
			file = new File(file, band.getName()+1+extention);
			try {
				form.getFile().transferTo(file);
			} catch (IllegalStateException | IOException e) {
			}
			band.setPath(extention);
			band.setVersion(form.getVersion()+1);
			bandRepository.save(band);
		}
		if(form.getFile2()!=null && !form.getFile2().isEmpty()){
			int index2 = form.getFile2().getOriginalFilename().lastIndexOf(".");
			String extention2 = form.getFile2().getOriginalFilename().substring(index2);
			String path2 = System.getProperty("catalina.home")+"/images/band/";
			File file2 = new File(path2);
			if(!file2.exists())file2.mkdirs();
			file2 = new File(file2, band.getName()+2+extention2);
			try {
				form.getFile2().transferTo(file2);
			} catch (IllegalStateException | IOException e) {
			}
			band.setPath2(extention2);
			band.setVersion2(form.getVersion2()+1);
			bandRepository.save(band);
		}
	}

	@Override
	public Band findByName(String name) {
		return bandRepository.findByName(name);
	}

	@Override
	public List<Band> findAll() {
		return bandRepository.findAll();
	}

	@Override
	public void delete(int id) {
		bandRepository.delete(id);
		
	}

	@Override
	public BandForm findOneForm(int id) {
		Band band = bandRepository.findOneCountryInited(id);
		BandForm form = new BandForm();
		form.setId(band.getId());
		form.setCountry(band.getCountry());
		form.setName(band.getName());
		form.setFoundationYear(String.valueOf(band.getFoundationYear()));
		form.setPath(band.getPath());
		form.setVersion(band.getVersion());
		form.setPath2(band.getPath2());
		form.setVersion2(band.getVersion2());
		form.setAlbums(band.getAlbums());
		return form;
	}

	@Override
	public Page<Band> findAll(Pageable pageable) {
		return bandRepository.findAll(pageable);
	}

	@Override
	public Page<Band> findAll(Pageable pageable, BandFilter bandFilter) {
		return bandRepository.findAll(new BandFilterAdapter(bandFilter), pageable);
	}

	@Override
	public Band findOne(int id) {
		return bandRepository.findOne(id);
	}

	@Override
	public Page<Band> findAll(Pageable pageable, BandsViewFilter bandsViewFilter) {
		return bandRepository.findAll(new BandsViewFilterSpecification(bandsViewFilter), pageable);
	}



}
