package be.vdab.services;

import be.vdab.entities.Filiaal;

public interface FiliaalService {

	void create(Filiaal filiaal);

	Filiaal read(long id);

	void update(Filiaal filiaal);

	void delete(long id);

	Iterable<Filiaal> findAll();

	Iterable<Filiaal> findByPostcodeBetween(int van, int tot);

	long findAantalFilialen();
}
