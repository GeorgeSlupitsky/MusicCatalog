package ua.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(indexes={@Index(columnList = "name"),
		@Index(columnList = "yearOfRelease"),
		@Index(columnList = "albumRating")})
public class Album {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int yearOfRelease;
	
	private int albumRating;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Genre genre;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TypeOfAlbum typeOfAlbum;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TypeOfRecord typeOfRecord;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Country country;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Label label;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Band band;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Album_Collection", joinColumns =
	@JoinColumn(name = "album_id"), inverseJoinColumns = 
	@JoinColumn(name = "collection_id"))
	private List<Collection> collections = new ArrayList<Collection>();
	
	private String path;
	
	private int version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public int getAlbumRating() {
		return albumRating;
	}

	public void setAlbumRating(int albumRating) {
		this.albumRating = albumRating;
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

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public List<Collection> getCollections() {
		return collections;
	}

	public void setCollections(List<Collection> collection) {
		this.collections = collection;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
