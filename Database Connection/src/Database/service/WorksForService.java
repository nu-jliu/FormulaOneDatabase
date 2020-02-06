package Database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class WorksForService {
	private Connections dbService = null;

	public WorksForService(Connections dbService) {
		this.dbService = dbService;
	}

	public boolean addWorksFor(String Dname, String Tname,int year) {
		try {
			CallableStatement cs = this.dbService.getConnection().prepareCall("{? = call AddWorksFor(?,?,?)}");
			cs.setString(2, Dname);
			cs.setString(3, Tname);
			cs.setInt(4,year);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "Please Entry valid Team Name");
				return false;
			}
			if (errorCode == 3) {
				JOptionPane.showMessageDialog(null, "Please Entry valid  Year");
				return false;
			}
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Please Entry valid Driver Name");
				return false;
			}
			if (errorCode == 4) {
				JOptionPane.showMessageDialog(null, "record already exist");
				return false;
			}
			if (errorCode == 5) {
				JOptionPane.showMessageDialog(null, "This Driver already have record with another team for this year");
				return false;
			}
			JOptionPane.showMessageDialog(null, "WorksFor record has been added successfully");
			return true;
		} catch (SQLException e) {
			
	
				JOptionPane.showMessageDialog(null, "Failed to add a record");
//			e.printStackTrace();
			return false;
		}

	}
	public boolean updateWorksFor(String Dname, String Tname,int year) {
		try {
			CallableStatement cs = this.dbService.getConnection().prepareCall("{? = call UpdateWorksFor(?,?,?)}");
			cs.setString(2, Dname);
			cs.setString(3, Tname);
			cs.setInt(4,year);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "Please Entry valid Team Name");
				return false;
			}
			if (errorCode == 3) {
				JOptionPane.showMessageDialog(null, "Please Entry valid  Year");
				return false;
			}
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Please Entry valid Driver Name");
				return false;
			}
			if (errorCode == 4) {
				JOptionPane.showMessageDialog(null, "record already exist");
				return false;
			}
			
			JOptionPane.showMessageDialog(null, "WorksFor record has been updated successfully");
			return true;
		} catch (SQLException e) {
			
	
				JOptionPane.showMessageDialog(null, "Failed to update a record");
//			e.printStackTrace();
			return false;
		}

	}


}
