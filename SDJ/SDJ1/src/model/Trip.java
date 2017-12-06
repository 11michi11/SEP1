package model;

import java.util.Map;

public class Trip extends Event {

	private String location;

	public Trip(Map<String, Object> configuration) {
		super(configuration);
		this.location = (String) configuration.getOrDefault("location", "");
	}

	public void modify(Map<String, Object> configuration) {
		super.modify(configuration);
		this.location = (String) configuration.getOrDefault("location", "");
	}

	public boolean canBeFinalized() {
		if (super.canBeFinalized() && !location.equals(""))
			return true;
		return false;
	}

	public String toString() {
		return super.getTitle() + ": " + super.getDescription() + "\nDate: " + super.getStartDate().toString() + "-"
				+ super.getEndDate().toString() + "\nLocation: " + location + "\nPrice: " + super.getPrice()
				+ "\nPrice for members: " + super.getPriceForMembers() + "\nAvaliable places: " + super.getPrice();
	}

}
