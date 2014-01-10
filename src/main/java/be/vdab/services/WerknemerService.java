package be.vdab.services;

import be.vdab.entities.Werknemer;

public interface WerknemerService {
	Iterable<Werknemer> findAll();
}
