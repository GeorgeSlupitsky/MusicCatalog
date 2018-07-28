package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.TypeOfRecord;

public interface TypeOfRecordRepository extends JpaRepository<TypeOfRecord, Integer>, JpaSpecificationExecutor<TypeOfRecord> {

	TypeOfRecord findByName (String name);
}
