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
	    else if (search(Integer.toString(e.getID()), needle))
		searchedMembers.add(e);
	}

	return searchedMembers;
    }

    public static ArrayList<Lecturer> searchForLecturers(ArrayList<Lecturer> lecturers, String needle) {
	ArrayList<Lecturer> searchedLecturers = new ArrayList<Lecturer>();
	for (Lecturer e : lecturers) {
	    if (search(e.getName(), needle) || search(Integer.toString(e.getID()), needle))
		searchedLecturers.add(e);
	    else
		for (Category i : e.getCategories())
		    if (search(i.toString(), needle))
			searchedLecturers.add(e);
	}
	return searchedLecturers;
    }

    public static ArrayList<Event> searchForEvents(ArrayList<Event> events, String needle) {
	ArrayList<Event> searchedEvents = new ArrayList<Event>();
	for (Event e : events) {
	    if (search(e.getTitle(), needle) || search(Integer.toString(e.getID()), needle)
		    || search(e.getStartDate().toString(), needle))
		searchedEvents.add(e);
	    /*else if (e.getClass().getName().equals("model.Lecture")) {
		if (search(((Lecture) e).getCategory().toString(), needle)) {
		    System.out.println(((Lecture) e).getCategory().toString());
		    searchedEvents.add(e);
		}
	    } else if (e.getClass().getName().equals("model.Seminar")) {
		for (Category i : ((Seminar) e).getCategories())
		    if (search(i.toString(), needle)) {
			System.out.println(i.toString());
			searchedEvents.add(e);
		    }
	    } else if (e.getClass().getName().equals("model.Workshop")) {
		for (Category i : ((Workshop) e).getCategories())
		    if (search(i.toString(), needle)) {
			System.out.println(i.toString());
			searchedEvents.add(e);
		    }
	    }*/
	    
	    else {
		
        	    switch (e.getClass().getName()) {
                	    case "model.Lecture":
                		if (search(((Lecture) e).getCategory().toString(), needle)) {
                		    System.out.println(((Lecture) e).getCategory().toString());
                		    searchedEvents.add(e);
                		}
                		break;
                	    case "model.Seminar":
                		for (Category i : ((Seminar) e).getCategories()) {
                		    if (search(i.toString(), needle)) {
                			System.out.println(i.toString());
                			searchedEvents.add(e);
                		    }
                		}
                		break;
                	    case "model.Workshop":
                		for (Category i : ((Workshop) e).getCategories()) {
                		    if (search(i.toString(), needle)) {
                			System.out.println(i.toString());
                			searchedEvents.add(e);
                		    }
                		}
                		break;
                	    default: break;
        	    }
	    }
	}
	return searchedEvents;
    }

    public static boolean search(String stack, String needle) {
	stack = stack.toLowerCase();
	needle = needle.toLowerCase();
	Pattern p = Pattern.compile(stack);
	Matcher m = p.matcher("");
	m.reset(needle);
	if (m.matches() || m.hitEnd()) {
	    return true;
	} else if (stack.length() > needle.length()) {
	    String newStack = stack.substring(1, stack.length());
	    return search(newStack, needle);
	} else {
	    return false;
	}
    }
}
