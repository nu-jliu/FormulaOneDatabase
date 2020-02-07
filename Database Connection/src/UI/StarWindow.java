package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.service.Connections;
import Database.service.TeamService;

public class StarWindow {
	JFrame frame;
	Connections dbservice;
	private int UID;
	
	public StarWindow(Connections dbservice, int UID) {
		this.dbservice = dbservice;
		this.UID = UID;
		frame = new JFrame("Formula1Tracker");
		frame.setBounds(100, 150, 703, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel team = new JLabel("Team");
		team.setBounds(0, 0, 68, 16);
		frame.add(team);

		JLabel teamname = new JLabel("Name");
		teamname.setBounds(0, 23, 68, 16);
		frame.add(teamname);

		JTextField teamName = new JTextField();
		teamName.setBounds(40, 20, 90, 25);
		frame.add(teamName);

		JButton addTeam = new JButton("Add Team");
		addTeam.setBounds(0, 50, 100, 25);

		JLabel Driver = new JLabel("Driver");
		Driver.setBounds(0, 90, 68, 16);
		frame.add(Driver);

		JLabel drivername = new JLabel("Name");
		drivername.setBounds(0, 113, 68, 16);
		frame.add(drivername);

		JTextField DriverName = new JTextField();
		DriverName.setBounds(40, 110, 90, 25);
		frame.add(DriverName);

		JButton addDriver = new JButton("Add Driver");
		addDriver.setBounds(0, 140, 100, 25);
		
//		ActionListener addteamListener = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				String name = teamName.getText();
//				cs = dbservice.getConnection().prepareCall("{? = call get_UID(?)}");
//				cs.registerOutParameter(1, Types.INTEGER);
//				cs.setString(2, userName);
//				cs.execute();
//				UID = cs.getInt(1);
//			}
//
//		};
	}

}
