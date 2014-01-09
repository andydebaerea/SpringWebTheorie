package be.vdab.web;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.Adres;

public class FiliaalControllerTest {
	private FiliaalController filiaalController;
	private Iterable<Filiaal> filialen;
	private FiliaalService filiaalService;
	private Filiaal filiaal;
	private ServletContext servletContext;

	@Before
	public void setUp() {
		filialen = Collections.emptyList();
		filiaalService = Mockito.mock(FiliaalService.class);
		servletContext = Mockito.mock(ServletContext.class);
		Mockito.when(filiaalService.findAll()).thenReturn(filialen);
		Mockito.when(servletContext.getRealPath("/images")).thenReturn("");
		filiaalController = new FiliaalController(filiaalService, servletContext);
		filiaal = new Filiaal("naam1", true, BigDecimal.ONE, new Date(),
				new Adres("straat1", "huisnr1", 1, "gemeente1"));
		Mockito.when(filiaalService.read(1L)).thenReturn(filiaal);
	}

	@Test
	public void findAllActiveertJuisteView() {
		Assert.assertEquals("filialen/filialen", filiaalController.findAll()
				.getViewName());
	}

	@Test
	public void findAllMaakRequestAttribuutFilialen() {
		Assert.assertSame(filialen, filiaalController.findAll().getModelMap()
				.get("filialen"));
	}
	
	@Test
	public void readActiveertJuisteView() {
	Assert.assertEquals("filialen/filiaal", filiaalController.read(1L).getViewName());
	}
	
	@Test
	public void readMetBestaandeIDGeeftFiliaalTerug() {
	Assert.assertSame(filiaal,
	filiaalController.read(1L).getModelMap().get("filiaal"));
	}
	
	@Test
	public void readMetOnbestaandeIDGeeftNullTerug() {
	Assert.assertNull(
	filiaalController.read(666L).getModelMap().get("filiaal"));
	}
}
