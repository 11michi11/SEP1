package model;
import java.util.ArrayList;

public class EventList {

   private ArrayList<Event> events;
   
	public EventList() {
	   events = new ArrayList<Event>();
	}

	public void addEvent(Event event) {
	   events.add(event);
	}
	
	public Event getEventByID(int ID) throws EventNotFoundException {
      
      for(Event i: events)
         if (ID == i.getID())
            return i;
         
      throw new EventNotFoundException("No such event");
   }

	public ArrayList<Event> getFinalizedEvents() {
		ArrayList<Event> finalizedEvents = new ArrayList<Event>();
	   for (Event e: events)
		{
		   if (e.isFinalized())
		   {
		      finalizedEvents.add(e);
		   }
		}
	   return finalizedEvents;
	}

	public ArrayList<Event> getNotFinalizedEvents() {
	   ArrayList<Event> notFinalizedEvents = new ArrayList<Event>();
      for (Event e: events)
      {
         if (!e.isFinalized())
         {
            notFinalizedEvents.add(e);
         }
      }
      return notFinalizedEvents;
	}

	public ArrayList<Event> getAllEvents() {
      return this.events;
	}
	
	public EventList getFinalizedNotFinished() {
      EventList finalizedNotFinished=new EventList();
      for (Event i : events)
         if(i.isFinalized() && !(i.isFinished()))
            finalizedNotFinished.addEvent(i);;
      return finalizedNotFinished;
   }
	
	public String toString() {
      String print = "";
      for (Event i : events)
         print += i.toString() + "\n";
      return print;
   }

	public Event getNextEvent() {
		return null;
	}

	public boolean hasNext() {
		return false;
	}

}
