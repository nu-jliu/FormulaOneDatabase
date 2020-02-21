package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Database.service.Connections;
import Database.service.DriverService;
import Database.service.ParticipatesService;
import Database.service.RaceService;
import Database.service.TeamService;
import Database.service.WatchService;
import Database.service.WorksForService;
import Input.inputFromFile;

public class UpdateWindow {
	JFrame frame;
	Connections dbservice;
	private int UID;
	private JTextField fileName;

	public UpdateWindow(Connections dbservice, int UID) {
		this.dbservice = dbservice;
		this.UID = UID;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 150, 820, 650);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		DriverService driverService = new DriverService(this.dbservice);
		TeamService teamService = new TeamService(this.dbservice);
		RaceService raceService = new RaceService(this.dbservice);

		ArrayList<String> driverNames = driverService.getDriverNames();
		JComboBox<String> drivers = new JComboBox<>();
		JComboBox<String> drivers2 = new JComboBox<>();
		JComboBox<String> drivers3 = new JComboBox<>();
		for (String s : driverNames) {
			drivers.addItem(s);
			drivers2.addItem(s);
			drivers3.addItem(s);
		}

		ArrayList<String> teamNames = teamService.getTeamNameList();
		JComboBox<String> teams = new JComboBox<>();
		for (String s : teamNames)
			teams.addItem(s);

		ArrayList<String> raceNames = raceService.getRaceNameList();
		JComboBox<String> races = new JComboBox<>();
		JComboBox<String> races2 = new JComboBox<>();
		for (String s : raceNames) {
			races.addItem(s);
			races2.addItem(s);
		}

		ArrayList<Integer> allYears = raceService.getRaceYear();
		JComboBox<Integer> years = new JComboBox<>();
		for (int y : allYears)
			years.addItem(y);

		drivers.setBounds(30, 200, 140, 25);
		drivers2.setBounds(664, 289, 130, 25);
		drivers3.setBounds(80, 370, 130, 25);
		races.setBounds(210, 200, 250, 25);
		races2.setBounds(50, 450, 250, 25);
		teams.setBounds(280, 370, 90, 25);
		years.setBounds(450, 450, 90, 16);
		this.frame.getContentPane().add(drivers);
		this.frame.getContentPane().add(drivers2);
		this.frame.getContentPane().add(drivers3);
		this.frame.getContentPane().add(races);
		this.frame.getContentPane().add(races2);
		this.frame.getContentPane().add(teams);
		this.frame.getContentPane().add(years);

		JLabel team = new JLabel("Team");
		team.setBounds(0, 0, 68, 16);
		this.frame.getContentPane().add(team);

		JLabel teamname = new JLabel("Name");
		teamname.setBounds(0, 23, 68, 16);
		this.frame.getContentPane().add(teamname);

		JTextField teamName = new JTextField();
		teamName.setBounds(40, 20, 90, 25);
		this.frame.getContentPane().add(teamName);

		JLabel manf = new JLabel("Manufacture");
		manf.setBounds(140, 23, 90, 16);
		this.frame.getContentPane().add(manf);

		JTextField manfName = new JTextField();
		manfName.setBounds(220, 20, 90, 25);
		this.frame.getContentPane().add(manfName);

		JLabel num = new JLabel("Model Number");
		num.setBounds(320, 23, 90, 16);
		this.frame.getContentPane().add(num);

		JTextField modelNum = new JTextField();
		modelNum.setBounds(410, 20, 90, 25);
		this.frame.getContentPane().add(modelNum);

		JButton addTeam = new JButton("Add Team");
		addTeam.setBounds(0, 50, 100, 25);

		JButton updateTeam = new JButton("Update Team");
		updateTeam.setBounds(200, 50, 200, 25);

		JLabel Driver = new JLabel("Driver");
		Driver.setBounds(0, 90, 68, 16);
		this.frame.getContentPane().add(Driver);

		JLabel drivername = new JLabel("Name");
		drivername.setBounds(0, 113, 68, 16);
		this.frame.getContentPane().add(drivername);

		JTextField DriverName = new JTextField();
		DriverName.setBounds(40, 110, 90, 25);
		this.frame.getContentPane().add(DriverName);

		JLabel DOB = new JLabel("DOB");
		DOB.setBounds(320, 113, 90, 16);
		this.frame.getContentPane().add(DOB);

		JTextField dob = new JTextField();
		dob.setBounds(350, 110, 90, 25);
		this.frame.getContentPane().add(dob);

		JButton addDriver = new JButton("Add Driver");
		addDriver.setBounds(0, 140, 100, 25);

		JButton updateDriver = new JButton("Update Driver");
		updateDriver.setBounds(250, 140, 150, 25);

		JLabel part = new JLabel("Participates");
		part.setBounds(0, 180, 90, 16);
		this.frame.getContentPane().add(part);

