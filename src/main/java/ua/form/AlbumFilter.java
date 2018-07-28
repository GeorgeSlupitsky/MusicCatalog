package ua.form;

import java.util.List;

public class AlbumFilter {

	private int minYearOfRelease;
	
	private int maxYearOfRelease;
	
	private int minAlbumRating;
	
	private int maxAlbumRating;
	
	private List<Integer> genreId;
	
	private List<Integer> typeOfAlbumId;
	
	private List<Integer> typeOfRecordId;
	
	private List<Integer> countryId;
	
	private List<Integer> labelId;
	
	private List<Integer> bandId;

	public List<Integer> getBandId() {
		return bandId;
	}

	public void setBandId(List<Integer> bandId) {
		this.bandId = bandId;
	}

	public int getMinYearOfRelease() {
		return minYearOfRelease;
	}

	public void setMinYearOfRelease(int minYearOfRelease) {
		this.minYearOfRelease = minYearOfRelease;
	}

	public int getMaxYearOfRelease() {
		return maxYearOfRelease;
	}

	public void setMaxYearOfRelease(int maxYearOfRelease) {
		this.maxYearOfRelease = maxYearOfRelease;
	}

	public int getMinAlbumRating() {
		return minAlbumRating;
	}

	public void setMinAlbumRating(int minAlbumRating) {
		this.minAlbumRating = minAlbumRating;
	}

	public int getMaxAlbumRating() {
		return maxAlbumRating;
	}

	public void setMaxAlbumRating(int maxAlbumRating) {
		this.maxAlbumRating = maxAlbumRating;
	}

	public List<Integer> getGenreId() {
		return genreId;
	}

	public void setGenreId(List<Integer> genreId) {
		this.genreId = genreId;
	}

	public List<Integer> getTypeOfAlbumId() {
		return typeOfAlbumId;
	}

	public void setTypeOfAlbumId(List<Integer> typeOfAlbumId) {
		this.typeOfAlbumId = typeOfAlbumId;
	}

	public List<Integer> getTypeOfRecordId() {
		return typeOfRecordId;
	}

	public void setTypeOfRecordId(List<Integer> typeOfRecordId) {
		this.typeOfRecordId = typeOfRecordId;
	}

	public List<Integer> getCountryId() {
		return countryId;
	}

	public void setCountryId(List<Integer> countryId) {
		this.countryId = countryId;
	}

	public List<Integer> getLabelId() {
		return labelId;
	}

	public void setLabelId(List<Integer> labelId) {
		this.labelId = labelId;
	}

	@Override
	public String toString() {
		return "AlbumFilter [minYearOfRelease=" + minYearOfRelease + ", maxYearOfRelease=" + maxYearOfRelease
				+ ", minAlbumRating=" + minAlbumRating + ", maxAlbumRating=" + maxAlbumRating + ", genreId=" + genreId
				+ ", typeOfAlbumId=" + typeOfAlbumId + ", typeOfRecordId=" + typeOfRecordId + ", countryId=" + countryId
				+ ", labelId=" + labelId + ", bandId=" + bandId + "]";
	}
	
}
