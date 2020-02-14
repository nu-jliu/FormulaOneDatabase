package Database.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class ParticipatesService {
	
	private Connections dbconnection = null;
	
	public ParticipatesService(Connections connection) {
		this.dbconnection = connection;
	}
	
	public boolean addParticipates(int year, String drivername, String racename, int rank) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call AddParticipates(?, ?, ?, ?)}");
			cs.setString(2, drivername);
			cs.setInt(3, year);
			cs.setString(4, racename);
			cs.setInt(5, rank);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 10) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return false;
			}
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Add Failed");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean updateParticipates(int year, String drivername, String racename, int rank) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call UpdateParticipates(?, ?, ?, ?)}");
			cs.setString(2, drivername);
			cs.setInt(3, year);
			cs.setString(4, racename);
			cs.setInt(5, rank);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 10) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return false;
			}
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Failed");
			e.printStackTrace();
			return false;
		}
	}

}
