package domain.model;


import java.io.Serializable;

public class Member extends Participant implements Serializable {

	private static int nextID = 0;
	private final MyDate dateOfMembership;
	private final int ID;
	private int phone;
	private int paymentYear;
	private String address;

	public Member(String name, String address, int phone, String email, MyDate dateOfMembership) {
		super(name, email);
		this.address = address;
		this.phone = phone;
		this.dateOfMembership = dateOfMembership.copy();
		this.ID = ++nextID;
	}
	
	public static int getNextId() {
	    return nextID;
	}
	
	public static void setNextID (int lastID) {
	    nextID = lastID;
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
	
	public void unPay() {
		this.paymentYear = MyDate.getCurrentYear() - 1;
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
	
	public String toString () {
	   return ID+";"+super.toString()+";"+address+";"+phone+";"+dateOfMembership+";"+paymentYear;
	}

}
