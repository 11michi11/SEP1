package model;

import java.io.IOException;
import java.util.ArrayList;

import fileManager.FileManager;

public class VIAManager {

	private MemberList members;
	private LecturerList lecturers;
	private Newsletter newsletter;
	private EventList events;
	private FileManager fileManager;

	public void sendReminderEmailToMembers() {

	}

	public void signUpMember(String name, String address, int phone, String email, MyDate dateOfMembership,
			int paymentYear) throws IOException {
		members.addMember(new Member(name, address, phone, email, dateOfMembership));
		fileManager.generateMemberFile(members);
	}

	public void signUpLecturer(String name, String email, int phone, ArrayList<Category> categories,
			boolean wantsAdvertise) throws IOException {
		lecturers.addLecturer(new Lecturer(name, email, phone, categories, wantsAdvertise));
		fileManager.generateLecturerFile(lecturers);
	}

	public void signUpParticipantToEvent(int eventId, Participant participant) throws EventNotFoundException {
		events.getEventByID(eventId).signUpParticipant(participant);
	}

	public void generateNewsletter() throws IOException {
		fileManager.generateNewsletterFile(newsletter.generate());
	}

}
