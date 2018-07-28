package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Band;

public interface BandRepository extends JpaRepository<Band, Integer>, JpaSpecificationExecutor<Band>  {

	@Query(value="SELECT distinct b FROM Band b LEFT JOIN FETCH b.country LEFT JOIN FETCH b.albums a LEFT JOIN FETCH a.genre LEFT JOIN FETCH a.typeOfAlbum LEFT JOIN FETCH a.label LEFT JOIN FETCH a.typeOfRecord LEFT JOIN FETCH a.country",
			countQuery="SELECT count(b) FROM Band b")
	Page<Band> findAll(Pageable pageable);
	
	@Query("SELECT b FROM Band b LEFT JOIN FETCH b.country WHERE b.id=:id")
	Band findOneCountryInited(@Param("id")int id);

	Band findByName (String name);

}
