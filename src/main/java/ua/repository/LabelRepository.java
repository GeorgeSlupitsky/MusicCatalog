package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Integer>, JpaSpecificationExecutor<Label>  {
	
	@Query("SELECT l FROM Label l WHERE l.id=:id")
	Label findOne(@Param("id")int id);
	
	Label findByName (String name); 
}
