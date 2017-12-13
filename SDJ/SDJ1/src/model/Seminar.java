package model;

import java.util.ArrayList;
import java.util.Map;

public class Seminar extends Event {

	private ArrayList<Category> categories;
	private ArrayList<Lecturer> lecturers;

	public Seminar(Map<String, Object> configuration) {
		super(configuration);
		this.categories = (ArrayList<Category>) configuration.getOrDefault("category", new ArrayList<Category>());
		this.lecturers = (ArrayList<Lecturer>) configuration.getOrDefault("lecturers", new ArrayList<Lecturer>());
	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.categories = (ArrayList<Category>) configuration.getOrDefault("category", this.categories);
		this.lecturers = (ArrayList<Lecturer>) configuration.getOrDefault("lecturers", this.lecturers);
	}

	public ArrayList<Lecturer> getLecturers() {
		return this.lecturers;
	}

	public ArrayList<Category> getCategories() {
		return this.categories;
	}
	
	public boolean canBeFinalized() {
		if (super.canBeFinalized() && !categories.isEmpty())
			return true;
		return false;
	}

	public String toString() {
		String title = super.getTitle();
		String description = super.getDescription();
		String startDate = super.getStartDate().toString();
		String endDate = super.getEndDate().toString();
		String lecturers = parseLecturersList();
		double price = super.getPrice();
		double priceForMembers = super.getPriceForMembers();
		int places = super.getCapacity();

		return "\t" + title + "\n" + description + "\nDate: " + startDate + "-" + endDate + "\nLecturers: " + lecturers
			+"\nCategories of given speeches: " + categories.toString().substring(1, categories.toString().length()-1)
			
				+ "\nPrice: " + price + "\nPrice for members: " + priceForMembers + "\nAvaliable places: " + places;
	}

	private String parseLecturersList() {
		if (lecturers.size() > 0) {
			StringBuilder sb = new StringBuilder("");
			for (Lecturer e : lecturers)
				sb.append(e.getName() + ", ");
			sb.replace(sb.length() - 2, sb.length(), "");
			return sb.toString();
		}
		return "";
	}
}
