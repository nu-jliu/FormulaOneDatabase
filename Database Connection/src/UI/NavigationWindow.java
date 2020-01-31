package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Database.service.Connections;



public class NavigationWindow {
	JFrame frame;
	JButton Team;
	JButton Race;
	JButton Driver;
	JTable Table;
	JTextField Search;
	Connections connection;
	DefaultTableModel model;
	
	public NavigationWindow(Connections connection) {
		this.connection = connection;
		frame = new JFrame("Formula1Tracker");
		frame.setBounds(100, 100, 543, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Search = new JTextField();
		
		Table = new JTable();
		model = new DefaultTableModel();
		Table.setBounds(0, 100, 200, 200);
		Table.setModel(model);
		frame.getContentPane().add(Table);
		
		Team = new JButton("Team");
		ActionListener TeamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queryData("Team");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
			
		};
		Team.addActionListener(TeamListener);
		Team.setBounds(200, 350, 90, 25);

		Race = new JButton("Race");
		ActionListener RaceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					queryData("Race");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		};
		Race.addActionListener(RaceListener);
		Race.setBounds(200, 300, 90, 25);

		Driver = new JButton("Driver");
		ActionListener DriverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queryData("Driver");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
			
		};
		Driver.addActionListener(DriverListener);
		Driver.setBounds(200, 250, 90, 25);

		frame.getContentPane().add(Driver);
		frame.getContentPane().add(Team);
		frame.getContentPane().add(Race);
		
		frame.setVisible(true);
	}
	
	public void closeFrame() {
		frame.dispose();
	}
	
	public void queryData(String tableName) throws Exception{
		String query = "select * from " + tableName;
		System.out.println(query);
		PreparedStatement stmt;
		ResultSet rs;
		stmt = this.connection.getConnection().prepareStatement(query);
//		stmt.setString(1, tableName);
		rs = stmt.executeQuery();	
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
