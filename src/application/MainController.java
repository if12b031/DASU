package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MainController implements Initializable {

	@FXML private Button buttonFileDecrypt;
	@FXML private Button buttonGenPair;
	@FXML private Button buttonFileEncrypt;
	@FXML private Button buttonPKEncrypt;
	@FXML private Button buttonLoadPrivate;
	@FXML private Button buttonLoadPublic;
	@FXML private Button buttonBrowseSavePath;
	@FXML private Button buttonSavePath;
	@FXML private Button decrypt;
	@FXML private Button encrypt;
	@FXML private Button loadKeyPair;
	@FXML private Button generateKeyPair;
	
	Keys myKeys = new Keys();
	CryptLogic myCryptLogic = new CryptLogic();
	
	
	@FXML
	public void decrypt(){
		
		String fileWithPath, privateKeyWithPath;
		fileWithPath = buttonFileDecrypt.getText();
		privateKeyWithPath = buttonLoadPrivate.getText();
		try {
			myKeys.LoadPrivateKey(privateKeyWithPath);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myCryptLogic.decrypt(fileWithPath, myKeys);		
	}
	
	@FXML
	public void encrypt(){
		String fileWithPath;
		String publicKeyPath;
		
		fileWithPath = buttonFileEncrypt.getText();
		publicKeyPath = buttonPKEncrypt.getText();
		
		try {
			myKeys.LoadPublicKey(publicKeyPath);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myCryptLogic.encrypt(fileWithPath, myKeys);
	}
	
	@FXML
	public void genKeyPair(){
		
		String savingPath;
		
		savingPath = buttonGenPair.getText();
		
		myKeys.generateKeyPair();
		try {
			myKeys.SaveKeyPair(savingPath);
		} catch (IOException e) {
			System.out.println(savingPath+" is not a valid path!");
		}
		
		System.out.println("generate Key-Pair in "+savingPath);
	}
	
	@FXML
	public void savePath(){
		
		String savingPath;
		
		savingPath = buttonBrowseSavePath.getText();
		
		myKeys.setSavingPath(savingPath);
		
		System.out.println("general Path = "+savingPath);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		buttonFileDecrypt.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				 FileChooser fileChooser = new FileChooser();	
				 fileChooser.setTitle("Select File");
				 //Set extension filter
		         FileChooser.ExtensionFilter extFilter =
		         new FileChooser.ExtensionFilter("*", "*");
		         fileChooser.getExtensionFilters().add(extFilter);
		          //Show open file dialog
		          File file = fileChooser.showOpenDialog(null);
		          if(file!=null)
		        	  
		        	  buttonFileDecrypt.setText(file.getPath());
		          }
		 });
		
		buttonGenPair.setOnAction(new EventHandler<ActionEvent>() {//directory search
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Select Directory");
                //Show open file dialog
                File file = directoryChooser.showDialog(null);
               if(file!=null){
            	   
                    buttonGenPair.setText(file.getPath());
               }
            }

});
		
		buttonBrowseSavePath.setOnAction(new EventHandler<ActionEvent>() {//directory search
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Select Directory");
                //Show open file dialog
                File file = directoryChooser.showDialog(null);
               if(file!=null){
            	   
                    buttonBrowseSavePath.setText(file.getPath());
               }
            }

});
		
		buttonFileEncrypt.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				 FileChooser fileChooser = new FileChooser();	
				 fileChooser.setTitle("Select File");
				 //Set extension filter
		         FileChooser.ExtensionFilter extFilter =
		         new FileChooser.ExtensionFilter("*", "*");
		         fileChooser.getExtensionFilters().add(extFilter);
		          //Show open file dialog
		          File file = fileChooser.showOpenDialog(null);
		          if(file!=null)
		        	  
		        	  buttonFileEncrypt.setText(file.getPath());
		          }
		 });
		
		buttonPKEncrypt.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				 FileChooser fileChooser = new FileChooser();	
				 fileChooser.setTitle("Select Public-Key");
				 //Set extension filter
		         FileChooser.ExtensionFilter extFilter =
		         new FileChooser.ExtensionFilter("*", "*");
		         fileChooser.getExtensionFilters().add(extFilter);
		          //Show open file dialog
		          File file = fileChooser.showOpenDialog(null);
		          if(file!=null)
		        	  
		        	  buttonPKEncrypt.setText(file.getPath());
		          }
		 });
		
		buttonLoadPrivate.setOnAction(new EventHandler<ActionEvent>() {
			@Override

			public void handle(ActionEvent event) {

				 FileChooser fileChooser = new FileChooser();	
				 fileChooser.setTitle("Select Private-Key");
				 //Set extension filter
		         FileChooser.ExtensionFilter extFilter =
		         new FileChooser.ExtensionFilter("*", "*");
		         fileChooser.getExtensionFilters().add(extFilter);
		          //Show open file dialog
		          File file = fileChooser.showOpenDialog(null);
		          if(file!=null)
		        	  
		        	  buttonLoadPrivate.setText(file.getPath());
		          }
		 });
	}
}
