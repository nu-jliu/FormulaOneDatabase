package Database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DriverService {
	private Connections dbService = null;

	public DriverService(Connections dbService) {
		this.dbService = dbService;
	}

	public boolean addDriver(String name, String date) {
		try {
			CallableStatement cs = this.dbService.getConnection().prepareCall("{? = call AddDriver(?,?)}");
			cs.setString(2, name);
			java.util.Date oldDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			cs.setDate(3, new java.sql.Date(oldDate.getTime()));
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "Age range is invalid");
				return false;
			}
			if (errorCode == 3) {
				JOptionPane.showMessageDialog(null, "Please Entry Date of Birth");
				return false;
			}
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Please Entry Name");
				return false;
			}
			JOptionPane.showMessageDialog(null, "Driver has been added successfully");
			return true;
		} catch (SQLException | ParseException e) {
			if (e instanceof ParseException)
				JOptionPane.showMessageDialog(null, "Invalid Date Input");
			else
				JOptionPane.showMessageDialog(null, "Failed to add a driver");
//			e.printStackTrace();
			return false;
		}

	}
	public boolean updateDriver(String name, String date) {
		try {
			CallableStatement cs = this.dbService.getConnection().prepareCall("{? = call UpdateDriver(?,?)}");
			cs.setString(2, name);
			java.util.Date oldDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			cs.setDate(3, new java.sql.Date(oldDate.getTime()));
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "Please entry Date of Birth");
				return false;
			}
			if (errorCode == 3) {
				JOptionPane.showMessageDialog(null, "Please entry Name");
				return false;
			}
			if (errorCode == 4) {
				JOptionPane.showMessageDialog(null, "The age range is invalid");
				return false;
			}
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Please Entry valid Name");
				return false;
			}
			JOptionPane.showMessageDialog(null, "Successfully changed the driver info");
			return true;
		} catch (SQLException | ParseException e) {
			JOptionPane.showMessageDialog(null, "Failed to update a driver");
			//e.printStackTrace();
			return false;
		}

	}

	public ArrayList<String> getDriverNames() {
		ArrayList<String> driverNames = new ArrayList<>();
		try {
			CallableStatement cs = this.dbService.getConnection().prepareCall("{call getDrivers}");
			ResultSet rs = cs.executeQuery();
			while (rs.next())
				driverNames.add(rs.getString("Name"));
			return driverNames;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to add driver");
			e.printStackTrace();
			return driverNames;
		}
	}
	
	public ArrayList<Integer> getStatsYear() {
		ArrayList<Integer> years  = new ArrayList<>();
		try {
			CallableStatement cs = this.dbService.getConnection().prepareCall("{call get_Stats_Years}");
			ResultSet rs = cs.executeQuery();
			while (rs.next())
				years.add(rs.getInt("Year"));
			return years;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to get years");
			e.printStackTrace();
			return years;
		}
	}
}
