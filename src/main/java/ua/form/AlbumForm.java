package ua.form;



import org.springframework.web.multipart.MultipartFile;

import ua.entity.Band;
import ua.entity.Country;
import ua.entity.Genre;
import ua.entity.Label;
import ua.entity.TypeOfAlbum;
import ua.entity.TypeOfRecord;

public class AlbumForm {

	private int id;
	
	private Genre genre;
	
	private TypeOfAlbum typeOfAlbum;
	
	private TypeOfRecord typeOfRecord;
	
	private Country country;
	
	private Label label;
	
	private Band band;
	
	private String name;
	
	private String yearOfRelease;
	
	private String albumRating;
	
	private int version;
	
	private String path;
	
	private MultipartFile file;

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public TypeOfAlbum getTypeOfAlbum() {
		return typeOfAlbum;
	}

	public void setTypeOfAlbum(TypeOfAlbum typeOfAlbum) {
		this.typeOfAlbum = typeOfAlbum;
	}

	public TypeOfRecord getTypeOfRecord() {
		return typeOfRecord;
	}

	public void setTypeOfRecord(TypeOfRecord typeOfRecord) {
		this.typeOfRecord = typeOfRecord;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(String yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public String getAlbumRating() {
		return albumRating;
	}

	public void setAlbumRating(String albumRating) {
		this.albumRating = albumRating;
	}
	
}
