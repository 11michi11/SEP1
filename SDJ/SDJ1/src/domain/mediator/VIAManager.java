package domain.mediator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observer;

import domain.model.Category;
import domain.model.Event;
import domain.model.EventList;
import domain.model.EventNotFoundException;
import domain.model.Lecture;
import domain.model.Lecturer;
import domain.model.LecturerList;
import domain.model.Member;
import domain.model.MemberList;
import domain.model.MyDate;
import domain.model.Participant;
import domain.model.Seminar;
import domain.model.Trip;
import domain.model.Workshop;

public class VIAManager implements ModelManager{

	private MemberList members;
	private LecturerList lecturers;
	private EventList events;
	private ArrayList<File> newsletterFiles;

	public VIAManager() {
		this.newsletterFiles = new ArrayList<>();
		this.lecturers = new LecturerList();
		this.members = new MemberList();
		this.events = new EventList();
	}

	public String getMembersString() {
		return members.toString();
	}

	public String getLecturersString() {
		return lecturers.toString();
	}

	public void signUpMember(String name, String address, int phone, String email, MyDate dateOfMembership) {
		members.addMember(new Member(name, address, phone, email, dateOfMembership));
	}

	public void deleteMember(Member member) {
		members.deleteMember(member);
	}

	public void signUpLecturer(String name, String email, int phone, ArrayList<Category> categories,
			boolean wantsAdvertise) {
		lecturers.addLecturer(new Lecturer(name, email, phone, categories, wantsAdvertise));
	}

	public void signUpParticipantToEvent(Participant participant, int eventId) throws EventNotFoundException {
		events.getEventByID(eventId).signUpParticipant(participant);
	}

	public void generateNewsletter(String newsletterContent) throws IOException {
		FileManager.generateNewsletter(newsletterContent, newsletterFiles, events, lecturers);
	}

	public ArrayList<Member> getAllMembers() {
		return this.members.getAllMembers();
	}

	public ArrayList<Lecturer> getAllLecturers() {
		return this.lecturers.getAllLecturers();
	}

	public ArrayList<Event> getAllEvents() {
		return this.events.getAllEvents();
	}

	public ArrayList<File> getAllNewsletters() {
		return this.newsletterFiles;
	}

	public MemberList getMemberList() {
		return members;
	}

	public LecturerList getLecturerList() {
		return lecturers;
	}

	public EventList getEventList() {
		return events;
	}

	public void updateMemberList() throws ClassNotFoundException, IOException {
//		 this.members = FileManager.readMemberFile();
		this.members = FileManager.readMemberFile(new File("src/resources/members.txt"));
	}

	public void updateLecturerList() throws ClassNotFoundException, IOException {
		// this.lecturers = FileManager.readLecturerFile();
		this.lecturers = FileManager.readLecturerFile(new File("src/resources/lecturer.txt"));
	}

	public void updateEventList() throws ClassNotFoundException, IOException {
		// this.events = FileManager.readEventFile();
		this.events = FileManager.readEventFile(new File("src/resources/events.txt"));
	}

	public void updateNewsletterList() throws ClassNotFoundException, IOException {
		this.newsletterFiles = FileManager.getAllNewsletters();
	}

	public void addEvent(Map<String, Object> configuration) {
		Event event;
		switch ((String) configuration.get("type")) {
		case "Lecture":
			event = new Lecture(configuration);
			events.addEvent(event);
			break;
		case "Workshop":
			event = new Workshop(configuration);
			events.addEvent(event);
			break;
		case "Seminar":
			event = new Seminar(configuration);
			events.addEvent(event);
			break;
		case "Trip":
			event = new Trip(configuration);
			events.addEvent(event);
			break;
		}
	}

	public String readNewsletter(MyDate date) throws FileNotFoundException {
		return FileManager.readNewsletter(date, newsletterFiles);
	}

	public void registerObserver(Observer observer){
		members.addObserver(observer);
	}

}