		JLabel driID = new JLabel("Driver");
		driID.setBounds(0, 203, 68, 16);
		this.frame.getContentPane().add(driID);

		JLabel raID = new JLabel("Race");
		raID.setBounds(175, 203, 90, 16);
		this.frame.getContentPane().add(raID);

		JLabel year = new JLabel("Year");
		year.setBounds(630, 203, 90, 16);
		this.frame.getContentPane().add(year);

		JTextField raceYear = new JTextField();
		raceYear.setBounds(660, 200, 90, 25);
		this.frame.getContentPane().add(raceYear);

		JLabel RANK = new JLabel("Rank");
		RANK.setBounds(480, 203, 90, 16);
		this.frame.getContentPane().add(RANK);

		JTextField rank = new JTextField();
		rank.setBounds(510, 200, 90, 25);
		this.frame.getContentPane().add(rank);

		JButton addPart = new JButton("Add Participates");
		addPart.setBounds(0, 230, 150, 25);

		JButton updatePart = new JButton("Update Participates");
		updatePart.setBounds(250, 230, 130, 25);

		JLabel race = new JLabel("Race");
		race.setBounds(0, 270, 68, 16);
		this.frame.getContentPane().add(race);

		JLabel weather = new JLabel("Weather");
		weather.setBounds(0, 293, 68, 16);
		this.frame.getContentPane().add(weather);

		JTextField raWeather = new JTextField();
		raWeather.setBounds(70, 290, 90, 25);
		this.frame.getContentPane().add(raWeather);

		JLabel date = new JLabel("Date");
		date.setBounds(170, 293, 90, 16);
		this.frame.getContentPane().add(date);

		JTextField raDate = new JTextField();
		raDate.setBounds(200, 290, 90, 25);
		this.frame.getContentPane().add(raDate);

		JLabel racename = new JLabel("Race Name");
		racename.setBounds(290, 293, 90, 16);
		this.frame.getContentPane().add(racename);

		JTextField raceName = new JTextField();
		raceName.setBounds(360, 290, 90, 25);
		this.frame.getContentPane().add(raceName);

		JLabel laTime = new JLabel("Laptime");
		laTime.setBounds(460, 293, 90, 16);
		this.frame.getContentPane().add(laTime);

		JTextField laptime = new JTextField();
		laptime.setBounds(510, 293, 90, 25);
		this.frame.getContentPane().add(laptime);

		JLabel id = new JLabel("Driver Name");
		id.setBounds(600, 297, 90, 16);
		this.frame.getContentPane().add(id);

		JButton addRace = new JButton("Add Race");
		addRace.setBounds(0, 320, 100, 25);

		JButton updateRace = new JButton("Update Race");
		updateRace.setBounds(250, 320, 125, 25);

		JButton goBack = new JButton("Go Back");
		goBack.setBounds(280, 540, 90, 25);

		JLabel WF = new JLabel("Works For");
		WF.setBounds(0, 350, 90, 16);
		this.frame.getContentPane().add(WF);

		JLabel DN = new JLabel("Driver Name");
		DN.setBounds(0, 370, 90, 16);
		this.frame.getContentPane().add(DN);

		JLabel TN = new JLabel("Team Name");
		TN.setBounds(210, 370, 90, 16);
		this.frame.getContentPane().add(TN);

		JLabel SY = new JLabel("Year");
		SY.setBounds(400, 370, 90, 16);
		this.frame.getContentPane().add(SY);

		JTextField SYT = new JTextField();
		SYT.setBounds(460, 370, 90, 25);
		this.frame.getContentPane().add(SYT);

		JLabel W = new JLabel("Watch Race");
		W.setBounds(0, 430, 90, 16);
		this.frame.getContentPane().add(W);

		JLabel Race = new JLabel("Race");
		Race.setBounds(0, 450, 90, 16);
		this.frame.getContentPane().add(Race);

		JLabel theYear = new JLabel("Year");
		theYear.setBounds(400, 450, 90, 16);
		this.frame.getContentPane().add(theYear);

		JButton WBT = new JButton("Watch Race");
		WBT.setBounds(0, 480, 130, 25);

		JButton AdW = new JButton("Add Works For");
		AdW.setBounds(0, 400, 130, 25);

		JButton UpW = new JButton("Update Works For");
		UpW.setBounds(300, 400, 150, 25);

