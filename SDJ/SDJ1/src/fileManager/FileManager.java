package fileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.CATEGORY;
import model.Event;
import model.EventList;
import model.Lecture;
import model.Lecturer;
import model.LecturerList;
import model.Member;
import model.MemberList;
import model.MyDate;
import model.Newsletter;

public class FileManager {

	private File eventFile, lecturerFile, memberFile;
	private ArrayList<File> newsletterFiles;

	public FileManager() {
		this.newsletterFiles = new ArrayList<File>();
	}

	public void generateEventFile(EventList eventList) throws IOException {
		eventFile = new File("events.bin");

		FileOutputStream fos = new FileOutputStream(eventFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(eventList);
		out.close();
		fos.close();
	}

	public void generateLecturerFile(LecturerList lecturerList) throws IOException {
		lecturerFile = new File("lecturers.bin");

		FileOutputStream fos = new FileOutputStream(lecturerFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(lecturerList);
		out.close();
		fos.close();
	}

	public void generateMemberFile(MemberList memberList) throws IOException {
		memberFile = new File("members.bin");

		FileOutputStream fos = new FileOutputStream(memberFile);
		ObjectOutputStream out = new ObjectOutputStream(fos);

		out.writeObject(memberList);
		out.close();
		fos.close();
	}

	public void generateNewsletterFile(String newsletterContent) throws IOException {
		newsletterFiles.add(new File("Newsletter_" + new MyDate().toString() + ".txt"));
		PrintWriter out = new PrintWriter(newsletterFiles.get(newsletterFiles.size() - 1));

		out.println(newsletterContent);

		out.flush();
		out.close();
	}

	public EventList readEventFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(eventFile);

		read = new ObjectInputStream(fis);
		EventList events = (EventList) read.readObject();

		fis.close();
		read.close();

		return events;
	}

	public LecturerList readLecturerFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(lecturerFile);

		read = new ObjectInputStream(fis);
		LecturerList lecturers = (LecturerList) read.readObject();

		fis.close();
		read.close();

		return lecturers;
	}

	public MemberList readMemberFile() throws IOException, ClassNotFoundException {
		ObjectInputStream read = null;
		FileInputStream fis = new FileInputStream(memberFile);

		read = new ObjectInputStream(fis);
		MemberList members = (MemberList) read.readObject();

		fis.close();
		read.close();

		return members;
	}

	public String readNewsletterFile(MyDate date) throws FileNotFoundException {
		File file;
		String newsletter = "";
		for (File i : newsletterFiles) {
			if (i.getName().equals("Newsletter_" + date.toString() + ".txt")) {
				file = i;
				Scanner read = new Scanner(file);
				while (read.hasNext())
					newsletter += read.nextLine() + "\n";
				read.close();
				return newsletter;
			} else {
				throw new FileNotFoundException("No newsletter on " + date.toString());
			}
		}
		return "Something went wrong...";
	}

	public MemberList readMemberFile(File file) throws FileNotFoundException {
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

	public Lecturer readLecturerFileInside(String line) {

		String name, email;
		String[] divide, categoriesDivide;
		int phone;
		boolean wantsAdvertise;
		ArrayList<CATEGORY> categories = new ArrayList<CATEGORY>();
		divide = line.split(";");
		name = divide[0].trim();
		email = divide[1].trim();
		phone = Integer.parseInt(divide[2].trim());
		categoriesDivide = divide[3].split(",");
		for (String e: categoriesDivide)
		{
		   categories.add(CATEGORY.parse(e.trim()));
		}

		wantsAdvertise = Boolean.parseBoolean(divide[4].trim());
		return (new Lecturer(name, email, phone, categories, wantsAdvertise));
	}

	public LecturerList readLecturerFile(File file) throws FileNotFoundException {
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

	public EventList readEventFile(File file) throws FileNotFoundException {
		EventList events = new EventList();
		String line, title, description, type;
		String[] divide, divideDate;
		int phone, capacity, startDay, startMonth, startYear, endDay, endMonth, endYear;
		MyDate startDate, endDate;
		double price;
		boolean finalized;

		Scanner read = new Scanner(file);
		while (read.hasNext()) {
			HashMap<String, Object> event = new HashMap<String, Object>();
			line = read.nextLine();
			divide = line.split(";");
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
			
			switch (type.toLowerCase()) {
			case "lecture":
				// call the readLecturerFieInside somehow c:
				event.put("lecturer", readLecturerFileInside(divide[8].trim()));
				events.addEvent(new Lecture(event));
				break;
			case "seminar":
			case "workshop":
				event.put("lecturer", divide[8].trim());
				events.addEvent(new Event(event));
				break;

			default:
				break;
			}
			events.addEvent(new Event(event));
		}

		read.close();
		return events;
	}

}
