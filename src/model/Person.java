package model;

import java.io.*;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String lastName;
	private String gender;
	private String birthdate;
	private String height;
	private String nationality;
	private String profile;

	public Person(String code, String name, String lastName, String gender, String birthdate, String height,
			String nationality) {

		this.code = code;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.height = height;
		this.nationality = nationality;
		
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	

}
