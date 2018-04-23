package domain.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ParticipantList implements Serializable {

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

	public void removeParticipant(Participant participant) {
		this.participants.remove(participant);
	}

	public int getSize() {
		return participants.size();
	}

}
