package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.TypeOfRecord;
import ua.form.TypeOfRecordFilter;
import ua.repository.TypeOfRecordRepository;
import ua.service.TypeOfRecordService;
import ua.service.implementation.specification.TypeOfRecordFilterSpecification;

@Service
@Transactional
public class TypeOfRecordServiceImplementation implements TypeOfRecordService {

	@Autowired
	TypeOfRecordRepository typeOfRecordRepository;
	
	@Override
	public TypeOfRecord addTypeOfRecord(TypeOfRecord name) {
		return typeOfRecordRepository.save(name);
	}

	@Override
	public void save(TypeOfRecord typeOfRecord) {
		typeOfRecordRepository.save(typeOfRecord);
	}

	@Override
	public TypeOfRecord findByName(String name) {
		return typeOfRecordRepository.findByName(name);
	}

	@Override
	public List<TypeOfRecord> findAll() {
		return typeOfRecordRepository.findAll();
	}

	@Override
	public void delete(int id) {
		typeOfRecordRepository.delete(id);		
	}

	@Override
	public TypeOfRecord findOne(int id) {
		return typeOfRecordRepository.findOne(id);
	}

	@Override
	public Page<TypeOfRecord> findAll(Pageable pageable) {
		return typeOfRecordRepository.findAll(pageable);
	}

	@Override
	public Page<TypeOfRecord> findAll(Pageable pageable, TypeOfRecordFilter typeOfRecordFilter) {
		return typeOfRecordRepository.findAll(new TypeOfRecordFilterSpecification(typeOfRecordFilter), pageable);
	}
}
