package Database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

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
			cs.setString(4, model_num);
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
			JOptionPane.showMessageDialog(null, "Failed to add a team");
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<String> getTeamNameList() {
		ArrayList<String> teamNames = new ArrayList<>();
		try {
			CallableStatement cs = this.dbConnection.getConnection().prepareCall("{call SelectTeamName}");
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while (rs.next())
				teamNames.add(rs.getString("Team_Name"));
			return teamNames;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to get the team name set");
			e.printStackTrace();
			return teamNames;
		}

	}

	public ArrayList<TeamInfo> getTeamInfo(String name) {
		ArrayList<TeamInfo> info = new ArrayList<>();
		try {
			CallableStatement cs = this.dbConnection.getConnection().prepareCall("{? = call getTeamsInfo(?)}");
			cs.setString(2, name);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			int errorCode = cs.getInt(1);
			if (errorCode == 10) {
				JOptionPane.showMessageDialog(null, "Enter Team Name");
				return info;
			}
			while (rs.next()) {
				TeamInfo ti = new TeamInfo(rs.getString("Team_Name"), rs.getString("Engine_manf"),
						rs.getString("Model_Num"));
				info.add(ti);
			}
			return info;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to get Team info");
			e.printStackTrace();
			return info;
		}

	}

	public class TeamInfo {
		String name;
		String manf;
		String num;

		public TeamInfo(String n, String m, String nu) {
			this.name = n;
			this.manf = m;
			this.num = nu;
		}
	}
}
