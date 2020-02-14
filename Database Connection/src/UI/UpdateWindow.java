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
import Database.service.WatchService;
import Database.service.WorksForService;

public class UpdateWindow {
	JFrame frame;
	Connections dbservice;
	private int UID;

	public UpdateWindow(Connections dbservice, int UID) {
		this.dbservice = dbservice;
		this.UID = UID;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 150, 800, 750);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JLabel team = new JLabel("Team");
		team.setBounds(0, 0, 68, 16);
		this.frame.add(team);

		JLabel teamname = new JLabel("Name");
		teamname.setBounds(0, 23, 68, 16);
		this.frame.add(teamname);

		JTextField teamName = new JTextField();
		teamName.setBounds(40, 20, 90, 25);
		this.frame.add(teamName);

		JLabel manf = new JLabel("Manufacture");
		manf.setBounds(140, 23, 90, 16);
		this.frame.add(manf);

		JTextField manfName = new JTextField();
		manfName.setBounds(220, 20, 90, 25);
		this.frame.add(manfName);

		JLabel num = new JLabel("Model Number");
		num.setBounds(320, 23, 90, 16);
		this.frame.add(num);

		JTextField modelNum = new JTextField();
		modelNum.setBounds(410, 20, 90, 25);
		this.frame.add(modelNum);

		JButton addTeam = new JButton("Add Team");
		addTeam.setBounds(0, 50, 100, 25);
		
		JButton updateTeam = new JButton("Update Team");
		updateTeam.setBounds(200, 50, 200, 25);
		

		JLabel Driver = new JLabel("Driver");
		Driver.setBounds(0, 90, 68, 16);
		this.frame.add(Driver);

		JLabel drivername = new JLabel("Name");
		drivername.setBounds(0, 113, 68, 16);
		this.frame.add(drivername);

		JTextField DriverName = new JTextField();
		DriverName.setBounds(40, 110, 90, 25);
		this.frame.add(DriverName);

		JLabel DOB = new JLabel("DOB");
		DOB.setBounds(320, 113, 90, 16);
		this.frame.add(DOB);
		

		JTextField dob = new JTextField();
		dob.setBounds(350, 110, 90, 25);
		this.frame.add(dob);

		JButton addDriver = new JButton("Add Driver");
		addDriver.setBounds(0, 140, 100, 25);
		
		JButton updateDriver = new JButton("Update Driver");
		updateDriver.setBounds(250, 140, 150, 25);
		
		JLabel part = new JLabel("Participates");
		part.setBounds(0, 180, 90, 16);
		this.frame.add(part);

		JLabel driID = new JLabel("Driver");
		driID.setBounds(0, 203, 68, 16);
		this.frame.add(driID);

		JTextField driverID = new JTextField();
		driverID.setBounds(40, 200, 90, 25);
		this.frame.add(driverID);

		JLabel raID = new JLabel("Race");
		raID.setBounds(140, 203, 90, 16);
		this.frame.add(raID);
		
		JLabel year = new JLabel("Year");
		year.setBounds(470, 203, 90, 16);
		this.frame.add(year);
		
		JTextField raceYear = new JTextField();
		raceYear.setBounds(500, 203, 90, 25);
		this.frame.add(raceYear);

		JTextField raceID = new JTextField();
		raceID.setBounds(170, 200, 90, 25);
		this.frame.add(raceID);

		JLabel RANK = new JLabel("Rank");
		RANK.setBounds(320, 203, 90, 16);
		this.frame.add(RANK);

		JTextField rank = new JTextField();
		rank.setBounds(350, 200, 90, 25);
		this.frame.add(rank);

		JButton addPart = new JButton("Add Participates");
		addPart.setBounds(0, 230, 150, 25);
		
		JButton updatePart = new JButton("Update Participates");
		updatePart.setBounds(250, 230, 150, 25);

		JLabel race = new JLabel("Race");
		race.setBounds(0, 270, 68, 16);
		this.frame.add(race);

		JLabel weather = new JLabel("Weather");
		weather.setBounds(0, 293, 68, 16);
		this.frame.add(weather);

		JTextField raWeather = new JTextField();
		raWeather.setBounds(70, 290, 90, 25);
		this.frame.add(raWeather);

		JLabel date = new JLabel("Date");
		date.setBounds(170, 293, 90, 16);
		this.frame.add(date);

		JTextField raDate = new JTextField();
		raDate.setBounds(200, 290, 90, 25);
		this.frame.add(raDate);

		JLabel racename = new JLabel("Race Name");
		racename.setBounds(290, 293, 90, 16);
		this.frame.add(racename);

		JTextField raceName = new JTextField();
		raceName.setBounds(360, 290, 90, 25);
		this.frame.add(raceName);
		
