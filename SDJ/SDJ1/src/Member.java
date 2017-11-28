public class Member extends Participant {

	private String address;
	private int phone;
	private MyDate dateOfMembership;
	private int paymentYear;
	private int ID;
	private static int nextID;

	public Member(String name, String address, int phone, String email, MyDate dateOfMembership, int paymentYear) {
	   super(name,email);
	   this.address = address;
	   this.phone = phone;
	   this.dateOfMembership = dateOfMembership.copy(); //is the relation composition ??
	   this.paymentYear = paymentYear;
	   ID = nextID; ///??????????? is it necessary to add nextID++ ??
	}

	public int getPhone()
   {
      return phone;
   }

   public void setPhone(int phone)
   {
      this.phone = phone;
   }

   public int getPaymentYear()
   {
      return paymentYear;
   }

   public String getAddress()
   {
      return address;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public void setID(int iD)
   {
      ID = iD;
   }

   public boolean hasPaid() {
		return paymentYear==MyDate.getCurrentYear();
	}

	public void pay() {
	   paymentYear = MyDate.getCurrentYear();
	}

	public int getID() {
		return ID;
	}

}
