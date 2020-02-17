package Database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class RaceService {

	private Connections dbconnection = null;

	public RaceService(Connections connection) {
		this.dbconnection = connection;
	}

	public boolean addRace(String weather, String date, String racename, String laptime, String drivername) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call AddRace(?,?,?,?,?)}");
			cs.setString(2, weather);
			java.util.Date oldDate = null;
			java.util.Date oldTime = null;
			try {
				oldDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				oldTime = new SimpleDateFormat("mm:ss.SSS").parse(laptime);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Incorrect input format");
				e.printStackTrace();
				return false;
			}
			cs.setDate(3, new java.sql.Date(oldDate.getTime()));
			cs.setString(4, racename);
			cs.setTime(5, new Time(oldTime.getTime()));
			cs.setString(6, drivername);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return false;
			}
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to add race");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateRace(String weather, String date, String racename, String laptime, String drivername) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call UpdateRace(?,?,?,?,?)}");
			cs.setString(2, (weather.length() == 0) ? null : weather);
			java.util.Date oldDate = null;
			java.util.Date oldTime = null;
			try {
				if (date.length() != 0)
					oldDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				if (laptime.length() != 0)
					oldTime = new SimpleDateFormat("mm:ss.SSS").parse(laptime);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Incorrect input format");
				e.printStackTrace();
				return false;
			}
			cs.setDate(3, (oldDate == null) ? null : new java.sql.Date(oldDate.getTime()));
			cs.setString(4, (racename.length() == 0) ? null : racename);
			cs.setTime(5, (oldTime == null) ? null : new Time(oldTime.getTime()));
			cs.setString(6, (drivername.length() == 0) ? null : drivername);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return false;
			}
			return true;
		} catch (SQLException e) {
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
			JOptionPane.showMessageDialog(null, "Failed to get name list");
			e.printStackTrace();
			return nameList;
		}
	}

	public ArrayList<RaceInfo> getRaceInfo(Date time, String racename) {
		ArrayList<RaceInfo> info = new ArrayList<>();
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call getRaceInfo(?,?)}");
			cs.setTime(2, (Time) time);
			cs.setString(3, racename);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			int errorCode = cs.getInt(1);
			if (errorCode == 10) {
				JOptionPane.showMessageDialog(null, "Invalid input");
				return info;
			}
			while (rs.next()) {
				RaceInfo ri = new RaceInfo(rs.getString("Weather"), rs.getDate("Date"), rs.getString("Race_Name"),
						rs.getTime("Lap_Time"), rs.getString("Name"));
				info.add(ri);
			}
			return info;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to get race info");
			e.printStackTrace();
			return info;
		}

	}

	public class RaceInfo {

		String weather;
		Date racetime;
		String racename;
		Time laptime;
		String drivername;

		public RaceInfo(String w, Date r, String ra, Time l, String d) {
			this.weather = w;
			this.racename = ra;
			this.drivername = d;
			this.laptime = l;
			this.racetime = r;
		}

	}
}
