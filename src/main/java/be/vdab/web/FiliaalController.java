package be.vdab.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
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
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.exceptions.FiliaalMetDezeNaamBestaatAlException;
import be.vdab.services.FiliaalService;

@Controller
@RequestMapping("/filialen")
public class FiliaalController {
	private final ServletContext servletContext;
	private final Logger logger = LoggerFactory
			.getLogger(FiliaalController.class);

	private final FiliaalService filiaalService;

	@Autowired
	/*
	 * met deze annotation injecteert Spring de parameter filiaalService met de
	 * bean die de interface FiliaalService implementeert
	 */
	public FiliaalController(FiliaalService filiaalService,
			ServletContext servletContext) {
		this.filiaalService = filiaalService;
		this.servletContext = servletContext;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		return new ModelAndView("filialen/filialen", "filialen",
				filiaalService.findAll());
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	public ModelAndView createForm() {
		return new ModelAndView("filialen/toevoegen", "filiaal", new Filiaal());
	}

	@InitBinder("filiaal")
	public void initBinderFiliaal(DataBinder dataBinder) {
		Filiaal filiaal = (Filiaal) dataBinder.getTarget();
		if (filiaal.getAdres() == null) {
			filiaal.setAdres(new AdresForm());
		} else {
			filiaal.setAdres(new AdresForm(filiaal.getAdres()));
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Filiaal filiaal, BindingResult bindingResult,
			@RequestParam("foto") Part part) {
		if (part != null && part.getSize() != 0) {
			String contentType = part.getContentType();
			if (!"image/jpg".equals(contentType)
					&& "image/pjpeg".equals(contentType)) {
				bindingResult.reject("fotofout");
			}
		}
		if (!bindingResult.hasErrors()) {
			try {
				filiaalService.create(filiaal);
				if (part != null && part.getSize() != 0) {
					String filiaalFotosPad = servletContext
							.getRealPath("/images");
					part.write(filiaalFotosPad + '/' + filiaal.getId() + ".jpg");
				}
				return "redirect:/";
			} catch (IOException ex) {
				logger.error("fouten bij opslaan foto" + ex.getStackTrace());
			} catch (FiliaalMetDezeNaamBestaatAlException fex) {
				bindingResult
						.rejectValue("naam", "filiaalMetDezeNaamBestaatAl");
			}
		}
		return "filialen/toevoegen";
	}

	@RequestMapping(method = RequestMethod.GET, params = "id")
	public ModelAndView read(@RequestParam long id) {
		ModelAndView modelAndView = new ModelAndView("filialen/filiaal");
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal != null) {
			modelAndView.addObject("filiaal", filiaal);
			String filiaalFotoPad = servletContext.getRealPath("/images") + '/'
					+ filiaal.getId() + ".jpg";
			File file = new File(filiaalFotoPad);
			modelAndView.addObject("heeftFoto", file.exists());
		}
		return modelAndView;
	}

	@RequestMapping(value = "verwijderen", method = RequestMethod.POST, params = "id")
	public String delete(@RequestParam long id,
			RedirectAttributes redirectAttributes) {
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal == null) {
			return "redirect:/";
		}
		try {
			filiaalService.delete(id);
			String filiaalFotoPad = servletContext.getRealPath("/images") + '/'
					+ filiaal.getId() + ".jpg";
			File file = new File(filiaalFotoPad);
			if (file.exists()) {
				file.delete();
			}
			redirectAttributes.addAttribute("id", id);
			redirectAttributes.addAttribute("naam", filiaal.getNaam());
			return "redirect:/filialen/verwijderd";
		} catch (FiliaalHeeftNogWerknemersException ex) {
			redirectAttributes.addAttribute("id", id);
			redirectAttributes.addAttribute("fout",
					"Filiaal is niet verwijderd, het bevat nog werknemers");
			return "redirect:/filialen";
		}

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
		dataBinder.initDirectFieldAccess();
	}

	@RequestMapping(value = "wijzigen", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam long id) {
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal == null) {
			return new ModelAndView("redirect:/filialen");
		}
		return new ModelAndView("filialen/wijzigen", "filiaal", filiaal);
	}

	@RequestMapping(value = "wijzigen", method = RequestMethod.POST)
	public String update(@Valid Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "filialen/wijzigen";
		}
		try {
			filiaalService.update(filiaal);
			return "redirect:/";
		} catch (FiliaalMetDezeNaamBestaatAlException ex) {
			bindingResult.rejectValue("naam", "filiaalMetDezeNaamBestaatAl");
			return "filialen/wijzigen";
		}
	}
}
