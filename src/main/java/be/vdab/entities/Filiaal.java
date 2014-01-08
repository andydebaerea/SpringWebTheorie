package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import be.vdab.valueobjects.Adres;

public class Filiaal implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String naam;
	private boolean hoofdFiliaal;
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal waardeGebouw;
	@DateTimeFormat(style= "S-")
	private Date inGebruikName;
	private Adres adres;

	public Filiaal(String naam, boolean hoofdFiliaal, BigDecimal waardeGebouw,
			Date inGebruikName, Adres adres) {
		setNaam(naam);
		setHoofdFiliaal(hoofdFiliaal);
		setWaardeGebouw(waardeGebouw);
		setInGebruikName(inGebruikName);
		setAdres(adres);
	}

	public Filiaal(long id, String naam, boolean hoofdFiliaal,
			BigDecimal waardeGebouw, Date inGebruikName, Adres adres) {
		this(naam, hoofdFiliaal, waardeGebouw, inGebruikName, adres);
		setId(id);
	}

	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public boolean isHoofdFiliaal() {
		return hoofdFiliaal;
	}

	public BigDecimal getWaardeGebouw() {
		return waardeGebouw;
	}

	public Date getInGebruikName() {
		return inGebruikName;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setHoofdFiliaal(boolean hoofdFiliaal) {
		this.hoofdFiliaal = hoofdFiliaal;
	}

	public void setWaardeGebouw(BigDecimal waardeGebouw) {
		this.waardeGebouw = waardeGebouw;
	}

	public void setInGebruikName(Date inGebruikName) {
		this.inGebruikName = inGebruikName;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s:%d", naam, id);
	}
}
