package Input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Database.service.Connections;
import Database.service.ParticipatesService;

public class inputFromFile {

	String filePath;
	private Connections dbconnection = null;
	ParticipatesService participatesService;

	public inputFromFile(String filePath, Connections connection) {
		this.filePath = filePath;
		this.dbconnection = connection;
		this.participatesService = new ParticipatesService(dbconnection);

	}

	public void fileReader(String racename, int year) throws IOException {
		File excel = new File(filePath);
		if (excel.isFile() && excel.exists()) {

		} else {
			JOptionPane.showMessageDialog(null, "The File is Not Existed");
			return;
		}
		String[] split = excel.getName().split("\\.");
		if (!"xlsx".equals(split[1])) {
			JOptionPane.showMessageDialog(null, "Wrong File Type Detected");
			return;
		}
		FileInputStream fis = new FileInputStream(excel);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);
		int firstRowIndex = sheet.getFirstRowNum() + 1;
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = firstRowIndex; r < lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				String driverName = null;
				int rank = 0;
				for (int c = firstCellIndex; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 1:
						driverName = cell.toString();
						break;

					case 2:
						rank = Integer.parseInt(cell.toString());
						break;
					}
					participatesService.addParticipates(year, driverName, racename, rank);
				}
			}
		}
	}
}
