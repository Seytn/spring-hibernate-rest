package pl.sikorski.kamil.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.sikorski.kamil.springapp.repositories.SystemContractRepository;
import pl.sikorski.kamil.springapp.services.ImportFileService;

@RequestMapping
@Controller
public class ReadExcelController {

	@Autowired
	ImportFileService importFileService;

	@Autowired
	SystemContractRepository systemContractDAO;


	@PostMapping("/importfile")
	public String importFromExcelToSQL2(Model model, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		// redirectAttributes.addFlashAttribute("message",
		// "You successfully uploaded " + file.getOriginalFilename() + "!");
		//

		Boolean importFileResult = importFileService.importFile(file);
		Boolean fileOrSQL = true;
		model.addAttribute("fileOrSQL", fileOrSQL);
		model.addAttribute("importFileResult", importFileResult);
		model.addAttribute("sqlresult", systemContractDAO.findAllSysCont());
		return "import";

	}

}
