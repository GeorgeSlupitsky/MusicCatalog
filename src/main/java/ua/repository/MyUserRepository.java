package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer>{

	@Query("SELECT distinct mu FROM MyUser mu LEFT JOIN FETCH mu.country LEFT JOIN FETCH mu.collections")
	List<MyUser> findAll();
	
	@Query("SELECT mu FROM MyUser mu LEFT JOIN FETCH mu.country WHERE mu.id=:id")
	MyUser findOneCountryInited (@Param("id")int id);
	
	@Query("SELECT distinct mu FROM MyUser mu LEFT JOIN FETCH mu.collections WHERE mu.id=:id")
	MyUser findOneCollectionInited (@Param("id")int id);
	
	MyUser findByLogin(String login);

	MyUser findByEmail(String email);

}
