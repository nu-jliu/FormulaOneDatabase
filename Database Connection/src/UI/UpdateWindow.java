package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.service.Connections;
import Database.service.TeamService;

public class UpdateWindow {
	JFrame frame;
	Connections dbservice;
	
	public UpdateWindow(Connections dbservice) {
		this.dbservice = dbservice;
		frame = new JFrame("Formula1Tracker");
		frame.setBounds(100, 100, 703, 543);
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
		
		JLabel manf = new JLabel("Manufacture");
		manf.setBounds(140, 23, 90, 16);
		frame.add(manf);
		
		JTextField manfName = new JTextField();
		manfName.setBounds(220, 20, 90, 25);
		frame.add(manfName);
		
		JLabel num = new JLabel("Model Number");
		num.setBounds(320, 23, 90, 16);
		frame.add(num);
		
		JTextField modelNum = new JTextField();
		modelNum.setBounds(410, 20, 90, 25);
		frame.add(modelNum);
		
		JButton addTeam = new JButton("Add Team");
		addTeam.setBounds(0, 50, 100, 25);
		
		TeamService teamService = new TeamService(dbservice);
		ActionListener addteamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = teamName.getText();
				String manfname = manfName.getText();
				String number = modelNum.getText();
				teamService.addTeam(name, manfname, number);
			}
			
		};
		addTeam.addActionListener(addteamListener);
		frame.getContentPane().add(addTeam);
		
		JButton goBack = new JButton("Go Back");
		goBack.setBounds(200, 400, 90, 25);
		ActionListener backListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				NavigationWindow nw = new NavigationWindow(dbservice);
			}
			
		};
		goBack.addActionListener(backListener);
		frame.getContentPane().add(goBack);
		
//		JLabel driver = new JLabel("Driver");
//		driver.setBounds(50, 0, 68, 16);
//		frame.add(driver);
//		
//		JLabel driverFName = new JLabel("Name");
//		driverFName.setBounds(50, 23, 68, 16);
//		frame.add(driverFName);
//		
//		JTextField FName = new JTextField();
//		FName.setBounds(40, 20, 90, 25);
//		frame.add(FName);
//		
//		JLabel manf = new JLabel("Manufacture");
//		manf.setBounds(140, 23, 90, 16);
//		frame.add(manf);
//		
//		JTextField manfName = new JTextField();
//		manfName.setBounds(220, 20, 90, 25);
//		frame.add(manfName);
//		
//		JLabel num = new JLabel("Model Number");
//		num.setBounds(320, 23, 90, 16);
//		frame.add(num);
//		
//		JTextField modelNum = new JTextField();
//		modelNum.setBounds(410, 20, 90, 25);
//		frame.add(modelNum);
//		
//		JButton addTeam = new JButton("Add Team");
//		addTeam.setBounds(0, 50, 100, 25);
//		
//		TeamService teamService = new TeamService(dbservice);
//		ActionListener addteamListener = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				String name = teamName.getText();
//				String manfname = manfName.getText();
//				String number = modelNum.getText();
//				teamService.addTeam(name, manfname, number);
//			}
//			
//		};
//		addTeam.addActionListener(addteamListener);
//		frame.getContentPane().add(addTeam);
		
		frame.setVisible(true);
	}
	
	public void closeFrame() {
		frame.dispose();
	}

}