		ActionListener addteamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = teamName.getText();
				String manfname = manfName.getText();
				String number = modelNum.getText();
				teamService.addTeam(name, manfname, number);

			}

		};

		WatchService WatchService = new WatchService(dbservice, this.UID);
		ActionListener WatchListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Race = (String) races2.getSelectedItem();
				int year = (int) years.getSelectedItem();
				WatchService.addHistory(Race, year);
			}

		};

		ActionListener updateteamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = teamName.getText();
				String manfname = manfName.getText();
				String number = modelNum.getText();
				teamService.updateTeam(name, manfname, number);

			}

		};

		ActionListener adddriverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dname = DriverName.getText();
				String ddob = dob.getText();
				driverService.addDriver(dname, ddob);
			}

		};
		ActionListener updatedriverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dname = DriverName.getText();
				String ddob = dob.getText();
				driverService.updateDriver(dname, ddob);
			}

		};

		ParticipatesService participatesService = new ParticipatesService(dbservice);
		ActionListener addParticipatesListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) throws NumberFormatException {
				int driverRank, theYear;
				String did = (String) drivers.getSelectedItem();
				String rid = (String) races.getSelectedItem();
				driverRank = Integer.parseInt(rank.getText());
				theYear = Integer.parseInt(raceYear.getText());
				participatesService.addParticipates(theYear, did, rid, driverRank);
			}
		};

		ActionListener updateParticipatesListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int driverRank, theYear;
				String did = (String) drivers.getSelectedItem();
				String rid = (String) races.getSelectedItem();
				try {
					driverRank = Integer.parseInt(rank.getText());
					theYear = Integer.parseInt(raceYear.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid Input");
					e.printStackTrace();
					return;
				}
				participatesService.updateParticipates(theYear, did, rid, driverRank);

			}

		};

		ActionListener addRaceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String weather = raWeather.getText();
				String raceDate = raDate.getText();
				String raName = raceName.getText();
				String lapTime = laptime.getText();
				String Dname = (String) drivers2.getSelectedItem();
				raceService.addRace(weather, raceDate, raName, lapTime, Dname);
			}

		};

		ActionListener updateRaceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String weather = raWeather.getText();
				String raceDate = raDate.getText();
				String raName = raceName.getText();
				String lapTime = laptime.getText();
				String Dname = (String) drivers2.getSelectedItem();
				raceService.updateRace(weather, raceDate, raName, lapTime, Dname);
			}

		};

		WorksForService WorksForService = new WorksForService(dbservice);

		ActionListener addWorksForListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Dname = (String) drivers3.getSelectedItem();
				String Tname = (String) teams.getSelectedItem();
				int year;
				try {
					year = Integer.parseInt(SYT.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input year");
					e.printStackTrace();
					return;
				}
				WorksForService.addWorksFor(Dname, Tname, year);
			}

		};

		ActionListener updateWorksForListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Dname = (String) drivers3.getSelectedItem();
				String Tname = (String) teams.getSelectedItem();

				int year;
				try {
					year = Integer.parseInt(SYT.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input year");
					e.printStackTrace();
					return;
				}
				WorksForService.updateWorksFor(Dname, Tname, year);
			}

		};

		addTeam.addActionListener(addteamListener);
		updateTeam.addActionListener(updateteamListener);
		this.frame.getContentPane().add(addTeam);
		this.frame.getContentPane().add(updateTeam);

		addDriver.addActionListener(adddriverListener);
		updateDriver.addActionListener(updatedriverListener);
		this.frame.getContentPane().add(addDriver);
		this.frame.getContentPane().add(updateDriver);

		addPart.addActionListener(addParticipatesListener);
		updatePart.addActionListener(updateParticipatesListener);
		this.frame.getContentPane().add(addPart);
		this.frame.getContentPane().add(updatePart);

		addRace.addActionListener(addRaceListener);
		updateRace.addActionListener(updateRaceListener);
		this.frame.getContentPane().add(addRace);
		this.frame.getContentPane().add(updateRace);

		AdW.addActionListener(addWorksForListener);
		this.frame.getContentPane().add(AdW);

		WBT.addActionListener(WatchListener);
		this.frame.getContentPane().add(WBT);

		UpW.addActionListener(updateWorksForListener);
		this.frame.getContentPane().add(UpW);

		ActionListener backListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				new NavigationWindow(dbservice, UID);
			}

		};
		goBack.addActionListener(backListener);
		this.frame.getContentPane().add(goBack);

		fileName = new JTextField();
		fileName.setBounds(460, 230, 203, 25);
		frame.getContentPane().add(fileName);
		fileName.setColumns(10);

		JLabel fileNameLabel = new JLabel("File Path");
		fileNameLabel.setEnabled(false);
		fileNameLabel.setBounds(410, 235, 46, 14);
		frame.getContentPane().add(fileNameLabel);

		JButton btnNewButton = new JButton("Load File");
		btnNewButton.setBounds(670, 231, 89, 23);
		ActionListener fileActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = fileName.getText();
				String racename = (String) races.getSelectedItem();
				String yearString = raceYear.getText();
				int y = Integer.parseInt(yearString);
				inputFromFile inputFromFile = new inputFromFile(filePath, dbservice);
				try {
					inputFromFile.fileReader(racename, y);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
		};
		btnNewButton.addActionListener(fileActionListener);
		frame.getContentPane().add(btnNewButton);

		this.frame.setVisible(true);
	}

	public void closeFrame() {
		this.frame.dispose();
	}
}
