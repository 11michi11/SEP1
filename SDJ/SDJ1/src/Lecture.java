public class Lecture extends Event {

	private CATEGORY category;
	private Lecturer lecturer;

	
	public Lecture(String title, MyDate date, double price, CATEGORY category, Lecturer lecturer)
   {
      super(title, date, price);
      this.category = category;
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
