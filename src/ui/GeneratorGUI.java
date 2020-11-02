package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.Generator;

public class GeneratorGUI {

	@FXML
	private BorderPane mainPane;
	
	 @FXML
	    private TextField numberUsers;

	    @FXML
	    private TextField lastName;

	    @FXML
	    private TextField gender;

	    @FXML
	    private TextField birthdate;

	    @FXML
	    private TextField name;

	    @FXML
	    private TextField heigth;

	    @FXML
	    private TextField nationality;

	    @FXML
	    private ImageView image;
	    
	    @FXML
	    private TextField nameSearched;
	    
	    @FXML
	    private ListView<?> listView;
    
	Generator main;

	public GeneratorGUI(Generator main) {
		this.main = main;
	}

	public void loadSettinWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(setting);

	}
	
	public void loadCreate(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateUser.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(setting);

	}
	
	public void loadSearch(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchUser.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(setting);

	}

	@FXML
	void create(ActionEvent event)   throws IOException {
		
		loadCreate(null);
	}


	@FXML
	void search(ActionEvent event)  throws IOException{

		loadSearch(null);
	}
	
    @FXML
    void back(ActionEvent event) throws IOException {

    	loadSettinWindow(null);
    }

    @FXML
    void delete(ActionEvent event) {

    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText("user has been deleted");
		alert.showAndWait();
    }

    @FXML
    void edit(ActionEvent event) {

    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText("the information has been edited correctly");
		alert.showAndWait();
    }

    @FXML
    void view(ActionEvent event) {

    }
    
    @FXML
    void createUser(ActionEvent event) {

    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText("user successfully registered");
		alert.showAndWait();
    }

    @FXML
    void selectImage(ActionEvent event) {

    }
    

    @FXML
    void createNumber(ActionEvent event) {

    }

}
