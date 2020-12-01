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
	private RadioButton RBname;

	@FXML
	private ToggleGroup criterial;

	@FXML
	private RadioButton RBfullname;

	@FXML
	private RadioButton RBsurname;

	@FXML
	private RadioButton RBcode;

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
		main.init();
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
			if (nameEdit.getText().equals("") || lastNameEdit.getText().equals("") || genderEdit.getText().equals("")
					|| birthdateEdit.getText().equals("") || heigthEdit.getText().equals("")
					|| nationalityEdit.getText().equals("")) {
				throw new IllegalArgumentException();
			}
			main.edit(observableList.get(0), nameEdit.getText(), lastNameEdit.getText(), genderEdit.getText(),
					birthdateEdit.getText(), heigthEdit.getText(), nationalityEdit.getText());
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Congrats");
			alert.setContentText("user sitting correctly");
			alert.showAndWait();

		} catch (IllegalArgumentException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("invalid information");
			alert.setContentText("fill in all the fields");
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
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
		main.delete(observableList.get(0));
		observableList.clear();
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText("user has been deleted");
		alert.showAndWait();
	}

	public ObservableList<Person> observableList;

	@FXML
	void searchUser(ActionEvent event) {

		try {

			
			if (nameSearched.getText().equals("")) {
				throw new IllegalArgumentException();
			}

			String criteriaSearch = ((RadioButton) criterial.getSelectedToggle()).getText();
			main.search(criteriaSearch, nameSearched.getText());

		} catch (IllegalArgumentException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Invalid Information");
			alert.setContentText("Fill in all the fields");
			alert.showAndWait();
		}
		observableList = FXCollections.observableArrayList(main.getSearched());

		Searched.setItems(observableList);
		ColumnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		ColumnCode.setCellValueFactory(new PropertyValueFactory<Person, String>("code"));
		ColumnSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

	}

	@FXML
	void edit(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(setting);

		Person p = observableList.get(0);
		imageEdit.setImage(Generator.profiles.get(p.getCode()));
		nameEdit.setText(p.getName());
		lastNameEdit.setText(p.getLastName());
		genderEdit.setText(p.getGender());
		nationalityEdit.setText(p.getNationality());
		birthdateEdit.setText(p.getBirthdate());
		heigthEdit.setText(p.getHeight());
	}

	@FXML
	void view(ActionEvent event) {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		Person p = observableList.get(0);
		alert.setContentText(p.getName()+ "\n"+ p.getLastName()+"\n"+ p.getBirthdate() +"\n"+ p.getGender()+"\n"+ p.getNationality()+"\n");
		alert.showAndWait();
	}

	@FXML
	void createUser(ActionEvent event) {

		try {

			if (name.getText().equals("") || lastName.getText().equals("") || gender.getText().equals("")
					|| birthdate.getText().equals("") || heigth.getText().equals("")
					|| nationality.getText().equals("")) {

				throw new IllegalArgumentException();
			}
			main.add(image.getImage(), name.getText(), lastName.getText(), gender.getText(), birthdate.getText(),
					heigth.getText(), nationality.getText());

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText("user successfully registered");
			alert.showAndWait();

		} catch (Exception e) {

			System.out.println(e);
			System.out.println(e.getMessage());
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

	public static int MAX = 999999999;

	@FXML
	void createNumber(ActionEvent event) {
		String tmp = numberUsers.getText();
		try {
			int num = tmp.trim().isEmpty() ? MAX : Integer.parseInt(tmp);
			if (num < 1)
				throw new IllegalArgumentException();
			main.addPeople(num);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(" congratulations");
			alert.setContentText("the users have been created");
			alert.showAndWait();
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("invalid number");
			alert.setContentText("enter only positive numbers");
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
