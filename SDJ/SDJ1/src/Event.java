public abstract class Event {

	private String title;
	private MyDate startDate;
	private MyDate endDate;
	private double price;
	private boolean finalized;
	private boolean finished;
	private String description;
	private int CAPACITY;
	private static double discount;
	private ParticipantList participantList;

	public Event(String title, MyDate date, String location, double price) {

	}

	public Event(String title, MyDate date, String location) {

	}

	public abstract void modify();

	public void finalize() {
	   finalized=true;
	}
	public void finish() {
	   finished=true;
	}
	public boolean isFinalized() {
		return finalized;
	}
	public boolean isFinished() {
	   return finished;
	}
	public double getPrice() {
		return price;
	}
	public double getPriceForMembers() {
		return price-price*discount;
	}
	public int getNumberOfAvailablePlaces() {
		return CAPACITY-participantList.getSize();
	}

	public void signUpParticipant(String name, String email) {
	   participantList.addParticipant(new Participant(name, email));
	}

}
