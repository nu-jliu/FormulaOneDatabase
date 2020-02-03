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
				String username = Username.getText();
				String password = Password.getText();
				String email = Email.getText();
				try {
					boolean successLogin = regService.register(username, email, password);
					if (successLogin) {
						closeFrame();
						LoginWindow ui = new LoginWindow();
					}
				} catch (SQLException e) {

				}
			}

		};
		btnButton.addActionListener(loginListener);
		frame.getContentPane().add(btnButton);

		frame.setVisible(true);
	}

	public void closeFrame() {
		this.frame.dispose();
	}
}