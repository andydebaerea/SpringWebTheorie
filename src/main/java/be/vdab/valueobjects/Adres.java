package be.vdab.valueobjects;

import java.io.Serializable;

public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	private String straat;
	private String huisNr;
	private Integer postcode;
	private String gemeente;

	public Adres(String straat, String huisNr, Integer postcode, String gemeente) {
		this.straat = straat;
		this.huisNr = huisNr;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}

	
	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	
	@Override
	public String toString() {
	return String.format("%s %s %d %s", straat, huisNr, postcode, gemeente);
	}
}
