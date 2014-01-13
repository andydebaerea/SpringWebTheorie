package be.vdab.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import be.vdab.entities.Filiaal;

@XmlRootElement(name = "filialen")
@XmlAccessorType(XmlAccessType.FIELD)
public class FiliaalListREST {
	private static final String URI_TEMPLATE = "/filialen";
	@XmlElement(name = "filiaal")
	private List<FiliaalListItemREST> filialen = new ArrayList<>();
	private Link link;

	protected FiliaalListREST() { // voor JAXB
	}

	public FiliaalListREST(Iterable<Filiaal> filialen) {
		for (Filiaal filiaal : filialen) {
			this.filialen.add(new FiliaalListItemREST(filiaal));
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(URI_TEMPLATE).build().toUri();
		this.link = new Link("self", uri);
	}

	public List<FiliaalListItemREST> getFilialen() {
		return filialen;
	}

	public Link getLink() {
		return link;
	}

}
