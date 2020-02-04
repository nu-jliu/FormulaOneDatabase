package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Database.service.Connections;

public class PersonalWindow {
	JFrame frame;
	JButton Team;
	JButton Driver;
	JTable Table;
	Connections connection;
	DefaultTableModel model;
	int UID;

	public PersonalWindow(Connections connection, int UID) {
		this.UID = UID;
		this.connection = connection;
		frame = new JFrame("Formula1Tracker");
		frame.setBounds(100, 100, 543, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Table = new JTable();
		model = new DefaultTableModel();
		Table.setBounds(43, 33, 443, 200);
		Table.setModel(model);
		frame.getContentPane().add(Table);

		Team = new JButton("Team");
		ActionListener TeamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Team");
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
			
		};
		Team.addActionListener(TeamListener);
		Team.setBounds(200, 350, 90, 25);
		
		Driver = new JButton("Driver");
		ActionListener DriverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Driver");
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
			
		};
		Driver.addActionListener(DriverListener);
		Driver.setBounds(200, 250, 90, 25);
		JButton update = new JButton("Update");
		update.setBounds(200, 400, 90, 25);
		ActionListener updateListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PersonalWindow.this.closeFrame();
				UpdateWindow update = new UpdateWindow(connection, PersonalWindow.this.UID);
			}
			
		};
		update.addActionListener(updateListener);
		frame.getContentPane().add(update);
		frame.getContentPane().add(Driver);
		frame.getContentPane().add(Team);
		frame.setVisible(true);
	}
	
	public void closeFrame() {
		frame.dispose();
	}
	
	public void queryData(String tableName) throws Exception{
		String query = "";
		if(tableName.equals("Team")) {
			query = "{? = call get_Starred_Team (?)}";
		}
		else if(tableName.equals("Driver")) {
			query = "{? = call get_Starred_Driver (?)}";
		}
		CallableStatement cs = this.connection.getConnection().prepareCall(query);
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setInt(2, this.UID);
		ResultSet rs;
		rs = cs.executeQuery();	
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		for(int i = 1; i <= count; i++) {
			model.addColumn(rsmd.getColumnName(i));
		}
    	String[] row = new String[count];
    	while(rs.next()) {
    		for(int i = 0; i < count; i++) {
    			row[i] = rs.getString(i + 1);
    		}
    		model.addRow(row);
    	}
	}
}
