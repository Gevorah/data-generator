package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Generator;



public class Main extends Application {

	private GeneratorGUI generatorGUI;
	private static  Generator generator;

	public Main() {

		generator= new Generator();
		generatorGUI = new GeneratorGUI(generator);

	}

	public static void main(String[] args) {

		launch(args);
		
		}

	

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPane.fxml"));

		loader.setController(generatorGUI);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Main Window");
		generatorGUI.loadSettinWindow(null);
		stage.show();
		
	}


}
