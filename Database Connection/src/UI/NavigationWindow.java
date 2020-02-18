package UI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Database.service.Connections;
import Database.service.WatchService;



public class NavigationWindow {
	JFrame frame;
	JButton Team;
	JButton Race;
	JButton WorksFor;
	JButton Driver;
	JButton Stats;
	JTable Table;
	JScrollPane scrollpane;
	
	Connections connection;
	DefaultTableModel model;
	
	public NavigationWindow(Connections connection, int UID) {
		this.connection = connection;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 100, 700, 700);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		
		this.Table = new JTable();
		this.scrollpane = new JScrollPane(this.Table);
		this.model = new DefaultTableModel();
		this.scrollpane.setBounds(43, 33, 597, 200);
		this.Table.setModel(model);
//		this.Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				JTable theTable = NavigationWindow.this.Table;
				int row = theTable.getSelectedRow();
				int column = theTable.getSelectedColumn();
				if (theTable.getRowCount() > 0 && theTable.getColumnCount() > 0 && theTable.getValueAt(0, 0).toString().equals("RID") && column == 3) {
					WatchService watchservice = new WatchService(NavigationWindow.this.connection, UID);
					String raceName = theTable.getValueAt(row, column).toString();
					watchservice.addHistory(raceName);
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
					e1.printStackTrace();
				}				
			}
			
		};
		this.Team.addActionListener(TeamListener);
		this.Team.setBounds(550, 244, 90, 25);
		
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
		this.WorksFor.setBounds(143, 385, 90, 25);
		
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
		this.Race.setBounds(298, 244, 90, 25);

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
		this.Driver.setBounds(43, 244, 90, 25);
		
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
		this.Stats.setBounds(43, 316, 90, 25);
		
		JButton update = new JButton("Update");
		update.setBounds(43, 385, 90, 25);
		ActionListener updateListener = new ActionListener() {

		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeFrame();
				new UpdateWindow(connection, UID);
			}
			
		};
		JButton personal = new JButton("Personal");
		personal.setBounds(550, 385, 90, 25);
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
		if(tableName.equals("Team")) 
			query = "{? = call get_All_Teams}";
		else if(tableName.equals("Driver")) 
			query = "{? = call get_All_Drivers}";
		else if(tableName.equals("Race")) 
			query = "{? = call get_All_Races}";
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
    	String[] rowData = new String[count];
    	while(rs.next()) {
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
}
