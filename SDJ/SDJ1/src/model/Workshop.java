package model;

import java.util.ArrayList;
import java.util.Map;

public class Workshop extends Event {

	private ArrayList<CATEGORY> categories;
	private Lecturer lecturer; 

	public Workshop(Map<String, Object> configuration) {
		super(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", new ArrayList<CATEGORY>());
	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.categories = (ArrayList<CATEGORY>) configuration.getOrDefault("category", this.categories);
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
