package application;

import java.io.*;
import java.security.*;
import java.security.spec.*;
 
public class Keys {
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private KeyFactory keyFactory;
	private KeyPairGenerator keyGen;
	
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
		File filePublicKey = new File(path + "/public.key");
		FileInputStream fis = new FileInputStream(path + "/public.key");
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
		/*
		// Read Public Key.
		File filePublicKey = new File(path + "/public.key");
		FileInputStream fis = new FileInputStream(path + "/public.key");
		byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
		fis.read(encodedPublicKey);
		fis.close();
		*/
		 
		// Read Private Key
		File filePrivateKey = new File(path + "/private.key");
		FileInputStream fis = new FileInputStream(path + "/private.key");
		byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
		fis.read(encodedPrivateKey);
		fis.close();
		 
		// Generate KeyPair
		/*
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		 */
		
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
	 
		this.setPrivateKey(privateKey);
	}
	
	public void dumpKeys() {
		PublicKey pub = publicKey;
		System.out.println("Public Key: " + getHexString(pub.getEncoded()));
		 
		PrivateKey priv = privateKey;
		System.out.println("Private Key: " + getHexString(priv.getEncoded()));
	}
	 
	private String getHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
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
}