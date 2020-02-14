package Database.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Connections {
	private Connection connection = null;
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	public boolean connect() throws SQLException, FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream("src/formulaOne.properties"));
		String connectionUrl = "jdbc:sqlserver://golem.csse.rose-hulman.edu;" 
				+ "databaseName=Formula1Database;"
				+ "user=zhaoy10;" 
				+ "password=Zyqsq20000103";
		String url = this.SampleURL;
		String serverName = props.getProperty("serverName");
		String databaseName = props.getProperty("databaseName");
		String user = props.getProperty("serverUsername");
		String pass = props.getProperty("serverPassword");
		url = url.replace("${dbServer}", serverName);
		url = url.replace("${dbName}", databaseName);
		url = url.replace("${user}", user);
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("password");
        pass = decryptor.decrypt(pass);
		url = url.replace("${pass}", pass);

		try {
			this.connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to build a connection");
			return false;
		}
		
		if (connection != null) 
			return true;
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
