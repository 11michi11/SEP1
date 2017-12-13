package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import fileManager.FileManager;

public class VIAManager {

    private MemberList members;
    private LecturerList lecturers;
    private EventList events;
    private ArrayList<File> newsletterFiles;

    public VIAManager() {
	this.newsletterFiles = new ArrayList<File>();
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
	// this.members = FileManager.readMemberFile();
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

}
