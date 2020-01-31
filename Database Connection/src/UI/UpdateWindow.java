package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.service.Connections;

public class UpdateWindow {
	JFrame frame;
	Connections dbservice;
	
	public UpdateWindow(Connections dbservice) {
		this.dbservice = dbservice;
		frame = new JFrame("Formula1Tracker");
		frame.setBounds(100, 100, 543, 543);
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
		manfName.setBounds(200, 20, 90, 25);
		frame.add(manfName);
		
		frame.setVisible(true);
	}
	
	public void closeFrame() {
		frame.dispose();
	}

}
