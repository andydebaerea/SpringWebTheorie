package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.WerknemerDAO;
import be.vdab.entities.Werknemer;

@Service
@Transactional(readOnly = true)
public class WerknemerServiceImpl implements WerknemerService {
	private final WerknemerDAO werknemerDAO;

	@Autowired
	public WerknemerServiceImpl(WerknemerDAO werknemerDAO) {
		this.werknemerDAO = werknemerDAO;
	}

	@Override
	public Iterable<Werknemer> findAll() {
		return werknemerDAO.findAll(new Sort("familienaam", "voornaam"));
	}
}
