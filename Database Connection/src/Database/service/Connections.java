package Database.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connections {
	private Connection connection = null;
	public boolean connect() throws SQLException { 
	        String connectionUrl =
					"jdbc:sqlserver://golem.csse.rose-hulman.edu;"
					+ "databaseName=Formula1Database;"
					+ "user=zhaoy1020;"
					+ "password=Zyqsq20000103";
	
	        this.connection = DriverManager.getConnection(connectionUrl);
			if(connection!=null) {
				return true;
				
			}
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
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Failed to close the connection");
			e.printStackTrace();
		} 
	}
		

/*	//DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS STRING IS EDITED
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection  connection = null;

	private String databaseName;
	private String serverName;

	public Connections(String serverName, String databaseName) {
		//DO NOT CHANGE THIS METHOD
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect(String user, String pass) throws SQLException {
		String connectionUrl =
				"jdbc:sqlserver://"+serverName+";"
				+ "databaseName="+databaseName+";"
				+ "user="+user+";"
				+ "password="+pass;
		

        
			connection  = DriverManager.getConnection(connectionUrl);
		if(connection!=null) {
			return true;
			
		}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	
}
