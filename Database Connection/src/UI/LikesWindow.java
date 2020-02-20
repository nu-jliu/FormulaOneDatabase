package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

		DriverService driverService = new DriverService(this.dbService);
		ArrayList<String> driverNames = driverService.getDriverNames();
		JComboBox<String> drivers = new JComboBox<>();
		drivers.setBounds(210, 87, 90, 25);
		for (String s : driverNames)
			drivers.addItem(s);
		this.frame.getContentPane().add(drivers);

		TeamService teamService = new TeamService(this.dbService);
		ArrayList<String> teamNames = teamService.getTeamNameList();
		JComboBox<String> teams = new JComboBox<>();
		teams.setBounds(210, 145, 90, 25);
		for (String s : teamNames)
			teams.addItem(s);
		this.frame.getContentPane().add(teams);

		JButton likeDriver = new JButton("Like Driver");
		likeDriver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) drivers.getSelectedItem();
				LikesWindow.this.likeQuery("Drive", name);
			}

		});
		likeDriver.setBounds(200, 278, 100, 25);
		this.frame.getContentPane().add(likeDriver);

		JButton likeTeam = new JButton("Like Team");
		likeTeam.setSize(100, 25);
		likeTeam.setLocation(200, 314);
		likeTeam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) teams.getSelectedItem();
				LikesWindow.this.likeQuery("Team", name);
			}

		});
		this.frame.getContentPane().add(likeTeam);

		JButton deleteLikedDriver = new JButton("Delete Driver");
		deleteLikedDriver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) teams.getSelectedItem();
				LikesWindow.this.deleteQuery("Driver", name);
			}

		}); 
		deleteLikedDriver.setBounds(200, 386, 100, 25);
		
		JButton deleteLikedTeam = new JButton("Delete Team");
		deleteLikedTeam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String) teams.getSelectedItem();
				LikesWindow.this.deleteQuery("Team", name);
			}

		}); 
		frame.getContentPane().setLayout(null);
		
		deleteLikedTeam.setBounds(200, 350, 100, 25);
		
		this.frame.getContentPane().add(deleteLikedTeam);
		this.frame.getContentPane().add(deleteLikedDriver);

		JButton back = new JButton("Return");
		back.setBounds(200, 422, 100, 25);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LikesWindow.this.closeFrame();
				new PersonalWindow(LikesWindow.this.dbService, LikesWindow.this.UID);
			}

		});
		this.frame.getContentPane().add(back);
		
		JLabel lblNewLabel = new JLabel("Driver");
		lblNewLabel.setBounds(71, 92, 66, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Team");
		lblNewLabel_1.setBounds(71, 150, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

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
			} else if (errorCode == 2) {
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

	public void deleteQuery(String item, String name) {
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
			CallableStatement cs2 = this.dbService.getConnection().prepareCall("{? = call delete_Star_" + item + "(?, ?)}");
			cs2.setInt(2, this.UID);
			cs2.setInt(3, id);
			cs2.registerOutParameter(1, Types.INTEGER);
			cs2.execute();
			int errorCode = cs2.getInt(1);
			if (errorCode == 1) {
				JOptionPane.showMessageDialog(null, "User or liked item not exist");
				return;
			} else if (errorCode == 2) {
				JOptionPane.showMessageDialog(null, "The item is not in your favorite list");
				return;
			}
			return;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Failed to delete");
			e.printStackTrace();
			return;
		}
	}
}