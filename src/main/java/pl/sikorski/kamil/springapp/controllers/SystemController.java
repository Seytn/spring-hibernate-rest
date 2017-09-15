package pl.sikorski.kamil.springapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.sikorski.kamil.springapp.repositories.SystemContractRepository;
import pl.sikorski.kamil.springapp.repositories.SystemRepository;
import pl.sikorski.kamil.springapp.services.ImportFileService;

@RequestMapping
@Controller
public class SystemController {
	@Autowired
	ImportFileService importFileService;

	@Autowired
	private SystemRepository systemRepository;

	@Autowired
	private SystemContractRepository systemContractRepository;

	@GetMapping("/importsql")
	public String findAllSysCont(Model model) {
		String sqlresult = systemContractRepository.findAllSysCont();
		Boolean importSQLResult;

		importSQLResult = sqlresult.equals("[]") ? false : true;

		Boolean fileOrSQL = false;
		model.addAttribute("fileOrSQL", fileOrSQL);
		model.addAttribute("importSQLResult", importSQLResult);
		model.addAttribute("sqlresult", sqlresult);
		return "import";
	}
	
	//TODO update impl updateContract with data from form
	@PostMapping("/update")
	public String updateContract(Model model, @RequestParam("id") String id, @RequestParam("active") String active,
			@RequestParam("amount") String amount, @RequestParam("amount_period") String amount_period,
			@RequestParam("amount_type") String amount_type,
			@RequestParam("authorization_percent") String authorization_percent,
			@RequestParam("from_date") String from_date, @RequestParam("order_number") String order_number,
			@RequestParam("request") String request, @RequestParam("to_date") String to_date,
			@RequestParam("system_id") String system_id,
			RedirectAttributes redirectAttributes) {

		List<String> dataList = new ArrayList<>();
		dataList.add(id);
		dataList.add(active);
		dataList.add(amount);
		dataList.add(amount_period);
		dataList.add(amount_type);
		dataList.add(authorization_percent);
		dataList.add(from_date);
		dataList.add(order_number);
		dataList.add(request);
		dataList.add(to_date);
		dataList.add(system_id);
		
 
		System.out.println(id + " " + active + " " + from_date + " post test");
		model.addAttribute("sqlresult", systemContractRepository.findAllSysCont());
		return "update";

	};

	@GetMapping("/system/addSys")
	public String addSystems(Model model) {
		systemRepository.addSystems();
		model.addAttribute("sqlresult", systemContractRepository.findAllSysCont());
		return "sample";

	};

	@GetMapping("/system/addCont")
	public String addContractsTest(Model model) {
		systemContractRepository.addContractsTest();
		model.addAttribute("sqlresult", systemContractRepository.findAllSysCont());
		return "sampleContract";

	};

	@GetMapping("/system/find")
	public void findSys() {
		systemRepository.findSys();
	}

	@GetMapping("/system/findall")
	public Query findAllSys() {
		return systemRepository.findAllSysName();
	}

}
