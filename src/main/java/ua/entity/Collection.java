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
@Table(indexes={@Index(columnList = "name")})
public class Collection {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private MyUser user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Album_Collection", joinColumns =
	@JoinColumn(name = "collection_id"), inverseJoinColumns = 
	@JoinColumn(name = "album_id"))
	private List<Album> albums = new ArrayList<>();
	
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

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> album) {
		this.albums = album;
	}
	
}
