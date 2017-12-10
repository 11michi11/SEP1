package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Serializable {

	private String name, email;
	private int phone;
	private ArrayList<Category> categories;
	private boolean wantsAdvertise;
	private int ID;
	private static int nextID = 0;

	public Lecturer(String name, String email, int phone, ArrayList<Category> categories, boolean wantsAdvertise) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.categories = categories;
		this.wantsAdvertise = wantsAdvertise;
		this.ID = ++nextID;
	}
	
	public static Lecturer getDefaultLecturer() {
		return new Lecturer("", "", -1, new ArrayList<Category>(), false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getID() {
		return this.ID;
	}
	
	public static int getNextId() {
	    return nextID;
	}
	
	public static void setNextID (int lastID) {
	    nextID = lastID;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public boolean isWantsAdvertise() {
		return wantsAdvertise;
	}

	public void setWantsAdvertise(boolean wantsAdvertise) {
		this.wantsAdvertise = wantsAdvertise;
	}

	public String toString() {
		return "Name: " + name + "\nEmail address: " + email + "\nPhone number: " + phone
				+ "\nCategories of given speeches: " + categories.toString();
	}

}
