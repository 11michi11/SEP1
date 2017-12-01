package model;
import java.util.ArrayList;
public class Newsletter {

	private ArrayList<Event> events;

	private ArrayList<Lecturer> lecturers;

	private String additionalInfo;

	private MyDate date;

	public boolean actual;

	public Newsletter(ArrayList<Event> events, ArrayList<Lecturer> lecturers) {
	      
	   
	}

	public String askForAdditionallnfo() {
		
	}

	public String generate() {
		return new MyDate()+"\nEvents: "+events.toString()+"\nLecturers: "+lecturers.toString();
	}

}
