package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Album;
import ua.entity.Band;
import ua.form.BandsViewFilter;

public class BandsViewFilterSpecification implements Specification<Band> {

	private final BandsViewFilter filter;

	public BandsViewFilterSpecification(BandsViewFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Band> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class){
			root.fetch("country", JoinType.LEFT);
			Fetch<Band, Album> fetch = root.fetch("albums", JoinType.LEFT);
			fetch.fetch("genre", JoinType.LEFT);
			fetch.fetch("typeOfAlbum", JoinType.LEFT);
			fetch.fetch("label", JoinType.LEFT);
			fetch.fetch("typeOfRecord", JoinType.LEFT);
			fetch.fetch("country", JoinType.LEFT);
			query.distinct(true);
		}
		if(filter!=null&&filter.getName()!=null&&!filter.getName().isEmpty())
			return cb.like(cb.lower(root.get("name")), filter.getName().toLowerCase()+"%");
			return null;
	}
	
	
}
