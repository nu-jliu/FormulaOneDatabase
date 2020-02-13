package Database.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connections {
	private Connection connection = null;

	public boolean connect() throws SQLException {
		String connectionUrl = "jdbc:sqlserver://golem.csse.rose-hulman.edu;" 
				+ "databaseName=Formula1Database;"
				+ "user=zhaoy10;" 
				+ "password=Zyqsq20000103";

		try {
			this.connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to build a connection");
			return false;
		}
		
		if (connection != null) 
			return true;
		// Code here.
		// Handle any errors that may have occurred.
		return false;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to close the connection");
			e.printStackTrace();
		}
	}
}
