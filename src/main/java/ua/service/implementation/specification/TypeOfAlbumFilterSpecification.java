package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.TypeOfAlbum;
import ua.form.TypeOfAlbumFilter;

public class TypeOfAlbumFilterSpecification implements Specification<TypeOfAlbum>{

	private final TypeOfAlbumFilter filter;

	public TypeOfAlbumFilterSpecification(TypeOfAlbumFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<TypeOfAlbum> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter!=null&&filter.getName()!=null&&!filter.getName().isEmpty())
			return cb.like(cb.lower(root.get("name")), "%"+filter.getName().toLowerCase()+"%");
			return null;
	}
	
	
}
