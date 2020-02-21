package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

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
	private int UID;
	
	public LoginWindow() throws SQLException, FileNotFoundException, IOException{
		this.frmLogin = new JFrame();
		this.frmLogin.setTitle("Login"); 
		this.frmLogin.setBounds(100, 100, 543, 443);
		this.frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmLogin.getContentPane().setLayout(null);

		Connections connection = new Connections();
		try {
			connection.connect();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Failed to connect to the server");
//			e1.printStackTrace();
		}
		UserService loginService = new UserService(connection);

		this.username = new JTextField();
		this.username.setBounds(190, 226, 155, 22);
		this.frmLogin.getContentPane().add(username);
		this.username.setColumns(10);

		this.password = new JPasswordField();
		this.password.setBounds(190, 280, 155, 22);
		this.frmLogin.getContentPane().add(password);
		this.password.setColumns(10);

		JButton btnButton = new JButton("Login");
		btnButton.setBounds(107, 335, 90, 25);
		ActionListener loginListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userName = username.getText();
				String passWord = password.getText();
				CallableStatement cs;
				try {
					cs = connection.getConnection().prepareCall("{? = call get_UID(?)}");
					cs.registerOutParameter(1, Types.INTEGER);
					cs.setString(2, userName);
					cs.execute();
					LoginWindow.this.UID = cs.getInt(1);
				} catch (SQLException e1) {
//					e1.printStackTrace();
				}
				try {
					boolean successLogin = loginService.login(userName, passWord);
					if (successLogin) {
						closeFrame();
						new NavigationWindow(connection, LoginWindow.this.UID);
					} else {
					}
				} catch (SQLException e) {

				}
			}

		};
		btnButton.addActionListener(loginListener);
		this.frmLogin.getContentPane().add(btnButton);

		this.btnNewButton = new JButton("Register");
		this.btnNewButton.setBounds(321, 335, 90, 25);
		ActionListener registerListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginWindow.this.frmLogin.dispose();
				RegWindow reg = new RegWindow(loginService);
			}

		};
		this.btnNewButton.addActionListener(registerListener);
		this.frmLogin.getContentPane().add(btnNewButton);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(107, 229, 68, 16);
		this.frmLogin.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(110, 283, 68, 16);
		this.frmLogin.getContentPane().add(lblPassword);

		ImageIcon ii = new ImageIcon("image/icon.png");
		JLabel iconLabel = new JLabel(ii);
		iconLabel.setBounds(62, 13, 389, 188);
		this.frmLogin.getContentPane().add(iconLabel);
		
		KeyListener expressLogin = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					loginListener.actionPerformed(null);
				else if (e.getKeyCode() == KeyEvent.VK_F8) {
					closeFrame();
					new NavigationWindow(connection, LoginWindow.this.UID);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// do nothing
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// do nothing 
				
			}
			
		};
		
		/*
		 * back door for testing purpose, not included in final program
		 */
		
		this.username.addKeyListener(expressLogin);
		this.password.addKeyListener(expressLogin);

		this.frmLogin.setVisible(true);
	}

	public void closeFrame() {
		this.frmLogin.dispose();
	}

}
