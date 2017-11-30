import java.util.ArrayList;

public class LecturerList {
   private ArrayList<Lecturer> lecturerList;
   
	public void LecturerList() {
	   lecturerList=new ArrayList<Lecturer>();
	}
	public void addLecturer(Lecturer lecturer) {
	   lecturerList.add(lecturer);
	}
	public ArrayList<Lecturer> getLecturersToAdvertise() {
		ArrayList<Lecturer> lecturersToAdvertise=new ArrayList<Lecturer>();
		for(Lecturer i: lecturerList)
		   if(i.isWantsAdvertise())
		      lecturersToAdvertise.add(i);
		return lecturersToAdvertise;
	}
	public ArrayList<Lecturer> getLecturersToPay() {
	   ArrayList<Lecturer> lecturersToPay=new ArrayList<Lecturer>();
      for(Lecturer i: lecturerList)
         if(!(i.isWantsAdvertise()))
            lecturersToPay.add(i);
      return lecturersToPay;
	}
	public ArrayList<Lecturer> getAllLecturersInCategory(String category) {
		ArrayList<Lecturer> AllLecturersInCategory=new ArrayList<Lecturer>();
		for(Lecturer i:lecturerList)
		   if(!(i.getCategories().findCategory(category).equals("No such category")))
		         AllLecturersInCategory.add(i);
		return AllLecturersInCategory;
	}

	public ArrayList<Lecturer> getAllLecturers() {
		return lecturerList;
	}
	public Lecturer getNextLecturer() {
		return null;
	}
	public boolean hasNext() {
		return false;
	}

}
