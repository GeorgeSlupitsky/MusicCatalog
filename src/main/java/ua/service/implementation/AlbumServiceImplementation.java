package ua.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Album;
import ua.entity.Collection;
import ua.form.AlbumFilter;
import ua.form.AlbumForm;
import ua.repository.AlbumRepository;
import ua.repository.CollectionRepository;
import ua.service.AlbumService;
import ua.service.implementation.specification.AlbumFilterAdapter;

@Service
@Transactional
public class AlbumServiceImplementation implements AlbumService {

	@Autowired
	AlbumRepository albumRepository;
	
	@Autowired
	CollectionRepository collectionRepository;
	
	@Override
	public Album addAlbum(Album name) {
		return albumRepository.save(name);
	}

	@Override
	@Transactional
	public void save(AlbumForm form) {
		Album album = new Album();
		album.setId(form.getId());
		album.setGenre(form.getGenre());
		album.setTypeOfAlbum(form.getTypeOfAlbum());
		album.setTypeOfRecord(form.getTypeOfRecord());
		album.setCountry(form.getCountry());
		album.setLabel(form.getLabel());
		album.setBand(form.getBand());
		album.setName(form.getName());
		album.setYearOfRelease(Integer.parseInt(form.getYearOfRelease()));
		album.setAlbumRating(Integer.parseInt(form.getAlbumRating()));
		album.setPath(form.getPath());
		album.setVersion(form.getVersion());
		albumRepository.saveAndFlush(album);
		if(form.getFile()!=null && !form.getFile().isEmpty()){
			int index = form.getFile().getOriginalFilename().lastIndexOf(".");
			String extention = form.getFile().getOriginalFilename().substring(index);
			String path = System.getProperty("catalina.home")+"/images/album/"+album.getBand().getName();
			File file = new File(path);
			if(!file.exists())file.mkdirs();
			file = new File(file, album.getName()+extention);
			try {
				form.getFile().transferTo(file);
			} catch (IllegalStateException | IOException e) {
			}
			album.setPath(extention);
			album.setVersion(form.getVersion()+1);
			albumRepository.save(album);
		}
	}

	@Override
	public Album findByName(String name) {
		return albumRepository.findByName(name);
	}
	
	@Override
	public Album findByBand(String name) {
		return albumRepository.findByBand(name);
	}
	
	@Override
	public Album findByTypeOfRecord(String name) {
		return albumRepository.findByTypeOfRecord(name);
	}
	
	@Override
	public Album findByCountry(String name) {
		return albumRepository.findByCountry(name);
	}

	@Override
	public List<Album> findAll() {
		return albumRepository.findAll();
	}

	@Override
	public void delete(int id) {
		albumRepository.delete(id);		
	}

	@Override
	public AlbumForm findOneForm(int id) {
		Album album = albumRepository.findOneGenreAndTypeOfAlbumAndTypeOfRecordAndCountryAndLabelInited(id);
		AlbumForm form = new AlbumForm();
		form.setId(album.getId());
		form.setGenre(album.getGenre());
		form.setTypeOfAlbum(album.getTypeOfAlbum());
		form.setTypeOfRecord(album.getTypeOfRecord());
		form.setCountry(album.getCountry());
		form.setLabel(album.getLabel());
		form.setBand(album.getBand());
		form.setName(album.getName());
		form.setYearOfRelease(String.valueOf(album.getYearOfRelease()));
		form.setAlbumRating(String.valueOf(album.getAlbumRating()));
		form.setPath(album.getPath());
		form.setVersion(album.getVersion());
		return form;
	}

	@Override
	public Page<Album> findAll(Pageable pageable) {
		return albumRepository.findAll(pageable);
	}

	@Override
	public Page<Album> findAll(Pageable pageable, AlbumFilter albumFilter) {
		return albumRepository.findAll(new AlbumFilterAdapter(albumFilter), pageable);
	}

	@Override
	public Album findOne(int id) {
		return albumRepository.findOne(id);
	}

	@Transactional
	@Override
	public boolean addAlbumToCollection(int colId, int id) {
		boolean add = false;
		Collection collection = collectionRepository.findOne(colId);
		Album album = albumRepository.findOne(id);
		if(collection!=null){
			collection.getAlbums().add(album);
			collectionRepository.save(collection);
			add = true;
		}
		return add;
	}

	@Transactional
	@Override
	public void removeAlbumFromCollection(int colId, int id) {
		Collection collection = collectionRepository.findOne(colId);
		Album album = albumRepository.findOne(id);
		int albumId = album.getId();
		collection.getAlbums().removeIf((a)-> a.getId() == albumId);
	}

	@Override
	public List<Album> findAll(AlbumFilter albumFilter) {
		return albumRepository.findAll(new AlbumFilterAdapter(albumFilter));
	}

	

}
