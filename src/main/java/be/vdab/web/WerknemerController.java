package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/werknemers")
class WerknemerController {
	private final WerknemerService werknemerService;

	@Autowired
	public WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}

	@RequestMapping
	public ModelAndView findAll() {
		return new ModelAndView("werknemers/werknemers", "werknemers",
				werknemerService.findAll());
	}
}