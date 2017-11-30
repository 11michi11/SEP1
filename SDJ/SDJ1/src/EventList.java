import java.util.ArrayList;

public class EventList {

   private ArrayList<Event> events;
   
	public EventList() {
	   events = new ArrayList<Event>();
	}

	public void addEvent(Event event) {
	   events.add(event);
	}

	public ArrayList<Event> getFinalizedEvents() {
		ArrayList<Event> finalizedEvents = new ArrayList<Event>();
	   for (Event e: events)
		{
		   if (e.isFinalize())
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
         if (!e.isFinalize())
         {
            notFinalizedEvents.add(e);
         }
      }
      return notFinalizedEvents;
	}

	public ArrayList<Event> getAllEvents() {
      return this.events;
	}

	public Event getNextEvent() {
		return null;
	}

	public boolean hasNext() {
		return false;
	}

}
