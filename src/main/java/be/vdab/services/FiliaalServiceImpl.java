package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.exceptions.FiliaalMetDezeNaamBestaatAlException;

@Service
@Transactional(readOnly = true,isolation = Isolation.READ_COMMITTED)
public class FiliaalServiceImpl implements FiliaalService {
	private final FiliaalDAO filiaalDAO;

	@Autowired
	public FiliaalServiceImpl(FiliaalDAO filiaalDAO) {
		this.filiaalDAO = filiaalDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void create(Filiaal filiaal) {
		if (filiaalDAO.findByNaam(filiaal.getNaam()) != null) {
			throw new FiliaalMetDezeNaamBestaatAlException();
		}
		filiaalDAO.create(filiaal);
	}

	@Override
	public Filiaal read(long id) {
		return filiaalDAO.read(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Filiaal filiaal) {
		Filiaal anderFiliaal = filiaalDAO.findByNaam(filiaal.getNaam());
		if (anderFiliaal != null && anderFiliaal.getId() != filiaal.getId()) {
			throw new FiliaalMetDezeNaamBestaatAlException();
		}
		filiaalDAO.update(filiaal);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(long id) {
		if (filiaalDAO.findAantalWerknemers(id) != 0) {
			throw new FiliaalHeeftNogWerknemersException();
		}
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
