public class Workshop extends Event {

	private CATEGORY categories;
	private Lecturer lecturer;

	public Workshop(String title, MyDate date, double price, CATEGORY categories, Lecturer lecturer)
   {
      super(title, date, price);
      this.categories = categories;
      this.lecturer = lecturer;
   }


   public void modify() {

	}
   
   public String toString()
   {
      return super.getTitle()+": "+super.getDescription()+"\nDate: "+super.getStartDate().toString()+"-"+super.getEndDate().toString()+
            "\nLecturer: "+lecturer.getName()+"\nPrice: "+super.getPrice()+
            "\nPrice for members: "+super.getPriceForMembers()+"\nAvaliable places: "+super.getPrice();
      
   }

}
