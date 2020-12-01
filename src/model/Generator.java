package model;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;
import datastructure.*;
import javafx.scene.image.Image;

public class Generator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AVL<String, Person> nameAVL;
	private AVL<String, Person> surnameAVL;
	private AVL<String, Person> completeNameAVL;
	private AVL<String, Person> codeAVL;
	public static HashMap<String, Image> profiles = new HashMap<String, Image>();;
	private ArrayList<Person> generate;
	private ArrayList<Person> searched;

	public Generator() {
		codeAVL = new AVL<String, Person>();
		nameAVL = new AVL<String, Person>();
		surnameAVL = new AVL<String, Person>();
		completeNameAVL = new AVL<String, Person>();
		generate = new ArrayList<Person>();
		searched = new ArrayList<Person>();
	}

	public void addTest(Image profile, String key, String name, String lastName, String gender, String birthdate, String height,
			String nationality) {
		profiles.put(key, profile);
		nameAVL.put(name, new Person(key, name, lastName, gender, birthdate, height, nationality));
		surnameAVL.put(lastName, new Person(key, name, lastName, gender, birthdate, height, nationality));
		completeNameAVL.put(name+" "+lastName, new Person(key, name, lastName, gender, birthdate, height, nationality));
		codeAVL.put(key, new Person(key, name, lastName, gender, birthdate, height, nationality));
	}
	
	public void add(Image profile, String name, String lastName, String gender, String birthdate, String height,
			String nationality) {
		String key= codeGenerator();
		profiles.put(key, profile);;
		nameAVL.put(name, new Person(key, name, lastName, gender, birthdate, height, nationality));
		surnameAVL.put(lastName, new Person(key, name, lastName, gender, birthdate, height, nationality));
		completeNameAVL.put(name+" "+lastName, new Person(key, name, lastName, gender, birthdate, height, nationality));
		codeAVL.put(key, new Person(key, name, lastName, gender, birthdate, height, nationality));
	}

	public void addPeople(int num) throws FileNotFoundException, IOException {
		generatePeople(num);
		for(Person tmp:generate) {
			System.out.println("1");
			nameAVL.put(tmp.getName(), tmp);
			surnameAVL.put(tmp.getLastName(), tmp);
			completeNameAVL.put(tmp.getName()+" "+tmp.getLastName(), tmp);
			codeAVL.put(tmp.getCode(), tmp);
		}
	}
	
	public void generatePeople(Integer toAdd) throws FileNotFoundException, IOException {
		generate.clear();
		List<String> list = nationalityGenerator(toAdd);
		int k=0;
		for(int i=0; i<list.size() && k<toAdd; i++) {
			String[] country = list.get(i).split(",");
			int percent = Integer.parseInt(country[1]);
			int j=0;
			while(j++<percent) {
				String key = codeGenerator();
				String[] method = nameGenerator().split(" ");
				String name = method[0];
				String lastName = method[1];
				imageGenerator(key);
				generate.add(new Person(key, name, lastName, genderGenerator(), birthDateGenerator(), heightGenerator(), country[0]));
				k++;
			}
		}
	}

	public ArrayList<Person> getSearched() {
		return searched;
	}

	public static String codeGenerator() {
		int code = (int) (Math.random() * 1000000000);
		return String.valueOf(code);
	}

	public void imageGenerator(String key) {
		try {
			URL url = new URL("https://thispersondoesnotexist.com/image");
			URLConnection urlc = url.openConnection();
			urlc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			urlc.connect();
			BufferedInputStream is = new BufferedInputStream(urlc.getInputStream());
			FileOutputStream fos = new FileOutputStream("images" + File.separator +key+".jpeg");
			byte[] array = new byte[1000];
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}
			is.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Image im = new Image("file:images/"+key+".jpeg");
		Generator.profiles.put(key, im);
	}

	public final static int NL = 6781;
	public final static int SNL = 162252;
	public final static String NAMES = "data" + File.separator + "names.csv";
	public final static String SURNAMES = "data" + File.separator + "surnames.csv";

	public static String nameGenerator() throws IOException, FileNotFoundException {
		BufferedReader brn = new BufferedReader(new FileReader(NAMES));
		BufferedReader brsn = new BufferedReader(new FileReader(SURNAMES));
		String name = null;
		String surname = null;
		int i = 0;
		int n = (int) (Math.random() * NL + 1);
		int sn = (int) (Math.random() * SNL + 2);
		while (brn.readLine()!= null) {
			if (i++ == n - 1) {
				name = brn.readLine().split(",")[0];
				break;
			}
		}
		i = 0;
		while (brsn.readLine()!= null) {
			if (i++ == sn - 1) {
				surname = brsn.readLine().split(",")[0];
				break;
			}
		}
		brn.close();
		brsn.close();
		return name + " " + surname.substring(0, 1) + surname.substring(1).toLowerCase();
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
		return num + " cm";
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

	public final static String POPULATION = "data" + File.separator + "population.csv";

	public static List<String> nationalityGenerator(Integer num) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(POPULATION));
		List<String> list = new ArrayList<String>();
		String country = br.readLine();
		while ((country=br.readLine()) != null) {
			String[] tmp = country.split(",");
			double percent = Double.parseDouble(tmp[10].substring(0,tmp[10].length()-2))/100 +0.0005;
			int population = (int)(num*percent);	
			list.add(tmp[0] + "," + (population==0?1:population));
		}
		br.close();
		return list;
	}

	public void delete(Person node) {
		nameAVL.remove(node.getBirthdate());
		surnameAVL.remove(node.getLastName());
		completeNameAVL.remove(node.getName()+ " "+ node.getLastName());
		codeAVL.remove(node.getCode());
	}

	public void search(String criteria, String character) {
		searched.clear();
		
		if(criteria.equals("Name")) {
			searched.add(nameAVL.search(character));
			
		}
		else if(criteria.equals("Surname")) {
			searched.add(surnameAVL.search(character));
			
		}
		else if(criteria.equals("Full Name")) {
			searched.add(completeNameAVL.search(character));
			
		}
		else if(criteria.equals("Code")) {
			searched.add(codeAVL.search(character));
			
		}
		
	}

	public void edit(Person toEdit, String name, String lastName, String gender, String birthdate, String height,
			String nationality) {

		if (!name.equals(toEdit.getName())) {
			toEdit.setName(name);
		}
		if (!lastName.equals(toEdit.getLastName())) {
			toEdit.setLastName(lastName);
		}
		if (!gender.equals(toEdit.getGender())) {
			toEdit.setGender(gender);
		}
		if (!birthdate.equals(toEdit.getBirthdate())) {
			toEdit.setBirthdate(birthdate);
		}
		if (!height.equals(toEdit.getHeight())) {
			toEdit.setHeight(height);
		}
		if (!nationality.equals(toEdit.getNationality())) {
			toEdit.setNationality(nationality);
		}
		nameAVL.remove(toEdit.getName());
		nameAVL.put(toEdit.getName(),toEdit);
		surnameAVL.remove(toEdit.getLastName());
		surnameAVL.put(toEdit.getLastName(),toEdit);
		completeNameAVL.remove(toEdit.getName()+" "+toEdit.getLastName());
		completeNameAVL.put(toEdit.getName()+" "+toEdit.getLastName(),toEdit);
		codeAVL.remove(toEdit.getCode());
		codeAVL.put(toEdit.getCode(),toEdit);
	}
	
	public void init() {
		addTest(new Image("file:images/perfil.jpg"),"002154","cristian","cordoba","male", "2002/12/18","172","colombia");
		addTest(new Image("file:images/find.png"),"461","lina","nilson","female", "1965/02/16","165","Canada");
		addTest(new Image("file:images/perfil.jpg"),"75375","annita","ouritao","female", "2015/06/02","150","Brazil");
		addTest(new Image("file:images/perfil.jpg"),"677537","Munir","lavnrame","male", "1972/01/24","187","Afghanistan");
		
	
	}

}
