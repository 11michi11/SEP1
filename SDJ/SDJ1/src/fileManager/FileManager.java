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
import model.Event;
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

	private static File eventFile = new File("events.bin");
	private static File lecturerFile = new File("lecturers.bin");
	private static File memberFile = new File("members.bin");

	public static void generateEventFile(EventList eventList) throws IOException {

		FileOutputStream fos = new FileOutputStream(eventFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(eventList);
		out.writeInt(Event.getNextId());
		out.close();
		fos.close();
	}

	public static void generateLecturerFile(LecturerList lecturerList) throws IOException {

		FileOutputStream fos = new FileOutputStream(lecturerFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(lecturerList);
		out.writeInt(Lecturer.getNextId());
		out.close();
		fos.close();
	}

	public static void generateMemberFile(MemberList memberList) throws IOException {

		FileOutputStream fos = new FileOutputStream(memberFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(memberList);
		out.writeInt(Member.getNextId());
		out.close();
		fos.close();
	}

	public static EventList readEventFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(eventFile);

		read = new ObjectInputStream(fis);
		EventList events = (EventList) read.readObject();
		Event.setNextID(read.readInt());
		fis.close();
		read.close();

		return events;
	}

	public static LecturerList readLecturerFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(lecturerFile);

		read = new ObjectInputStream(fis);
		LecturerList lecturers = (LecturerList) read.readObject();
		Lecturer.setNextID(read.readInt());
		fis.close();
		read.close();

		return lecturers;
	}

	public static MemberList readMemberFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(memberFile);

		read = new ObjectInputStream(fis);
		MemberList members = (MemberList) read.readObject();
		Member.setNextID(read.readInt());
		fis.close();
		read.close();

		return members;
	}

	public static MemberList readMemberFile(File file) throws FileNotFoundException {
		MemberList members = new MemberList();
		String line, name, email, address;
		String[] divide, divideDate, divideTime;
		int phone, day, month, year, hour, minute;
		MyDate dateOfMembership;

		Scanner read = new Scanner(file);
		read.nextLine(); // skip first line
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
			divideTime = divideDate[3].split(":");
			hour = Integer.parseInt(divideTime[0]);
			minute = Integer.parseInt(divideTime[1]);
			dateOfMembership = new MyDate(day, month, year, hour, minute);
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
		name = divide[0];
		email = divide[1];
		phone = Integer.parseInt(divide[2]);
		categoriesDivide = divide[3].split(",");
		for (String e : categoriesDivide) {
			categories.add(Category.parseCategory(e));
		}

		wantsAdvertise = Boolean.parseBoolean(divide[4].trim());
		/*
		 * String check = divide[4]; System.out.println(check);
		 */
		return (new Lecturer(name, email, phone, categories, wantsAdvertise));
	}

	public static LecturerList readLecturerFile(File file) throws FileNotFoundException {
		LecturerList lecturers = new LecturerList();
		String line;
		Scanner read = new Scanner(file);
		read.nextLine(); // skip first line
		while (read.hasNext()) {
			line = read.nextLine();
			lecturers.addLecturer(readLecturerFileInside(line));

		}

		read.close();
		return lecturers;
	}

	public static EventList readEventFile(File file) throws FileNotFoundException {
		EventList events = new EventList();
		String line, type, part1, part2;
		String[] divide, divideStartDate, divideEndDate, divideStartTime, divideEndTime, divideLecturers;
		int startDay, startMonth, startYear, startHour, startMinute, endDay, endMonth, endYear, endHour, endMinute;

		Scanner read = new Scanner(file);
		read.nextLine(); // skip first line
		read.nextLine(); // skip first line
		read.nextLine(); // skip first line
		read.nextLine(); // skip first line
		while (read.hasNext()) {
			HashMap<String, Object> event = new HashMap<String, Object>();
			line = read.nextLine();
			part1 = line.substring(0, line.indexOf("{"));
			part2 = line.substring(line.indexOf("{") + 1, line.length() - 1);
			// divideAll = line.split("{");

			// divideAll[1].substring(0, divideAll[1].length()-2);
			divide = part1.split(";");
			type = divide[0];

			event.put("title", divide[1]);
			divideStartDate = divide[2].split("/");
			startDay = Integer.parseInt(divideStartDate[0]);
			startMonth = Integer.parseInt(divideStartDate[1]);
			startYear = Integer.parseInt(divideStartDate[2]);
			divideStartTime = divideStartDate[3].split(":");
			startHour = Integer.parseInt(divideStartTime[0]);
			startMinute = Integer.parseInt(divideStartTime[1]);
			event.put("startDate", new MyDate(startDay, startMonth, startYear, startHour, startMinute));
			divideEndDate = divide[3].split("/");
			endDay = Integer.parseInt(divideEndDate[0]);
			endMonth = Integer.parseInt(divideEndDate[1]);
			endYear = Integer.parseInt(divideEndDate[2]);
			divideEndTime = divideEndDate[3].split(":");
			endHour = Integer.parseInt(divideEndTime[0]);
			endMinute = Integer.parseInt(divideEndTime[1]);
			event.put("endDate", new MyDate(endDay, endMonth, endYear, endHour, endMinute));
			event.put("price", Double.parseDouble(divide[4]));
			event.put("finalized", Boolean.parseBoolean(divide[5]));
			event.put("description", divide[6]);
			event.put("capacity", Integer.parseInt(divide[7]));
			ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();

			switch (type.toLowerCase()) {
			case "lecture":
				// lecturerLine = divideAll[1].trim();
				// lecturerLine.substring(0, lecturerLine.length() - 2);
				// divideLecturers = part2.split(",");

				// event.put("lecturer", readLecturerFileInside(part2.substring(1,
				// part2.length()-1)));

				// readLecturerFileInside(part2.substring(1, part2.length() - 1));
				event.put("lecturer", readLecturerFileInside(part2.substring(1, part2.length() - 1)));
				events.addEvent(new Lecture(event));
				break;
			case "seminar":
				// lecturerLine = divideAll[1].trim();
				// lecturerLine.substring(0, lecturerLine.length() - 2);
				divideLecturers = part2.split(",");
				for (String i : divideLecturers) {
					readLecturerFileInside(i.substring(1, i.length() - 1));
					lecturers.add(readLecturerFileInside(i.substring(1, i.length() - 1)));
				}
				event.put("lecturers", lecturers);
				events.addEvent(new Seminar(event));
				break;

			case "workshop":
				// lecturerLine = divideAll[1].trim();
				// lecturerLine.substring(0, lecturerLine.length() - 2);
				divideLecturers = part2.split(",");
				for (String i : divideLecturers) {
					readLecturerFileInside(i.substring(1, i.length() - 1));
					lecturers.add(readLecturerFileInside(i.substring(1, i.length() - 1)));
				}
				event.put("lecturers", lecturers);
				events.addEvent(new Workshop(event));
				break;

			case "trip":
				event.put("location", part2);
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
