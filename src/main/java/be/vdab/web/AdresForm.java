package be.vdab.web;

import be.vdab.valueobjects.Adres;

public class AdresForm extends Adres {
	private static final long serialVersionUID = 1L;
	
	AdresForm() {
	}
	
	AdresForm(Adres adres) {
		setStraat(adres.getStraat());
		setHuisNr(adres.getHuisNr());
		setPostcode(adres.getPostcode());
		setGemeente(adres.getGemeente());
	}
	
	@Override
	public void setStraat(String straat) {
	super.setStraat(straat);
	}
	@Override
	public void setHuisNr(String huisNr) {
	super.setHuisNr(huisNr);
	}
	@Override
	public void setPostcode(Integer postcode) {
	super.setPostcode(postcode);
	}
	@Override
	public void setGemeente(String gemeente) {
	super.setGemeente(gemeente);
	}
}
