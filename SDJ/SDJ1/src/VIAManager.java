public class VIAManager {
   MemberList ListOfMembers=new MemberList();
   LecturerList ListOfLecturers=new LecturerList();
   
	public void sendReminderEmailToMembers() {

	}

	public void signUpMember(String name, String address, int phone, String email, MyDate dateOfMembership, int paymentYear) {
	   ListOfMembers.addMember(new Member(name, address, phone, email, dateOfMembership, paymentYear));
	}

	public void signUpLecturer(String name, String email, int phone, CATEGORY categories, boolean wantsAdvertise) {
	   ListOfLecturers.addLecturer(new Lecturer(name, email, phone, categories, wantsAdvertise));
	}

	public void signUpParticipantToEvent(Event event, Participant participant) {

	}

}
