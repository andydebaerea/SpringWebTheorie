package be.vdab.rest;

import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import be.vdab.entities.Filiaal;

@XmlAccessorType(XmlAccessType.FIELD)
public class FiliaalListItemREST {
	private static final String URI_TEMPLATE = "/filialen/{id}";
	@XmlAttribute
	private long id;
	@XmlAttribute
	private String naam;
	private Link link;

	protected FiliaalListItemREST() { // voor JAXB
	}

	public FiliaalListItemREST(Filiaal filiaal) {
		this.id = filiaal.getId();
		this.naam = filiaal.getNaam();
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(URI_TEMPLATE).buildAndExpand(id).toUri();
		this.link = new Link("self", uri);
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Link getLink() {
		return link;
	}
	
}
