package ua.service.implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ua.entity.Band;
import ua.form.BandFilter;

public class BandFilterAdapter implements Specification<Band>{

	private final BandFilter bandFilter;
	
	private final List<Specification<Band>> list = new ArrayList<>();
	
	public BandFilterAdapter(BandFilter bandFilter) {
		this.bandFilter = bandFilter;
	}

	private void findByCountryId(){
		if(bandFilter.getCountryId()!=null && !bandFilter.getCountryId().isEmpty()){
			list.add((root, cq, cb) -> root.get("country").in(bandFilter.getCountryId()));
		}
	}
	
	private void findByMinMaxFoundationYear(){
		if(bandFilter.getMaxFoundationYear() !=0 && bandFilter.getMinFoundationYear()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("foundationYear");
				return cb.between(exp, bandFilter.getMinFoundationYear(), bandFilter.getMaxFoundationYear());
			});
		}else if(bandFilter.getMaxFoundationYear()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("foundationYear");
				return cb.lessThan(exp, bandFilter.getMaxFoundationYear());
			});
		}else if(bandFilter.getMinFoundationYear()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("foundationYear");
				return cb.greaterThan(exp, bandFilter.getMinFoundationYear());
			});
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Band> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
			root.fetch("country", JoinType.LEFT);
			query.distinct(true);
		}
		if(bandFilter!=null){
			findByCountryId();
			findByMinMaxFoundationYear();
		}
		if(list.size()==0)return null;
		Specifications<Band> spec = Specifications.where(list.get(0));
		for(int i = 1; i < list.size(); i++){
			spec = spec.and(list.get(i));
		}
		return spec.toPredicate(root, query, cb);
	}

}
