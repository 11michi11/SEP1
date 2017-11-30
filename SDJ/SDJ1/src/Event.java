import java.util.Map;

public abstract class Event {

	private static double discount;
	private String title;
	private MyDate startDate;
	private MyDate endDate;
	private double price;
	private boolean finalized;
	private boolean finished;
	private String description;
	private int capacity;
	private ParticipantList participantList;

	public Event(Map<String, Object> configuration) {
		this.title = (String) configuration.getOrDefault("title", "");
		this.startDate = (MyDate) configuration.getOrDefault("startDay", MyDate.getDefaultDate());
		this.startDate = (MyDate) configuration.getOrDefault("endDay", MyDate.getDefaultDate());
		this.price = (double) configuration.getOrDefault("price", 0);
		this.finalized = (boolean) configuration.getOrDefault("finalized", false);
		this.description = (String) configuration.getOrDefault("description", "");
		this.capacity = (int) configuration.getOrDefault("CAPACITY", 0);
		this.participantList = new ParticipantList();
	}

	public void modify(Map<String, Object> configuration) {
		this.title = (String) configuration.getOrDefault("title", this.title);
		this.startDate = (MyDate) configuration.getOrDefault("startDay", this.startDate);
		this.endDate = (MyDate) configuration.getOrDefault("endDay", this.endDate);
		this.price = (double) configuration.getOrDefault("price", this.price);
		this.finalized = (boolean) configuration.getOrDefault("finalized", this.finalized);
		this.description = (String) configuration.getOrDefault("description", this.description);
		this.capacity = (int) configuration.getOrDefault("CAPACITY", this.capacity);
	};

	private void finalizeEvent() {
		finalized = true;
	}

	public void finish() {
		finished = true;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean canBeFinalized() {
		if (!this.title.equals("") && !this.startDate.equals(MyDate.getDefaultDate())
				&& !this.endDate.equals(MyDate.getDefaultDate()) && this.price != 0 && !this.description.equals("")
				&& capacity != 0)
			return true;
		return false;
	}

	public double getPrice() {
		return price;
	}

	public double getPriceForMembers() {
		return price - price * discount;
	}

	public int getNumberOfAvailablePlaces() {
		return capacity - participantList.getSize();
	}

	public void signUpParticipant(String name, String email) {
		participantList.addParticipant(new Participant(name, email));
	}

}
