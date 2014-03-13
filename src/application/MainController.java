package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class MainController implements Initializable {

	@FXML private Button button1;
	@FXML private Label label1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		button1.setText("Browse File");

		button1.setOnAction(new EventHandler<ActionEvent>() {
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
		        	  
		              label1.setText(file.getPath());
		          }
		 });		
	}
}
