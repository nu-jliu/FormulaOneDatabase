package UI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Database.service.Connections;

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
		goBack.addActionListener(backListener);
		this.frame.getContentPane().add(goBack);
		this.frame.getContentPane().add(update);
		this.frame.getContentPane().add(Driver);
		this.frame.getContentPane().add(Team);
		
		ActionListener historyListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		};
		
		historyButton = new JButton("Unviewed Race");
		historyButton.setBounds(194, 398, 107, 23);
		frame.getContentPane().add(historyButton);
		this.frame.setVisible(true);
	}

	public void closeFrame() {
		this.frame.dispose();
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
}