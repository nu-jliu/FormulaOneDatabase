package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		btnButton.setBounds(200, 305, 90, 25);
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
					JOptionPane.showMessageDialog(null, "SQL Exception");
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "File Not Found");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "IO Exception");
				}
			}

		};

		KeyListener register = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					loginListener.actionPerformed(null);
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

		this.Username.addKeyListener(register);
		this.Email.addKeyListener(register);
		this.Password.addKeyListener(register);

		btnButton.addActionListener(loginListener);
		this.frame.getContentPane().add(btnButton);

		JButton changePassword = new JButton("Change Password");
		changePassword.setBounds(170, 335, 150, 25);
		
		
		
		ActionListener changeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = RegWindow.this.Username.getText();
				String password = RegWindow.this.Password.getText();
				String email = RegWindow.this.Email.getText();
				try {
					boolean successLogin = regService.changePassword(username, email, password);
					if (successLogin) {
						RegWindow.this.closeFrame();
						new LoginWindow();
					}
				} catch (SQLException e) {

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};
		changePassword.addActionListener(changeListener);
		this.frame.getContentPane().add(changePassword);
		
		JButton backButton = new JButton("Go Back");
		backButton.setBounds(170, 365, 150, 25);
		
		ActionListener backListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
				try {
					new LoginWindow();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}				
			}
		};
		backButton.addActionListener(backListener);
		this.frame.getContentPane().add(backButton);
		this.frame.setVisible(true);
	}

	public void closeFrame() {
		this.frame.dispose();
	}
}