import java.awt.EventQueue;

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
		frmLogin.getContentPane().add(btnButton);
		
		btnNewButton = new JButton("Register");
		btnNewButton.setBounds(321, 335, 90, 25);
		frmLogin.getContentPane().add(btnNewButton);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(107, 229, 68, 16);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(110, 283, 68, 16);
		frmLogin.getContentPane().add(lblPassword);
		
		ImageIcon ii = new ImageIcon("imgs/icon.png");
		JLabel iconLabel = new JLabel(ii);
		iconLabel.setBounds(62, 13, 389, 188);
		frmLogin.getContentPane().add(iconLabel);
		
		frmLogin.setVisible(true);
	}
}
