package server.domain.mediator;

import server.domain.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observer;

public interface ModelManager {

	String getMembersString();

	String getLecturersString();

	void signUpMember(String name, String address, int phone, String email, MyDate dateOfMembership);

	void deleteMember(Member member);

	void signUpLecturer(String name, String email, int phone, ArrayList<Category> categories,
	                    boolean wantsAdvertise);

	void signUpParticipantToEvent(Participant participant, int eventId) throws EventNotFoundException;

	void generateNewsletter(String newsletterContent) throws IOException;

	ArrayList<Member> getAllMembers();

	ArrayList<Lecturer> getAllLecturers();

	ArrayList<Event> getAllEvents();

	ArrayList<File> getAllNewsletters();

	MemberList getMemberList();

	LecturerList getLecturerList();

	EventList getEventList();

	void updateMemberList() throws ClassNotFoundException, IOException;

	void updateLecturerList() throws ClassNotFoundException, IOException;

	void updateEventList() throws ClassNotFoundException, IOException;

	void updateNewsletterList() throws ClassNotFoundException, IOException;

	void addEvent(Map<String, Object> configuration);

	String readNewsletter(MyDate date) throws FileNotFoundException;

	void registerObserver(Observer observer);
}
