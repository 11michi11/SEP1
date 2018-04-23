package domain.mediator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import domain.model.Category;
import domain.model.Event;
import domain.model.EventList;
import domain.model.EventNotFoundException;
import domain.model.Lecturer;
import domain.model.LecturerList;
import domain.model.Member;
import domain.model.MemberList;
import domain.model.MyDate;
import domain.model.Participant;

public interface ModelManager {

	public String getMembersString();

	public String getLecturersString();

	public void signUpMember(String name, String address, int phone, String email, MyDate dateOfMembership);

	public void deleteMember(Member member);

	public void signUpLecturer(String name, String email, int phone, ArrayList<Category> categories,
			boolean wantsAdvertise);

	public void signUpParticipantToEvent(Participant participant, int eventId) throws EventNotFoundException;

	public void generateNewsletter(String newsletterContent) throws IOException;

	public ArrayList<Member> getAllMembers();

	public ArrayList<Lecturer> getAllLecturers();

	public ArrayList<Event> getAllEvents();

	public ArrayList<File> getAllNewsletters();

	public MemberList getMemberList();

	public LecturerList getLecturerList();

	public EventList getEventList();

	public void updateMemberList() throws ClassNotFoundException, IOException;

	public void updateLecturerList() throws ClassNotFoundException, IOException;

	public void updateEventList() throws ClassNotFoundException, IOException;

	public void updateNewsletterList() throws ClassNotFoundException, IOException;

	public void addEvent(Map<String, Object> configuration);

	public String readNewsletter(MyDate date) throws FileNotFoundException;
}
