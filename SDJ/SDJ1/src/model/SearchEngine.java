package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {

	public ArrayList<Member> searchForMembers(MemberList members, String needle) {
		 ArrayList<Member> searchedMembers= new ArrayList<Member>();
		while (members.hasNext()) {
			Member member = members.next();
			if (search(member.getName(), needle) || search(Integer.toString(member.getID()), needle))
				searchedMembers.add(member);
		}
		return searchedMembers;
	}

	public ArrayList<Lecturer> searchForLecturers(LecturerList lecturers, String needle) {
		ArrayList<Lecturer> searchedLecturers = new ArrayList<Lecturer>();
		while (lecturers.hasNext()) {
			Lecturer lecturer = lecturers.next();
			if (search(lecturer.getName(), needle) || search(Integer.toString(lecturer.getID()), needle))
				searchedLecturers.add(lecturer);
		}
		return searchedLecturers;
	}

	public ArrayList<Event> searchForEvents(EventList events, String needle) {
		ArrayList<Event> searchedEvents = new ArrayList<Event>();
		while (events.hasNext()) {
			Event event = events.next();
			if (search(event.getTitle(), needle) || search(Integer.toString(event.getID()), needle)
					|| search(event.getStartDate().toString(), needle))
				searchedEvents.add(event);
		}
		return searchedEvents;
	}

	public boolean search(String stack, String needle){
	Pattern p=Pattern.compile(stack);
	Matcher m=p.matcher("");
	m.reset(needle);
	if(m.matches() || m.hitEnd())
	   return true;
	else if(stack.length()>needle.length()){
	    char[] temp=new char[stack.length()-1];
	    stack.getChars(1, stack.length(), temp, 0);
	    String newStack="";
	    for(char i:temp)
	       newStack+=i;
	    search(newStack, needle);
	    return false;
	}
	else
	   return false;}
}
