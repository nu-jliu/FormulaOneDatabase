package UI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Database.service.Connections;
import Database.service.DriverService;
import Database.service.WatchService;
import Database.service.WatchService.RaceInfo;

public class PersonalWindow {
	JFrame frame;
	JButton Team;
	JButton Driver;
	JTable Table;
	JScrollPane scorllpane;
	Connections connection;
	DefaultTableModel model;
	int UID;
	int Accessbility;
	private JButton historyButton;

	public PersonalWindow(Connections connection, int UID) {
		this.UID = UID;
		this.connection = connection;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 100, 543, 543);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		this.Table = new JTable();
		this.scorllpane = new JScrollPane(this.Table);
		this.model = new DefaultTableModel();
		this.scorllpane.setBounds(43, 33, 443, 200);
		this.Table.setModel(this.model);
//		this.Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.frame.getContentPane().add(this.scorllpane);
		CallableStatement cs;
		try {
			cs = connection.getConnection().prepareCall("{? = call get_Accessbility(?)}");
			cs.setInt(2, UID);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			this.Accessbility = cs.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL Exception -User Acessbility.");
			e.printStackTrace();
		}
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
		this.Team.setBounds(200, 300, 90, 25);

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

		JButton likes = new JButton("Like");
		ActionListener likeListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				new LikesWindow(connection, UID);
			}
		};
		likes.addActionListener(likeListener);
		likes.setBounds(200, 350, 90, 25);
		this.frame.getContentPane().add(likes);
		JButton update = new JButton("Update");
		update.setBounds(200, 432, 90, 25);
		ActionListener updateListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Accessbility < 2) {
					JOptionPane.showMessageDialog(null, "No Acessbility");
				} else {
					PersonalWindow.this.closeFrame();
					new UpdateWindow(connection, PersonalWindow.this.UID);
				}
			}

		};
		update.addActionListener(updateListener);
		JButton goBack = new JButton("Go Back");
		goBack.setBounds(200, 468, 90, 25);
		ActionListener backListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				new NavigationWindow(connection, UID);
			}

		};

		JComboBox<Integer> yearbox = new JComboBox<Integer>();
		DriverService driverService = new DriverService(this.connection);
		ArrayList<Integer> yearsArrayList = driverService.getStatsYear();
		for (int s : yearsArrayList)
			yearbox.addItem(s);
		yearbox.setBounds(311, 399, 90, 20);
		frame.getContentPane().add(yearbox);

		goBack.addActionListener(backListener);
		this.frame.getContentPane().add(goBack);
		this.frame.getContentPane().add(update);
		this.frame.getContentPane().add(Driver);
		this.frame.getContentPane().add(Team);

		ActionListener historyListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WatchService watchService = new WatchService(connection, UID);
				int year = (int) yearbox.getSelectedItem();
				getUnviewedRace(UID, year);
			}

		};

		historyButton = new JButton("Unviewed Race");
		historyButton.setBounds(194, 398, 107, 23);
		historyButton.addActionListener(historyListener);
		frame.getContentPane().add(historyButton);
		this.frame.setVisible(true);
	}

	public void closeFrame() {
		this.frame.dispose();
	}

	public void getUnviewedRace(int UID, int year) {
//		ArrayList<RaceInfo> race = new ArrayList<>();
//		try {
//			CallableStatement cs = this.dbConnection.getConnection()
//					.prepareCall("{? = call get_not_watched_race(?,?)}");
//			cs.setInt(2, UID);
//			cs.setInt(3, year);
//			cs.registerOutParameter(1, Types.INTEGER);
//			ResultSet rs = cs.executeQuery();
//			int errorCode = cs.getInt(1);
//			if (errorCode == 10) {
//				JOptionPane.showMessageDialog(null, "Please select a valid year");
//				return race;
//			}
//			while (rs.next()) {
//				RaceInfo ri = new RaceInfo(rs.getString("Race Name"), rs.getDate("Date"), rs.getString("Weather"),
//						rs.getTime("Fastest Lap Time"), rs.getString("Fastest Driver"), rs.getString("Champion"));
//				race.add(ri);
//			}
//			return race;
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "Failed to get  info");
//			e.printStackTrace();
//			return race;
//		}
//		
		ArrayList<RaceInfo> race = new ArrayList<>();
		try {
			CallableStatement cs = this.connection.getConnection().prepareCall("{? = call get_not_watched_race(?,?)}");
			cs.setInt(2, UID);
			cs.setInt(3, year);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				this.model.addColumn(rsmd.getColumnName(i));
			}
			String[] rowData = new String[count];
			while (rs.next()) {
				RaceInfo ri = new RaceInfo(rs.getString("Race Name"), rs.getDate("Date"), rs.getString("Weather"),
						rs.getTime("Fastest Lap Time"), rs.getString("Fastest Driver"), rs.getString("Champion"));
				race.add(ri);
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
			e.printStackTrace();
		}
	}

	public void queryData(String tableName) throws Exception {
		String query = "";
		if (tableName.equals("Team")) {
			query = "{? = call get_Starred_Team (?)}";
		} else if (tableName.equals("Driver")) {
			query = "{? = call get_Starred_Driver (?)}";
		}
		CallableStatement cs = this.connection.getConnection().prepareCall(query);
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setInt(2, this.UID);
		ResultSet rs;
		rs = cs.executeQuery();
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
	}

	public class RaceInfo {
		String name;
		Date date;
		String weather;
		Time laptime;
		String drivername;
		String champion;

		public RaceInfo(String n, Date d, String w, Time l, String dr, String c) {
			this.name = n;
			this.date = d;
			this.weather = w;
			this.laptime = l;
			this.drivername = dr;
			this.champion = c;
		}
	}

}