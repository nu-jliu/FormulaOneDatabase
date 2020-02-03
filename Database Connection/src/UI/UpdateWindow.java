package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.service.Connections;
import Database.service.DriverService;
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

		JLabel Driver = new JLabel("Driver");
		Driver.setBounds(0, 90, 68, 16);
		frame.add(Driver);

		JLabel drivername = new JLabel("Name");
		drivername.setBounds(0, 113, 68, 16);
		frame.add(drivername);

		JTextField DriverName = new JTextField();
		DriverName.setBounds(40, 110, 90, 25);
		frame.add(DriverName);

		JLabel Age = new JLabel("Age");
		Age.setBounds(140, 113, 90, 16);
		frame.add(Age);

		JTextField age = new JTextField();
		age.setBounds(170, 110, 90, 25);
		frame.add(age);

		JLabel DOB = new JLabel("DOB");
		DOB.setBounds(320, 113, 90, 16);
		frame.add(DOB);

		JTextField dob = new JTextField();
		dob.setBounds(350, 110, 90, 25);
		frame.add(dob);

		JButton addDriver = new JButton("Add Driver");
		addDriver.setBounds(0, 140, 100, 25);

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

		DriverService driverService = new DriverService(dbservice);
		ActionListener adddriverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String dname = DriverName.getText();
				int dage= 0;
				try {
				 dage = Integer.parseInt(age.getText());}
				catch(NumberFormatException e) {
					
				}
				String ddob = dob.getText();

				driverService.addDriver(dage, dname, ddob);
			}

		};
		addTeam.addActionListener(addteamListener);
		frame.getContentPane().add(addTeam);

		addDriver.addActionListener(adddriverListener);
		frame.getContentPane().add(addDriver);

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
		frame.setVisible(true);
	}

	public void closeFrame() {
		frame.dispose();
	}

}
