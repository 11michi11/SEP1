package model;

import java.util.ArrayList;
import java.util.Map;

public class Seminar extends Event {

	private ArrayList<CATEGORY> categories;
	private Lecturer lecturer;

	public Seminar(Map<String, Object> configuration) {
		super(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", new ArrayList<CATEGORY>());
		this.lecturer = (Lecturer) configuration.getOrDefault("lecturer", this.lecturer);
	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", this.categories);
		this.lecturer = (Lecturer) configuration.getOrDefault("lecturer", this.lecturer);
	}

	public boolean canBeFinalized() {
		if (super.canBeFinalized() && !categories.isEmpty())
			return true;
		return false;
	}

	public String toString() {
		return super.getTitle() + ": " + super.getDescription() + "\nDate: " + super.getStartDate().toString() + "-"
				+ super.getEndDate().toString() + "\nLecturer: " + lecturer.getName() + "\nPrice: " + super.getPrice()
				+ "\nPrice for members: " + super.getPriceForMembers() + "\nAvaliable places: " + super.getPrice();

	}
}
