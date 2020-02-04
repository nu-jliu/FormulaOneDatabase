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
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 100, 543, 543);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		
		this.Table = new JTable();
		this.model = new DefaultTableModel();
		this.Table.setBounds(43, 33, 443, 200);
		this.Table.setModel(model);
		this.frame.getContentPane().add(Table);

		this.Team = new JButton("Team");
		ActionListener TeamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PersonalWindow.this.model = new DefaultTableModel();
					PersonalWindow.this.Table.setModel(PersonalWindow.this.model);
					queryData("Team");
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
			
		};
		this.Team.addActionListener(TeamListener);
		this.Team.setBounds(200, 350, 90, 25);
		
		Driver = new JButton("Driver");
		ActionListener DriverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PersonalWindow.this.model = new DefaultTableModel();
					PersonalWindow.this.Table.setModel(PersonalWindow.this.model);
					queryData("Driver");
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
			
		};
		this.Driver.addActionListener(DriverListener);
		this.Driver.setBounds(200, 250, 90, 25);
		JButton update = new JButton("Update");
		update.setBounds(200, 400, 90, 25);
		ActionListener updateListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PersonalWindow.this.closeFrame();
				new UpdateWindow(connection, PersonalWindow.this.UID);
			}
			
		};
		update.addActionListener(updateListener);
		JButton goBack = new JButton("Go Back");
		goBack.setBounds(200, 450, 90, 25);
		ActionListener backListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				new NavigationWindow(connection, UID);
			}

		};
		goBack.addActionListener(backListener);
		this.frame.getContentPane().add(goBack);
		this.frame.getContentPane().add(update);
		this.frame.getContentPane().add(Driver);
		this.frame.getContentPane().add(Team);
		this.frame.setVisible(true);
	}
	
	public void closeFrame() {
		this.frame.dispose();
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
    		this.model.addRow(row);
    	}
	}
}
