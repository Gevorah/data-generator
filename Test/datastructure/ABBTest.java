package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Person;

class ABBTest {
	
	ABB <String, Person> prueba;
	
	public void setUp1() {
		
		prueba= new ABB<String, Person>();
		prueba.put("10254", new Person("10254","cristian","cordoba","male", "2002/12/18","172","colombia"));
		prueba.put("4465416", new Person("4465416","lina","nilson","female", "1965/02/16","165","Canada"));
		prueba.put("45132", new Person("45132","annita","ouritao","female", "2015/06/02","150","Brazil"));
		prueba.put("62326", new Person("62326","Munir","lavnrame","male", "1972/01/24","187","Afghanistan"));
	}

	@Test
	void SearchTest() {
		setUp1();
		assertEquals("cristian",prueba.search("10254").getName());
		assertEquals("nilson",prueba.search("4465416").getLastName());
		assertEquals("187",prueba.search("62326").getHeight());
		assertEquals("Brazil",prueba.search("45132").getNationality());
		assertEquals("male",prueba.search("10254").getGender());
		
	}
	
	@Test
	void removeTest() {
		setUp1();
		prueba.remove("62326");
		prueba.remove("45132");
		assertEquals(null,prueba.search("62326"));
		assertEquals(null,prueba.search("45132"));
	}
}
