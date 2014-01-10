package be.vdab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Filiaal;

public interface FiliaalDAO extends JpaRepository<Filiaal, Long>{
	Filiaal findByNaam(String naam);
	public List<Filiaal> findByAdresPostcodeBetweenOrderByNaamAsc( int van, int tot);
}
