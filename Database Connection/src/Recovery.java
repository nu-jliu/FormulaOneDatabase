import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Database.service.Connections;
import recovery.fileLoader;

public class Recovery {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Connections connection = new Connections();
		try {
			connection.connect();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Failed to connect to the server");
			e1.printStackTrace();
		}
		fileLoader fileLoader = new fileLoader(connection);
	}

}
