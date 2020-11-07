package ui;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.Generator;
import model.Person;

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
	private TableView<Person> Searched;

	@FXML
	private TableColumn<Person, String> ColumnCode;

	@FXML
	private TableColumn<Person, String> ColumnName;

	@FXML
	private TableColumn<Person, String> ColumnSurname;

	@FXML
	private ToggleGroup searchCriteria;

	@FXML
	private TextField lastNameEdit;

	@FXML
	private TextField genderEdit;

	@FXML
	private TextField birthdateEdit;

	@FXML
	private TextField nameEdit;

	@FXML
	private TextField heigthEdit;

	@FXML
	private TextField nationalityEdit;

	@FXML
	private ImageView imageEdit;

	Generator main;

	public GeneratorGUI(Generator main) {
		this.main = main;
	}

	public void loadSettingWindow(ActionEvent event) throws IOException {

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
	void editButton(ActionEvent event) {

		try {

		/*	main.edit(toEdit, nameEdit.getText(), lastNameEdit.getText(), genderEdit.getText(), birthdateEdit.getText(),
					heigthEdit.getText(), nationalityEdit.getText());*/

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("invalid information");
			alert.setContentText("fill in all the fields");
			alert.showAndWait();
		}
	}

	@FXML
	void create(ActionEvent event) throws IOException {

		loadCreate(null);
	}

	@FXML
	void search(ActionEvent event) throws IOException {

		loadSearch(null);
	}

	@FXML
	void back(ActionEvent event) throws IOException {

		loadSettingWindow(null);

	}

	@FXML
	void delete(ActionEvent event) {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText("user has been deleted");
		alert.showAndWait();
	}

	@FXML
	void searchUser(ActionEvent event) {

		String criteria = ((RadioButton) searchCriteria.getSelectedToggle()).getText();

		try {

			main.search(criteria, nameSearched.getText());

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("invalid information");
			alert.setContentText("fill in all the fields");
			alert.showAndWait();
		}

		/*ObservableList<Person> observableList;
		observableList = FXCollections.observableArrayList(main.getSearched());

		Searched.setItems(observableList);
		ColumnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		ColumnCode.setCellValueFactory(new PropertyValueFactory<Person, String>("code"));
		ColumnSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
*/
	}

	@FXML
	void edit(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(setting);

	}

	@FXML
	void view(ActionEvent event) {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText("");
		alert.showAndWait();
	}

	@FXML
	void createUser(ActionEvent event) {

		try {

			main.add(name.getText(), lastName.getText(), gender.getText(), birthdate.getText(), heigth.getText(),
					nationality.getText());

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText("user successfully registered");
			alert.showAndWait();

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("invalid information");
			alert.setContentText("fill in all the fields");
			alert.showAndWait();
		}
	}

	@FXML
	void selectImage(ActionEvent event) {
		JFileChooser choose = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("All images", "jpg", "jpeg", "png");
		choose.setAcceptAllFileFilterUsed(false);
		choose.setFileFilter(filter);
		int op = choose.showOpenDialog(null);
		if (op == JFileChooser.APPROVE_OPTION) {
			Image avatar = new Image("file:" + choose.getSelectedFile().getAbsolutePath());
			image.setImage(avatar);
		}
	}

	@FXML
	void createNumber(ActionEvent event) {

	}

}
