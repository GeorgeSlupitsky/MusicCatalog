package ua.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Album;
import ua.entity.Country;

public class BandForm {

	private int id;
	
	private Country country;
	
	private String name;
	
	private String foundationYear;
	
	private int version;
	
	private String path;
	
	private MultipartFile file;
	
	private int version2;
	
	private String path2;
	
	private MultipartFile file2;
	
	private List<Album> albums;

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(String foundationYear) {
		this.foundationYear = foundationYear;
	}

	public int getVersion2() {
		return version2;
	}

	public void setVersion2(int version2) {
		this.version2 = version2;
	}

	public String getPath2() {
		return path2;
	}

	public void setPath2(String path2) {
		this.path2 = path2;
	}

	public MultipartFile getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
	
}
