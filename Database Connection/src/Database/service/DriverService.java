package Database.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class DriverService {
private Connections dbService = null;
	
	public DriverService(Connections dbService) {
		this.dbService = dbService;
	}
	
/*	public boolean addResturant(String restName, String addr, String contact) {
		//TODO: Task 5
		JOptionPane.showMessageDialog(null, "Add Restaurant not implemented.");
		return false;
	}*/
	

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
