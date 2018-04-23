package domain.mediator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
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

public interface ModelManager extends Remote{
	
	public String getMembersString() throws RemoteException;
	public String getLecturersString() throws RemoteException;
	public void signUpMember(String name, String address, int phone, String email, MyDate dateOfMembership) throws RemoteException;
	public void deleteMember(Member member) throws RemoteException;
	public void signUpLecturer(String name, String email, int phone, ArrayList<Category> categories,boolean wantsAdvertise) throws RemoteException;
	public void signUpParticipantToEvent(Participant participant, int eventId) throws EventNotFoundException, RemoteException;
	public void generateNewsletter(String newsletterContent) throws IOException, RemoteException;
	public ArrayList<Member> getAllMembers() throws RemoteException;
	public ArrayList<Lecturer> getAllLecturers() throws RemoteException;
	public ArrayList<Event> getAllEvents() throws RemoteException;
	public ArrayList<File> getAllNewsletters() throws RemoteException;
	public MemberList getMemberList() throws RemoteException;
	public LecturerList getLecturerList() throws RemoteException;
	public EventList getEventList()  throws RemoteException;
	public void updateMemberList() throws ClassNotFoundException, IOException, RemoteException;
	public void updateLecturerList() throws ClassNotFoundException, IOException, RemoteException;
	public void updateEventList() throws ClassNotFoundException, IOException, RemoteException;
	public void updateNewsletterList() throws ClassNotFoundException, IOException, RemoteException;
	public void addEvent(Map<String, Object> configuration) throws RemoteException;
	public String readNewsletter(MyDate date) throws FileNotFoundException, RemoteException;
	
	
	

}
