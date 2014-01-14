package be.vdab.restClient;

import java.net.URI;

import be.vdab.entities.Filiaal;
import be.vdab.rest.FiliaalListItemREST;
import be.vdab.rest.FiliaalListREST;
import be.vdab.rest.FiliaalREST;

public interface FiliaalClient {
	FiliaalListREST findAll();
	FiliaalREST find(FiliaalListItemREST filiaalListItemREST);
	FiliaalREST read(long id);
	void update(FiliaalREST filiaal);
	URI create(Filiaal filiaal);
	void delete(long id);
}
