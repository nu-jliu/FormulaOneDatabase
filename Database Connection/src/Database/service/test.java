package Database.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("password");
        String encryptedPassword = encryptor.encrypt("Zyqsq20000103");
        System.out.println(encryptedPassword);
	}

}
