package ua.form;

import java.util.List;

public class BandFilter {
	
	private int minFoundationYear;
	
	private int maxFoundationYear;
	
	private List<Integer> countryId;

	public int getMinFoundationYear() {
		return minFoundationYear;
	}

	public void setMinFoundationYear(int minFoundationYear) {
		this.minFoundationYear = minFoundationYear;
	}

	public int getMaxFoundationYear() {
		return maxFoundationYear;
	}

	public void setMaxFoundationYear(int maxFoundationYear) {
		this.maxFoundationYear = maxFoundationYear;
	}

	public List<Integer> getCountryId() {
		return countryId;
	}

	public void setCountryId(List<Integer> countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "BandFilter [minFoundationYear=" + minFoundationYear + ", maxFoundationYear=" + maxFoundationYear
				+ ", countryId=" + countryId + "]";
	}
	
}
