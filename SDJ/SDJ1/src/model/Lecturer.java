package model;

import java.util.ArrayList;

public class Lecturer {

	private String name, email;
	private int phone;
	private ArrayList<CATEGORY> categories;
	private boolean wantsAdvertise;

	public Lecturer(String name, String email, int phone, ArrayList<CATEGORY> categories, boolean wantsAdvertise) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.categories = categories;
		this.wantsAdvertise = wantsAdvertise;
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

	public ArrayList<CATEGORY> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<CATEGORY> categories) {
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
