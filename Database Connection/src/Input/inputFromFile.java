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
import Database.service.RecoverService;

public class inputFromFile {

	String filePath;
	private Connections dbconnection = null;
	ParticipatesService participatesService;
	RecoverService recoverservice;

	public inputFromFile(String filePath, Connections connection) {
		this.filePath = filePath;
		this.dbconnection = connection;
	}

	public void readParticipates(String racename, int year) throws IOException {
		this.participatesService = new ParticipatesService(dbconnection);
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
						break;

					case 1:
						if (Double.valueOf(cell.toString()).intValue() == 0) {
							JOptionPane.showMessageDialog(null, "null or 0 detected");
							break;
						}
						rank = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				participatesService.addParticipates(year, driverName, racename, rank);
			}
		}
	}

	public void recoverDriver() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		for (int r = firstRowIndex + 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				int DID = 0;
				String Name = null;
				String DOB = null;
				int Number = 0;
				for (int c = firstCellIndex; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						DID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						System.out.println("Index" + c);
						Name = cell.toString();
						System.out.println(Name);
						break;

					case 2:
						System.out.println("Index" + c);
						DOB = cell.toString();
						System.out.println(DOB);
						break;
					case 3:
						Number = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				recoverservice.recoverDrivers(DID, Name, DOB, Number);
			}
		}
	}

	public void recoverRace() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		for (int r = firstRowIndex + 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				int RID = 0;
				String weather = null;
				String date = null;
				String racename = null;
				String laptime = null;
				int DID = 0;
				for (int c = firstCellIndex; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						RID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						weather = cell.toString();
						break;

					case 2:
						date = cell.toString();
						break;
						
					case 3:
						racename = cell.toString();
						break;
					
					case 4:
						laptime = cell.toString();
						break;
					
					case 5:
						DID = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				recoverservice.recoverRace(RID, weather, date, racename, laptime, DID);;
			}
		}
	}

	public void recoverTeam() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				int TID = 0;
				String Name = null;
				String engine = null;
				String model = null;
				for (int c = firstCellIndex; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						TID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						Name = cell.toString();
						break;

					case 2:
						engine = cell.toString();
						break;
					case 3:
						model = cell.toString();
						break;
					default:
						break;
					}
				}
				recoverservice.recoverTeam(TID, Name, engine, model);
			}
		}
	}

	public void recoverParticipates() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		for (int r = firstRowIndex + 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				int RID = 0;
				int DID = 0;
				int Rank = 0;
				for (int c = firstCellIndex; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						RID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						DID = Double.valueOf(cell.toString()).intValue();
						break;

					case 2:
						Rank = Double.valueOf(cell.toString()).intValue();
						break;
					default:
						break;
					}
				}
				recoverservice.recoverParticipates(RID, DID, Rank);
			}
		}
	}

	public void recoverUser() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int lastCellIndex = row.getLastCellNum();
				int UID = 0;
				String user = null;
				String email = null;
				String hash = null;
				String salt = null;
				int access = 1;
				for (int c = 1; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						UID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						user = cell.toString();
						break;

					case 2:
						email = cell.toString();
						break;

					case 3:
						hash = cell.toString();
						break;

					case 4:
						salt = cell.toString();
						break;

					case 5:
						access = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				recoverservice.recoverUsers(UID, user, email, hash, salt, access);
				}
			}
		}
	}

	public void recoverHistory() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int lastCellIndex = row.getLastCellNum();
				int UID = 0;
				int RID = 0;
				for (int c = 1; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						UID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						RID = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				recoverservice.recoverHistory(UID, RID);
			}
		}
	}

	public void recoverStarTeam() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int lastCellIndex = row.getLastCellNum();
				int UID = 0;
				int TID = 0;
				for (int c = 1; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						UID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						TID = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				recoverservice.recoverStarTeam(UID, TID);
			}
		}
	}

	public void recoverStarDriver() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int lastCellIndex = row.getLastCellNum();
				int UID = 0;
				int DID = 0;
				for (int c = 1; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						UID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						DID = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				recoverservice.recoverStarDriver(UID, DID);
			}
		}
	}

	public void recoverStats() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		int lastRowIndex = sheet.getLastRowNum();
		for (int r = 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int lastCellIndex = row.getLastCellNum();
				int year = 0;
				int DID = 0;
				int point = 0;
				for (int c = 1; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						year = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						DID = Double.valueOf(cell.toString()).intValue();
						break;

					case 2:
						point = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				recoverservice.recoverStats(year, DID, point);
			}
		}
	}

	public void recoverWorksFor() throws IOException {
		this.recoverservice = new RecoverService(this.dbconnection);
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
		for (int r = firstRowIndex + 1; r <= lastRowIndex; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				int DID = 0;
				int TID = 0;
				int year = 0;
				for (int c = firstCellIndex; c < lastCellIndex; c++) {
					Cell cell = row.getCell(c);
					switch (c) {
					case 0:
						DID = Double.valueOf(cell.toString()).intValue();
						break;

					case 1:
						TID = Double.valueOf(cell.toString()).intValue();
						break;

					case 2:
						year = Double.valueOf(cell.toString()).intValue();
						break;

					default:
						break;
					}
				}
				recoverservice.recoverWorksFor(DID, TID, year);
			}
		}
	}
}