		JLabel laTime = new JLabel("Laptime");
		laTime.setBounds(450, 290, 90, 16);
		this.frame.add(laTime);
		
		JTextField laptime = new JTextField();
		laptime.setBounds(500, 293, 90, 25);
		this.frame.add(laptime);
		
		JLabel id = new JLabel("Driver Name");
		id.setBounds(600, 290, 90, 16);
		this.frame.add(id);
		
		JTextField newID = new JTextField();
		newID.setBounds(670, 293, 90, 25);
		this.frame.add(newID);

		JButton addRace = new JButton("Add Race");
		addRace.setBounds(0, 320, 100, 25);
		
		JButton star = new JButton("Likes");
		star.setBounds(0, 680, 100, 25);
		JButton goBack = new JButton("Go Back");
		goBack.setBounds(200, 680, 90, 25);
		
		//works for part
		JLabel WF = new JLabel("Works For");
		WF.setBounds(0, 350 , 90, 16);
		this.frame.add(WF);
		
		JLabel DN = new JLabel("Driver Name");
		DN.setBounds(0, 370 , 90, 16);
		this.frame.add(DN);
		
		JTextField DNT = new JTextField();
		DNT.setBounds(80, 370, 90, 25);
		this.frame.add(DNT);
		
		JLabel TN = new JLabel("Team Name");
		TN.setBounds(200, 370, 90, 16);
		this.frame.add(TN);
		
		JTextField TNT = new JTextField();
		TNT.setBounds(280, 370, 90, 25);
		this.frame.add(TNT);
		
		JLabel SY = new JLabel("Year");
		SY.setBounds(400, 370, 90, 16);
		this.frame.add(SY);
		
		JTextField SYT = new JTextField();
		SYT.setBounds(460, 370, 90, 25);
		this.frame.add(SYT);
		
		JLabel W = new JLabel("Watch Race");
		W.setBounds(0, 430, 90, 16);
		this.frame.add(W);
		
		JLabel Race = new JLabel("Race");
		Race.setBounds(0, 450, 90, 16);
		this.frame.add(Race);
		
		JTextField RT = new JTextField();
		RT.setBounds(50, 450, 90, 25);
		this.frame.add(RT);
		

		JButton WBT = new JButton("Watch Race");
		WBT.setBounds(0, 480, 130, 25);
		
		JButton AdW = new JButton("Add Works For");
		AdW.setBounds(0, 400, 130, 25);
	
		JButton UpW = new JButton("Update Works For");
		UpW.setBounds(300, 400, 150, 25);
		
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
		
		WatchService WatchService = new WatchService(dbservice, this.UID);
		ActionListener WatchListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Race = RT.getText();
				
				WatchService.addHistory(Race);
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

		DriverService driverService = new DriverService(dbservice);
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
			public void actionPerformed(ActionEvent arg0) {
				int  driverRank, theYear;
				String did = driverID.getText();
				String rid = raceID.getText();
				try {
					driverRank = Integer.parseInt(rank.getText());
					theYear = Integer.parseInt(raceYear.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid Input");
					e.printStackTrace();
					return;
				}
				participatesService.addParticipates(theYear, did, rid, driverRank);
			}
			
		};
		ActionListener updateParticipatesListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int  driverRank, theYear;
				String did = driverID.getText();
				String rid = raceID.getText();
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
		RaceService raceService = new RaceService(dbservice);
		ActionListener addRaceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String weather = raWeather.getText();
				String raceDate = raDate.getText();
				String raName = raceName.getText();
				String lapTime = laptime.getText();
				String Dname = newID.getText();
				raceService.addRace(weather, raceDate, raName, lapTime, Dname);
			}
			
		};
		
		WorksForService WorksForService = new WorksForService(dbservice);
		ActionListener addWorksForListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Dname = DNT.getText();
				String Tname = TNT.getText();
				
				int year;
				try {
					year = Integer.parseInt( SYT.getText());
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
				String Dname = DNT.getText();
				String Tname = TNT.getText();
				
				int year;
				try {
					year = Integer.parseInt( SYT.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input year");
					e.printStackTrace();
					return;
				}
			
				
					
				WorksForService.updateWorksFor(Dname, Tname, year);
			}
			
		};
		ActionListener likesListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateWindow.this.closeFrame();
				new LikesWindow(UpdateWindow.this.dbservice, UpdateWindow.this.UID);
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
		this.frame.getContentPane().add(addRace);
		
		star.addActionListener(likesListener);
		this.frame.getContentPane().add(star);
		
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
		this.frame.setVisible(true);
	}

	public void closeFrame() {
		this.frame.dispose();
	}

}
