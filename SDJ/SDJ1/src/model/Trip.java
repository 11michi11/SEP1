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
	this.location = (String) configuration.getOrDefault("location", this.location);
    }

    public boolean canBeFinalized() {
	if (super.canBeFinalized() && !location.equals(""))
	    return true;
	return false;
    }

    public String toString() {
	String title = super.getTitle();
	String description = super.getDescription();
	String startDate = super.getStartDate().toString();
	String endDate = super.getEndDate().toString();
	String location = this.location;
	double price = super.getPrice();
	double priceForMembers = super.getPriceForMembers();
	int places = super.getCapacity();

	return "\t" + title + "\n" + description + "\nDate: " + startDate + "-" + endDate + "\nLocation: " + location
		+ "\nPrice: " + price + "\nPrice for members: " + priceForMembers + "\nAvaliable places: " + places;
	}

}
