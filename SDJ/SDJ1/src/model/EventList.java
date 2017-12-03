package model;

import java.util.ArrayList;
import java.util.Iterator;

public class EventList {

	private ArrayList<Event> events;
	private Iterator<Event> iter;

	public EventList() {
		this.events = new ArrayList<Event>();
		this.iter = events.iterator();
	}

	public void addEvent(Event event) {
		this.events.add(event);
	}

	public Event getEventByID(int ID) throws EventNotFoundException {
		for (Event i : this.events)
			if (ID == i.getID())
				return i;

		throw new EventNotFoundException("No such event");
	}

	public ArrayList<Event> getFinalizedEvents() {
		ArrayList<Event> finalizedEvents = new ArrayList<Event>();
		for (Event e : this.events) {
			if (e.isFinalized()) {
				finalizedEvents.add(e);
			}
		}
		return finalizedEvents;
	}

	public ArrayList<Event> getNotFinalizedEvents() {
		ArrayList<Event> notFinalizedEvents = new ArrayList<Event>();
		for (Event e : this.events) {
			if (!e.isFinalized()) {
				notFinalizedEvents.add(e);
			}
		}
		return notFinalizedEvents;
	}

	public ArrayList<Event> getAllEvents() {
		return this.events;
	}

	public EventList getFinalizedNotFinished() {
		EventList finalizedNotFinished = new EventList();
		for (Event i : this.events)
			if (i.isFinalized() && !(i.isFinished()))
				finalizedNotFinished.addEvent(i);
		;
		return finalizedNotFinished;
	}

	public String toString() {
		String print = "";
		for (Event i : this.events)
			print += i.toString() + "\n";
		return print;
	}

	public boolean hasNext() {
		return iter.hasNext();
	}

	public Event next() {
		return iter.next();
	}

}
