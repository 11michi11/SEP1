public class Trip extends Event {

	private String location;

	public Trip(String title, MyDate date, String location, double price)
   {
      super(title, date, price);
      this.location = location;
   }

   public void modify() {

	}
   
   public String toString()
   {
      return super.getTitle()+": "+super.getDescription()+"\nDate: "+super.getStartDate().toString()+"-"+super.getEndDate().toString()+
            "\nLocation: "+location+"\nPrice: "+super.getPrice()+
            "\nPrice for members: "+super.getPriceForMembers()+"\nAvaliable places: "+super.getPrice();
      
   }

}
