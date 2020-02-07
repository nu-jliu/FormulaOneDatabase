package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Database.service.UserService;

public class RegWindow {

	JFrame frame;
	JButton regButton;
	JTextField Username;
	JTextField Password;
	JTextField Email;

	UserService regService;

	public RegWindow(UserService regService) {
		this.regService = regService;
		this.frame = new JFrame("Formula1Tracker");
		this.frame.setBounds(100, 100, 543, 443);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		this.Username = new JTextField();
		this.Username.setBounds(190, 226, 155, 22);
		this.frame.getContentPane().add(Username);
		this.Username.setColumns(10);

		this.Password = new JTextField();
		this.Password.setBounds(190, 280, 155, 22);
		this.frame.getContentPane().add(Password);
		this.Password.setColumns(10);

		this.Email = new JTextField();
		this.Email.setBounds(190, 253, 155, 22);
		this.frame.getContentPane().add(Email);
		this.Email.setColumns(10);

		ImageIcon icon = new ImageIcon("image/icon.png");
		JLabel iconLabel = new JLabel(icon);
		iconLabel.setBounds(62, 13, 389, 188);
		this.frame.getContentPane().add(iconLabel);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(110, 229, 68, 16);
		this.frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(110, 283, 68, 16);
		this.frame.getContentPane().add(lblPassword);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(110, 256, 68, 16);
		this.frame.getContentPane().add(lblEmail);

		JButton btnButton = new JButton("Register");
		btnButton.setBounds(200, 335, 90, 25);
		ActionListener loginListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = RegWindow.this.Username.getText();
				String password = RegWindow.this.Password.getText();
				String email = RegWindow.this.Email.getText();
				try {
					boolean successLogin = regService.register(username, email, password);
					if (successLogin) {
						RegWindow.this.closeFrame();
						new LoginWindow();
					}
				} catch (SQLException e) {

				}
			}

		};
		btnButton.addActionListener(loginListener);
		this.frame.getContentPane().add(btnButton);

		this.frame.setVisible(true);
	}

	public void closeFrame() {
		this.frame.dispose();
	}
}