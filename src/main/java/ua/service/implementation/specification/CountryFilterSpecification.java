package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Country;
import ua.form.CountryFilter;

public class CountryFilterSpecification implements Specification<Country>{

	private final CountryFilter filter;
	
	public CountryFilterSpecification(CountryFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter!=null&&filter.getName()!=null&&!filter.getName().isEmpty())
			return cb.like(cb.lower(root.get("name")), "%"+filter.getName().toLowerCase()+"%");
			return null;
	}

	
	
	

}
