package model;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import datastructure.AVL;
import datastructure.Node;

public class Generator {
	
	private AVL<String,Person> avl;
	private ArrayList <Person> people;
	

	public Generator(AVL avl) {
		
		avl= new AVL();
		people= new ArrayList <Person>();
	}

	public void add(String code, String name, String lastName, String gender, String birthdate, String height,
			String nationality, String profile){
		
		Person tmp= new Person( code,  name,  lastName,  gender,  birthdate,  height,nationality,  profile);
		avl.insert(code, tmp);
		people.add(tmp);
	}
	
	public void addNumberOfUsers(Integer maximum) {}
	
	public void delete(Person node) {
		
		avl.remove(node.getCode());
		people.remove(node);
	}
	
	public void search(Node node, String character) {}
	
	public void suggestions(String parameter, String mode) {
		
		try {
			
			String [] tittles= {"code",  "name",  "lastName",  "gender",  "birthdate",  "height", "nationality"};
			String SQL = "select *from contacts where "+mode+" like"+'"'+parameter+'"'+"_%";
			DefaultTableModel dtm = new DefaultTableModel(null,tittles);
			Statement st;
			Connection conn;
			
			//st = conn.prepareStatement(SQL);
			
			
		}
		
		catch(Exception e) {}
	}
}
