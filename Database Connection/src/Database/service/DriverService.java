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
	
	public boolean addDriver(int age, String name, String date) {
		try {
			CallableStatement cs = this.dbService.getConnection().prepareCall("{? = call AddDriver(?,?,?)}");
			cs.setInt(2, age);
			cs.setString(3, name);
			java.util.Date oldDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
			cs.setDate(4, new java.sql.Date(oldDate.getTime()));
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Age range is invalid");
				return false;
			}
			return true;
		} catch (SQLException | ParseException e) {
			if (e instanceof ParseException)
				JOptionPane.showMessageDialog(null, "Invalid Date Input");
			else
				JOptionPane.showMessageDialog(null, "Failed to add a driver");
			e.printStackTrace();
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
}
