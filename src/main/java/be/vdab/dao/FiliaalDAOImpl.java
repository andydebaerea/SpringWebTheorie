package be.vdab.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;

@Repository
public class FiliaalDAOImpl implements FiliaalDAO {
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final FiliaalRowMapper filiaalRowMapper = new FiliaalRowMapper();
	private final SimpleJdbcInsert simpleJdbcInsert;

	private static final String SQL_FIND_ALL = "select id, naam, hoofdFiliaal, straat, huisNr, "
			+ "postcode, gemeente, inGebruikName, waardeGebouw from filialen order by naam";
	private static final String SQL_FIND_BY_POSTCODE = "select id, naam, hoofdFiliaal, straat, huisNr, "
			+ "postcode, gemeente, inGebruikName, waardeGebouw from filialen "
			+ "where postcode between :van and :tot order by naam";
	private static final String SQL_READ = "select id, naam, hoofdFiliaal, straat, huisNr, postcode, gemeente,"
			+ "inGebruikName, waardeGebouw from filialen where id = :id";
	private static final String SQL_FIND_AANTAL_FILIALEN = "select count(*) from filialen";
	private static final String SQL_FIND_BY_NAAM = "select id, naam, hoofdFiliaal, straat, huisNr, "
			+ "postcode, gemeente, inGebruikName, waardeGebouw from filialen where naam = :naam";
	private static final String SQL_DELETE = "delete from filialen where id = ?";
	private static final String SQL_FIND_AANTAL_WERKNEMERS = "select count(*) from werknemers "
			+ "where filiaalId = ?";
	private static final String SQL_UPDATE = "update filialen set naam=?, hoofdFiliaal=?, straat=?, "
			+ "huisNr=?, postcode=?, gemeente=?, inGebruikName=?, waardeGebouw=? where id=?";

	@Autowired
	public FiliaalDAOImpl(JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("filialen");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
	}

	@Override
	public void create(Filiaal filiaal) {
		Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("naam", filiaal.getNaam());
		kolomWaarden.put("hoofdFiliaal", filiaal.isHoofdFiliaal());
		kolomWaarden.put("straat", filiaal.getAdres().getStraat());
		kolomWaarden.put("huisNr", filiaal.getAdres().getHuisNr());
		kolomWaarden.put("postcode", filiaal.getAdres().getPostcode());
		kolomWaarden.put("gemeente", filiaal.getAdres().getGemeente());
		kolomWaarden.put("inGebruikName", filiaal.getInGebruikName());
		kolomWaarden.put("waardeGebouw", filiaal.getWaardeGebouw());
		Number id = simpleJdbcInsert.executeAndReturnKey(kolomWaarden);
		filiaal.setId(id.longValue());
	}

	@Override
	public Filiaal read(long id) {
		Map<String, Long> parameters = Collections.singletonMap("id", id);
		try {
			return namedParameterJdbcTemplate.queryForObject(SQL_READ,
					parameters, filiaalRowMapper);
		} catch (IncorrectResultSizeDataAccessException ex) {
			return null; // record niet gevonden
		}
	}

	public void update(Filiaal filiaal) {
		jdbcTemplate.update(SQL_UPDATE, filiaal.getNaam(), filiaal
				.isHoofdFiliaal(), filiaal.getAdres().getStraat(), filiaal
				.getAdres().getHuisNr(), filiaal.getAdres().getPostcode(),
				filiaal.getAdres().getGemeente(), filiaal.getInGebruikName(),
				filiaal.getWaardeGebouw(), filiaal.getId());
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update(SQL_DELETE, id);
	}

	@Override
	public long findAantalWerknemers(long id) {
		return jdbcTemplate.queryForObject(SQL_FIND_AANTAL_WERKNEMERS,
				Long.class, id);
	}

	@Override
	public Iterable<Filiaal> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, filiaalRowMapper);
	}

	@Override
	public Iterable<Filiaal> findByPostcodeBetween(int van, int tot) {
		Map<String, Integer> parameters = new HashMap<>();
		parameters.put("van", van);
		parameters.put("tot", tot);
		return jdbcTemplate.query(SQL_FIND_BY_POSTCODE, filiaalRowMapper);
	}

	@Override
	public long findAantalFilialen() {
		return jdbcTemplate
				.queryForObject(SQL_FIND_AANTAL_FILIALEN, Long.class);
	}

	private static class FiliaalRowMapper implements RowMapper<Filiaal> {
		@Override
		public Filiaal mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			return new Filiaal(resultSet.getLong("id"),
					resultSet.getString("naam"),
					resultSet.getBoolean("hoofdFiliaal"),
					resultSet.getBigDecimal("waardeGebouw"),
					resultSet.getDate("inGebruikName"), new Adres(
							resultSet.getString("straat"),
							resultSet.getString("huisNr"),
							resultSet.getInt("postcode"),
							resultSet.getString("gemeente")));
		}
	}

	@Override
	public Filiaal findByNaam(String naam) {
		Map<String, String> parameters = Collections.singletonMap("naam", naam);
		try {
			return namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_NAAM,
					parameters, filiaalRowMapper);
		} catch (IncorrectResultSizeDataAccessException ex) {
			return null; // record niet gevonden
		}
	}
}
