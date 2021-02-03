package com.application.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReadFileService implements IReadFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReadFileService.class);

	@Autowired
	private HttpServletRequest request;

	@Override
	public String uploadFile(MultipartFile multipartFile) throws IllegalStateException, IOException {



		File convertedFile = new File(multipartFile.getOriginalFilename());

		convertedFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(multipartFile.getBytes());
		fos.close();

		LOGGER.info("this is the path: " + convertedFile.getAbsolutePath());

		FileInputStream fileInput = new FileInputStream(new File(convertedFile.getAbsolutePath().toString()));
		Workbook workbook = new XSSFWorkbook(fileInput);

		Sheet sheet = workbook.getSheetAt(0);

		Map<Integer, List<String>> data = new HashMap<>();
		int i = 0;
		for (Row row : sheet) {
			data.put(i, new ArrayList<String>());
			for (Cell cell : row) {
				switch (cell.getCellTypeEnum()) {
				case STRING: {
					data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
					break;
				}
				case NUMERIC: {
					if (DateUtil.isCellDateFormatted(cell)) {
						data.get(i).add(cell.getDateCellValue() + "");
					} else {
						data.get(i).add(cell.getNumericCellValue() + "");
					}
					break;
				}
				case BOOLEAN: {
					data.get(i).add(cell.getBooleanCellValue() + "");

					break;
				}
				case FORMULA: {
					data.get(i).add(cell.getCellFormula() + "");
					break;
				}
				default:
					data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
					break;
				}
			}
			i++;
		}
		JSONObject object = new JSONObject();
		for (int j = 0; j < data.size(); j++) {
			object.put(j, data.get(j));

		}
		LOGGER.info(object + "");
		convertedFile.delete();
		return object.toString();
	}

}
