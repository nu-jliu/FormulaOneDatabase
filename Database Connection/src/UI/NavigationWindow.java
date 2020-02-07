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



public class NavigationWindow {
	JFrame frame;
	JButton Team;
	JButton Race;
	JButton WorksFor;
	JButton Driver;
	JButton Stats;
	JTable Table;
	
	Connections connection;
	DefaultTableModel model;
	
	public NavigationWindow(Connections connection, int UID) {
		this.connection = connection;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 100, 700, 700);
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
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Team");
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
			
		};
		this.Team.addActionListener(TeamListener);
		this.Team.setBounds(200, 350, 90, 25);
		
		this.WorksFor = new JButton("WorksFor");
		ActionListener WorksForListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("WorksFor");
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
			
		};
		this.WorksFor.addActionListener(WorksForListener);
		this.WorksFor.setBounds(200, 500, 90, 25);
		
		this.Race = new JButton("Race");
		ActionListener RaceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Race");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		};
		this.Race.addActionListener(RaceListener);
		this.Race.setBounds(200, 300, 90, 25);

		this.Driver = new JButton("Driver");
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
		this.Driver.addActionListener(DriverListener);
		this.Driver.setBounds(200, 250, 90, 25);
		
		this.Stats = new JButton("Stats");
		ActionListener StatsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Stats");
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
			
		};
		this.Stats.addActionListener(StatsListener);
		this.Stats.setBounds(200, 400, 90, 25);
		
		JButton update = new JButton("Update");
		update.setBounds(200, 450, 90, 25);
		ActionListener updateListener = new ActionListener() {

		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeFrame();
				new UpdateWindow(connection, UID);
			}
			
		};
		JButton personal = new JButton("Personal");
		personal.setBounds(190, 600, 110, 25);
		ActionListener personalListener = new ActionListener() {

		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeFrame();
				new PersonalWindow(connection, UID);
			}
			
		};
		personal.addActionListener(personalListener);
		update.addActionListener(updateListener);
		this.frame.getContentPane().add(update);
		this.frame.getContentPane().add(Driver);
		this.frame.getContentPane().add(Team);
		this.frame.getContentPane().add(Race);
		this.frame.getContentPane().add(personal);
		this.frame.getContentPane().add(Stats);
		this.frame.getContentPane().add(WorksFor);
		this.frame.setVisible(true);
	}
	
	public void closeFrame() {
		this.frame.dispose();
	}
	
	public void queryData(String tableName) throws Exception{
		String query = "";
		if(tableName.equals("Team")) {
			query = "{? = call get_All_Teams}";
		}
		else if(tableName.equals("Driver")) {
			query = "{? = call get_All_Drivers}";
		}
		else if(tableName.equals("Race")) {
			query = "{? = call get_All_Races}";
		}
		else if (tableName.equals("Stats"))
			query = "{? = call get_all_stats}";
		else if(tableName.equals("WorksFor"))
			query = "{? = call get_all_WorksFor}";
		CallableStatement cs = this.connection.getConnection().prepareCall(query);
		cs.registerOutParameter(1, Types.INTEGER);
		ResultSet rs;
		rs = cs.executeQuery();	
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		for(int i = 1; i <= count; i++) {
			this.model.addColumn(rsmd.getColumnName(i));
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
