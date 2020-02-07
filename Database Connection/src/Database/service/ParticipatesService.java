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
	
	public boolean addParticipates(String date, String name, int rank) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call AddParticipates(?, ?, ?)}");
			cs.setString(2, name);
			java.util.Date oldDate;
			oldDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
			cs.setDate(3, new java.sql.Date(oldDate.getTime()));
			cs.setInt(4, rank);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 10) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return false;
			}
			return true;
		} catch (SQLException | ParseException e) {
			JOptionPane.showMessageDialog(null, "Add Failed");
			e.printStackTrace();
			return false;
		}
		
	}

}
