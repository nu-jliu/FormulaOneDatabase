package Database.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class UserService {
	private static final Random RANDOM = new SecureRandom();
	private static final Base64.Encoder enc = Base64.getEncoder();
	private static final Base64.Decoder dec = Base64.getDecoder();
	private Connections dbService = null;

	public UserService(Connections dbService) {
		this.dbService = dbService;
	}

	public boolean useApplicationLogins() {
		return true;
	}

	public boolean login(String username, String password) throws SQLException {
		PreparedStatement stmt;
		ResultSet rs;
		String salt = "";
		String hash = "";
		String query = "select [PasswordSalt], [PasswordHash] from [User] where username = ?";
		if (username == null || username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "ERROR: Login Fail -Null Input");
			return false;
		}
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				salt = rs.getString(1);
				hash = rs.getString(2);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, ("ERROR: Login Fail -SQLException"));
			return false;
		}
		if (salt.isEmpty()) {
			JOptionPane.showMessageDialog(null, "ERROR: Login Fail");
			return false;
		}
		byte[] realSalt = dec.decode(salt);
		if (hashPassword(realSalt, password).equals(hash)) {
			JOptionPane.showMessageDialog(null, "Login Successful");
			return true;
		}
		JOptionPane.showMessageDialog(null, "ERROR: Login Fail");
		return false;
	}

	public boolean register(String username, String Email, String password) throws SQLException {
		byte[] newSalt = getNewSalt();
		String salt = getStringFromBytes(newSalt);
		password = hashPassword(newSalt, password);
		int returnValue;
		try {
			CallableStatement cs = this.dbService.getConnection()
					.prepareCall("{? = call Register_New_User(?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			cs.setString(3, Email);
			cs.setString(4, salt);
			cs.setString(5, password);
			cs.execute();
			returnValue = cs.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR: Register Failed");
			e.printStackTrace();
			return false;
		}
		if (returnValue == 10) {
			JOptionPane.showMessageDialog(null, "ERROR: Register Failed - Invalid Input");
			return false;
		} else {
			JOptionPane.showMessageDialog(null, "Registration successful");
			return true;
		}
	}
	
	public boolean changePassword(String username, String Email, String password) throws SQLException {
		byte[] newSalt = getNewSalt();
		String salt = getStringFromBytes(newSalt);
		password = hashPassword(newSalt, password);
		int returnValue;
		try {
			CallableStatement cs = this.dbService.getConnection()
					.prepareCall("{? = call change_Password(?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, username);
			cs.setString(3, Email);
			cs.setString(4, salt);
			cs.setString(5, password);
			cs.execute();
			returnValue = cs.getInt(1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR: Change Password Failed");
			e.printStackTrace();
			return false;
		}
		if (returnValue == 10) {
			JOptionPane.showMessageDialog(null, "ERROR: Change Passwprd Failed - Invalid Input");
			return false;
		} else {
			JOptionPane.showMessageDialog(null, "Password Changed successful");
			return true;
		}
	}

	public byte[] getNewSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}

	public String getStringFromBytes(byte[] data) {
		return enc.encodeToString(data);
	}

	public String hashPassword(byte[] salt, String password) {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f;
		byte[] hash = null;
		try {
			f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			JOptionPane.showMessageDialog(null, "An error occurred during password hashing. See stack trace.");
			e.printStackTrace();
		}
		return getStringFromBytes(hash);
	}

}
