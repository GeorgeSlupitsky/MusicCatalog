package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.TypeOfAlbum;

public interface TypeOfAlbumRepository extends JpaRepository<TypeOfAlbum, Integer>, JpaSpecificationExecutor<TypeOfAlbum> {
	
	TypeOfAlbum findByName (String name);

}
