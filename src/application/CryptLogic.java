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
			
			// Encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, myKeys.getPublicKey());
			cipherText = cipher.doFinal(plainText);
		    
			System.out.println( "Finish encryption. " );
			
		    // Write encrypted text to file
		    //cut inputPath into 2 pieces 1 = path, 2 = endung und dann added man einfach _encrypted dazwischen fertig
		    FileOutputStream fs = new FileOutputStream(myKeys.getSavingPath() + "/encryptedText.txt");
		    fs.write(cipherText);
		    fs.close();
		    System.out.println( "Saved in : " + myKeys.getSavingPath() + "/encryptedText.txt" );

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
		 	cipher.init(Cipher.DECRYPT_MODE, myKeys.getPrivateKey());
			decryptedText = cipher.doFinal(decryptedText);
			
			System.out.println( "Finish decryption: " );
			
			// Write decrypted text to file
		    FileOutputStream fs = new FileOutputStream(myKeys.getSavingPath() + "/decryptedText.txt");
		    fs.write(decryptedText);
		    fs.close();
		    System.out.println( "Saved in : " + myKeys.getSavingPath() + "/decryptedText.txt" );
			
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	}
}
