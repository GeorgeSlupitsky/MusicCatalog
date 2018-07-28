package ua.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Label;
import ua.form.LabelFilter;
import ua.form.LabelForm;
import ua.repository.LabelRepository;
import ua.service.LabelService;
import ua.service.implementation.specification.LabelFilterSpecification;

@Service
@Transactional
public class LabelServiceImplementation implements LabelService {

	@Autowired
	LabelRepository labelRepository;
	
	@Override
	public Label addLabel(Label name) {
		return labelRepository.save(name);
	}

	@Override
	@Transactional
	public void save(LabelForm form) {
		Label label = new Label();
		label.setId(form.getId());
		label.setName(form.getName());
		label.setPath(form.getPath());
		label.setVersion(form.getVersion());
		label.setAlbums(form.getAlbums());
		labelRepository.saveAndFlush(label);
		if(form.getFile()!=null && !form.getFile().isEmpty()){
			int index = form.getFile().getOriginalFilename().lastIndexOf(".");
			String extention = form.getFile().getOriginalFilename().substring(index);
			String path = System.getProperty("catalina.home")+"/images/label/";
			File file = new File(path);
			if(!file.exists())file.mkdirs();
			file = new File(file, label.getName()+extention);
			try {
				form.getFile().transferTo(file);
			} catch (IllegalStateException | IOException e) {
			}
			label.setPath(extention);
			label.setVersion(form.getVersion()+1);
			labelRepository.save(label);
		}
	}

	@Override
	public Label findByName(String name) {
		return labelRepository.findByName(name);
	}

	@Override
	public List<Label> findAll() {
		return labelRepository.findAll();
	}

	@Override
	public void delete(int id) {
		labelRepository.delete(id);		
	}

	@Override
	public LabelForm findOneForm(int id) {
		Label label = labelRepository.findOne(id);
		LabelForm form = new LabelForm();
		form.setId(label.getId());
		form.setName(label.getName());
		form.setPath(label.getPath());
		form.setVersion(label.getVersion());
		form.setAlbums(label.getAlbums());
		return form;
	}

	@Override
	public Page<Label> findAll(Pageable pageable) {
		return labelRepository.findAll(pageable);
	}

	@Override
	public Page<Label> findAll(Pageable pageable, LabelFilter labelFilter) {
		return labelRepository.findAll(new LabelFilterSpecification(labelFilter), pageable);
	}

	@Override
	public Label findOne(int id) {
		return labelRepository.findOne(id);
	}
}
