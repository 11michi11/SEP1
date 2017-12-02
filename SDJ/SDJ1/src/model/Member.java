package model;

public class Member extends Participant {

	private String address;
	private int phone;
	private final MyDate dateOfMembership;
	private int paymentYear;
	private final int ID;
	private static int nextID = 0;

	public Member(String name, String address, int phone, String email, MyDate dateOfMembership, int paymentYear) {
		super(name, email);
		this.address = address;
		this.phone = phone;
		this.dateOfMembership = dateOfMembership.copy();
		this.paymentYear = paymentYear;
		this.ID = ++nextID;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public boolean hasPaid() {
		return paymentYear == MyDate.getCurrentYear();
	}

	public int getPaymentYear() {
		return paymentYear;
	}
	
	public void pay() throws MemberAlreadyPaidException {
		if (!this.hasPaid())
			paymentYear = MyDate.getCurrentYear();
		else
			throw new MemberAlreadyPaidException("Member has paid for that year");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getID() {
		return ID;
	}

}
