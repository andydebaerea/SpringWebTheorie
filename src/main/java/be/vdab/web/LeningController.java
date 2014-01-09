package be.vdab.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Lening;

@Controller
@RequestMapping("/leningen")
@SessionAttributes("lening")
public class LeningController {
	private final Logger logger = LoggerFactory
			.getLogger(LeningController.class);

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	public ModelAndView createForm1() {
		return new ModelAndView("leningen/toevoegen1", "lening", new Lening());
	}

	@RequestMapping(method = RequestMethod.POST, params = "van1naar2")
	public String createForm1Naar2(
			@Validated(Lening.Stap1.class) Lening lening,
			BindingResult bindingResult) {
		return "leningen/"
				+ (bindingResult.hasErrors() ? "toevoegen1" : "toevoegen2");
	}

	@RequestMapping(method = RequestMethod.POST, params = "van2naar1")
	public String createForm2Naar1(@ModelAttribute Lening lening) {
		return "leningen/toevoegen1";
	}

	@RequestMapping(method = RequestMethod.POST, params = "bevestigen")
	public String create(@Validated(Lening.Stap2.class) Lening lening,
			BindingResult bindingResult, SessionStatus sessionStatus) {
		if (bindingResult.hasErrors()) {
			return "leningen/toevoegen2";
		} else {
			logger.info("Lening record toevoegen aan de database");
			sessionStatus.setComplete();
			return "redirect:/";
		}
	}

	@RequestMapping(method = RequestMethod.POST, params = "nogeennummer")
	public String nogEenNummer(@ModelAttribute Lening lening) {
		lening.nogEenTelefoonNr();
		return "leningen/toevoegen1";
	}
}
