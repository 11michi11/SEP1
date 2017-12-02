package model;

public class Newsletter {

	private EventList events;
	private LecturerList lecturers;
	private String additionalInfo;
	private MyDate date;
	public boolean actual;

	public Newsletter(EventList events, LecturerList lecturers) {
		this.events = events.getFinalizedNotFinished();
		this.lecturers = lecturers.getLecturersToAdvertise();
		date = new MyDate();
	}

	public String askForAdditionallnfo() {
		return "";
	}

	public String generate() {
		return date + "\nEvents: " + events.toString() + "\nLecturers: " + lecturers.toString() + "\n" + additionalInfo;
	}

}
