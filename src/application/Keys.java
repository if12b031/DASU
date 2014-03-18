package application;

import java.io.*;
import java.security.*;
import java.security.spec.*;
 
public class Keys {
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private KeyFactory keyFactory;
	private KeyPairGenerator keyGen;
	private String savingPath;
	
	public Keys() {
		try {
			this.keyFactory = KeyFactory.getInstance("RSA");
			this.keyGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	public void generateKeyPair() {		
		// Define length of keys
		// And set Private and Public Key
		KeyPair keyPair;
		keyGen.initialize(1024);
		KeyPair generatedKeyPair = keyGen.genKeyPair();
		keyPair = generatedKeyPair;
		this.setPublicKey(keyPair.getPublic());
		this.setPrivateKey(keyPair.getPrivate());
	}
	
	
	//diese funktion umschreiben auf savePublicKey und savePrivateKey 
	public void SaveKeyPair(String path) throws IOException {		
		// Store Public Key
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
			publicKey.getEncoded());
		FileOutputStream fos = new FileOutputStream(path + "/public.key");
		fos.write(x509EncodedKeySpec.getEncoded());
		fos.close();
		 
		// Store Private Key
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				privateKey.getEncoded());
		fos = new FileOutputStream(path + "/private.key");
		fos.write(pkcs8EncodedKeySpec.getEncoded());
		fos.close();
	}
		 
	public void LoadPublicKey(String path)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {		
		// Read extern Public Key
		File filePublicKey = new File(path);
		FileInputStream fis = new FileInputStream(path);
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		fis.read(encodedPublicKey);
		fis.close();
		 
		// Save extern Public Key
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
										encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		 
		this.setPublicKey(publicKey);
	}
	
	public void LoadPrivateKey(String path)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {		
		 
		// Read Private Key
		File filePrivateKey = new File(path);
		FileInputStream fis = new FileInputStream(path);
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fis.read(encodedPrivateKey);
		fis.close();
		
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
	 
		this.setPrivateKey(privateKey);
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public String getSavingPath() {
		return savingPath;
	}
	public void setSavingPath(String savingPath) {
		this.savingPath = savingPath;
	}
}