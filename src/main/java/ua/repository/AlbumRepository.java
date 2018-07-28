package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer>, JpaSpecificationExecutor<Album>  {

	@Query("SELECT a FROM Album a LEFT JOIN FETCH a.genre g LEFT JOIN FETCH a.typeOfAlbum ta LEFT JOIN FETCH a.typeOfRecord tr LEFT JOIN FETCH a.country c LEFT JOIN FETCH a.label l LEFT JOIN FETCH a.band")
	List<Album> findAll();
	
	@Query("SELECT a FROM Album a LEFT JOIN FETCH a.genre g LEFT JOIN FETCH a.typeOfAlbum ta LEFT JOIN FETCH a.typeOfRecord tr LEFT JOIN FETCH a.country c LEFT JOIN FETCH a.label l LEFT JOIN FETCH a.band WHERE a.id=:id")
	Album findOneGenreAndTypeOfAlbumAndTypeOfRecordAndCountryAndLabelInited(@Param("id")int id);

	@Query("SELECT a FROM Album a JOIN a.collections c LEFT JOIN FETCH a.genre LEFT JOIN FETCH a.typeOfAlbum LEFT JOIN FETCH a.typeOfRecord LEFT JOIN FETCH a.country LEFT JOIN FETCH a.label LEFT JOIN FETCH a.band where c.id=?1") 
	List<Album> findByCollectionId(int id);

	Album findByName (String name);

	Album findByBand(String name);

	Album findByCountry(String name);

	Album findByTypeOfRecord(String name);

}
