package pl.sikorski.kamil.springapp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.sikorski.kamil.springapp.entities.System;
import pl.sikorski.kamil.springapp.entities.SystemContract;
import pl.sikorski.kamil.springapp.repositories.SystemContractRepository;
import pl.sikorski.kamil.springapp.repositories.SystemRepository;

@Service
public class ImportFileService {

	@Autowired
	private SystemContractRepository systemContractDAO;

	@Autowired
	private SystemRepository systemDAO;

	@Autowired
	private Path rootLocation;

	List<String> dataListFromExcel;

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	File file;

	MultipartFile multipartFile;

	String fileName;

	public boolean importFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
		fileName = multipartFile.getOriginalFilename();
		String fileFormat = validateFormat();
		boolean isOkFlag = true;
		try {

			if (fileFormat.equals("xlsx") || fileFormat.equals("xls")) {
				delete(multipartFile);
				copy(multipartFile);
				file = new File(rootLocation.resolve(fileName).toString());

				switch (fileFormat) {
				case "xls":
					importXlsFileInit();
					break;
				case "xlsx":
					importXlsxFileInit();
					break;
				}

			} else
				throw new InvalidFormatException("Wrong format" + fileFormat);

			dataListToSQL();
			delete(multipartFile);

		} catch (InvalidFormatException | IOException e) {
			java.lang.System.out.println("IO or InvalidFormat Exception");
//			e.printStackTrace();
			isOkFlag = false;
		} catch (IllegalStateException e) {
			java.lang.System.out.println("Zły format pliku. Transfer error.");
//			e.printStackTrace();
			isOkFlag = false;
		} catch (RuntimeException e) {
			java.lang.System.out.println("Pozostałe wyjątki związane z plikiem.");
//			e.printStackTrace();
			isOkFlag = false;
		}
		return isOkFlag;
	}

	private String validateFormat() {

		String extension = "";

		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
			extension = fileName.substring(i + 1);
		}

		if (extension.equals("xls"))
			return "xls";
		else if (extension.equals("xlsx"))
			return "xlsx";
		else
			return "other";

	}

	private void importXlsFileInit() throws IOException, InvalidFormatException {
		FileInputStream fis = new FileInputStream(file);

		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheetAt(0);

		dataListFromExcel = new ArrayList<>();
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		for (Row row : sheet) {
			BigDecimal lastNotEmpty = BigDecimal.valueOf(row.getLastCellNum());
			int lastRow = lastNotEmpty.setScale(-1, RoundingMode.HALF_UP).intValue();

			for (int cn = 0; cn < lastRow; cn++) {

				Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
				int cellType = formulaEvaluator.evaluateInCell(cell).getCellType();

				if (cellType == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
					addToTempObj(cell.getDateCellValue());
				} else {
					addToTempObj(cell.toString());
				}
			}
		}
		wb.close();

		java.lang.System.out.println("Data xls list size: " + dataListFromExcel.size());

	}

	public void importXlsxFileInit() throws IOException, InvalidFormatException {

		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		dataListFromExcel = new ArrayList<>();
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		for (Row row : sheet) {
			BigDecimal lastNotEmpty = BigDecimal.valueOf(row.getLastCellNum());
			int lastRow = lastNotEmpty.setScale(-1, RoundingMode.HALF_UP).intValue();

			for (int cn = 0; cn < lastRow; cn++) {

				Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
				int cellType = formulaEvaluator.evaluateInCell(cell).getCellType();

				if (cellType == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
					addToTempObj(cell.getDateCellValue());
				} else {
					addToTempObj(cell.toString());
				}
			}
		}
		wb.close();

		java.lang.System.out.println("Data xlsx list size: " + dataListFromExcel.size());
	}

	public void dataListToSQL() {
		Map<String, Long> resultMap = getSystemMap();
		// row by row from dataList to SQL
		for (int i = 0; i < dataListFromExcel.size() / 10 - 1; i++)
			try {
				systemContractDAO.addContract(parseAndReturnSystemContract(i, resultMap, dataListFromExcel));
			} catch (ParseException e) {
				java.lang.System.out.println("Wrong data. (Parse date). Row: " + (i + 1));
				// e.printStackTrace();
			} catch (NumberFormatException e) {
				java.lang.System.out.println("Wrong data. (string instead of double). Row: " + (i + 1));
				// e.printStackTrace();
			}

	}

	public Map<String, Long> getSystemMap() {
		Query query = systemDAO.findAllSysName();
		if (query.equals("[]"))
			throw new NullPointerException("No systems");

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		Map<String, Long> resultMap = new HashMap<>(resultList.size());

		for (Object[] result : resultList)
			resultMap.put((String) result[0], (Long) result[1]);

		return resultMap;

	}

	public SystemContract parseAndReturnSystemContract(int i, Map<String, Long> resultMap, List<String> dataList)
			throws ParseException, NumberFormatException, NullPointerException {
		int rowSelector = i * 10;
		List<System> systemList = systemDAO.findAllSys();

		// system id to system
		System sys = null;
		int sysId = resultMap.get(dataList.get(10 + rowSelector)).intValue();
		sys = systemList.get(sysId - 1);
		
		// date parsing
		String cellValueFromDate = dataList.get(13 + rowSelector);
		String cellValueToDate = dataList.get(14 + rowSelector);

		Date sqlFromDate = null;
		if (!cellValueFromDate.isEmpty()) {
			java.util.Date parsed = format.parse(cellValueFromDate);
			sqlFromDate = new java.sql.Date(parsed.getTime());
		}

		Date sqlToDate = null;
		if (!cellValueToDate.isEmpty()) {
			java.util.Date parsed = format.parse(cellValueToDate);
			sqlToDate = new java.sql.Date(parsed.getTime());
		}

		// parse doubles
		Double request = 0d;
		if (!dataList.get(11 + rowSelector).toString().isEmpty()) {
			request = Double.parseDouble(dataList.get(11 + rowSelector));
		}

		Double amount = 0d;
		if (!dataList.get(15 + rowSelector).toString().isEmpty()) {
			amount = Double.parseDouble(dataList.get(15 + rowSelector));
		}

		Double authorizationPercent = 0d;
		if (!dataList.get(18 + rowSelector).toString().isEmpty()) {
			authorizationPercent = Double.parseDouble(dataList.get(18 + rowSelector));
		}

		// parse boolean
		Boolean active = false;
		if (dataList.get(19 + rowSelector).contains("true")) {
			active = true;
		}

		// parse strings
		String orderNumber = dataList.get(12 + rowSelector);
		String amountType = dataList.get(16 + rowSelector);
		String amountPeriod = dataList.get(17 + rowSelector);

		// make systemContract
		SystemContract systemContract = new SystemContract();
		systemContract.setSystem(sys);
		systemContract.setRequest(request);
		systemContract.setOrderNumber(orderNumber);
		systemContract.setFromDate(sqlFromDate);
		systemContract.setToDate(sqlToDate);
		systemContract.setAmount(amount);
		systemContract.setAmountType(amountType);
		systemContract.setAmountPeriod(amountPeriod);
		systemContract.setAuthorizationPercent(authorizationPercent);
		systemContract.setActive(active);
		return systemContract;
	}

	private void addToTempObj(String stringCellValue) {
		dataListFromExcel.add(stringCellValue);

	}

	private void addToTempObj(java.util.Date dateCellValue) {
		String cellValue = format.format(dateCellValue);
		dataListFromExcel.add(cellValue);
	}

	public void copy(MultipartFile file) throws IOException {

		Files.copy(file.getInputStream(), rootLocation.resolve(fileName));

	}

	public void delete(MultipartFile file) throws IOException {

		Files.deleteIfExists(rootLocation.resolve(fileName));

	}
}
