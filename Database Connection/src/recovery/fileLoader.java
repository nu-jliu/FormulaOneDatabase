package recovery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.service.Connections;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class fileLoader extends JFrame {

	private JPanel contentPane;
	private JTextField DriverPath;
	private JTextField RacePath;
	private JTextField TeamPath;
	private JTextField UserPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Connections connection = new Connections();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileLoader frame = new fileLoader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		
		JLabel DriverLB = new JLabel("Driver");
		DriverLB.setBounds(10, 11, 46, 14);
		contentPane.add(DriverLB);
		
		DriverPath = new JTextField();
		DriverPath.setBounds(52, 8, 86, 20);
		contentPane.add(DriverPath);
		DriverPath.setColumns(10);
		
		JButton DriverBtn = new JButton("Load Drivers");
		DriverBtn.setBounds(148, 7, 100, 25);
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
		contentPane.add(UserBtn);
	}
}
