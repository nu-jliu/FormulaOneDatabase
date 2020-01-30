package Database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TeamService {
	
	private Connections dbConnection = null;
	
	public TeamService(Connections connection) {
		this.dbConnection = connection;
	}
	
	public boolean addTeam(String name, String manf, String model_num) {
		try {
			CallableStatement cs = this.dbConnection.getConnection().prepareCall("{? = call AddTeam(?,?,?)}");
			cs.setString(2, name);
			cs.setString(3, manf);
			cs.setString(3, model_num);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Team name cannot be null or empty");
				return false;
			} else if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "Tean name already exists");
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Failed to add a team");
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<String> getTeamNameList() {
		ArrayList<String> teamNames = new ArrayList<String>();
		try {
			CallableStatement cs = this.dbConnection.getConnection().prepareCall("{call SelectTeamName}");
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while (rs.next()) {
				teamNames.add(rs.getString("Team_Name"));
			}
			return teamNames;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Failed to get the team name set");
			e.printStackTrace();
			return teamNames;
		}
		
	}
}
