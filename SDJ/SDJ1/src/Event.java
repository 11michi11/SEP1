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

	public Event(String title, MyDate date, String location, double price) {

	}

	public Event(String title, MyDate date, String location) {

	}

	public abstract void modify();

	public void finalize() {

	}

	public void finish() {

	}

	public boolean isFinalize() {
		return false;
	}

	public boolean isFinished() {
		return false;
	}

	public double getPrice() {
		return 0;
	}

	public double getPriceForMembers() {
		return 0;
	}

	public int getNumberOfAvailablePlaces() {
		return 0;
	}

	public void signUpParticipant() {

	}

}
