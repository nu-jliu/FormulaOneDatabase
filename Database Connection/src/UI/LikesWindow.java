package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Database.service.Connections;
import Database.service.DriverService;
import Database.service.TeamService;

public class LikesWindow {
	private JFrame frame;
	private Connections dbService;
	private int UID;
	
	public LikesWindow(Connections dbservice, int UID) {
		this.dbService = dbservice;
		this.UID = UID;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 150, 750, 543);
		
		JPanel newPanel = new JPanel();
		
		DriverService driverService = new DriverService(this.dbService);
		ArrayList<String> driverNames = driverService.getDriverNames();
		JComboBox<String> drivers = new JComboBox<>();
		for (String s : driverNames) 
			drivers.addItem(s);
		newPanel.add(drivers, BorderLayout.WEST);
		
		TeamService teamService = new TeamService(this.dbService);
		ArrayList<String> teamNames = teamService.getTeamNameList();
		JComboBox<String> teams = new JComboBox<>();
		for (String s : teamNames)
			teams.addItem(s);
		newPanel.add(teams, BorderLayout.EAST);
		
		this.frame.getContentPane().add(newPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		
		JButton likeDriver = new JButton("Like Driver");
		likeDriver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) drivers.getSelectedItem();
				LikesWindow.this.likeQuery("Drive", name);
			}
			
		});
		buttonPanel.add(likeDriver, BorderLayout.WEST);
		
		JButton likeTeam = new JButton("Like Team");
		likeTeam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) teams.getSelectedItem();
				LikesWindow.this.likeQuery("Team", name);
			}
			
		});
		buttonPanel.add(likeTeam, BorderLayout.EAST);
		
		this.frame.add(buttonPanel, BorderLayout.NORTH);
		
		JButton back = new JButton("Return");
		back.setBounds(200, 500, 100, 25);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LikesWindow.this.closeFrame();
				new UpdateWindow(LikesWindow.this.dbService, LikesWindow.this.UID);
			}
			
		});
		this.frame.getContentPane().add(back, BorderLayout.SOUTH);
		
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void closeFrame() {
		this.frame.dispose();
	}
	
	public void likeQuery(String item, String name) {
		try {
			CallableStatement cs1 = this.dbService.getConnection().prepareCall("{? = call get_" + item + "ID(?)}");
			cs1.setString(2, name);
			cs1.registerOutParameter(1, Types.INTEGER);
			cs1.execute();
			int id = cs1.getInt(1);
			if (id == -1) {
				JOptionPane.showMessageDialog(null, "Invalid Name Input");
				return;
			}
			CallableStatement cs2 = this.dbService.getConnection().prepareCall("{? = call AddStar_" + item + "(?, ?)}");
			cs2.setInt(2, this.UID);
			cs2.setInt(3, id);
			cs2.registerOutParameter(1, Types.INTEGER);
			cs2.execute();
			int errorCode = cs2.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "User or liked item not exist");
				return;
			}
			else if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "The item is already in your favorite list");
				return;
			}
			return;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to like");
			e.printStackTrace();
			return;
		}
		
	}
}
