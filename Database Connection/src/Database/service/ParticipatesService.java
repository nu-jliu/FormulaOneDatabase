package Database.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class ParticipatesService {
	
	private Connections dbconnection = null;
	
	public ParticipatesService(Connections connection) {
		this.dbconnection = connection;
	}
	
	public boolean addParticipates(int rid, int did, int rank) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call AddParticipates(?, ?, ?)}");
			cs.setInt(2, rid);
			cs.setInt(3, did);
			cs.setInt(4, rank);
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

}
