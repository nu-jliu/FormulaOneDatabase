package Database.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
			cs.setDate(4, (Date) new SimpleDateFormat("yyyy-mm-dd").parse(date));
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Age range is invalid");
				return false;
			}
			return true;
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			if (e instanceof ParseException)
				JOptionPane.showMessageDialog(null, "Invalid Date Input");
			else
				JOptionPane.showMessageDialog(null, "Failed to add a driver");
			e.printStackTrace();
			return false;
		}
		
	}

	public ArrayList<String> getRestaurants() {	
		
		ArrayList<String> Drivers = new ArrayList<String>();
		String query = "select Name,Age,DOB\n"+"from Driver\n";
		Connection con = this.dbService.getConnection();
		try(Statement STMT= con.createStatement())
		{
		ResultSet DR= STMT.executeQuery(query);
		while(DR.next())
		{
			String DriverName= DR.getString("Name,Age,DOB");
			Drivers.add(DriverName);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Drivers;
	}
}
