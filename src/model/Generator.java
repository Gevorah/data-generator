package model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.table.DefaultTableModel;

import datastructure.AVL;
import datastructure.Node;

public class Generator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AVL<String, Person> people;
	private ArrayList<Person> tmp;
	private BufferedReader br1;
	private BufferedReader br2;

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

	public final static String NAMES = "data" + File.separator + "names.csv";
	public final static String SURNAMES = "data" + File.separator + "surnames.csv";

	public String[][] nameGenerator() throws IOException {
		br1 = new BufferedReader(new FileReader(NAMES));
		String s1 = br1.readLine();
		String[][] names = null;
		int i = 0;
		while (s1 != null) {
			br2 = new BufferedReader(new FileReader(SURNAMES));
			String s2 = br2.readLine();
			int j=0;
			while (s2 != null) {
				//
			}
			i++;
		}
		return names;
	}

	public static String birthDateGenerator() {

		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(1930, 2020);

		gc.set(gc.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(gc.DAY_OF_YEAR, dayOfYear);

		return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);

	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
	
	public static String heightGenerator() {
		int num = (int) (Math.random() * ((200 - 30) + 30));

		return num +" cm";

	}

	public static void main(String[] args) {
	
		System.out.println(birthDateGenerator());
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
