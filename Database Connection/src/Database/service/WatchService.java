package Database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class WatchService {

	private Connections dbConnection = null;
	private int uid;

	public WatchService(Connections connection, int UID) {
		this.dbConnection = connection;
		this.uid=UID;
	}

	public boolean addHistory(String race, int year) {
		try {
			CallableStatement cs = this.dbConnection.getConnection().prepareCall("{? = call AddHistory(?,?,?)}");
			cs.setString(2, race);
			cs.setInt(3, uid);
			cs.setInt(4, year);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Race Name cannot be empty");
				return false;
			} else if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "Invalid Year");
				return false;
			} else if (errorCode == 3) {
				JOptionPane.showMessageDialog(null, "No Race Found");
				return false;
			} else if (errorCode == 4 || errorCode == 5) {
				JOptionPane.showMessageDialog(null, "Illegal User: " + this.uid);
				return false;
			} else if (errorCode == 6) {
				JOptionPane.showMessageDialog(null, "Duplication Entry");
				return false;
			}
			JOptionPane.showMessageDialog(null, "Histroy has been added suscessfully");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to add a history");
			e.printStackTrace();
			return false;
		}
	}
	

	public ArrayList<String> getTeamNameList() {
		ArrayList<String> teamNames = new ArrayList<>();
		try {
			CallableStatement cs = this.dbConnection.getConnection().prepareCall("{call SelectTeamNames}");
			cs.execute();
			ResultSet rs = cs.getResultSet();
			while (rs.next())
				teamNames.add(rs.getString("Team_name"));
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
