package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {

    public static ArrayList<Member> searchForMembers(ArrayList<Member> members, String needle) {
	
	ArrayList<Member> searchedMembers = new ArrayList<Member>();
	for (Member e : members) {
	    if (search(e.getName(), needle) || search(Integer.toString(e.getID()), needle))
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
	if (m.matches() || m.hitEnd())
	    return true;
	else if (stack.length() > needle.length()) {
	    char[] temp = new char[stack.length() - 1];
	    stack.getChars(1, stack.length(), temp, 0);
	    String newStack = "";
	    for (char i : temp)
		newStack += i;
	    search(newStack, needle);
	    return false;
	} else
	    return false;
    }
}
