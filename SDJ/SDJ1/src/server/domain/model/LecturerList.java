package server.domain.model;

import java.io.Serializable;
import java.util.ArrayList;

public class LecturerList implements Serializable {

	private ArrayList<Lecturer> lecturerList;

	public LecturerList() {
		lecturerList = new ArrayList<Lecturer>();
	}

	public void addLecturer(Lecturer lecturer) {
		lecturerList.add(lecturer);
	}

	public LecturerList getLecturersToAdvertise() {
		LecturerList lecturersToAdvertise = new LecturerList();
		for (Lecturer i : lecturerList)
			if (i.ifWantsAdvertise())
				lecturersToAdvertise.addLecturer(i);

		return lecturersToAdvertise;
	}

	public ArrayList<Lecturer> getLecturersToPay() {
		ArrayList<Lecturer> lecturersToPay = new ArrayList<Lecturer>();
		for (Lecturer i : lecturerList)
			if (!(i.ifWantsAdvertise()))
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
}
