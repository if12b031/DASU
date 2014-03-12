package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static String path = "C:\\Documents and Settings\\Georg\\Desktop";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);		
		
		Keys myKeys = new Keys();
		CryptLogic myCryptLogic = new CryptLogic();
		
		try {
			String encryptedtextFilePath = "C:\\Documents and Settings\\Georg\\Desktop/encryptedText.txt";
			String plaintextFilePath = "C:\\Documents and Settings\\Georg\\Desktop/plainText.txt";
			
			myKeys.generateKeyPair();			
			System.out.println("Generated Key Pair");
			
			myKeys.SaveKeyPair(path);
			
			myKeys.LoadPublicKey(path);	 
			System.out.println("Loaded Key Pair");
			
			myKeys.LoadKeyPair(path);
			myKeys.dumpKeys();
			
			myCryptLogic.encrypt(plaintextFilePath, myKeys);
			myCryptLogic.decrypt(encryptedtextFilePath, myKeys);
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
