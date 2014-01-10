package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.exceptions.FiliaalMetDezeNaamBestaatAlException;

@Service
@Transactional(readOnly = true)
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
		filiaalDAO.save(filiaal);
	}

	@Override
	public Filiaal read(long id) {
		return filiaalDAO.findOne(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Filiaal filiaal) {
		Filiaal anderFiliaal = filiaalDAO.findByNaam(filiaal.getNaam());
		if (anderFiliaal != null && anderFiliaal.getId() != filiaal.getId()) {
			throw new FiliaalMetDezeNaamBestaatAlException();
		}
		filiaalDAO.save(filiaal);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(long id) {
		Filiaal filiaal = filiaalDAO.findOne(id);
		if (!filiaal.getWerknemers().isEmpty()) {
			throw new FiliaalHeeftNogWerknemersException();
		}
		filiaalDAO.delete(id);
	}

	@Override
	public Iterable<Filiaal> findAll() {
		return filiaalDAO.findAll(new Sort("naam"));
	}

	@Override
	public Iterable<Filiaal> findByPostcodeBetween(int van, int tot) {
		return filiaalDAO.findByAdresPostcodeBetweenOrderByNaamAsc(van, tot);
	}

	@Override
	public long findAantalFilialen() {
		return filiaalDAO.count();
	}

}
