package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {
    
    public MemberList searchForMembers(MemberList members, String needle){
	MemberList searchedMembers=new MemberList();
	while(members.hasNext()) {
	    Member member=members.getNext();
	    if(search(member.getName(), needle) || search(Integer.toString(member.getID()), needle))
		searchedMembers.addMember(member);
	}
	return searchedMembers;	    
    }
    public LecturerList searchForLecturers(LecturerList lecturers, String needle){
	LecturerList searchedLecturers=new LecturerList();
	while(lecturers.hasNext()) {
	    Lecturer lecturer=lecturers.getNext();
	    if(search(lecturer.getName(), needle) || search(Integer.toString(lecturer.getID()), needle))
		searchedLecturers.addLecturer(lecturer);
	}
	return searchedLecturers;	    
    }
    public EventList searchForEvents(EventList events, String needle){
	EventList searchedEvents=new EventList();
	while(events.hasNext()) {
	    Event event=events.getNext();
	    if(search(event.getTitle(), needle) || search(Integer.toString(event.getID()), needle) || search(event.getStartDate().toString(), needle))
		searchedEvents.addEvent(event);
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
	   return false;
    }
}
