package UI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Database.service.Connections;
import Database.service.DriverService;
import Database.service.WatchService;



public class NavigationWindow {
	JFrame frame;
	JButton Team;
	JButton Race;
	JButton WorksFor;
	JButton Driver;
	JButton Stats;
	JButton teamStats;
	JTable Table;
	JScrollPane scrollpane;
	JComboBox<Integer> yearbox;
	Connections connection;
	DefaultTableModel model;
	
	public NavigationWindow(Connections connection, int UID) {
		this.connection = connection;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 100, 700, 400);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		
		this.Table = new JTable();
		this.scrollpane = new JScrollPane(this.Table);
		this.model = new DefaultTableModel();
		this.scrollpane.setBounds(43, 33, 597, 200);
		this.Table.setModel(model);
		
		this.yearbox = new JComboBox<Integer>();
		DriverService driverService = new DriverService(this.connection);
		ArrayList<Integer> yearsArrayList = driverService.getStatsYear();
		for(int s : yearsArrayList)
			yearbox.addItem(s);
		yearbox.setBounds(561, 245, 79, 22);
		frame.getContentPane().add(yearbox);
		
		this.Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				JTable theTable = NavigationWindow.this.Table;
				int row = theTable.getSelectedRow();
				if (theTable.getRowCount() > 0 && theTable.getColumnCount() > 0 && theTable.getColumnName(0).equals("Race Name")) {
					WatchService watchservice = new WatchService(NavigationWindow.this.connection, UID);
					String raceName = (String) theTable.getValueAt(row, 0);
					int year = Integer.parseInt(((String) theTable.getValueAt(row, 1)).substring(0, 4));
					watchservice.addHistory(raceName, year);
				}
					
			}
			
		});
		this.frame.getContentPane().add(this.scrollpane);
		
		this.Team = new JButton("Team");
		ActionListener TeamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Team");
				} catch (Exception e1) {
//					e1.printStackTrace();
				}				
			}
			
		};
		this.Team.addActionListener(TeamListener);
		this.Team.setBounds(243, 244, 90, 25);
		
		this.WorksFor = new JButton("WorksFor");
		ActionListener WorksForListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("WorksFor");
				} catch (Exception e1) {
//					e1.printStackTrace();
				}				
			}
			
		};
		this.WorksFor.addActionListener(WorksForListener);
		this.WorksFor.setBounds(343, 244, 90, 25);
		
		this.Race = new JButton("Race");
		ActionListener RaceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Race");
				} catch (Exception e1) {
//					e1.printStackTrace();
				}
			}
			
		};
		this.Race.addActionListener(RaceListener);
		this.Race.setBounds(143, 244, 90, 25);

		this.Driver = new JButton("Driver");
		ActionListener DriverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model);
					queryData("Driver");
				} catch (Exception e1) {
//					e1.printStackTrace();
				}				
			}
			
		};
		this.Driver.addActionListener(DriverListener);
		this.Driver.setBounds(43, 244, 90, 25);
		
		this.Stats = new JButton("Stats");
		ActionListener StatsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model); 
					queryStats((int) yearbox.getSelectedItem(), false);
				} catch (Exception e1) {
//					e1.printStackTrace();
				}				
			}
			
		};
		
		this.Stats.addActionListener(StatsListener);
		this.Stats.setBounds(443, 244, 90, 25);
		
		this.teamStats = new JButton("StatTeam");
		this.teamStats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model = new DefaultTableModel();
					Table.setModel(model); 
					queryStats((int) yearbox.getSelectedItem(), true);
				} catch (Exception e1) {
//					e1.printStackTrace();
				}
			}
			
		});
		
		this.teamStats.setBounds(443, 272, 90, 25);
		
		JButton personal = new JButton("Personal");
		personal.setBounds(432, 320, 90, 25);
		ActionListener personalListener = new ActionListener() {

		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeFrame();
				new PersonalWindow(connection, UID);
			}
			
		};
		personal.addActionListener(personalListener);
		this.frame.getContentPane().add(this.Driver);
		this.frame.getContentPane().add(this.Team);
		this.frame.getContentPane().add(this.Race);
		this.frame.getContentPane().add(personal);
		this.frame.getContentPane().add(this.Stats);
		this.frame.getContentPane().add(this.teamStats);
		this.frame.getContentPane().add(this.WorksFor);
		
		this.frame.setVisible(true);
	}
	
	public void closeFrame() {
		this.frame.dispose();
	}
	
	public void queryData(String tableName) throws Exception{
		String query = "";
		if (tableName.equals("Team")) 
			query = "{? = call get_All_Teams}";
		else if (tableName.equals("Driver")) 
			query = "{? = call get_All_Drivers(" + (int) yearbox.getSelectedItem() +")}";
		else if (tableName.equals("Race")) 
			query = "{? = call get_All_Races}";
		else if (tableName.equals("WorksFor"))
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
    	String[] rowData = new String[count];
    	while (rs.next()) {
    		for(int i = 0; i < count; i++) 
    			rowData[i] = rs.getString(i + 1);
    		this.model.addRow(rowData);
    	}
    	this.Table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
    	int totalWidth = 0; 
    	for (int c = 0; c < this.Table.getColumnCount(); c++)
    	{
    	    TableColumn column = this.Table.getColumnModel().getColumn(c);
    	    int preferredWidth = column.getMinWidth();
    	    int maxWidth = column.getMaxWidth();
    	    for (int r = 0; r < this.Table.getRowCount(); r++)
    	    {
    	        TableCellRenderer rend = this.Table.getCellRenderer(r, c);
    	        Component comp = this.Table.prepareRenderer(rend, r, c);
    	        int width = comp.getPreferredSize().width + this.Table.getIntercellSpacing().width;
    	        preferredWidth = Math.max(preferredWidth, width);
    	        if (preferredWidth >= maxWidth)
    	        {
    	            preferredWidth = maxWidth;
    	            break;
    	        }
    	    }
    	    column.setPreferredWidth( preferredWidth );
    	    totalWidth += preferredWidth;
    	}
    	if (totalWidth < this.Table.getWidth()) {
    		int totalOffset = this.Table.getWidth() - totalWidth;
    		int offset = totalOffset / this.Table.getColumnCount();
    		for (int c = 0; c < this.Table.getColumnCount(); c++) {
    			TableColumn column = this.Table.getColumnModel().getColumn(c);
    			int width = column.getPreferredWidth();
    			width += offset;
    			totalOffset -= offset;
    			column.setPreferredWidth(width);
    		}
    		TableColumn column = this.Table.getColumnModel().getColumn(0);
    		int width = column.getPreferredWidth();
    		width += totalOffset;
    		column.setPreferredWidth(width);
    	}
    	
	}
	
	public void queryStats(int year, boolean isTeam) {
		String query = (isTeam) ? "{? = call get_Team_Stats(?)}" : "{? = call get_Stats(?)}";

		try {
			CallableStatement cs;
			cs = this.connection.getConnection().prepareCall(query);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, year);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				this.model.addColumn(rsmd.getColumnName(i));
			}
			String[] rowData = new String[count];
			while (rs.next()) {
				for (int i = 0; i < count; i++)
					rowData[i] = rs.getString(i + 1);
				this.model.addRow(rowData);
			}
			this.Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			int totalWidth = 0;
			for (int c = 0; c < this.Table.getColumnCount(); c++) {
				TableColumn column = this.Table.getColumnModel().getColumn(c);
				int preferredWidth = column.getMinWidth();
				int maxWidth = column.getMaxWidth();
				for (int r = 0; r < this.Table.getRowCount(); r++) {
					TableCellRenderer rend = this.Table.getCellRenderer(r, c);
					Component comp = this.Table.prepareRenderer(rend, r, c);
					int width = comp.getPreferredSize().width + this.Table.getIntercellSpacing().width;
					preferredWidth = Math.max(preferredWidth, width);
					if (preferredWidth >= maxWidth) {
						preferredWidth = maxWidth;
						break;
					}
				}
				column.setPreferredWidth(preferredWidth);
				totalWidth += preferredWidth;
			}
			if (totalWidth < this.Table.getWidth()) {
				int totalOffset = this.Table.getWidth() - totalWidth;
				int offset = totalOffset / this.Table.getColumnCount();
				for (int c = 0; c < this.Table.getColumnCount(); c++) {
					TableColumn column = this.Table.getColumnModel().getColumn(c);
					int width = column.getPreferredWidth();
					width += offset;
					totalOffset -= offset;
					column.setPreferredWidth(width);
				}
				TableColumn column = this.Table.getColumnModel().getColumn(0);
				int width = column.getPreferredWidth();
				width += totalOffset;
				column.setPreferredWidth(width);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}
}
