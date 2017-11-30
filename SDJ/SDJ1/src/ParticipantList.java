import java.util.ArrayList;

public class ParticipantList {
   
     private ArrayList<Participant> participants;
     
     public ParticipantList() {
	      
	   this.participants = new ArrayList<Participant>();
	}

	public ArrayList<Participant> getAllPerticipants() {
		
	   return this.participants;
		
	}

	public void addParticipant(Participant participant) {
	   
	   this.participants.add(participant);
	}

	public boolean hasNext() {
		return false;
	}

	public Participant getNext() {
		return null;
	}

}
