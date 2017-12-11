package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LecturerList implements Serializable {
	
	private ArrayList<Lecturer> lecturerList;
	private Iterator<Lecturer> iter;
	
	public LecturerList() {
		lecturerList = new ArrayList<Lecturer>();
		this.iter = lecturerList.iterator();
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

	public ArrayList<Lecturer> getAllLecturersInCategory(Category category) {
		ArrayList<Lecturer> AllLecturersInCategory = new ArrayList<Lecturer>();
		for (Lecturer i : lecturerList)
			for (Category j : i.getCategories())
				if (j.equals(category))
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
	
	public boolean hasNext() {
		return iter.hasNext();
	}

	public Lecturer next() {
		return iter.next();
	}

}
