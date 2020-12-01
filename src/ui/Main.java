package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Generator;


public class Main extends Application {

	private GeneratorGUI generatorGUI;
	private static  Generator generator;
	private Scene scene;

	public Main() {
		if (generator == null) generator = new Generator();
		generatorGUI = new GeneratorGUI(generator);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		ObjectInputStream entrada = null;
		try
		{
			entrada = new ObjectInputStream(new FileInputStream("data" + File.separator + "data.txt")); 
			generator = (Generator)entrada.readObject(); 
			entrada.close();
		}
		catch(Exception e)
		{
			if(entrada != null) entrada.close();
		}

		launch(args);


		ObjectOutputStream salida = new ObjectOutputStream(
				new FileOutputStream("data" + File.separator + "data.txt"));
		salida.writeObject(generator);
		salida.close();
	}

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPane.fxml"));

		loader.setController(generatorGUI);
		Parent root = loader.load();

		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Data Generator");
		generatorGUI.loadSettingWindow(null);
		stage.show();
		stage.sizeToScene();

	}

	/*private GeneratorGUI generatorGUI;
	private static  Generator generator;

	public Main() {
		generator= new Generator();
		generatorGUI = new GeneratorGUI(generator);
	}

	public static void main(String[] args) {
		launch(args);	
	}

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
	}*/

}



