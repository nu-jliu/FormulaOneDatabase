package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Database.service.Connections;
import Database.service.UserService;

public class LoginWindow {

	private JFrame frmLogin;
	private JTextField username;
	private JTextField password;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */

	public LoginWindow() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 543, 443);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		Connections connection = new Connections();
		try {
			connection.connect();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Failed to connect to the server");
			e1.printStackTrace();
		}
		UserService loginService = new UserService(connection);

		username = new JTextField();
		username.setBounds(190, 226, 155, 22);
		frmLogin.getContentPane().add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(190, 280, 155, 22);
		frmLogin.getContentPane().add(password);
		password.setColumns(10);

		JButton btnButton = new JButton("Login");
		btnButton.setBounds(107, 335, 90, 25);
		ActionListener loginListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userName = username.getText();
				String passWord = password.getText();
				try {
					boolean successLogin = loginService.login(userName, passWord);
					if (successLogin) {
						closeFrame();
						NavigationWindow nw = new NavigationWindow(connection);
					} else {
					}
				} catch (SQLException e) {

				}
			}

		};
		btnButton.addActionListener(loginListener);
		frmLogin.getContentPane().add(btnButton);

		btnNewButton = new JButton("Register");
		btnNewButton.setBounds(321, 335, 90, 25);
		ActionListener registerListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				RegWindow reg = new RegWindow(loginService);
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

	public void closeFrame() {
		this.frmLogin.dispose();
	}

}
