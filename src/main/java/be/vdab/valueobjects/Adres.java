package be.vdab.valueobjects;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import be.vdab.constraints.Postcode;

public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(min = 1, max = 50, message = "{Size.tekst}")
	private String straat;
	@NotNull
	@Size(min = 1, max = 7, message = "{Size.tekst}")
	private String huisNr;
	@NotNull
	@Postcode
	private Integer postcode;
	@NotNull
	@Size(min = 1, max = 50, message = "{Size.tekst}")
	private String gemeente;
	
	protected Adres() {
	}

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

	protected void setStraat(String straat) {
		this.straat = straat;
	}

	protected void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}

	protected void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	protected void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	
	@Override
	public String toString() {
	return String.format("%s %s %d %s", straat, huisNr, postcode, gemeente);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gemeente == null) ? 0 : gemeente.hashCode());
		result = prime * result + ((huisNr == null) ? 0 : huisNr.hashCode());
		result = prime * result
				+ ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adres other = (Adres) obj;
		if (gemeente == null) {
			if (other.gemeente != null)
				return false;
		} else if (!gemeente.equals(other.gemeente))
			return false;
		if (huisNr == null) {
			if (other.huisNr != null)
				return false;
		} else if (!huisNr.equals(other.huisNr))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		return true;
	}
}
