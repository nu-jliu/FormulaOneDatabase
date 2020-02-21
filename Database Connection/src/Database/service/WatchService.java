package Database.service;

import java.awt.Component;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Database.service.TeamService.TeamInfo;

public class WatchService {

	private Connections dbConnection = null;
	private int uid;

	public WatchService(Connections connection, int UID) {
		this.dbConnection = connection;
		this.uid = UID;
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

//	public void getUnviewedRace(int UID, int year) {
////		ArrayList<RaceInfo> race = new ArrayList<>();
////		try {
////			CallableStatement cs = this.dbConnection.getConnection()
////					.prepareCall("{? = call get_not_watched_race(?,?)}");
////			cs.setInt(2, UID);
////			cs.setInt(3, year);
////			cs.registerOutParameter(1, Types.INTEGER);
////			ResultSet rs = cs.executeQuery();
////			int errorCode = cs.getInt(1);
////			if (errorCode == 10) {
////				JOptionPane.showMessageDialog(null, "Please select a valid year");
////				return race;
////			}
////			while (rs.next()) {
////				RaceInfo ri = new RaceInfo(rs.getString("Race Name"), rs.getDate("Date"), rs.getString("Weather"),
////						rs.getTime("Fastest Lap Time"), rs.getString("Fastest Driver"), rs.getString("Champion"));
////				race.add(ri);
////			}
////			return race;
////		} catch (SQLException e) {
////			JOptionPane.showMessageDialog(null, "Failed to get  info");
////			e.printStackTrace();
////			return race;
////		}
////		
//		ArrayList<RaceInfo> race = new ArrayList<>();
//		try {
//			CallableStatement cs = this.dbConnection.getConnection()
//					.prepareCall("{? = call get_not_watched_race(?,?)}");
//			cs.setInt(2, UID);
//			cs.setInt(3, year);
//			cs.registerOutParameter(1, Types.INTEGER);
//			ResultSet rs = cs.executeQuery();
//			ResultSetMetaData rsmd = rs.getMetaData();
//			int count = rsmd.getColumnCount();
//			for (int i = 1; i <= count; i++) {
//				this.model.addColumn(rsmd.getColumnName(i));
//			}
//			String[] rowData = new String[count];
//			while (rs.next()) {
//				RaceInfo ri = new RaceInfo(rs.getString("Race Name"), rs.getDate("Date"), rs.getString("Weather"),
//						rs.getTime("Fastest Lap Time"), rs.getString("Fastest Driver"), rs.getString("Champion"));
//				race.add(ri);
//			}
//			this.Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//			int totalWidth = 0;
//			for (int c = 0; c < this.Table.getColumnCount(); c++) {
//				TableColumn column = this.Table.getColumnModel().getColumn(c);
//				int preferredWidth = column.getMinWidth();
//				int maxWidth = column.getMaxWidth();
//				for (int r = 0; r < this.Table.getRowCount(); r++) {
//					TableCellRenderer rend = this.Table.getCellRenderer(r, c);
//					Component comp = this.Table.prepareRenderer(rend, r, c);
//					int width = comp.getPreferredSize().width + this.Table.getIntercellSpacing().width;
//					preferredWidth = Math.max(preferredWidth, width);
//					if (preferredWidth >= maxWidth) {
//						preferredWidth = maxWidth;
//						break;
//					}
//				}
//				column.setPreferredWidth(preferredWidth);
//				totalWidth += preferredWidth;
//			}
//			if (totalWidth < this.Table.getWidth()) {
//				int totalOffset = this.Table.getWidth() - totalWidth;
//				int offset = totalOffset / this.Table.getColumnCount();
//				for (int c = 0; c < this.Table.getColumnCount(); c++) {
//					TableColumn column = this.Table.getColumnModel().getColumn(c);
//					int width = column.getPreferredWidth();
//					width += offset;
//					totalOffset -= offset;
//					column.setPreferredWidth(width);
//				}
//				TableColumn column = this.Table.getColumnModel().getColumn(0);
//				int width = column.getPreferredWidth();
//				width += totalOffset;
//				column.setPreferredWidth(width);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public class RaceInfo {
		String name;
		Date date;
		String weather;
		Time laptime;
		String drivername;
		String champion;

		public RaceInfo(String n, Date d, String w, Time l, String dr, String c) {
			this.name = n;
			this.date = d;
			this.weather = w;
			this.laptime = l;
			this.drivername = dr;
			this.champion = c;
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
