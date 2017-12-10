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

    public String toString() {
	String title = super.getTitle();
	String description = super.getDescription();
	String startDate = super.getStartDate().toString();
	String endDate = super.getEndDate().toString();
	String lecturerName = lecturer.getName();
	double price = super.getPrice();
	double priceForMembers = super.getPriceForMembers();
	int places = super.getCapacity();

	return "\t"+title + "\n" + description + "\nDate: " + startDate + "-" + endDate + "\nLecturer: " + lecturerName
		+ "\nPrice: " + price + "\nPrice for members: " + priceForMembers + "\nAvaliable places: " + places;
	/*
	return super.getTitle() + ": " + super.getDescription() + "\nDate: " + super.getStartDate().toString() + "-"
		+ super.getEndDate().toString() + "\nLecturer: " + lecturer.getName() + "\nPrice: " + super.getPrice()
		+ "\nPrice for members: " + super.getPriceForMembers() + "\nAvaliable places: " + super.getCapacity();
	*/
    }

}
