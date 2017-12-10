package model;

import java.util.Map;

public abstract class Event {

	public static double discount = 0.2;
	private String title;
	private MyDate startDate;
	private MyDate endDate;
	private double price;
	private boolean finalized;
	private boolean finished;
	private String description;
	private int capacity;
	private int ID;
	private static int nextID = 0;
	private ParticipantList participantList;

	public Event(Map<String, Object> configuration) {
		this.title = (String) configuration.getOrDefault("title", "");
		this.startDate = (MyDate) configuration.getOrDefault("startDay", MyDate.getDefaultDate());
		this.endDate = (MyDate) configuration.getOrDefault("endDay", MyDate.getDefaultDate());
		this.price = (double) configuration.getOrDefault("price", 0);
		this.finalized = (boolean) configuration.getOrDefault("finalized", false);
		this.description = (String) configuration.getOrDefault("description", "");
		this.capacity = (int) configuration.getOrDefault("CAPACITY", 0);
		this.ID = ++nextID;
		this.participantList = new ParticipantList();
	}

	public void modify(Map<String, Object> configuration) {
		this.title = (String) configuration.getOrDefault("title", this.title);
		this.startDate = (MyDate) configuration.getOrDefault("startDay", this.startDate);
		this.endDate = (MyDate) configuration.getOrDefault("endDay", this.endDate);
		this.price = (double) configuration.getOrDefault("price", this.price);
		this.finalized = (boolean) configuration.getOrDefault("finalized", this.finalized);
		this.description = (String) configuration.getOrDefault("description", this.description);
		this.capacity = (int) configuration.getOrDefault("CAPACITY", this.capacity);
	}

	public void finalizeEvent() {
		this.finalized = true;
	}

	public void finishEvent() {
		this.finished = true;
	}

	public boolean isFinalized() {
		return this.finalized;
	}

	public boolean isFinished() {
		return this.finished;
	}

	public boolean canBeFinalized() {
		if (!this.title.equals("") && !this.startDate.equals(MyDate.getDefaultDate())
				&& !this.endDate.equals(MyDate.getDefaultDate()) && this.price != 0 && !this.description.equals("")
				&& capacity != 0)
			return true;
		return false;
	}

	public double getPrice() {
		return this.price;
	}

	public double getPriceForMembers() {
		return this.price - (this.price * this.discount);
	}

	public int getNumberOfAvailablePlaces() {
		return this.capacity - this.participantList.getSize();
	}

	public void signUpParticipant(Participant participant) {
		this.participantList.addParticipant(participant);
	}

	public String getTitle() {
		return this.title;
	}

	public MyDate getStartDate() {
		return this.startDate;
	}

	public MyDate getEndDate() {
		return this.endDate;
	}

	public String getDescription() {
		return this.description;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public int getID() {
		return this.ID;
	}

	public ParticipantList getParticipantList() {
		return this.participantList;
	}

	public abstract String toString();

}
