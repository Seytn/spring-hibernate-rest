package pl.sikorski.kamil.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.sikorski.kamil.springapp.repositories.SystemContractRepository;

@Controller
public class HomeController implements ErrorController {

	@Autowired
	SystemContractRepository systemContractDAO;

	@GetMapping("/")
	public String homePage(Model model,
			@RequestParam(value = "val", required = false, defaultValue = "default") String val) {
		model.addAttribute("val", val);
		model.addAttribute("sqlresult", systemContractDAO.findAllSysCont());
		return "home";
	}

	@GetMapping("/error")
	public String getError() {
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "error";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		model.addAttribute("sqlresult", systemContractDAO.findAllSysCont());
		return "edit";
	}

}
