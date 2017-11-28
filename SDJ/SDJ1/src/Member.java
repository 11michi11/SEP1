public class Member extends Participant {

	private String address;

	private int phone;

	private MyDate dateOfMembership;

	private int paymentYear;

	private int ID;

	private static int nextID;

	public Member(String name, String address, int phone, String email, MyDate dateOfMembership, int paymentYear) {

	}

	public boolean hasPaid() {
		return false;
	}

	public void pay() {

	}

	public int getID() {
		return 0;
	}

}
