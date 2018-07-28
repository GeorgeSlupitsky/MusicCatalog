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

import ua.entity.Album;
import ua.form.AlbumFilter;

public class AlbumFilterAdapter implements Specification<Album>{

	private final AlbumFilter albumFilter;
	
	private final List<Specification<Album>> list = new ArrayList<>();

	public AlbumFilterAdapter(AlbumFilter albumFilter) {
		this.albumFilter = albumFilter;
	}
	
	private void findByGenreId(){
		if(albumFilter.getGenreId()!=null && !albumFilter.getGenreId().isEmpty()){
			list.add((root, cq, cb) -> root.get("genre").in(albumFilter.getGenreId()));
		}
	}
	
	private void findByTypeOfAlbumId(){
		if(albumFilter.getTypeOfAlbumId()!=null && !albumFilter.getTypeOfAlbumId().isEmpty()){
			list.add((root, cq, cb) -> root.get("typeOfAlbum").in(albumFilter.getTypeOfAlbumId()));
		}
	}
	
	private void findByTypeOfRecordId(){
		if(albumFilter.getTypeOfRecordId()!=null && !albumFilter.getTypeOfRecordId().isEmpty()){
			list.add((root, cq, cb) -> root.get("typeOfRecord").in(albumFilter.getTypeOfRecordId()));
		}
	}
	
	private void findByCountryId(){
		if(albumFilter.getCountryId()!=null && !albumFilter.getCountryId().isEmpty()){
			list.add((root, cq, cb) -> root.get("country").in(albumFilter.getCountryId()));
		}
	}
	
	private void findByLabelId(){
		if(albumFilter.getLabelId()!=null && !albumFilter.getLabelId().isEmpty()){
			list.add((root, cq, cb) -> root.get("label").in(albumFilter.getLabelId()));
		}
	}
	
	private void findByBandId(){
		if(albumFilter.getBandId()!=null && !albumFilter.getBandId().isEmpty()){
			list.add((root, cq, cb) -> root.get("band").in(albumFilter.getBandId()));
		}
	}
	
	private void findByMinMaxYearOfRelease(){
		if(albumFilter.getMaxYearOfRelease()!=0 && albumFilter.getMinYearOfRelease()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("yearOfRelease");
				return cb.between(exp, albumFilter.getMinYearOfRelease(), albumFilter.getMaxYearOfRelease());
			});
		}else if(albumFilter.getMaxYearOfRelease()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("yearOfRelease");
				return cb.lessThan(exp, albumFilter.getMaxYearOfRelease());
			});
		}else if(albumFilter.getMinYearOfRelease()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("yearOfRelease");
				return cb.greaterThan(exp, albumFilter.getMinYearOfRelease());
			});
		}
	}
	
	private void findByMinMaxAlbumRating(){
		if(albumFilter.getMaxAlbumRating()!=0&&albumFilter.getMinAlbumRating()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("albumRating");
				return cb.between(exp, albumFilter.getMinAlbumRating(), albumFilter.getMaxAlbumRating());
			});
		}else if(albumFilter.getMaxAlbumRating()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("albumRating");
				return cb.lessThan(exp, albumFilter.getMaxAlbumRating());
			});
		}else if(albumFilter.getMinAlbumRating()!=0){
			list.add((root, cq, cb)-> {
				Expression<Integer> exp = root.get("albumRating");
				return cb.greaterThan(exp, albumFilter.getMinAlbumRating());
			});
		}
	}

	@Override
	public Predicate toPredicate(Root<Album> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
			root.fetch("genre", JoinType.LEFT);
			root.fetch("typeOfAlbum", JoinType.LEFT);
			root.fetch("typeOfRecord", JoinType.LEFT);
			root.fetch("country", JoinType.LEFT);
			root.fetch("label", JoinType.LEFT);
			root.fetch("band", JoinType.LEFT);
			query.distinct(true);
		}
		if(albumFilter!=null){
			findByGenreId();
			findByTypeOfAlbumId();
			findByTypeOfRecordId();
			findByCountryId();
			findByLabelId();
			findByBandId();
			findByMinMaxYearOfRelease();
			findByMinMaxAlbumRating();
		}
		if(list.size()==0)return null;
		Specifications<Album> spec = Specifications.where(list.get(0));
		for(int i = 1; i < list.size(); i++){
			spec = spec.and(list.get(i));
		}
		return spec.toPredicate(root, query, cb);
	}
	
	
}
