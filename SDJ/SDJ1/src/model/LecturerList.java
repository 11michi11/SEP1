package model;

import java.util.ArrayList;

public class LecturerList {
	private ArrayList<Lecturer> lecturerList;

	public void LecturerList() {
		lecturerList = new ArrayList<Lecturer>();
	}

	public void addLecturer(Lecturer lecturer) {
		lecturerList.add(lecturer);
	}

	public LecturerList getLecturersToAdvertise() {
		LecturerList lecturersToAdvertise = new LecturerList();
		for (Lecturer i : lecturerList)
			if (i.isWantsAdvertise())
				lecturersToAdvertise.addLecturer(i);
		return lecturersToAdvertise;
	}

	public ArrayList<Lecturer> getLecturersToPay() {
		ArrayList<Lecturer> lecturersToPay = new ArrayList<Lecturer>();
		for (Lecturer i : lecturerList)
			if (!(i.isWantsAdvertise()))
				lecturersToPay.add(i);
		return lecturersToPay;
	}

	public ArrayList<Lecturer> getAllLecturersInCategory(CATEGORY category) {
		ArrayList<Lecturer> AllLecturersInCategory = new ArrayList<Lecturer>();
		for (Lecturer i : lecturerList)
			for(CATEGORY j: i.getCategories())
			   if(j.equals(category))
			      AllLecturersInCategory.add(i);
		return AllLecturersInCategory;
	}

	public ArrayList<Lecturer> getAllLecturers() {
		return lecturerList;
	}


	public String toString() {
		String print = "";
		for (Lecturer i : lecturerList)
			print += i.toString() + "\n";
		return print;
	}

	public Lecturer getNextLecturer() {
		return null;
	}

	public boolean hasNext() {
		return false;
	}
	


}
