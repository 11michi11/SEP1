package domain.model;

import java.util.ArrayList;
import java.util.Map;

public class Lecture extends Event implements Categorized{

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
				+ lecturerName + "\nCategorie of given speech: " + category.toString() + "\nPrice: " + price
				+ "\nPrice for members: " + priceForMembers + "\nAvaliable places: " + places;
	}

	@Override
	public ArrayList<Category> getCategories() {
		ArrayList<Category> list = new ArrayList<Category>();
		list.add(category);
		return list;
	}

}
