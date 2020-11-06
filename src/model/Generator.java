package model;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import datastructure.AVL;
import datastructure.Node;
import javafx.scene.image.Image;

public class Generator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AVL<String, Person> people;
	public static HashMap<String,Image> profiles;
	private ArrayList<Person> tmp;

	public Generator() {
		people = new AVL<String, Person>();
		tmp = new ArrayList<Person>();
	}

	public void add(String code, String name, String lastName, String gender, String birthdate, String height,
			String nationality, String profile) {
		people.put(code, new Person(code, name, lastName, gender, birthdate, height, nationality, profile));
	}

	public void addNumberOfUsers(Integer toAdd) {

		for(int i=1; i<toAdd; i++) {
			
			int key=codeGenerator();

			//people.put(key, new Person(key, nameGenerator(), lastName, genderGenerator(), birthDateGenerator(), heightGenerator(), nationality, profile) );

	}
	}

	public static int codeGenerator() {
		return (int) (Math.random() * 1000000000);
	}
	
	public void imageGenerator(String name) {
		try {
			URL url = new URL("https://thispersondoesnotexist.com/image");
			URLConnection urlc = url.openConnection();
			urlc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			urlc.connect();
			BufferedInputStream is = new BufferedInputStream(urlc.getInputStream());
			FileOutputStream fos = new FileOutputStream("data"+File.separator+"image.jpeg");
			byte[] array = new byte[1000];
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}
			is.close();
			fos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		profiles.put(name, new Image("data"+File.separator+"image.jpeg"));
	}
	
	public final static int NL = 6781;
	public final static int SNL = 162252;
	public final static String NAMES = "data" + File.separator + "names.csv";
	public final static String SURNAMES = "data" + File.separator + "surnames.csv";

	public static String nameGenerator() throws IOException, FileNotFoundException  {
		BufferedReader brn = new BufferedReader(new FileReader(NAMES));
		BufferedReader brsn = new BufferedReader(new FileReader(SURNAMES));
		boolean end = false;
		String name = null;
		String surname = null;
		int i = 0;
		int n = (int)(Math.random()*NL +1);
		int sn = (int)(Math.random()*SNL +2);
		while(brn.readLine()!=null) {
			if(i++==n-1) {
				name = brn.readLine().split(",")[0];
				break;
			}
		}
		i=0;
		while(brsn.readLine()!=null) {
			if(i++==sn-1) {
				surname = brsn.readLine().split(",")[0];
				break;
			}
		}
		brn.close();
		brsn.close();
		return name+" "+surname.substring(0,1)+surname.substring(1).toLowerCase();
	}
	

	public static String birthDateGenerator() {
		GregorianCalendar gc = new GregorianCalendar();
		int year = randBetween(1930, LocalDate.now().getYear());
		gc.set(Calendar.YEAR, year);
		int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH);
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
	
	public static String heightGenerator() {
		int num = (int) (Math.random() * ((200 - 30) + 30));
		return num +" cm";
	}

	public static void main(String[] args) throws IOException {
		//
	}

	public static String genderGenerator() {
		String gender = "";
		int num = (int) (Math.random() * ((2 - 1) + 1));
		if (num == 0) {
			gender = "Female";
		} else if (num == 1) {
			gender = "Male";

		}
		return gender;
	}

	public void delete(Person node) {
		people.remove(node.getCode());
	}

	public void search(Node node, String character) {

	}

	public void suggestions(String parameter, String mode) {

		try {

			String[] tittles = { "code", "name", "lastName", "gender", "birthdate", "height", "nationality" };
			String SQL = "select *from contacts where " + mode + " like" + '"' + parameter + '"' + "_%";
			DefaultTableModel dtm = new DefaultTableModel(null, tittles);
			Statement st;
			Connection conn;

			// st = conn.prepareStatement(SQL);

		}

		catch (Exception e) {
		}
	}
}
