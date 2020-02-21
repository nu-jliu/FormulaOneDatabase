package recovery;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.service.Connections;
import Input.inputFromFile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class fileLoader extends JFrame {

	private JPanel contentPane;
	private JTextField DriverPath;
	private JTextField RacePath;
	private JTextField TeamPath;
	private JTextField UserPath;
	Connections connection;
	inputFromFile inputFromFile;
	private JTextField WFPath;
	private JTextField PPath;
	private JTextField StatsPath;
	private JTextField STPath;
	private JTextField SDPath;
	private JTextField HistoryPath;
	/**
	 * Launch the application.
	 */
	
	public fileLoader(Connections connection) {
		this.connection = connection;
	}

	/**
	 * Create the frame.
	 */
	public fileLoader() {
		setTitle("DatabaseRecovery");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ActionListener driverListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = DriverPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverDriver();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener teamListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = TeamPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverTeam();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener raceListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = RacePath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverRace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener participatesListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = PPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverParticipates();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener WorksForListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = WFPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverWorksFor();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener userListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = UserPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverUser();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener historyListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = HistoryPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverHistory();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener STListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = STPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverStarTeam();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener SDListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = SDPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverStarDriver();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		ActionListener statsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String filePath = StatsPath.getText();
				inputFromFile inputFromFile = new inputFromFile(filePath, connection);
				try {
					inputFromFile.recoverStats();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Errors Detected in Loading");
					e1.printStackTrace();
				}
			}
			
		};
		
		JLabel DriverLB = new JLabel("Driver");
		DriverLB.setBounds(10, 11, 46, 14);
		contentPane.add(DriverLB);
		
		DriverPath = new JTextField();
		DriverPath.setBounds(52, 8, 86, 20);
		contentPane.add(DriverPath);
		DriverPath.setColumns(10);
		
		JButton DriverBtn = new JButton("Load Drivers");
		DriverBtn.setBounds(148, 7, 100, 25);
		DriverBtn.addActionListener(driverListener);
		contentPane.add(DriverBtn);
		
		JLabel RaceLB = new JLabel("Race");
		RaceLB.setBounds(10, 47, 46, 14);
		contentPane.add(RaceLB);
		
		RacePath = new JTextField();
		RacePath.setBounds(52, 44, 86, 20);
		contentPane.add(RacePath);
		RacePath.setColumns(10);
		
		JButton RaceBtn = new JButton("Load Races");
		RaceBtn.setBounds(148, 43, 100, 23);
		RaceBtn.addActionListener(raceListener);
		contentPane.add(RaceBtn);
		
		JLabel TeamLB = new JLabel("Team");
		TeamLB.setBounds(10, 79, 46, 14);
		contentPane.add(TeamLB);
		
		TeamPath = new JTextField();
		TeamPath.setBounds(52, 76, 86, 20);
		contentPane.add(TeamPath);
		TeamPath.setColumns(10);
		
		JButton TeamBtn = new JButton("Load Teams");
		TeamBtn.setBounds(148, 77, 100, 23);
		TeamBtn.addActionListener(teamListener);
		contentPane.add(TeamBtn);
		
		JLabel UserLB = new JLabel("User");
		UserLB.setBounds(258, 11, 46, 14);
		contentPane.add(UserLB);
		
		UserPath = new JTextField();
		UserPath.setBounds(300, 8, 86, 20);
		contentPane.add(UserPath);
		UserPath.setColumns(10);
		
		JButton UserBtn = new JButton("Load User");
		UserBtn.setBounds(408, 7, 89, 23);
		UserBtn.addActionListener(userListener);
		contentPane.add(UserBtn);
		
		JLabel WFLB = new JLabel("WorksFor");
		WFLB.setBounds(10, 110, 46, 14);
		contentPane.add(WFLB);
		
		WFPath = new JTextField();
		WFPath.setBounds(62, 107, 86, 20);
		contentPane.add(WFPath);
		WFPath.setColumns(10);
		
		JButton WFBtn = new JButton("Load WorksFor");
		WFBtn.setBounds(158, 106, 118, 23);
		WFBtn.addActionListener(WorksForListener);
		contentPane.add(WFBtn);
		
		JLabel ParticipatsLB = new JLabel("Participates");
		ParticipatsLB.setBounds(10, 143, 56, 14);
		contentPane.add(ParticipatsLB);
		
		PPath = new JTextField();
		PPath.setBounds(72, 140, 86, 20);
		contentPane.add(PPath);
		PPath.setColumns(10);
		
		JButton PBtn = new JButton("Load Participation");
		PBtn.setBounds(168, 140, 136, 23);
		PBtn.addActionListener(participatesListener);
		contentPane.add(PBtn);
		
		JLabel StatsLB = new JLabel("Stats");
		StatsLB.setBounds(10, 180, 46, 14);
		contentPane.add(StatsLB);
		
		StatsPath = new JTextField();
		StatsPath.setBounds(52, 177, 86, 20);
		contentPane.add(StatsPath);
		StatsPath.setColumns(10);
		
		JButton StatsBtn = new JButton("Load Stats");
		StatsBtn.setBounds(159, 176, 89, 23);
		StatsBtn.addActionListener(statsListener);
		contentPane.add(StatsBtn);
		
		JLabel STLB = new JLabel("Star Team");
		STLB.setBounds(258, 47, 65, 14);
		contentPane.add(STLB);
		
		STPath = new JTextField();
		STPath.setBounds(333, 44, 86, 20);
		contentPane.add(STPath);
		STPath.setColumns(10);
		
		JButton STBtn = new JButton("Load Star Team");
		STBtn.setBounds(429, 43, 136, 23);
		STBtn.addActionListener(STListener);
		contentPane.add(STBtn);
		
		JLabel SDLB = new JLabel("Star Driver");
		SDLB.setBounds(258, 79, 65, 14);
		contentPane.add(SDLB);
		
		SDPath = new JTextField();
		SDPath.setBounds(333, 76, 86, 20);
		contentPane.add(SDPath);
		SDPath.setColumns(10);
		
		JButton SDBtn = new JButton("Load Star Driver");
		SDBtn.setBounds(429, 75, 136, 23);
		SDBtn.addActionListener(SDListener);
		contentPane.add(SDBtn);
		
		JLabel HistoryLB = new JLabel("History");
		HistoryLB.setBounds(300, 110, 46, 14);
		contentPane.add(HistoryLB);
		
		HistoryPath = new JTextField();
		HistoryPath.setBounds(356, 107, 86, 20);
		contentPane.add(HistoryPath);
		HistoryPath.setColumns(10);
		
		JButton HistoryBtn = new JButton("Load History");
		HistoryBtn.setBounds(452, 106, 113, 23);
		HistoryBtn.addActionListener(historyListener);
		contentPane.add(HistoryBtn);
		
		
	}
}
