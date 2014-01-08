package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;

@Service
public class FiliaalServiceImpl implements FiliaalService {
	private final FiliaalDAO filiaalDAO;
	
	@Autowired
	public FiliaalServiceImpl(FiliaalDAO filiaalDAO) {
		this.filiaalDAO = filiaalDAO;
	}
	
	@Override
	public void create(Filiaal filiaal) {
		filiaalDAO.create(filiaal);
	}

	@Override
	public Filiaal read(long id) {
		return filiaalDAO.read(id);
	}

	@Override
	public void update(Filiaal filiaal) {
		filiaalDAO.update(filiaal);

	}

	@Override
	public void delete(long id) {
		filiaalDAO.delete(id);

	}

	@Override
	public Iterable<Filiaal> findAll() {
		return filiaalDAO.findAll();
	}

	@Override
	public Iterable<Filiaal> findByPostcodeBetween(int van, int tot) {
		return filiaalDAO.findByPostcodeBetween(van, tot);
	}

	@Override
	public long findAantalFilialen() {
		return filiaalDAO.findAantalFilialen();
	}

}
