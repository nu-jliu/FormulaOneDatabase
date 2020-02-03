package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Database.service.Connections;
import Database.service.DriverService;
import Database.service.ParticipatesService;
import Database.service.RaceService;
import Database.service.TeamService;

public class UpdateWindow {
	JFrame frame;
	Connections dbservice;
	private int UID;

	public UpdateWindow(Connections dbservice, int UID) {
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

		JLabel DOB = new JLabel("DOB");
		DOB.setBounds(320, 113, 90, 16);
		frame.add(DOB);

		JTextField dob = new JTextField();
		dob.setBounds(350, 110, 90, 25);
		frame.add(dob);

		JButton addDriver = new JButton("Add Driver");
		addDriver.setBounds(0, 140, 100, 25);
		
		JLabel part = new JLabel("Participates");
		part.setBounds(0, 180, 90, 16);
		frame.add(part);

		JLabel driID = new JLabel("DID");
		driID.setBounds(0, 203, 68, 16);
		frame.add(driID);

		JTextField driverID = new JTextField();
		driverID.setBounds(40, 200, 90, 25);
		frame.add(driverID);

		JLabel raID = new JLabel("RID");
		raID.setBounds(140, 203, 90, 16);
		frame.add(raID);

		JTextField raceID = new JTextField();
		raceID.setBounds(170, 200, 90, 25);
		frame.add(raceID);

		JLabel RANK = new JLabel("Rank");
		RANK.setBounds(320, 203, 90, 16);
		frame.add(RANK);

		JTextField rank = new JTextField();
		rank.setBounds(350, 200, 90, 25);
		frame.add(rank);

		JButton addPart = new JButton("Add Part");
		addPart.setBounds(0, 230, 100, 25);

		JLabel race = new JLabel("Race");
		race.setBounds(0, 270, 68, 16);
		frame.add(race);

		JLabel weather = new JLabel("Weather");
		weather.setBounds(0, 293, 68, 16);
		frame.add(weather);

		JTextField raWeather = new JTextField();
		raWeather.setBounds(70, 290, 90, 25);
		frame.add(raWeather);

		JLabel date = new JLabel("Date");
		date.setBounds(170, 293, 90, 16);
		frame.add(date);

		JTextField raDate = new JTextField();
		raDate.setBounds(200, 290, 90, 25);
		frame.add(raDate);

		JLabel racename = new JLabel("Race Name");
		racename.setBounds(320, 293, 90, 16);
		frame.add(racename);

		JTextField raceName = new JTextField();
		raceName.setBounds(350, 290, 90, 25);
		frame.add(raceName);
		
		JLabel laTime = new JLabel("Laptime");
		laTime.setBounds(450, 290, 90, 16);
		frame.add(laTime);
		
		JTextField laptime = new JTextField();
		laptime.setBounds(500, 293, 90, 25);
		frame.add(laptime);
		
		JLabel id = new JLabel("DID");
		id.setBounds(600, 290, 90, 16);
		frame.add(id);
		
		JTextField newID = new JTextField();
		newID.setBounds(650, 293, 90, 25);
		frame.add(newID);

		JButton addRace = new JButton("Add Race");
		addRace.setBounds(0, 320, 100, 25);
		
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
					
				}
				catch(NumberFormatException e) {
					
				}
				String ddob = dob.getText();

				driverService.addDriver(dage, dname, ddob);
			}

		};
		
		ParticipatesService participatesService = new ParticipatesService(dbservice);
		ActionListener addParticipatesListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int did, rid, driverRank;
				try {
					did = Integer.parseInt(driverID.getText());
					rid = Integer.parseInt(raceID.getText());
					driverRank = Integer.parseInt(rank.getText());
					participatesService.addParticipates(rid, did, driverRank);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid Input");
					e.printStackTrace();
				}
				
			}
			
		};
		RaceService raceService = new RaceService(dbservice);
		ActionListener addRaceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String weather = raWeather.getText();
				String raceDate = raDate.getText();
				String raName = raceName.getText();
				String lapTime = laTime.getText();
				int did;
				try {
					did = Integer.parseInt(newID.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input ID");
					e.printStackTrace();
					return;
				}
				raceService.addRace(weather, raceDate, raName, lapTime, did);
			}
			
		};
		
		addTeam.addActionListener(addteamListener);
		frame.getContentPane().add(addTeam);

		addDriver.addActionListener(adddriverListener);
		frame.getContentPane().add(addDriver);
		
		addPart.addActionListener(addParticipatesListener);
		frame.getContentPane().add(addPart);
		
		addRace.addActionListener(addRaceListener);
		frame.getContentPane().add(addRace);

		JButton goBack = new JButton("Go Back");
		goBack.setBounds(200, 400, 90, 25);
		ActionListener backListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				NavigationWindow nw = new NavigationWindow(dbservice, UID);
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
