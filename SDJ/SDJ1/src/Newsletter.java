import java.util.ArrayList;
public class Newsletter {

	private ArrayList<Event> events;
	private ArrayList<Lecturer> lecturers;
	private String additionalInfo;
	private MyDate date;
	public boolean actual;

	public Newsletter() {
	      events=new ArrayList<Event>();	
	      lecturers=new ArrayList<Lecturer>();
	}

	public String askForAdditionallnfo() {

	}

	public String generate() {
	   
	   date=new MyDate();
		return null;
	}

}
