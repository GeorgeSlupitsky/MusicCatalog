package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

	@Query("SELECT distinct c FROM Collection c LEFT JOIN FETCH c.albums LEFT JOIN FETCH c.user")
	List<Collection> findAll();
	
	@Query("SELECT c FROM Collection c LEFT JOIN FETCH c.user WHERE c.id=:id")
	Collection findOneUserInited(@Param("id")int id);
	
	Collection findByName (String name);


}
