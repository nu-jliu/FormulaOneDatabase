import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormulaOneUI {
	
	private JFrame frmLogin;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */

	
	public FormulaOneUI() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 543, 443);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(190, 226, 155, 22);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
	
		
		textField_1 = new JTextField();
		textField_1.setBounds(190, 280, 155, 22);
		frmLogin.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnButton = new JButton("Login");
		btnButton.setBounds(107, 335, 90, 25);
		ActionListener loginListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		btnButton.addActionListener(loginListener);
		frmLogin.getContentPane().add(btnButton);
		
		btnNewButton = new JButton("Register");
		btnNewButton.setBounds(321, 335, 90, 25);
		ActionListener registerListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmLogin.setVisible(false);
				RegWindow reg = new RegWindow();
			}
			
		};
		btnNewButton.addActionListener(registerListener);
		frmLogin.getContentPane().add(btnNewButton);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(107, 229, 68, 16);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(110, 283, 68, 16);
		frmLogin.getContentPane().add(lblPassword);
		
		ImageIcon ii = new ImageIcon("image/icon.png");
		JLabel iconLabel = new JLabel(ii);
		iconLabel.setBounds(62, 13, 389, 188);
		frmLogin.getContentPane().add(iconLabel);
		
		frmLogin.setVisible(true);
	}
	
	public class RegWindow {
		
		JFrame frame;
		JButton regButton;
		JTextField Username;
		JTextField Password;
		JTextField Email;
		
		public RegWindow() {
			frame = new JFrame("Formula1Tracker");
			frame.setBounds(100, 100, 543, 443);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);

			
			Username = new JTextField();
			Username.setBounds(190, 226, 155, 22);
			frame.getContentPane().add(Username);
			Username.setColumns(10);

			Password = new JTextField();
			Password.setBounds(190, 280, 155, 22);
			frame.getContentPane().add(Password);
			Password.setColumns(10);
			
			Email = new JTextField();
			Email.setBounds(190, 253, 155, 22);
			frame.getContentPane().add(Email);
			Email.setColumns(10);
			
			ImageIcon icon = new ImageIcon("image/icon.png");
			JLabel iconLabel = new JLabel(icon);
			iconLabel.setBounds(62, 13, 389, 188);
			frame.getContentPane().add(iconLabel);
			
			JLabel lblUsername = new JLabel("Username: ");
			lblUsername.setBounds(110, 229, 68, 16);
			frame.getContentPane().add(lblUsername);
			
			JLabel lblPassword = new JLabel("Password: ");
			lblPassword.setBounds(110, 283, 68, 16);
			frame.getContentPane().add(lblPassword);
			
			JLabel lblEmail = new JLabel("Email: ");
			lblEmail.setBounds(110, 256, 68, 16);
			frame.getContentPane().add(lblEmail);
			
			JButton btnButton = new JButton("Register");
			btnButton.setBounds(200, 335, 90, 25);
			ActionListener loginListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
					
				}
				
			};
			btnButton.addActionListener(loginListener);
			frame.getContentPane().add(btnButton);
			
			frame.setVisible(true);
		}
	}
	
	
}
