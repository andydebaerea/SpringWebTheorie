package be.vdab.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.FiliaalService;

@Controller
@RequestMapping("/")
public class IndexController {

	private final FiliaalService filiaalService;
	private final LocaleResolver localeResolver;
	private final Voorkeuren voorkeuren;

	@Autowired
	public IndexController(FiliaalService filiaalService,
			LocaleResolver localeResolver, Voorkeuren voorkeuren) {
		this.filiaalService = filiaalService;
		this.localeResolver = localeResolver;
		this.voorkeuren = voorkeuren;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("aantalFilialen",
				filiaalService.findAantalFilialen());
		modelAndView.addObject("kleur", voorkeuren.getAchtergrondkleur());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "locale" })
	public String index(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String locale) {
		String[] onderdelen = locale.split("_");
		localeResolver.setLocale(request, response, new Locale(onderdelen[0],
				onderdelen[1]));
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, params = "kleur")
	public ModelAndView kleurKeuze(@RequestParam String kleur) {
		voorkeuren.setAchtergrondkleur(kleur);
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("aantalFilialen",
				filiaalService.findAantalFilialen());
		modelAndView.addObject("kleur", kleur);
		return modelAndView;
	}
}
