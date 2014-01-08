package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/werknemers")
class WerknemerController {
	@RequestMapping
	public String findAll() {
		return "werknemers/werknemers";
	}
}