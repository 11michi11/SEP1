package model;
import java.util.ArrayList;

import fileManager.FileManager;

public class VIAManager {
	
	private MemberList members;
	private LecturerList lecturers;
	private FileManager fileManager;
	private Newsletter newsletter;
   
	public void sendReminderEmailToMembers() {
		this.members = new MemberList();
		this.lecturers= new LecturerList();
	}

	public void signUpMember(String name, String address, int phone, String email, MyDate dateOfMembership, int paymentYear) {
	   members.addMember(new Member(name, address, phone, email, dateOfMembership, paymentYear));
	}

	public void signUpLecturer(String name, String email, int phone, ArrayList<CATEGORY> categories, boolean wantsAdvertise) {
	   lecturers.addLecturer(new Lecturer(name, email, phone, categories, wantsAdvertise));
	}

	public void signUpParticipantToEvent(Event event, Participant participant) {

	}

}
