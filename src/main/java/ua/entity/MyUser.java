package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class MyUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887773884048342667L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
		private String name;
		
		private String surname;
		
		private String login;
		
		private String password;
		
		private String email;
		
		private String path;
		
		private int version;
		
		@ManyToOne(fetch=FetchType.LAZY)
		private Country country;
		
		@OneToMany(mappedBy="user")
		private List<Collection> collections = new ArrayList<>();
		
		@Enumerated
		private Role role;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Country getCountry() {
			return country;
		}

		public void setCountry(Country country) {
			this.country = country;
		}

		public List<Collection> getCollections() {
			return collections;
		}

		public void setCollections(List<Collection> collection) {
			this.collections = collection;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		@Override
		public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role.name());
			List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>(1);
			roles.add(sga);
			return roles;
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public String getUsername() {
			return String.valueOf(id);
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
		
	}
