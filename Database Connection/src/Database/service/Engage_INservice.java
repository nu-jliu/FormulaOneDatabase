package Database.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class Engage_INservice {
	
	private Connections dbconnection = null;
	
	public Engage_INservice(Connections connection) {
		this.dbconnection = connection;
	}
	
	public boolean addEngage_IN(int rid, int tid) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call AddEngage_IN(?,?)}");
			cs.setInt(2, rid);
			cs.setInt(3, tid);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Invalid ID input");
				return false;
			}
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to insert into Engage_in");
			e.printStackTrace();
			return false;
		}
		
	}
}
