package model;

import java.util.Map;

public class Lecture extends Event {

	private CATEGORY category;
	private Lecturer lecturer;

	public Lecture(Map<String, Object> configuration) {
		super(configuration);
		this.category = (CATEGORY) configuration.getOrDefault("category", CATEGORY.Other);
		this.lecturer = (Lecturer) configuration.getOrDefault("lecturer", Lecturer.getDefaultLecturer());
				
	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.category = (CATEGORY) configuration.getOrDefault("category", this.category);
		this.lecturer = (Lecturer) configuration.getOrDefault("lecturer", this.lecturer);
	}

	public boolean canBeFinalized() {
		if (super.canBeFinalized())
			return true;
		return false;
	}

	public String toString() {
		return super.getTitle() + ": " + super.getDescription() + "\nDate: " + super.getStartDate().toString() + "-"
				+ super.getEndDate().toString() + "\nLecturer: " + lecturer.getName() + "\nPrice: " + super.getPrice()
				+ "\nPrice for members: " + super.getPriceForMembers() + "\nAvaliable places: " + super.getPrice();

	}

}
