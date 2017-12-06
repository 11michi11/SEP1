package model;

import java.util.ArrayList;
import java.util.Map;

public class Seminar extends Event {

	private ArrayList<CATEGORY> categories;
	private ArrayList<Lecturer> lecturers;

	public Seminar(Map<String, Object> configuration) {
		super(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", new ArrayList<CATEGORY>());
		this.lecturers = (ArrayList<Lecturer>) configuration.getOrDefault("lecturers", new ArrayList<Lecturer>());
	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", this.categories);
		this.lecturers = (ArrayList<Lecturer>) configuration.getOrDefault("lecturers", this.lecturers);
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
		String lecturers = parseList();
		double price = super.getPrice();
		double priceForMembers = super.getPriceForMembers();
		int places = super.getCapacity();
		
		return title + "\n\t" + description + "\nDate: " + startDate + "-" + endDate + "\nLecturers: " + lecturers
				+ "\nPrice: " + price + "\nPrice for members: " + priceForMembers + "\nAvaliable places: " + places;
	}
	
	private String parseList() {
		StringBuilder sb = new StringBuilder("");
		for (Lecturer e : this.lecturers)
			sb.append(e.getName() + ", ");
		sb.replace(sb.length() - 2, sb.length(), "");
		return sb.toString();
	}
}
