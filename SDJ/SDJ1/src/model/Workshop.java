package model;

import java.util.ArrayList;
import java.util.Map;

public class Workshop extends Event {

	private ArrayList<CATEGORY> categories;
	private ArrayList<Lecturer> lecturers;

	public Workshop(Map<String, Object> configuration) {
		super(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", new ArrayList<CATEGORY>());
		this.lecturers = (ArrayList<Lecturer>) configuration.getOrDefault("lecturer", new ArrayList<Lecturer>());
	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", this.categories);
		this.lecturers = (ArrayList<Lecturer>) configuration.getOrDefault("lecturer", this.lecturers);
	}

	public boolean canBeFinalized() {
		if (super.canBeFinalized() && !categories.isEmpty())
			return true;
		return false;
	}

	public String toString() {
		return super.getTitle() + ": " + super.getDescription() + "\nDate: " + super.getStartDate().toString() + "-"
				+ super.getEndDate().toString() + "\nLecturer: " + lecturers.getName() + "\nPrice: " + super.getPrice()
				+ "\nPrice for members: " + super.getPriceForMembers() + "\nAvaliable places: " + super.getPrice();
	}

}
