package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {

    public static ArrayList<Member> searchForMembers(ArrayList<Member> members, String needle) {
	
	ArrayList<Member> searchedMembers = new ArrayList<Member>();
	for (Member e : members) {
	    if (search(e.getName(), needle))
		searchedMembers.add(e);
	    else if(search(Integer.toString(e.getID()), needle))
		searchedMembers.add(e);
	}

	return searchedMembers;
    }

    public static ArrayList<Lecturer> searchForLecturers(ArrayList<Lecturer> lecturers, String needle) {
	ArrayList<Lecturer> searchedLecturers = new ArrayList<Lecturer>();
	for (Lecturer e: lecturers) {
	    if (search(e.getName(), needle) || search(Integer.toString(e.getID()), needle))
		searchedLecturers.add(e);
	}
	return searchedLecturers;
    }

    public static ArrayList<Event> searchForEvents(ArrayList<Event> events, String needle) {
	ArrayList<Event> searchedEvents = new ArrayList<Event>();
	for (Event e: events) {
	    if (search(e.getTitle(), needle) || search(Integer.toString(e.getID()), needle)
		    || search(e.getStartDate().toString(), needle))
		searchedEvents.add(e);
	}
	return searchedEvents;
    }

    public static boolean search(String stack, String needle) {
	Pattern p = Pattern.compile(stack);
	Matcher m = p.matcher("");
	m.reset(needle);
	System.out.println(needle+" in "+stack);
	if (m.matches() || m.hitEnd()) {
	    System.out.println("case 1");
	    return true;
	}
	else if (stack.length() > needle.length()) {
	    String newStack = stack.substring(1,stack.length());
	    System.out.println("case 2");
	    return search(newStack, needle);
	} else {
	    System.out.println("case 3");
	    return false;
	}
    }
}
