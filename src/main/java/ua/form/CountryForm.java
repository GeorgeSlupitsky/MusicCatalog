package ua.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Album;
import ua.entity.Band;
import ua.entity.MyUser;

public class CountryForm {

	private int id;
	
	private String abbreviation;
	
	private String name;
	
	private String nameRU;
	
	private String nameUA;
	
	private int version;
	
	private String path;
	
	private MultipartFile file;
	
	private List<Band> bands;
	
	private List<Album> albums;
	
	private List<MyUser> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameRU() {
		return nameRU;
	}

	public void setNameRU(String nameRU) {
		this.nameRU = nameRU;
	}

	public String getNameUA() {
		return nameUA;
	}

	public void setNameUA(String nameUA) {
		this.nameUA = nameUA;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<Band> getBands() {
		return bands;
	}

	public void setBands(List<Band> bands) {
		this.bands = bands;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<MyUser> getUsers() {
		return users;
	}

	public void setUsers(List<MyUser> users) {
		this.users = users;
	}
	
}
