package Database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class RaceService {
	
	private Connections dbconnection = null;
	
	public RaceService(Connections connection) {
		this.dbconnection = connection;
	}
	
	public boolean addRace(String weather, int year, int month, int date, String racename, int hour, int minute, int second, int did) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("? = AddRace(?,?,?,?,?)");
			cs.setString(2,  weather);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, date);
			cs.setDate(3, (java.sql.Date) cal.getTime());
			cs.setString(4, racename);
			cal = Calendar.getInstance();
			cal.set(year, month, date, hour, minute, second);
			cs.setTime(6, (Time) cal.getTime());
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Driver ID not exist");
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Failed to add race");
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<String> getRaceNameList() {
		ArrayList<String> nameList = new ArrayList<>();
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call SelectRaseName}");
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while (rs.next()) 
				nameList.add(rs.getString("Race_Name"));
			return nameList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Failed to get name list");
			e.printStackTrace();
			return nameList;
		}
		
	}

}
