package model;

import java.util.Map;

public class Lecture extends Event {

	private Category category;
	private Lecturer lecturer;

	public Lecture(Map<String, Object> configuration) {
		super(configuration);
		this.category = (Category) configuration.getOrDefault("category", Category.Other);
		this.lecturer = (Lecturer) configuration.getOrDefault("lecturer", Lecturer.getDefaultLecturer());

	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.category = (Category) configuration.getOrDefault("category", this.category);
		this.lecturer = (Lecturer) configuration.getOrDefault("lecturer", this.lecturer);
	}

	public boolean canBeFinalized() {
		if (super.canBeFinalized())
			return true;
		return false;
	}

	public Lecturer getLecturer() {
		return this.lecturer;
	}
	
	public Category getCategory() {
	    return this.category;
	}

	public String toString() {
		String title = super.getTitle();
		String description = super.getDescription();
		String startDate = super.getStartDate().toString();
		String endDate = super.getEndDate().toString();
		String lecturerName = lecturer.getName();
		double price = super.getPrice();
		double priceForMembers = super.getPriceForMembers();
		int places = super.getCapacity();

		return "\t" + title + "\n" + description + "\nDate: " + startDate + "-" + endDate + "\nLecturer: "
				+ lecturerName + "\nCategories of given speeches: " + category.toString().substring(1, category.toString().length()-1)+ "\nPrice: " + price + "\nPrice for members: " + priceForMembers
				+ "\nAvaliable places: " + places;
	}

}
