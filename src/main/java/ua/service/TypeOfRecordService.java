package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.TypeOfRecord;
import ua.form.TypeOfRecordFilter;

public interface TypeOfRecordService {

	TypeOfRecord addTypeOfRecord (TypeOfRecord name);
	
	void save(TypeOfRecord typeOfRecord);
	
	TypeOfRecord findByName(String name);
	
	List<TypeOfRecord> findAll();

	void delete(int id);

	TypeOfRecord findOne(int id);

	Page<TypeOfRecord> findAll(Pageable pageable);

	Page<TypeOfRecord> findAll(Pageable pageable, TypeOfRecordFilter typeOfRecordFilter);
}
