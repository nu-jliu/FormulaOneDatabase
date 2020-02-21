package Database.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class RecoverService {

	private Connections dbconnection = null;

	public RecoverService(Connections dbconnection) {
		this.dbconnection = dbconnection;
	}

	public void recoverDrivers(int DID, String Name, String DOB, int Number) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call Pupulate_Driver(?,?,?,?)}");
			cs.setInt(2, DID);
			cs.setString(3, Name);
			java.util.Date oldDate = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
			cs.setDate(4, new java.sql.Date(oldDate.getTime()));
			cs.setInt(5, Number);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			JOptionPane.showMessageDialog(null, "Driver has been added successfully");
		} catch (SQLException | ParseException e) {
			if (e instanceof ParseException)
				JOptionPane.showMessageDialog(null, "Invalid Date Input");
			else
				JOptionPane.showMessageDialog(null, "Failed to add a driver");
		}
	}

	public void recoverUsers(int UID, String user, String email, String hash, String salt, int access) {
		try {
			CallableStatement cs = this.dbconnection.getConnection()
					.prepareCall("{? = call [Recover_User](?,?,?,?,?,?)}");
			cs.setInt(2, UID);
			cs.setString(3, user);
			cs.setString(4, email);
			cs.setString(5, hash);
			cs.setString(6, salt);
			cs.setInt(7, access);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Invalid input");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover a user");
			e.printStackTrace();
		}
	}

	public void recoverParticipates(int RID, int DID, int Rank) {
		try {
			CallableStatement cs = this.dbconnection.getConnection()
					.prepareCall("{? = call [Recover_Participates](?,?,?)}");
			cs.setInt(2, RID);
			cs.setInt(3, DID);
			cs.setInt(4, Rank);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			JOptionPane.showMessageDialog(null, "Participates has been added successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover participates");
		}
	}
	
	public void recoverTeam(int TID, String name, String engine, String model) {
		try {
			CallableStatement cs = this.dbconnection.getConnection()
					.prepareCall("{? = call [Recover_Team](?,?,?,?)}");
			cs.setInt(2, TID);
			cs.setString(3, name);
			cs.setString(4, engine);
			cs.setString(5, model);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			JOptionPane.showMessageDialog(null, "team have been added successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover team");
		}
	}

	public void recoverRace(int RID, String weather, String date, String racename, String laptime, int DID) {
		try {
			CallableStatement cs = this.dbconnection.getConnection()
					.prepareCall("{? = call [Recover_Race](?,?,?,?,?,?)}");
			cs.setInt(2, RID);
			cs.setString(3, weather);
			java.util.Date oldDate = null;
			java.util.Date oldTime = null;
			try {
				oldDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				oldTime = new SimpleDateFormat("mm:ss.SSS").parse(laptime);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Incorrect input format");
				e.printStackTrace();
			}
			cs.setDate(4, new java.sql.Date(oldDate.getTime()));
			cs.setString(5, racename);
			cs.setTime(6, new Time(oldTime.getTime()));
			cs.setInt(7, DID);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			int errorCode = cs.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "Invalid input");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover race");
			e.printStackTrace();
		}
	}

	public void recoverHistory(int UID, int RID) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call [Recover_History](?,?)}");
			cs.setInt(2, UID);
			cs.setInt(3, RID);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			JOptionPane.showMessageDialog(null, "Watched History has been added successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover watched History");
		}
	}

	public void recoverStarTeam(int UID, int TID) {
		try {
			CallableStatement cs = this.dbconnection.getConnection().prepareCall("{? = call [Recover_Star_Tean](?,?)}");
			cs.setInt(2, UID);
			cs.setInt(3, TID);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			JOptionPane.showMessageDialog(null, "Star Team has been added successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover star team");
		}
	}

	public void recoverStarDriver(int UID, int DID) {
		try {
			CallableStatement cs = this.dbconnection.getConnection()
					.prepareCall("{? = call [Recover_Star_Driver](?,?)}");
			cs.setInt(2, UID);
			cs.setInt(3, DID);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			JOptionPane.showMessageDialog(null, "Star Driver has been added successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover star driver");
		}
	}

	public void recoverStats(int year, int DID, int point) {
		try {
			CallableStatement cs = this.dbconnection.getConnection()
					.prepareCall("{? = call [Recover_Stats_by_Year](?,?)}");
			cs.setInt(2, year);
			cs.setInt(3, DID);
			cs.setInt(4, point);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			JOptionPane.showMessageDialog(null, "stats have been added successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to recover stats");
		}
	}

}
