package Input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
		if (!"xls".equals(split[1])) {
			JOptionPane.showMessageDialog(null, "Wrong File Type Detected");
			return;
		}
		FileInputStream fis = new FileInputStream(excel);
		Workbook wb = new HSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);
		int firstRowIndex = sheet.getFirstRowNum();
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = firstRowIndex; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				String driverName = null;
				int rank = 0;
				for (int c = firstCellIndex; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						driverName = cell.toString();
						System.out.println("Driver:" + driverName);
						break;

					case 1:
						rank = Double.valueOf(cell.toString()).intValue();
						System.out.println(rank);
						break;
					}
					participatesService.addParticipates(year, driverName, racename, rank);
				}
			}
		}
	}
}
