package be.vdab.web;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class VoorkeurenImpl implements Voorkeuren, Serializable {
	private static final long serialVersionUID = 1L;
	private String achtergrondkleur;

	@Override
	public void setAchtergrondkleur(String achtergrondkleur) {
		this.achtergrondkleur = achtergrondkleur;
	}

	@Override
	public String getAchtergrondkleur() {
		return achtergrondkleur;
	}

}
