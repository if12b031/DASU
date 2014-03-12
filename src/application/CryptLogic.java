package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class CryptLogic {

	private Cipher cipher;
	
	// Initialize cipher property
	public CryptLogic() {
		try {
			this.cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public void encrypt(String inputPath, Keys myKeys) {
		byte[] cipherText = null;
		try {
			FileInputStream fis = new FileInputStream(inputPath);
			byte[] plainText = new byte[fis.available()];
		    fis.read(plainText);
		    fis.close();
			System.out.println( "Plain: " + new String(plainText) );
			
			// Encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, myKeys.getKeyPair().getPublic());
			cipherText = cipher.doFinal(plainText);
		    
			System.out.println( "Finish encryption: " );
		    System.out.println( new String(cipherText) );
		    
		    // Write encrypted text to file
		    FileOutputStream fs = new FileOutputStream(Main.path + "/encryptedText.txt");
		    fs.write(cipherText);
		    fs.close();

		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void decrypt(String path, Keys myKeys) {
		byte[] decryptedText = null;
		try {						
			// Read encrypted text from file
			FileInputStream fis = new FileInputStream(path);
		    decryptedText = new byte[fis.available()];
		    fis.read(decryptedText);
		    fis.close();
			
		    // Decrypt the text using the private key
		 	cipher.init(Cipher.DECRYPT_MODE, myKeys.getKeyPair().getPrivate());
			decryptedText = cipher.doFinal(decryptedText);
			
			System.out.println( "Finish decryption: " );
			System.out.println( new String(decryptedText) );
			
			// Write decrypted text to file
		    FileOutputStream fs = new FileOutputStream(Main.path + "/decryptedText.txt");
		    fs.write(decryptedText);
		    fs.close();
			
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	}
}
