package fileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Category;
import model.EventList;
import model.Lecture;
import model.Lecturer;
import model.LecturerList;
import model.Member;
import model.MemberList;
import model.MyDate;
import model.Seminar;
import model.Trip;
import model.Workshop;

public class FileManager {

	private static File eventFile = new File ("events.bin");
	private static File lecturerFile = new File("lecturers.bin");
	private static File memberFile = new File("members.bin");

	public static void generateEventFile(EventList eventList) throws IOException {

		FileOutputStream fos = new FileOutputStream(eventFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(eventList);
		out.close();
		fos.close();
	}

	public static void generateLecturerFile(LecturerList lecturerList) throws IOException {

		FileOutputStream fos = new FileOutputStream(lecturerFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(lecturerList);
		out.close();
		fos.close();
	}

	public static void generateMemberFile(MemberList memberList) throws IOException {

		FileOutputStream fos = new FileOutputStream(memberFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(memberList);
		out.close();
		fos.close();
	}

	

	public static EventList readEventFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(eventFile);

		read = new ObjectInputStream(fis);
		EventList events = (EventList) read.readObject();

		fis.close();
		read.close();

		return events;
	}

	public static LecturerList readLecturerFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(lecturerFile);

		read = new ObjectInputStream(fis);
		LecturerList lecturers = (LecturerList) read.readObject();

		fis.close();
		read.close();

		return lecturers;
	}

	public static MemberList readMemberFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(memberFile);

		read = new ObjectInputStream(fis);
		MemberList members = (MemberList) read.readObject();

		fis.close();
		read.close();

		return members;
	}

	

	public static MemberList readMemberFile(File file) throws FileNotFoundException {
		MemberList members = new MemberList();
		String line, name, email, address;
		String[] divide, divideDate;
		int phone, day, month, year;
		MyDate dateOfMembership;

		Scanner read = new Scanner(file);
		while (read.hasNext()) {
			line = read.nextLine();
			divide = line.split(";");
			name = divide[0].trim();
			address = divide[1].trim();
			phone = Integer.parseInt(divide[2].trim());
			email = divide[3].trim();
			divideDate = divide[4].trim().split("/");
			day = Integer.parseInt(divideDate[0].trim());
			month = Integer.parseInt(divideDate[1].trim());
			year = Integer.parseInt(divideDate[2].trim());
			dateOfMembership = new MyDate(day, month, year);
			members.addMember(new Member(name, address, phone, email, dateOfMembership));
		}

		read.close();
		return members;
	}

	public static Lecturer readLecturerFileInside(String line) {

		String name, email;
		String[] divide, categoriesDivide;
		int phone;
		boolean wantsAdvertise;
		ArrayList<Category> categories = new ArrayList<Category>();
		divide = line.split(";");
		name = divide[0].trim();
		email = divide[1].trim();
		phone = Integer.parseInt(divide[2].trim());
		categoriesDivide = divide[3].split(",");
		for (String e : categoriesDivide) {
			categories.add(Category.parseCategory(e.trim()));
		}

		wantsAdvertise = Boolean.parseBoolean(divide[4].trim());
		return (new Lecturer(name, email, phone, categories, wantsAdvertise));
	}

	public static LecturerList readLecturerFile(File file) throws FileNotFoundException {
		LecturerList lecturers = new LecturerList();
		String line;
		Scanner read = new Scanner(file);
		while (read.hasNext()) {
			line = read.nextLine();
			lecturers.addLecturer(readLecturerFileInside(line));

		}

		read.close();
		return lecturers;
	}

	public static EventList readEventFile(File file) throws FileNotFoundException {
		EventList events = new EventList();
		String line, type, lecturerLine;
		String[] divide, divideDate, divideAll, divideLecturers;
		int startDay, startMonth, startYear, endDay, endMonth, endYear;

		Scanner read = new Scanner(file);
		while (read.hasNext()) {
			HashMap<String, Object> event = new HashMap<String, Object>();
			line = read.nextLine();
			divideAll = line.split("{");

			divide = divideAll[0].trim().split(";");
			type = divide[0].trim();

			event.put("title", divide[1].trim());
			divideDate = divide[2].trim().split("/");
			startDay = Integer.parseInt(divideDate[0].trim());
			startMonth = Integer.parseInt(divideDate[1].trim());
			startYear = Integer.parseInt(divideDate[2].trim());
			event.put("startDate", new MyDate(startDay, startMonth, startYear));
			divideDate = divide[3].trim().split("/");
			endDay = Integer.parseInt(divideDate[0].trim());
			endMonth = Integer.parseInt(divideDate[1].trim());
			endYear = Integer.parseInt(divideDate[2].trim());
			event.put("endDate", new MyDate(endDay, endMonth, endYear));
			event.put("price", Double.parseDouble(divide[4]));
			event.put("finalized", Boolean.parseBoolean(divide[5]));
			event.put("description", divide[6].trim());
			event.put("capacity", Integer.parseInt(divide[7].trim()));
			LecturerList lecturers = new LecturerList();

			switch (type.toLowerCase()) {
			case "lecture":
				// call the readLecturerFieInside somehow c:
				lecturerLine = divideAll[1].trim();
				lecturerLine.substring(0, lecturerLine.length() - 2);
				divideLecturers = lecturerLine.split(",");

				event.put("lecturer", readLecturerFileInside(divideLecturers[0].trim()));
				events.addEvent(new Lecture(event));
				break;
			case "seminar":
				lecturerLine = divideAll[1].trim();
				lecturerLine.substring(0, lecturerLine.length() - 2);
				divideLecturers = lecturerLine.split(",");
				for (String i : divideLecturers)
					lecturers.addLecturer(readLecturerFileInside(i.trim()));
				event.put("lecturers", lecturers);
				events.addEvent(new Seminar(event));
				break;

			case "workshop":
				lecturerLine = divideAll[1].trim();
				lecturerLine.substring(0, lecturerLine.length() - 2);
				divideLecturers = lecturerLine.split(",");
				for (String i : divideLecturers)
					lecturers.addLecturer(readLecturerFileInside(i.trim()));
				event.put("lecturers", lecturers);
				events.addEvent(new Workshop(event));
				break;

			case "trip":
				event.put("localization", divideAll[1].trim());
				events.addEvent(new Trip(event));
				break;
			default:
				System.out.println("No such type");
			}
		}

		read.close();
		return events;
	}

}
