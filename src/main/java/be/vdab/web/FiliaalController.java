package be.vdab.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;

@Controller
@RequestMapping("/filialen")
public class FiliaalController {
	private final Logger logger = LoggerFactory
			.getLogger(FiliaalController.class);

	private final FiliaalService filiaalService;

	@Autowired
	/*
	 * met deze annotation injecteert Spring de parameter filiaalService met de
	 * bean die de interface FiliaalService implementeert
	 */
	public FiliaalController(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		return new ModelAndView("filialen/filialen", "filialen",
				filiaalService.findAll());
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	public String createForm() {
		return "filialen/toevoegen";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create() {
		logger.info("filiaal record toevoegen aan database");
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, params = "id")
	public ModelAndView read(@RequestParam long id) {
		return new ModelAndView("filialen/filiaal", "filiaal",
				filiaalService.read(id));
	}

	@RequestMapping(value = "verwijderen", method = RequestMethod.POST, params = "id")
	public String delete(@RequestParam long id,
			RedirectAttributes redirectAttributes) {
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal == null) {
			return "redirect:/";
		}
		filiaalService.delete(id);
		redirectAttributes.addAttribute("id", id);
		redirectAttributes.addAttribute("naam", filiaal.getNaam());
		return ("redirect:/filialen/verwijderd");
	}

	@RequestMapping(value = "verwijderd", method = RequestMethod.GET, params = {
			"id", "naam" })
	public String deleted() {
		return "filialen/verwijderd";
	}

	@RequestMapping(value = "vantotpostcode", method = RequestMethod.GET)
	public ModelAndView findByPostcodeForm() {
		return new ModelAndView("filialen/vantotpostcode",
				"vanTotPostcodeForm", new VanTotPostcodeForm(1000, 9999));
	}

	@RequestMapping(method = RequestMethod.GET, params = { "vanpostcode",
			"totpostcode" })
	public ModelAndView findByPostcodeBetween(
			@Valid VanTotPostcodeForm vanTotPostcodeForm,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("filialen/vantotpostcode");
		if (!bindingResult.hasErrors() && !vanTotPostcodeForm.isValid()) {
			bindingResult.reject("fouteVanTotPostcode",
					new Object[] { vanTotPostcodeForm.getVanpostcode(),
							vanTotPostcodeForm.getTotpostcode() }, "");
		}
		if (!bindingResult.hasErrors()) {
			modelAndView.addObject("filialen", filiaalService
					.findByPostcodeBetween(vanTotPostcodeForm.getVanpostcode(),
							vanTotPostcodeForm.getTotpostcode()));
		}
		return modelAndView;
	}

	@InitBinder("vanTotPostcodeForm")
	public void initBinderVanTotPostcodeForm(DataBinder dataBinder) {
	}
}
